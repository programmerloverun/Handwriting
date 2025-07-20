package com.leo.proxy;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        MyInterface proxyObject = MyInterfaceFactory.createProxyObject(new MyHandler() {
            @Override
            public String functionBody(String methodName) {
                return "System.out.println(\"" + methodName + "\");";
            }
        });
        System.out.println(proxyObject);
        proxyObject.func1();
        proxyObject.func2();
        proxyObject.func3();

    }
}
