package com.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.entity.Brand;
import com.mybatis.entity.BrandExample;
import com.mybatis.mapper.BrandMapper;
import com.mybatis.mapper.CacheMapper;
import com.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Date:2021/11/27
 * Author:ybc
 * Description:
 */
public class CacheMapperTest {

    //判断同一个sqlSession是否查询1级缓存，答案：会查询1级缓存
    @Test
    public void testCache1(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper cacheMapper1 = sqlSession.getMapper(CacheMapper.class);
        System.out.println(cacheMapper1.getAddressByUserId(1));
        CacheMapper cacheMapper2 = sqlSession.getMapper(CacheMapper.class);
        System.out.println(cacheMapper2.getAddressByUserId(1));
    }

    //判断不同sqlSession是否查询1级缓存，答案：不会查询1级缓存
    @Test
    public void testCache2(){
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        CacheMapper cacheMapper1 = sqlSession1.getMapper(CacheMapper.class);
        System.out.println(cacheMapper1.getAddressByUserId(1));
        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper cacheMapper2 = sqlSession2.getMapper(CacheMapper.class);
        System.out.println(cacheMapper2.getAddressByUserId(1));
    }

    //判断相同sqlSession中间执行一次清除后，是否查询1级缓存，答案：不会查询1级缓存
    @Test
    public void testCache3(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper cacheMapper = sqlSession.getMapper(CacheMapper.class);
        System.out.println(cacheMapper.getAddressByUserId(1));
        sqlSession.clearCache();
        System.out.println(cacheMapper.getAddressByUserId(1));
    }

    //验证二级缓存生效的问题
    @Test
    public void testCache4(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getAddressByUserId(1));
            sqlSession1.close();
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getAddressByUserId(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证QBC
    @Test
    public void testMyBatis(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            BrandMapper brandMapper = sqlSession1.getMapper(BrandMapper.class);
            BrandExample brandExample = new BrandExample();
            brandExample.createCriteria().andNameEqualTo("MUJI制造商");
            List<Brand> brandList = brandMapper.selectByExample(brandExample);
            brandList.forEach(item -> System.out.println());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证QBC
    @Test
    public void testMyBatis2(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            BrandMapper brandMapper = sqlSession1.getMapper(BrandMapper.class);
            Brand brand = new Brand();
            brand.setId(1001000);
            brand.setName(null);
            brandMapper.updateByPrimaryKey(brand);
            brandMapper.updateByPrimaryKeySelective(brand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用MyBatis的分页插件实现分页功能：
     * 1.需要在查询功能之前开启分页才能声小
     * PageHelper.startPage(int pageNum, int pageSize)
     * 2.在查询功能之后获取分页相关信息
     * PageInfo<Brand> pageInfo = new PageInfo<>(brandList, 5);
     * list: 表示分页业数据
     * navigatePages：表示当前导航分页的数量
     **/
    @Test
    public void testMyBatis3(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            BrandMapper brandMapper = sqlSession1.getMapper(BrandMapper.class);
            PageHelper.startPage(2, 5);
            List<Brand> brandList = brandMapper.selectByExample(null);
            PageInfo<Brand> pageInfo = new PageInfo<>(brandList, 5);
            brandList.forEach(System.out::println);
            System.out.println(pageInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
