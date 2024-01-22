package com.chason;

import com.chason.service.english.EnglishWordsService;
import com.chason.service.japan.JpFiftyService;
import com.chason.service.japan.JpWordsService;

import java.util.*;

public class Application {

    public static void main(String[] args) {

        System.out.println("*****单词练习*****");
        System.out.println("-> 请选择语言： 1. 日本語　｜ 2. English");
        Scanner scanner = new Scanner(System.in);

        int languageType = scanner.nextInt();

        switch (languageType) {
            case 1:
                System.out.println("-> 请选择练习类型： 1 -> 五十音图 | 2 -> 日语单词");
                int type = scanner.nextInt();
                if (type ==  1) {
                    jpFiftyStarter();
                }
                if (type == 2) {
                    jpWordsStarter();
                }
                break;
            case 2:
                enWordStarter();
                break;
            default:
                break;
        }

    }


    public static void enWordStarter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-> 请选择文件：（输入文件序列号）");
        String index = scanner.next();
        System.out.println("-> 执行测试初始化");

        EnglishWordsService.initData(index);
        String correctRate = "0.0%";

        System.out.println("-> 请输入练习类型：");
        System.out.println("1 -> 根据英语写出中文含义");
        System.out.println("2 -> 根据中文含义写出英文");

        int testType  = scanner.nextInt();
        EnglishWordsService.doTest(testType);
        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + correctRate);
    }

    // fifty_01
    public static void jpFiftyStarter () {

        System.out.println("*****五十音图练习*****");
        System.out.println("-> 请输入练习文件：（输入文件序列号）");

        Scanner scanner = new Scanner(System.in);
        String index = scanner.next();

        System.out.println("-> 执行测试初始化");
        JpFiftyService.initFiftyData(index);

        String correctRate = "0.0%";
        System.out.println("-> 请输入练习类型：");
        System.out.println("1 -> 根据平假名写出片假名");
        System.out.println("2 -> 根据片假名写出平假名");
        System.out.println("3 -> 根据平假名写出拼写");
        System.out.println("4 -> 根据片假名写出拼写");

        int type = scanner.nextInt();
        switch (type) {
            case 1:
                correctRate = JpFiftyService.doTest1();
                break;
            case 2:
                correctRate = JpFiftyService.doTest2();
                break;
            case 3:
            case 4:
                correctRate = JpFiftyService.doTest3(type);
                break;
            default:
                break;
        }
        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + correctRate);
    }

    private static void jpWordsStarter() {

        System.out.println("*****日语单词练习*****");
        System.out.println("-> 请输入练习文件：（输入文件序列号）");

        Scanner scanner = new Scanner(System.in);
        String index = scanner.next();

        System.out.println("-> 执行测试初始化");
        JpWordsService.initWordsData(index);

        String correctRate = "0.0%";
        System.out.println("-> 请输入练习类型：");
        System.out.println("1 -> 根据日语写出中文含义");
        System.out.println("2 -> 根据日语发音写出中文含义");
        System.out.println("3 -> 根据中文含义写出日语");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                correctRate = JpWordsService.doTest1();
                break;
            case 2:
                correctRate = JpWordsService.doTest2();
                break;
            case 3:
                correctRate = JpWordsService.doTest3();
                break;
            default:
                break;
        }
        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + correctRate);
    }

}
