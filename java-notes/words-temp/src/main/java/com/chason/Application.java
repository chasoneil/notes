package com.chason;

import com.chason.entity.JpFifty;
import com.chason.entity.JpWords;
import com.chason.service.FiftyService;
import com.chason.service.WordsService;

import java.util.*;

public class Application {

    private static List<JpFifty> fifties = new ArrayList<>();

    private static List<JpWords> words = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("*****日语单词练习*****");
        System.out.println("-> 请选择练习类型： 1 -> 五十音图 | 2 -> 日语单词");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();

        if (type ==  1) {
            fiftyStarter();
        }

        if (type == 2) {
            wordsStarter();
        }
    }

    // 五十音
    public static void fiftyStarter () {

        System.out.println("*****五十音图练习*****");
        System.out.println("-> 请输入练习类型：");
        System.out.println("1 -> 根据平假名写出片假名");
        System.out.println("2 -> 根据片假名写出平假名");
        System.out.println("3 -> 根据平假名写出拼写");
        System.out.println("4 -> 根据片假名写出拼写");

        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        fifties = FiftyService.initFiftyData();

        String correctRate = "0.0%";
        switch (type) {
            case 1:
                correctRate = FiftyService.doTest1(fifties);
                break;
            case 2:
                correctRate = FiftyService.doTest2(fifties);
                break;
            case 3:
            case 4:
                correctRate = FiftyService.doTest3(fifties, type);
                break;
            default:
                break;
        }
        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + correctRate);
    }

    private static void wordsStarter() {

        System.out.println("*****日语单词练习*****");
        System.out.println("-> 请输入练习文件：（输入文件序列号）");

        Scanner scanner = new Scanner(System.in);
        String index = scanner.next();

        System.out.println("-> 执行测试初始化");
        words = WordsService.initWordsData(index);

        String correctRate = "0.0%";
        System.out.println("-> 请输入练习类型：");
        System.out.println("1 -> 根据日语写出中文含义");
        System.out.println("2 -> 根据日语发音写出中文含义");
        System.out.println("3 -> 根据中文含义写出日语");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                correctRate = WordsService.doTest1(words);
                break;
            case 2:
                correctRate = WordsService.doTest2(words);
                break;
            case 3:
                correctRate = WordsService.doTest3(words);
                break;
            default:
                break;
        }
        System.out.println("*** 测试结束 ***");
        System.out.println("正确率：" + correctRate);
    }

}
