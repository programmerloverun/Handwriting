package com.leo.proxy.utils;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Compiler {

    /**
     * 编译指定的Java文件。
     * @param javaFile 要编译的Java文件。
     */
    public static void compile(File javaFile) {
        // 获取系统Java编译器实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 获取标准文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        try {
            // 从给定的Java文件创建可迭代的Java文件对象集合
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(javaFile));

            // 设置编译选项，例如指定输出目录
            List<String> options = Arrays.asList("-d", "./proxy/target/classes");

            // 创建编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null,
                    fileManager,
                    null,
                    options,
                    null,
                    compilationUnits);

            // 执行编译
            boolean success = task.call();
            if (success) {
                System.out.println("Compilation succeeded.");
            } else {
                System.out.println("Compilation failed.");
            }
        } finally {
            // 关闭文件管理器以释放资源
            try {
                fileManager.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}