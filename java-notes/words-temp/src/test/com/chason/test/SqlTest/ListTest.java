package com.chason.test.SqlTest;

import com.chason.entity.english.EngSentence;
import com.chason.mapper.EngSentenceMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ListTest {

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
    public void listTest () {

        SqlSession sqlSession = factory.openSession();
        EngSentenceMapper engWordsMapper = sqlSession.getMapper(EngSentenceMapper.class);
        List<EngSentence> engSentences = engWordsMapper.listByIndex("03");
        System.out.println(engSentences);

    }

}
