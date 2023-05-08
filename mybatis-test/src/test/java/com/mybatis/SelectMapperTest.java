package com.mybatis;

import com.mybatis.entity.Address;
import com.mybatis.entity.User;
import com.mybatis.mapper.AddressMapper;
import com.mybatis.mapper.SelectMapper;
import com.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date:2021/11/27
 * Author:ybc
 * Description:
 */
public class SelectMapperTest {

    /**
     * MyBatis的各种查询功能：
     * 1、若查询出的数据只有一条
     * a>可以通过实体类对象接收
     * b>可以通过list集合接收
     * c>可以通过map集合接收
     * 结果：{password=123456, sex=男, id=3, age=23, email=12345@qq.com, username=admin}
     * 2、若查询出的数据有多条
     * a>可以通过实体类类型的list集合接收
     * b>可以通过map类型的list集合接收
     * c>可以在mapper接口的方法上添加@MapKey注解，此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中
     * 注意：一定不能通过实体类对象接收，此时会抛异常TooManyResultsException
     *
     * MyBatis中设置了默认的类型别名
     * java.lang.Integer-->int,integer
     * int-->_int,_integer
     * Map-->map
     * String-->string
     */

    //------------------------------查询----------------------------------------

    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUserToMap());
    }

    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserByIdToMap(3));
    }

    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getCount());
    }

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUser());
    }

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserById(3));
    }

    @Test
    public void getListByIdArr(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        int[] idArr = new int[]{1, 2, 3};
        System.out.println(mapper.getListByIdArr(idArr, "litemall"));
    }

    @Test
    public void getListByIdList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(mapper.getListByIdList(list));
    }

    //------------------------------新增----------------------------------------
    //单条新增：（传参为对象）
    @Test
    public void insertUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = new User("同学4", "123456", "15197289663", 1);
        mapper.insertUser(user);
        System.out.println(user);
    }

    //批量新增：（传参为Map）
    @Test
    public void batchInsertByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, String> map = new HashMap<>();
        map.put("username", "同学11");
        map.put("gender", "1");
        System.out.println(mapper.batchInsertByMap(map));
    }

    //批量新增：（传参为List）
    @Test
    public void batchInsertByList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = new ArrayList<>();
        list.add(new User("同学7", 0));
        list.add(new User("同学8", 0));
        System.out.println(mapper.batchInsertByList(list));
    }

    //------------------------------修改----------------------------------------
    //单条更新：（传参为对象）
    @Test
    public void modifyUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = new User(3, "1111", 1);
        System.out.println(mapper.updateUser(user));
    }

    //批量更新：（传参为集合）
    @Test
    public void batchUpdateByList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = new ArrayList<>();
        list.add(new User(18, "同学12", 1));
        list.add(new User(19, "同学13", 1));
        System.out.println(mapper.batchUpdateByList(list));
    }

    //批量更新：（传参为Map且map种包含list集合）
    @Test
    public void batchUpdateByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = new ArrayList<>();
        list.add(new User(18, "同学12", 1));
        list.add(new User(19, "同学13", 1));
        Map<String, Object> map = new HashMap<>();
        map.put("paramMap", list);
        System.out.println(mapper.batchUpdateByMap(map));
    }

    //------------------------------删除----------------------------------------
    //根据id删除一条：（传参为对象）
    @Test
    public void deleteById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = new User();
        user.setId(3);
        System.out.println(mapper.deleteById(user));
    }

    //批量删除：（传参为List）
    @Test
    public void batchDeleteByList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(mapper.batchDeleteByList(list));
    }

    //模糊查询
    @Test
    public void getListByParam(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getListByParam("同学"));
    }

    //动态设置表名
    @Test
    public void getListByTableName(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getListByTableName("litemall_user"));
    }

    //动态设置表名
    @Test
    public void  checkNameVerify(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        String username = "同学1";
        Integer id = 9;
        int result = mapper.checkNameVerify(username, id);
        System.out.println(result);
    }

    /**
     * 解决字段名和属性名不一致的情况：
     * a>为字段起别名，保持和属性名的一致
     * b>设置全局配置，将_自动映射为驼峰
     * <setting name="mapUnderscoreToCamelCase" value="true"/>
     * c>通过resultMap设置自定义的映射关系
     * <resultMap id="empResultMap" type="Emp">
     *     <id property="eid" column="eid"></id>
     *     <result property="empName" column="emp_name"></result>
     *     <result property="age" column="age"></result>
     *     <result property="sex" column="sex"></result>
     *     <result property="email" column="email"></result>
     * </resultMap>
     */
    @Test
    public void getUserById2(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserById2(4));
    }

    /**
     * 处理多对一的映射关系：
     * a>级联属性赋值
     * b>association
     * c>分步查询
     */
    @Test
    public void getUserAndAddressById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
//        User user = selectMapper.getUserAndAddressById(4);

        User user = selectMapper.getUserById2(4);
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        Address address = addressMapper.getAddressByUserId(user.getId());
        user.setAddress(address);
        System.out.println(user);
    }

    /**
     * 处理一对多的映射关系
     * a>collection
     * b>分步查询
     */
    @Test
    public void getAddressAndUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        //collection
        Address address = addressMapper.getAddressAndUserById(1);
        System.out.println(address);

        //分步查询
//        Address address = addressMapper.getAddressById(1);
//        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
//        User user = selectMapper.getUserById(address.getUserId());
//        address.getUserList().add(user);
//        System.out.println(address);
    }

    /**
     * TODO 验证延迟加载
     */
    @Test
    public void lazyLoading(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> userList = selectMapper.getAllUser();
        userList.forEach(item -> {
            System.out.println(item.getUsername());
//            System.out.println(item.getAddress());
        });
    }
}
