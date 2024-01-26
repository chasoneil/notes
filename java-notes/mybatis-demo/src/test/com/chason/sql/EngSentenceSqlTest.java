package com.chason.sql;

import com.chason.mapper.EngSentenceMapper;
import com.chason.mapper.EngWordsMapper;
import com.chason.pojo.EngSentence;
import com.chason.pojo.EngWords;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class EngSentenceSqlTest {

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
    public void selectTest() {

        SqlSession sqlSession = this.factory.openSession();
        EngSentenceMapper engWordsMapper = sqlSession.getMapper(EngSentenceMapper.class);
        List<EngSentence> lists = engWordsMapper.listAll();

        for (EngSentence sentence :lists) {
            System.out.println(sentence);
        }

        sqlSession.close();

    }

}
