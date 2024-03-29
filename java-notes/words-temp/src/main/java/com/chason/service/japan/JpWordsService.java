package com.chason.service.japan;


import com.chason.entity.japan.JpWords;
import com.chason.util.FileUtil;
import com.chason.util.StringUtil;
import java.util.*;

public class JpWordsService {

    private static Random random = new Random();

    public List<String> cache = new ArrayList<>();

    private String fileIndex;

    private static double correct = 0L;

    public static final String PREFIX = "japan/words/words_";

    public static List<JpWords> words = new ArrayList<>();

    public JpWordsService () {}

    public JpWordsService (String index) {
        this.fileIndex = index;
    }

    public static void initWordsData (String index) {
        FileUtil.initData(index, 2);
    }

    public static String doTest1 () {

        int size = words.size();
        int count = size;

        String msg = "";
        System.out.println("---> 请根据日语单词翻译中文含义 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            doWordTest1(index, 1, msg);
        }

        return (correct / count) * 100 + "%";
    }

    public static String doTest2 () {

        int size = words.size();
        int count = size;

        String msg = "";
        System.out.println("---> 请根据日语发音翻译中文含义 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            doWordTest1(index, 2, msg);
        }
        return (correct / count) * 100 + "%";
    }

    public static String doTest3 () {

        int size = words.size();
        int count = size;

        String res = "";
        System.out.println("---> 请根据中文含义写出日语单词 <---");
        while (size > 0) {
            int index = random.nextInt(size--);
            JpWords jpWords = words.get(index);
            Set<String> chMeans = jpWords.getChMeans();
            String ch = "";
            for (String str : chMeans) {
                ch += str + "; ";
            }
            System.out.println(ch + " -> ");
            Scanner scanner = new Scanner(System.in);
            res = scanner.next();

            if (jpWords.getJpWrite().equals(res)) {
                System.out.println("正确");
                correct++;
            } else {
                System.err.println("错误");
                System.out.println("正确答案:" + jpWords.getJpWrite());
            }
            words.remove(index);
        }
        return (correct / count) * 100 + "%";
    }

    private static void doWordTest1(int index, int type, String msg) {

        JpWords jpWords = words.get(index);
        switch (type) {
            case 1:
                System.out.println(jpWords.getJpWrite() + " -> ");
                break;
            case 2:
                System.out.println(jpWords.getJpRead() + " -> ");
                break;
            default:
                break;
        }

        Scanner scanner = new Scanner(System.in);
        String enter = scanner.next();
        Set<String> chMeans = jpWords.getChMeans();

        if (chMeans.contains(enter)) {
            System.out.println("正确");
            correct++;
        } else {
            boolean flag = false;
            for (String mean : chMeans) {
                if (mean.contains(enter)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                System.out.println("正确");
                correct++;
            } else {
                System.err.println("错误");
                for (String mean : chMeans) {
                    msg += mean + "; ";
                }

                System.out.println("正确答案:" + msg);
            }
        }
        words.remove(index);
    }

    public static void resolveData (String line, boolean isDatabase) {

        JpWords jpWords = new JpWords();

        String[] msg = line.split("#");
        if (msg.length != 3) {
            return;
        }

        if (StringUtil.isEmpty(msg[0]) || StringUtil.isEmpty(msg[1]) ||
                StringUtil.isEmpty(msg[2])) {
            return;
        }

        jpWords.setJpRead(msg[0]);
        jpWords.setJpWrite(msg[1]);

        if (isDatabase) {
            System.out.println("构建中...");
        }

        String means = msg[2];
        String[] readStr = means.split(":");
        Set<String> tar = new HashSet<>();
        for (int i=0; i<readStr.length; i++) {
            tar.add(readStr[i]);
        }

        jpWords.setChMeans(tar);
        words.add(jpWords);
    }

    /**
     * flush japan words to database
     */
    public void flush () {

        if (cache.size() == 0) {
            return;
        }

        for (String line : cache) {
            resolveData(line, true);
        }
    }

}
