package com.chason.util;

import java.io.*;
import java.net.URL;

public class ReverseUtils {

    public static void main(String[] args) {

        String fileName = "japan/words/words_03";

        URL url = ReverseUtils.class.getClassLoader().getResource(fileName);

        String file = url.getPath();

        String newFileName = file + "_temp";

        System.out.println(newFileName);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (CheckUtil.checkLine(line)) {

                    String[] msgs = line.split("#");
                    String newLine = "";
                    newLine = msgs[2] + "#" + msgs[1] + "#" + msgs[0];

                    bufferedWriter.write(newLine + "\n");
                }
            }

            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }



}
