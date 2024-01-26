package com.chason.service.english;

import com.alibaba.fastjson.JSON;
import com.chason.config.MybatisFactory;
import com.chason.entity.english.EngSentence;
import com.chason.entity.english.EngWords;
import com.chason.entity.english.Sentence;
import com.chason.mapper.EngSentenceMapper;
import com.chason.mapper.EngWordMapper;
import com.chason.util.FileUtil;
import com.chason.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

public class EnglishWordsService {

    public static List<EngWords> engWords = new ArrayList<>();

    public static final String PRIFIX = "english/words/words_";

    private static Map<String, EngSentence> currentSentence = new HashMap<>();

    private static String currentWord = "";

    private static List<Sentence> st = new ArrayList<>();

    private static double correct = 0L;

    private static SqlSession sqlSession;

    private static String currentIndex = "";

    static {
        if (sqlSession == null) {
            sqlSession = MybatisFactory.getSqlSession();
        }
    }

    public static String doTest(int type) {
        int size = engWords.size();
        int count = size;

        if (type == 1) {
            System.out.println("---> 请根据英语单词写出中文含义 <---");
        } else if (type == 2) {
            System.out.println("---> 请根据中文释义写出英语单词 <---");
        }

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        while (size > 0) {
            int index = random.nextInt(size--);
            EngWords englishWords = engWords.get(index);

            String res = scanner.next();
            if (type == 1) {
                System.out.println(englishWords.getWords() + " -> ");
                if (englishWords.getMeans().contains(res)) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.err.println("错误");
                }
            } else if (type == 2) {
                System.out.println(englishWords.getMeans() + " -> ");
                if (englishWords.getWords().contains(res)) {
                    System.out.println("正确");
                    correct++;
                } else {
                    System.err.println("错误");
                }
            }
            engWords.remove(index);
        }

        return (correct / count) * 100 + "%";
    }

    public static void initData (String index) {
        FileUtil.initData(index, 3);
    }

    public static void resolveData (String line) {
        EngWords engWord = new EngWords();

        String[] msg = line.split("#");
        if (msg.length == 2) {
            return;
        }

        if (StringUtil.isEmpty(msg[0]) || StringUtil.isEmpty(msg[1]) ||
                StringUtil.isEmpty(msg[2])) {
            return;
        }

        engWord.setWords(msg[0]);
        engWord.setMeans(msg[1]);
        engWords.add(engWord);
    }

    public static void save (String line, String index) {

        if (line.startsWith("Sentence")) {
            return;
        }

        currentIndex = index;

        String[] msgs = line.split("#");
        if (msgs.length == 3) {
            doInsertWord(msgs);
        }

        if (msgs.length == 2) {
            doInsertSentence(msgs);
        }
    }

    private static void doInsertWord (String[] msgs) {

        if (currentSentence.size() > 0) {
            // insert database
            EngSentenceMapper sentenceMapper = sqlSession.getMapper(EngSentenceMapper.class);
            currentSentence.get(currentWord).setEngSentence(JSON.toJSONString(st));
            sentenceMapper.insert(currentSentence.get(currentWord));
            sqlSession.commit();
            st.clear();
            currentSentence.clear();
        }

        currentWord = msgs[0];
        currentSentence.put(currentWord, new EngSentence(currentWord, currentIndex));
        EngWords engWords = new EngWords();
        engWords.setWords(msgs[0]);
        engWords.setMeans(msgs[1]);
        engWords.setWordType(msgs[2]);
        engWords.setFileIndex(currentIndex);

        // insert database
        EngWordMapper mapper = sqlSession.getMapper(EngWordMapper.class);
        mapper.insert(engWords);
        sqlSession.commit();
    }

    private static void doInsertSentence (String[] msgs) {
        st.add(new Sentence(msgs[0], msgs[1]));
    }

    public static void closeSqlSession()  {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    public static String doDBTest (String index, int testType) {

        SqlSession sqlSession = MybatisFactory.getSqlSession();
        Scanner scanner = new Scanner(System.in);

        if (testType == 1) {

        } else if (testType == 2) {

        } else if (testType == 3) {

            EngSentenceMapper engWordsMapper = sqlSession.getMapper(EngSentenceMapper.class);
            List<EngSentence> engSentences = engWordsMapper.listByIndex(index);
            System.out.println("-> 请根据中文句子翻译成英文");

            for (EngSentence engSentence : engSentences) {

                System.out.println("请使用单词：" + engSentence.getEngWord());
                List<Sentence> sentences = engSentence.sentences();

                for (Sentence sentence : sentences) {
                    System.out.println("中文：" + sentence.getMean());
                    String input = scanner.next();
                    System.out.println("答案：" + sentence.getSentence());
                }
            }
        }


        return null;

    }

}
