package com.chason;

import com.chason.entity.JpFifty;
import com.chason.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {

    private static List<JpFifty> words = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("***开始进行五十音图的测试***");
        System.out.println("请输入练习类型：");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        fiftyStarter(type);
    }

    // 五十音
    public static void fiftyStarter (int type) {
        initData();
        int size = words.size();
        int count = size;
        double correct = 0L;

        Random random = new Random();
        while (size > 0) {

            int index = random.nextInt(size);
            size--;

            JpFifty jpFifty = words.get(index);
            words.remove(index);

            String res = "";
            if (type == 1) {
                System.out.println("请根据平假名书写片假名 :" + jpFifty.getPing() + " -> ");
                Scanner scanner = new Scanner(System.in);
                res = scanner.next();
                if (jpFifty.getPian().equals(res)) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.err.println("错误");
                }
            } else if (type == 2) {
                System.out.println("请根据片假名书写平假名 :" + jpFifty.getPian() + " -> ");
                Scanner scanner = new Scanner(System.in);
                res = scanner.next();
                if (jpFifty.getPing().equals(res)) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.err.println("错误");
                }
            }
        }


        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + (correct / count) * 100 + "%");

    }


    public static void initData() {

        URL url = Application.class.getClassLoader().getResource("fifty");
        String file = url.getPath();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (StringUtil.isEmpty(line)) {
                    continue;
                }
                JpFifty jpFifty = new JpFifty(line.split("#")[0], line.split("#")[1]);
                words.add(jpFifty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
