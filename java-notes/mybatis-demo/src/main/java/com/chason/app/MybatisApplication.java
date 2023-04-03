package com.chason.app;

import com.chason.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisApplication {

    public static void main (String[] args) throws Throwable{

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        List<Student> lists = sqlSession.selectList("StudentMapper.selectAll");


        for (Student student :lists) {
            System.out.println(student.getName());
        }


    }

}
