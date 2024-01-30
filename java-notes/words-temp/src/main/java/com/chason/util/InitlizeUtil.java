package com.chason.util;

import com.chason.Application;
import com.chason.service.english.EnglishWordsService;
import com.chason.service.japan.JpWordsService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class InitlizeUtil {

    public static void main(String[] args) {

        System.out.println("****执行数据初始化****");
        System.out.println("-> 请选择初始化类型: 1 全量初始化 | 2 指定文件初始化");
        Scanner scanner = new Scanner(System.in);

        int initType = scanner.nextInt();

        if (initType == 1) {
            initAll();
        }

        if (initType == 2) {
            initFile();
        }
    }

    public static void initAll() {
        System.out.println("程序构建中,请选择指定文件初始化");
        System.exit(-1);
    }

    public static void initFile () {

        Scanner scanner = new Scanner(System.in);

        System.out.println("****执行指定文件初始化****");
        System.out.println("-> 请输入初始化的语言类型： 1 日语 | 2 英语");

        int languageType = scanner.nextInt();

        System.out.println("-> 请输入初始化文件序列号：");
        String index = scanner.next();

        String filePath = "";
        if (languageType == 1) {
            filePath = JpWordsService.PREFIX + index;
        }

        if (languageType == 2) {
            filePath = EnglishWordsService.PRIFIX + index;
        }

        URL url = Application.class.getClassLoader().getResource(filePath);

        if (url == null) {
            System.err.println("文件不存在");
            System.exit(-1);
        }

        String file = url.getPath();

        System.out.println("开始执行初始化数据...");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = "";
            if (languageType == 1) {

            }

            if (languageType == 2) {
                EnglishWordsService service = new EnglishWordsService(index);
                while ((line = br.readLine()) != null) {
                    if (CheckUtil.checkLine(line)) {
                        service.cache.add(line);
                    }
                }
                service.flush();
            }

            EnglishWordsService.closeSqlSession();
            System.out.println("数据初始化完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
