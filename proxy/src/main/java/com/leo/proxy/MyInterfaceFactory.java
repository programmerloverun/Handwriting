package com.leo.proxy;

import com.leo.proxy.utils.Compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyInterfaceFactory {

    public static File createFile() throws IOException {
        String content = "package com.leo.proxy;\n" +
                "\n" +
                "public class NameImpl implements MyInterface{\n" +
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
        File javaFile = new File("NameImpl.java");
        Files.write(javaFile.toPath(), content.getBytes());
        return javaFile;
    }

    public static void main(String[] args) throws IOException {
        File javaFile = createFile();
        Compiler.compile(javaFile);
    }
}
