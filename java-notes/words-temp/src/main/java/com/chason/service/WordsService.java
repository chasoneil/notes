package com.chason.service;

import com.chason.Application;
import com.chason.entity.JpFifty;
import com.chason.entity.JpWords;
import com.chason.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class WordsService {

    public static List<JpWords> initWordsData (String index) {

        List<JpWords> words = new ArrayList<>();
        String fileName = "words_" + index;
        String file = "";
        URL url = null;
        try {
            url = Application.class.getClassLoader().getResource(fileName);
            file = url.getPath();
        } catch (Exception e) {
            System.err.println("文件不存在！");
            System.exit(-1);
        }

        if (StringUtil.isEmpty(file)) {
            System.err.println("文件不存在！");
            System.exit(-1);
        }

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

    public static String doTest1 (List<JpWords> words) {

        int size = words.size();
        int count = size;

        Random random = new Random();
        String res = "";
        double correct = 0L;
        System.out.println("---> 请根据日语单词翻译中文含义 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            JpWords jpWords = words.get(index);
            System.out.println(jpWords.getJpWrite() + " -> ");
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();

            Set<String> chMeans = jpWords.getChMeans();
            if (chMeans.contains(res)) {
                System.out.println("正确");
                correct++;
            } else {
                boolean flag = false;
                for (String mean : chMeans) {
                    if (mean.contains(res)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.out.println("错误");
                }
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }

    public static String doTest2 (List<JpWords> words) {

        int size = words.size();
        int count = size;

        Random random = new Random();
        String res = "";
        double correct = 0L;
        System.out.println("---> 请根据日语发音翻译中文含义 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            JpWords jpWords = words.get(index);
            System.out.println(jpWords.getJpRead() + " -> ");
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();

            Set<String> chMeans = jpWords.getChMeans();
            if (chMeans.contains(res)) {
                System.out.println("正确");
                correct++;
            } else {
                boolean flag = false;
                for (String mean : chMeans) {
                    if (mean.contains(res)) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.out.println("错误");
                }
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }

    public static String doTest3 (List<JpWords> words) {

        int size = words.size();
        int count = size;

        Random random = new Random();
        String res = "";
        double correct = 0L;
        System.out.println("---> 请根据中文含义写出日语单词 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            JpWords jpWords = words.get(index);
            Set<String> chMeans = jpWords.getChMeans();
            String ch = "";
            for (String str : chMeans) {
                ch += str + "; ";
            }
            System.out.println(ch + " -> ");
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();

            if (jpWords.getJpWrite().equals(res)) {
                System.out.println("正确");
                correct++;
            } else {
                System.out.println("错误");
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }

}
