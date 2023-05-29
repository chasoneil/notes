package com.chason.class01.classloader;

/**
 * 怎么去加载一个 class
 */
public class _02_CustomClassLoader {

    public static void main(String[] args) {

        try {
            Class clazz = _02_CustomClassLoader.class.getClassLoader().loadClass("com.chason.class01.classloader._01_Class");
            System.out.println(clazz.getName());

        } catch (ClassNotFoundException e) {

        }

    }

}
