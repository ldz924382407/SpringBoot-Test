package com.mybatis.mapper;

import com.mybatis.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Date:2021/11/27
 * Author:ybc
 * Description:
 */
public interface SelectMapper {

    /**
     * 根据id查询用户信息
     */
    User getUserById(@Param("id") Integer id);

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();

    /**
     * 查询用户信息的总记录数
     */
    Integer getCount();


    /**
     * 根据id查询用户信息为一个map集合
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);

    /**
     * 查询所有用户信息为map集合
     */
//    List<Map<String, Object>> getAllUserToMap();
    @MapKey("id")
    Map<String, Object> getAllUserToMap();

    /**
     * 根据id数组查询集合为list
     * @Author 211145187
     * @Date 2023/4/24 22:55
     * @param arr id数组
     * @param schema 域名或者数据库名
     * @Return list
     **/
    List<User> getListByIdArr(@Param("arr") int[] arr, @Param("schema") String schema);

    List<User> getListByIdList(@Param("list") List<Integer> list);

    //单条新增：（传参为对象）
    int insertUser(@Param("user") User user);

    //批量新增：（传参为Map）
    int batchInsertByMap(@Param("map") Map<String, String> map);

    //批量新增：（传参为List）
    int batchInsertByList(@Param("list") List<User> list);

    //单条更新：（传参为对象）
    int updateUser(@Param("user") User user);

    //批量更新：（传参为集合）
    int batchUpdateByList(@Param("list") List<User> list);

    //批量更新：（传参为Map且map种包含list集合）
    int batchUpdateByMap(Map<String, Object> map);

    int deleteById(@Param("user") User user);

    int batchDeleteByList(@Param("list") List<Integer> list);

    List<User> getListByParam(@Param("username") String username);

    List<User> getListByTableName(@Param("tableName") String tableName);

    int checkNameVerify(@Param("username") String username, @Param("id") Integer id);

    /**
     * 根据id查询用户信息
     */
    User getUserById2(@Param("id") Integer id);

    //处理一对多的映射关系
    User getUserAndAddressById(@Param("id") Integer id);
}
