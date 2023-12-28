package com.chason;

import com.chason.entity.JpFifty;
import com.chason.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Application {

    private static List<JpFifty> words = new ArrayList<>();

    private static int count = 0;

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
        double correct = 0L;
        switch (type) {
            case 1:
                correct = doTest1();
                break;
            case 2:
                correct = doTest2();
                break;
            case 3:
            case 4:
                correct = doTest3(type);
                break;
            default:
                break;
        }
        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + (correct / count) * 100 + "%");

    }

    /**
     * 平假名 -> 片假名
     */
    private static double doTest1() {
        int size = count;
        Random random = new Random();
        String res = "";
        double correct = 0L;
        while (size > 0) {
            int index = random.nextInt(size--);
            JpFifty jpFifty = words.get(index);
            System.out.println("请根据平假名书写片假名 :" + jpFifty.getPing() + " -> ");
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
        return correct;
    }

    /**
     * 片假名 -> 平假名
     */
    private static double doTest2() {
        int size = count;
        Random random = new Random();
        String res = "";
        double correct = 0L;
        while (size > 0) {
            int index = random.nextInt(size--);
            JpFifty jpFifty = words.get(index);
            System.out.println("请根据片假名书写平假名 :" + jpFifty.getPian() + " -> ");
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
        return correct;
    }

    /**
     * 平假名 -> 读音
     */
    private static double doTest3(int type) {
        int size = count;
        Random random = new Random();
        String res = "";
        double correct = 0L;
        while (size > 0) {
            int index = random.nextInt(size--);
            JpFifty jpFifty = words.get(index);

            if (type == 3) {
                System.out.println("请根据平假名书写读音 :" + jpFifty.getPing() + " -> ");
            } else if (type == 4) {
                System.out.println("请根据片假名书写读音 :" + jpFifty.getPian() + " -> ");
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
        return correct;
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

        count = words.size();
    }


}
