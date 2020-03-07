package com.shentu.nio;

public class Test {
    public static void main(String[] args) {
        System.out.println(new A().method1());
    }
}

class A {
    int i = 1;

    public int method1() {
        final String s = getString();
        i++;
        return i++;
    }

    public String getString() {
        boolean flag = true;
        while(flag) {

        }
        return "a";
    }
}