package com.chason.test;

import java.util.ArrayList;
import java.util.List;

public class MethodAreaTest {

    private static List<String> test = new ArrayList<>();


    public static void main(String[] args) {

        List<String> test = new ArrayList<>();

        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");

        System.out.println(test);
        doRemove(test);
        System.out.println(test);

    }

    private static void doRemove(List<String> test) {

        for (int i=0; i<test.size(); i++) {
            if (test.get(i).equals("a")) {
                test.remove(i);
            } else if (test.get(i).equals("c")) {
                test.remove(i);
            }
        }
    }

    public static void doInit() {
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        test.add("e");
    }

    public static void doTest() {
        System.out.println("test");
        System.out.println(test);
    }

}
