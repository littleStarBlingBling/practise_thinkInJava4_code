package com.practise.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFile {

    public static byte[] read(File byteFile) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(byteFile));
        try {
            byte[] data = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(data);
            return data;
        } finally {
            bufferedInputStream.close();
        }
    }

    public static byte[] read(String byteFile) throws IOException {
        return read(new File(byteFile).getAbsoluteFile());
    }
}
