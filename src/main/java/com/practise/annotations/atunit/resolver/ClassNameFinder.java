package com.practise.annotations.atunit.resolver;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ClassNameFinder {

    public static String thisClass(byte[] classBytes) {
        // <常量池中位置序号，偏移量>
        Map<Integer, Integer> offsetTable = new HashMap<>();
        // <常量池中位置序号, 类名>
        Map<Integer, String> classNameTable = new HashMap<>();

        try {
            DataInputStream data = new DataInputStream(new ByteArrayInputStream(classBytes));
            // 字节码开头的魔术值：0xcafebabe
            int magic = data.readInt();
            // 次版本号
            int minorVersion = data.readShort();
            // 主版本号
            int majorVersion = data.readShort();
            // 常量池大小
            int constantPoolCount = data.readShort();

            int[] constantPool = new int[constantPoolCount];
            for (int i = 1; i < constantPoolCount; i++) {
                int tag = data.read();
                switch (tag) {
                    case 1: // UTF
                        // 获取到className
                        int length = data.readShort();
                        char[] bytes = new char[length];
                        for (int k = 0; k < length; k++) {
                            bytes[k] = (char) data.read();
                        }

                        String className = new String(bytes);
                        classNameTable.put(i, className);
                        break;
                    case 5: // LONG
                    case 6: // DOUBLE
                        // 丢掉8个字节，进行跳过
                        data.readLong();
                        i++;
                        break;
                    case 7: // CLASS
                        // 找到类的坐标
                        int offset = data.readShort();
                        offsetTable.put(i, offset);
                        break;
                    case 8: // STRING
                        // 丢掉两个字节
                        data.readShort();
                        break;
                    case 3: // INTEGER
                    case 4: // FLOAT
                    case 9: // FIELD_REF
                    case 10: // METHOD_REF
                    case 11: // INTERFACE_METHOD_REF
                    case 12: // NAME_AND_TYPE
                    case 18: // Invoke Dynamic
                        // 丢掉四个字节
                        data.readInt();
                        break;
                    case 15: // Method Handle
                        data.readByte();
                        data.readShort();
                        break;
                    case 16: // Method Type
                        data.readShort();
                        break;
                    default:
                        throw new RuntimeException("Bad tag " + tag);

                }
            }

            short accessFlags = data.readShort();
            String access = (accessFlags & 0x0001) == 0 ? "nonpublic:" : "public:";
            int thisClass = data.readShort();
            int superClass = data.readShort();
            // 拼接访问权限，并把类名中的 / 转换为 .
            return access + classNameTable.get(offsetTable.get(thisClass)).replace('/', '.');
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    // 输出路径下的public 非 enum 的全限定类名
    public static void main(String[] args) throws Exception {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.class");
        Files.walk(Paths.get("."))
                .filter(matcher::matches)
                .map(p -> {
                    try {
                        return thisClass(Files.readAllBytes(p));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).filter(s -> s.startsWith("public:"))
                .map(s -> s.split(":")[1])
                .filter(s -> !s.startsWith("enums."))
                .filter(s -> s.contains("."))
                .forEach(System.out::println);
    }
}
