package com.mybatis;

import com.mybatis.entity.User;
import com.mybatis.mapper.DynamicSQLMapper;
import com.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Date:2021/11/27
 * Author:ybc
 * Description:
 */
public class DynamicSQLMapperTest {

    @Test
    public void testIf(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        User user = dynamicSQLMapper.getUserByParamsWithIf(new User(9, "同学1", 0));
        System.out.println(user);
    }

    @Test
    public void testWhere(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        User user = dynamicSQLMapper.getUserByParamsWithWhere(new User(null, "同学1", 0));
        System.out.println(user);
    }

    @Test
    public void testTrim(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        User user = dynamicSQLMapper.getUserByParamsWithTrim(new User(9, "同学1", 0));
        System.out.println(user);
    }

    @Test
    public void testChoose(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<User> userList = dynamicSQLMapper.getUserByParamsWithChoose(new User(9, "同学1", 1));
        userList.forEach(System.out::println);
    }

    @Test
    public void testSQLFragment(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        User user = dynamicSQLMapper.getUserByParamsWithSQLFragment();
        System.out.println(user);
    }
}
