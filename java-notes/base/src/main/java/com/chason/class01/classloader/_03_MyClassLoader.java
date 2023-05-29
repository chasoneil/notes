package com.chason.class01.classloader;

import java.io.*;

/**
 * 自定义 classLoader
 */
public class _03_MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File("D:/temp", name.replace(".", "/").concat(".class"));

        try {

            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int b = 0;

            while ((b = fis.read()) != 0) {
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();

            fis.close();
            baos.close();

            return defineClass(name, bytes, 0, bytes.length);       // 将二进制变成class

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return super.findClass(name);
    }
}
