package com.chason.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisFactory {

    public static SqlSession getSqlSession() {
        return createFactory().openSession();
    }

    private static SqlSessionFactory createFactory () {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            return new SqlSessionFactoryBuilder().build(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
