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
                engWordStarter();
                break;
            default:
                break;
        }

    }


    public static void engWordStarter() {

        System.out.println("-> Please choose storage type： 1 Database | 2 File");

        Scanner scanner = new Scanner(System.in);
        int dataType =  scanner.nextInt();

        if (dataType == 1) {
            System.out.println("!! Need Database Support !!");
            doDatabaseTest();
        }

        if (dataType == 2) {
            doFileTest();
        }

    }

    private static void doDatabaseTest () {

        System.out.println("-> Please input file index:");

        Scanner scanner = new Scanner(System.in);
        String index = scanner.next();

        String correctRate = "0.0%";
        System.out.println("-> Please input practice：");
        System.out.println("1 -> translate English word to Chinese.");
        System.out.println("2 -> translate Chinese word to English.");
        System.out.println("3 -> do sentence practice by word.");

        int testType  = scanner.nextInt();
        correctRate = EnglishWordsService.doDBTest(index, testType);
        System.out.println("*** Test Finished ***");
        System.out.println("Correct Rate：" + correctRate);
    }

    private static void doFileTest () {

        Scanner scanner = new Scanner(System.in);
        System.out.println("-> Please input file index:");
        String index = scanner.next();

        System.out.println("-> initializing data...");
        EnglishWordsService.initData(index);

        String correctRate = "0.0%";
        System.out.println("-> Please input practice：");
        System.out.println("1 -> translate English word to Chinese.");
        System.out.println("2 -> translate Chinese word to English.");

        int testType  = scanner.nextInt();
        correctRate = EnglishWordsService.doTest(testType);

        System.out.println("*** Test Finished ***");
        System.out.println("Correct Rate：" + correctRate);
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
