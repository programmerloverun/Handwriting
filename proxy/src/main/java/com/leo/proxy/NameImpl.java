package com.leo.proxy;

public class NameImpl implements MyInterface{
    @Override
    public void func1() {
        System.out.println("func1");
    }

    @Override
    public void func2() {
        System.out.println("func2");
    }

    @Override
    public void func3() {
        System.out.println("func3");
    }
}
