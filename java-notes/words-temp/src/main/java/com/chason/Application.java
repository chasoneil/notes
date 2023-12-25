package com.chason;

import com.chason.entity.JpFifty;
import com.chason.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static List<JpFifty> words = new ArrayList<>();

    public static void main(String[] args) {
        starter();
    }


    public static void starter () {

        System.out.println("***开始进行五十音图的测试***");

        initData();
        int size = words.size();
        // 产生 0-size中的随机数


        Scanner scanner = new Scanner(System.in);
        scanner.next();

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
