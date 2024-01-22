package com.chason.service.english;

import com.chason.entity.english.EnglishWords;
import com.chason.util.FileUtil;
import com.chason.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class EnglishWordsService {

    public static List<EnglishWords> engWords = new ArrayList<>();

    public static final String PRIFIX = "english/words/words_";

    private static double correct = 0L;

    public static String doTest(int type) {
        int size = engWords.size();
        int count = size;

        if (type == 1) {
            System.out.println("---> 请根据英语单词写出中文含义 <---");
        } else if (type == 2) {
            System.out.println("---> 请根据中文释义写出英语单词 <---");
        }

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        while (size > 0) {
            int index = random.nextInt(size--);
            EnglishWords englishWords = engWords.get(index);

            String res = scanner.next();
            if (type == 1) {
                System.out.println(englishWords.getWords() + " -> ");
                if (englishWords.getMeans().contains(res)) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.err.println("错误");
                }
            } else if (type == 2) {
                System.out.println(englishWords.getMeans() + " -> ");
                if (englishWords.getWords().contains(res)) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.err.println("错误");
                }
            }
            engWords.remove(index);
        }

        return (correct / count) * 100 + "%";
    }

    public static void initData (String index) {
        FileUtil.initData(index, 3);
    }

    public static void resolveData (String line) {
        EnglishWords engWord = new EnglishWords();

        String[] msg = line.split("#");
        if (msg.length != 3) {
            return;
        }

        if (StringUtil.isEmpty(msg[0]) || StringUtil.isEmpty(msg[1]) ||
                StringUtil.isEmpty(msg[2])) {
            return;
        }

        engWord.setWords(msg[0]);
        engWord.setMeans(msg[1]);
        engWords.add(engWord);
    }



}
