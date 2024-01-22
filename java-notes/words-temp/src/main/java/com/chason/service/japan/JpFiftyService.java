package com.chason.service.japan;

import com.chason.entity.japan.JpFifty;
import com.chason.util.FileUtil;
import com.chason.util.StringUtil;
import java.util.*;

public class JpFiftyService {

    public static List<JpFifty> words = new ArrayList<>();

    public static final String PREFIX = "japan/fifty/fifty_";

    public static void initFiftyData (String index) {
        FileUtil.initData(index, 1);
    }

    /**
     * 平假名 -> 片假名
     */
    public static String doTest1() {

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
    public static String doTest2() {
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
    public static String doTest3(int type) {

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

    public static void resolveData (String line) {
        JpFifty jpFifty = new JpFifty();
        String[] msg = line.split("#");
        if (msg.length != 3) {
            return;
        }
        jpFifty.setPing(msg[0]);
        jpFifty.setPian(msg[1]);

        String reads = msg[2];
        if (StringUtil.isEmpty(reads)) {
            return;
        }

        String[] readStr = reads.split(":");
        Set<String> tar = new HashSet<>();
        for (int i=0; i<readStr.length; i++) {
            tar.add(readStr[i]);
        }
        jpFifty.setRead(tar);
        words.add(jpFifty);
    }

}
