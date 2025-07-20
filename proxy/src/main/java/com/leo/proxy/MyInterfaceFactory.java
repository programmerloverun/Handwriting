package com.leo.proxy;

import com.leo.proxy.utils.Compiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

public class MyInterfaceFactory {

    private static final AtomicInteger count = new AtomicInteger();

    public static File createjavaFile(String className) throws IOException {
        String func1Body = functionBody("func1");
        String func2Body = functionBody("func2");
        String func3Body = functionBody("func3");
        String content = "package com.leo.proxy;\n" +
                "\n" +
                "public class " + className + " implements MyInterface{\n" +
                "    @Override\n" +
                "    public void func1() {\n" +
                "        " + func1Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "        " + func2Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "        " + func3Body + "\n" +
                "    }\n" +
                "}\n";
        File javaFile = new File("proxy/" + className + ".java");
        // E:\projectSpace\Handwriting\proxy 写在子模块proxy下面
        Files.write(javaFile.toPath(), content.getBytes());
        return javaFile;
    }

    private static String getClassName() {
        return "MyInterface$proxy" + count.incrementAndGet();
    }

    private static String functionBody(String methodName) {
        return "System.out.println(\"" + methodName + "\");";
    }

    /**
     * 实例化对象
     *
     * @param className
     * @return
     * @throws Exception
     */
    private static MyInterface newInstance(String className) throws Exception {
        // 加载这个对象到虚拟机
        Class<?> aClass = MyInterfaceFactory.class.getClassLoader().loadClass(className);
        Constructor<?> constructor = aClass.getConstructor();
        MyInterface myInterface = (MyInterface) constructor.newInstance();
        return myInterface;
    }

    public static MyInterface createProxyObject() throws Exception {
        String className = getClassName();
        // 创建java文件
        File javaFile = createjavaFile(className);
        // 编译
        Compiler.compile(javaFile);
        // 实例化
        MyInterface proxy = newInstance("com.leo.proxy." + className);
        return proxy;
    }

}
