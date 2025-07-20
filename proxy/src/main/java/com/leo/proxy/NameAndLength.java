package com.leo.proxy;

public class NameAndLength implements MyInterface{
    @Override
    public void func1() {
        String methodName = "func1";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }

    @Override
    public void func2() {
        String methodName = "func2";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }

    @Override
    public void func3() {
        String methodName = "func3";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }
}
