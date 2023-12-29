package com.chason.service;

import com.chason.Application;
import com.chason.entity.JpWords;
import com.chason.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordsService {

    public static List<JpWords> initWordsData (String index) {

        List<JpWords> words = new ArrayList<>();

        String fileName = "words_" + index;
        URL url = Application.class.getClassLoader().getResource(fileName);
        String file = url.getPath();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (StringUtil.isEmpty(line)) {
                    continue;
                }
                JpWords jpWords = new JpWords();

                String[] msg = line.split("#");
                if (msg.length != 3) {
                    continue;
                }

                jpWords.setJpRead(msg[0]);
                jpWords.setJpWrite(msg[1]);

                String means = msg[2];
                if (StringUtil.isEmpty(means)) {
                    continue;
                }

                String[] readStr = means.split(":");
                Set<String> tar = new HashSet<>();
                for (int i=0; i<readStr.length; i++) {
                    tar.add(readStr[i]);
                }

                jpWords.setChMeans(tar);
                words.add(jpWords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }

    public static String doTest (String index) {
        return null;
    }

}
