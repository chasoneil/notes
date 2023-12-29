package com.chason.service;

import com.chason.Application;
import com.chason.entity.JpFifty;
import com.chason.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class FiftyService {

    public static List<JpFifty> initFiftyData () {

        List<JpFifty> words = new ArrayList<>();

        URL url = Application.class.getClassLoader().getResource("fifty");
        String file = url.getPath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (StringUtil.isEmpty(line)) {
                    continue;
                }
                JpFifty jpFifty = new JpFifty();

                String[] msg = line.split("#");
                if (msg.length != 3) {
                    continue;
                }
                jpFifty.setPing(msg[0]);
                jpFifty.setPian(msg[1]);

                String reads = msg[2];
                if (StringUtil.isEmpty(reads)) {
                    continue;
                }

                String[] readStr = reads.split(":");
                Set<String> tar = new HashSet<>();
                for (int i=0; i<readStr.length; i++) {
                    tar.add(readStr[i]);
                }
                jpFifty.setRead(tar);
                words.add(jpFifty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }


    /**
     * 平假名 -> 片假名
     */
    public static String doTest1(List<JpFifty> words) {

        int size = words.size();
        int count = size;

        Random random = new Random();
        String res = "";
        double correct = 0L;
        System.out.println("---> 请根据平假名书写片假名 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            JpFifty jpFifty = words.get(index);
            System.out.println(jpFifty.getPing() + " -> ");
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();
            if (jpFifty.getPian().equals(res)) {
                System.out.println("正确");
                correct++;
            } else {
                System.err.println("错误");
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }

    /**
     * 片假名 -> 平假名
     */
    public static String doTest2(List<JpFifty> words) {
        int size = words.size();
        int count = size;

        Random random = new Random();
        String res = "";
        double correct = 0L;

        System.out.println("---> 请根据片假名书写平假名 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            JpFifty jpFifty = words.get(index);
            System.out.println(jpFifty.getPian() + " -> ");
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();
            if (jpFifty.getPing().equals(res)) {
                System.out.println("正确");
                correct++;
            } else {
                System.err.println("错误");
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }


    /**
     * 平假名 -> 读音
     */
    public static String doTest3(List<JpFifty> words, int type) {

        int size = words.size();
        int count = size;

        Random random = new Random();
        String res = "";
        double correct = 0L;

        if (type == 3) {
            System.out.println("---> 请根据平假名书写读音 <---");
        }

        if (type == 4) {
            System.out.println("---> 请根据片假名书写读音 <---");
        }

        while (size > 0) {
            int index = random.nextInt(size--);
            JpFifty jpFifty = words.get(index);
            if (type == 3) {
                System.out.println(jpFifty.getPing() + " -> ");
            } else if (type == 4) {
                System.out.println(jpFifty.getPian() + " -> ");
            }
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();
            if (jpFifty.getRead().contains(res)) {
                System.out.println("正确");
                correct++;
            } else {
                System.err.println("错误");
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }

}
