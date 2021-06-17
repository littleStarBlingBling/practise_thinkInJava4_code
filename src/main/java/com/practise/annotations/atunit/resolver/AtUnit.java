package com.practise.annotations.atunit.resolver;

import com.practise.annotations.atunit.test.Test;
import com.practise.annotations.atunit.test.TestObjectCleanup;
import com.practise.annotations.atunit.test.TestObjectCreate;
import com.practise.io.ProcessFiles;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AtUnit implements ProcessFiles.Strategy {

    private static Class<?> testClass;
    // 收集失败的用例
    private static List<String> failedTests = new ArrayList<>();
    private static long testsRun = 0;
    private static long failures = 0;

    public static void main(String[] args) {
        // 开启断言
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        // 执行测试
        new ProcessFiles(new AtUnit(), "class").start(args);

        if (failures == 0) {
            System.out.println("OK(" + testsRun + "tests)");
        } else {
            System.out.println("(" + testsRun + "tests)");
            System.out.println("\n>>>" + failures + " FAILURE" + (failures > 1 ? "S" : "") + "<<<");
            for (String failed : failedTests) {
                System.out.println(" " + failed);
            }
        }
    }

    @Override
    public void process(File file) {
        try {
            String cName = ClassNameFinder.thisClass(Files.readAllBytes(file.toPath()));

            if (!cName.startsWith("public:")) {
                return;
            }
            // 去掉访问权限修饰符
            cName = cName.split(":")[1];
            // 忽略不在包下的类
            if (!cName.contains(".")) {
                return;
            }

            testClass = Class.forName(cName);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        TestMethods testMethods = new TestMethods();
        Method creator = null;
        Method cleanup = null;
        // 遍历类中的方法，收集有相应注解的方法
        for (Method method : testClass.getDeclaredMethods()) {
            testMethods.addIfTestMethod(method);
            // 初始化与清理方法只有一个
            if (creator == null) {
                creator = checkForCreatorMethod(method);
            }

            if (cleanup == null) {
                cleanup = checkForCleanupMethod(method);
            }
        }

        // 校验测试类是否有空的构造器
        if (testMethods.size() > 0) {
            if (creator == null) {
                try {
                    // 需要有 public 的空构造器
                    if (!Modifier.isPublic(testClass.getDeclaredConstructor(null).getModifiers())) {
                        System.out.println("Error: " + testClass + "no-arg constructor must be public");
                        System.exit(1);
                    }
                } catch (NoSuchMethodException e) {

                }
            }

            System.out.println(testClass.getName());
        }

        // 遍历测试方法，获得返回值
        for (Method method : testMethods) {
            System.out.print(" . " + method.getName() + " ");
            try {
                boolean success = false;
                // 创建对应的测试类，用完就丢弃，避免对其他的测试产生副作用
                Object testObject = createTestObject(creator);
                try {
                    if (method.getReturnType().equals(boolean.class)) {
                        // 方法调用需要传入所在类的实例
                        success = (Boolean) method.invoke(testObject);
                    } else {
                        method.invoke(testObject);
                        success = true;
                    }
                } catch (InvocationTargetException e) {
                    System.out.println(e.getCause());
                }

                System.out.println(success ? "" : "(failed)");
                testsRun++;
                if (!success) {
                    failures++;
                    failedTests.add(testClass.getName() + ": " + method.getName());
                }

                // 执行清理操作
                if (cleanup != null) {
                    cleanup.invoke(testObject, testObject);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 收集有 Test 注解并且返回值为 boolean 和 void 的方法
    public static class TestMethods extends ArrayList<Method> {

        void addIfTestMethod(Method method) {
            if (method.getAnnotation(Test.class) == null) {
                return;
            }
            if (!(method.getReturnType().equals(boolean.class) | method.getReturnType().equals(void.class))) {
                throw new RuntimeException("@Test method must return boolean or void");
            }

            // 将私有的方法设置为public
            method.setAccessible(true);
            add(method);

        }
    }

    // 检查 TestObjectCreate 注解的方法，包括返回类型、描述符
    private static Method checkForCreatorMethod(Method method) {
        if (method.getAnnotation(TestObjectCreate.class) == null) {
            return null;
        }
        if (!method.getReturnType().equals(testClass)) {
            throw new RuntimeException("@TestObjectCreate must return instance of Class to be tested");
        }

        if ((method.getModifiers() & Modifier.STATIC) < 1) {
            throw new RuntimeException("@TestObjectCreate must be static.");
        }

        method.setAccessible(true);
        return method;
    }

    // 检查 TestObjectCleanup 注解的方法，包括返回类型、描述符、参数类型
    private static Method checkForCleanupMethod(Method method) {
        if (method.getAnnotation(TestObjectCleanup.class) == null) {
            return null;
        }
        if (!method.getReturnType().equals(void.class)) {
            throw new RuntimeException("@TestObjectCleanup must return void");
        }

        if ((method.getModifiers() & Modifier.STATIC) < 1) {
            throw new RuntimeException("@TestObjectCleanup must be static.");
        }
        if (method.getParameterTypes().length == 0 || method.getParameterTypes()[0] != testClass) {
            throw new RuntimeException("@TestObjectCleanup must take an argument of the tested type.");
        }

        method.setAccessible(true);
        return method;
    }

    // 获得测试类的实例
    private static Object createTestObject(Method creator) {
        // 如果初始化方法不为空，就使用初始化方法的 invoke 传入所在的类
        if (creator != null) {
            try {
                return creator.invoke(testClass);
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Couldn't run TestObject (creator) method.");
            }
            // 使用无参构造器
        } else {
            try {
                return testClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Couldn't create a test object. Try using a @TestObject method.");
            }
        }

    }
}
