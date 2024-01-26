package com.chason.json;

import com.alibaba.fastjson.JSON;
import com.chason.pojo.EngSentence;
import com.chason.pojo.Sentence;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    private EngSentence engSentence;

    @Before
    public void initData () {
        EngSentence engSentence = new EngSentence();
        engSentence.setEngWord("abc");

        Sentence sentence = new Sentence();
        sentence.setMean("这是一个测试语句");
        sentence.setSentence("This is a test sentence");

        List<Sentence> sentences = new ArrayList<>();
        sentences.add(sentence);

        engSentence.setEngSentence(JSON.toJSONString(sentences));
        this.engSentence = engSentence;
    }


    @Test
    public void JavaToJsonString () {
        String s2 = JSON.toJSONString(this.engSentence);
        System.out.println(s2);
    }

    @Test
    public void JsonStringToJava () {

        String str = JSON.toJSONString(this.engSentence);
        EngSentence es1 = JSON.parseObject(str, EngSentence.class);


        String s1 = es1.getEngSentence();

        System.out.println(s1);
        List<Sentence> sentences1 = JSON.parseArray(s1, Sentence.class);

        System.out.println(str);

        List<Sentence> sentences = es1.sentences();
        System.out.println(sentences);


    }

}
