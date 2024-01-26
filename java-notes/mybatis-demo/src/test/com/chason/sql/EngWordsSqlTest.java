package com.chason.sql;

import com.chason.mapper.EngWordsMapper;
import com.chason.pojo.EngWords;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class EngWordsSqlTest {

    private SqlSessionFactory factory;

    @Before
    public void createFactory () {

        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            this.factory = factory;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void insertTest() {

        SqlSession sqlSession = this.factory.openSession();

        EngWords words = new EngWords();
        words.setWords("responsibility");
        words.setMeans("责任，职责");
        words.setWordType("名词");

        try {
            EngWordsMapper engWordsMapper = sqlSession.getMapper(EngWordsMapper.class);
            int result = engWordsMapper.insertEnglishWord(words);
            sqlSession.commit();        // 如果不执行 commit 则数据不会持久化到数据库
            Assert.assertEquals(1, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void selectTest () {

        SqlSession sqlSession = this.factory.openSession();
        EngWordsMapper engWordsMapper = sqlSession.getMapper(EngWordsMapper.class);
        List<EngWords> lists = engWordsMapper.selectAll();

        for(EngWords word : lists) {
            System.out.println(word);
        }

        sqlSession.close();
    }


}
