package com.leo.proxy;

import com.leo.proxy.utils.Compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class MyInterfaceFactory {

    private static final AtomicInteger count = new AtomicInteger();

    public static File createFile() throws IOException {
        String className = getClassName();
        String content = "package com.leo.proxy;\n" +
                "\n" +
                "public class " + className + " implements MyInterface{\n" +
                "    @Override\n" +
                "    public void func1() {\n" +
                "        System.out.println(\"func1\");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "        System.out.println(\"func2\");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "        System.out.println(\"func3\");\n" +
                "    }\n" +
                "}\n";
        File javaFile = new File("proxy/"+className + ".java");
        // E:\projectSpace\Handwriting\proxy 写在子模块proxy下面
        Files.write(javaFile.toPath(), content.getBytes());
        return javaFile;
    }

    public static String getClassName() {
        return "MyInterface$proxy" + count.incrementAndGet();
    }

    public static void main(String[] args) throws IOException {
        File javaFile = createFile();
        Compiler.compile(javaFile);
    }
}
