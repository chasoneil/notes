package com.chason.service.english;

import com.chason.config.MybatisFactory;
import com.chason.entity.english.EngSentence;
import com.chason.entity.english.EngWords;
import com.chason.mapper.EngSentenceMapper;
import com.chason.mapper.EngWordMapper;
import com.chason.util.FileUtil;
import com.chason.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

public class EnglishWordsService {

    private static Random random = new Random();

    private String fileIndex;

    private static int size;

    public static List<EngWords> engWords = new ArrayList<>();

    public static final String PRIFIX = "english/words/words_";

    public List<String> cache = new ArrayList<>();

    private static SqlSession sqlSession;

    private static double correct = 0L;

    static {
        sqlSession = MybatisFactory.getSqlSession();
    }

    public EnglishWordsService (String fileIndex) {
        this.fileIndex = fileIndex;
    }

    public static String doTest(int type) {

        Scanner scanner = new Scanner(System.in);
        size = engWords.size();
        while (engWords.size() > 0) {
            int index = random.nextInt(engWords.size());
            EngWords engWords = EnglishWordsService.engWords.get(index);
            String res = scanner.next();
            if (type == 1) {
                System.out.println(engWords.getWords() + " -> ");
                if (engWords.getMeans().contains(res)) {
                    System.out.println("CORRECT");
                    correct++;
                } else {
                    System.err.println("ERROR");
                }
            } else if (type == 2) {
                System.out.println(engWords.getMeans() + " -> ");
                if (engWords.getWords().contains(res)) {
                    System.out.println("CORRECT");
                    correct++;
                } else {
                    System.err.println("ERROR");
                }
            }
            EnglishWordsService.engWords.remove(engWords);
        }

        return (correct / size) * 100 + "%";
    }

    public static void initData (String index) {
        FileUtil.initData(index, 3);
    }

    public static void resolveData (String line) {

        String[] msg = line.split("#");
        if ("END".equalsIgnoreCase(line)) {
            return;
        }
        if (msg.length == 2) {
            return;
        }
        if (StringUtil.isEmpty(msg[0]) || StringUtil.isEmpty(msg[1]) ||
                StringUtil.isEmpty(msg[2])) {
            return;
        }

        EngWords engWord = new EngWords();
        engWord.setWords(msg[0]);
        engWord.setMeans(msg[1]);
        engWords.add(engWord);
    }

    public static void closeSqlSession()  {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    public static String doDBTest (String index, int testType) {

        Scanner scanner = new Scanner(System.in);

        switch (testType) {
            case 1:
            case 2:
                EngWordMapper engWordMapper = sqlSession.getMapper(EngWordMapper.class);
                List<EngWords> engWords = engWordMapper.listByIndex(index);
                size = engWords.size();

                if (testType == 1) {
                    System.out.println("-> Please translate to Chinese:");
                }

                if (testType == 2) {
                    System.out.println("-> Please translate to English:");
                }

                while (engWords.size() > 0) {

                    int idx = random.nextInt(engWords.size());
                    EngWords words = engWords.get(idx);

                    if (testType == 1) {
                        System.out.println("-> Word :" + words.getWords() + "（" + words.getWordType() + ")");
                    }

                    if (testType == 2) {
                        System.out.println("-> Word :" + words.getMeans() + "（" + words.getWordType() + ")");
                    }

                    String input = scanner.nextLine();

                    if (testType == 1) {
                        if (words.getMeans().contains(input)) {
                            correct++;
                        } else {
                            System.err.println("ERROR! Answer:" + words.getMeans());
                        }
                    }

                    if (testType == 2) {
                        if (words.getWords().equalsIgnoreCase(input)) {
                            correct++;
                        } else {
                            System.out.println("Answer:" + words.getWords());
                        }
                    }

                    engWords.remove(words);
                    System.out.println();
                }
                return (correct / size) * 100 + "%";
            case 3:
                EngSentenceMapper engWordsMapper = sqlSession.getMapper(EngSentenceMapper.class);
                List<EngSentence> engSentences = engWordsMapper.listByIndex(index);

                System.out.println("-> Please translate sentence:");

                while (engSentences.size() > 0) {
                    int idx = random.nextInt(engSentences.size());
                    EngSentence engSentence = engSentences.get(idx);
                    System.out.println("-> Please use word：" + engSentence.getEngWord());
                    System.out.println("Chinese :" + engSentence.getCnMean());
                    System.out.println("Yours :" + scanner.nextLine());
                    System.out.println("Answer:" + engSentence.getEngSentence());
                    System.out.println();
                    engSentences.remove(engSentence);

                }
                return "Only practice";
            default:
                return null;
        }
    }

    /**
     * flush cache to database
     */
    public void flush () {

        if (cache.size() == 0) {
            return;
        }

        EngSentenceMapper engSentenceMapper = sqlSession.getMapper(EngSentenceMapper.class);
        EngWordMapper     engWordMapper     = sqlSession.getMapper(EngWordMapper.class);
        String current = "";
        for (String line : cache) {
            if (!"END".equalsIgnoreCase(line)) {

                String[] split = line.split("#");
                if (split.length == 3) {    // word
                    current = split[0];
                    EngWords engWords = new EngWords();
                    engWords.setWords(split[0]);
                    engWords.setMeans(split[1]);
                    engWords.setWordType(split[2]);
                    engWords.setFileIndex(fileIndex);
                    engWordMapper.insert(engWords);
                }

                if (split.length == 2) {    // sentence
                    EngSentence engSentence = new EngSentence();
                    engSentence.setEngWord(current);
                    engSentence.setFileIndex(fileIndex);
                    engSentence.setEngSentence(split[0]);
                    engSentence.setCnMean(split[1]);
                    engSentenceMapper.insert(engSentence);
                }
            }
        }
        sqlSession.commit();
    }

}
