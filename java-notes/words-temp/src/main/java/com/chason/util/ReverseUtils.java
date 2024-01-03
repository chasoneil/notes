package com.chason.util;

import com.chason.service.FiftyService;
import com.chason.service.WordsService;

import java.io.*;
import java.net.URL;

public class ReverseUtils {

    public static void main(String[] args) {

        String fileName = "words/words_03";

        URL url = ReverseUtils.class.getClassLoader().getResource(fileName);

        String file = url.getPath();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (CheckUtil.checkLine(line)) {

                    String[] msgs = line.split("#");
                    String newLine = "";
                    newLine = msgs[2] + "#" + msgs[1] + "#" + msgs[0];

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
