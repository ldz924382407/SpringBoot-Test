package com.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.redis.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试redis相关功能
 * @Author 211145187
 * @Date 2022/9/20 18:27
 **/
@RequestMapping("/redis")
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //测试redisTemplate和stringRedisTemplate插入数据
    @GetMapping(value = "/redisTemplateAndStringRedisTemplate1")
    public void redisTemplateAndStringRedisTemplate1() {
        redisTemplate.opsForValue().set("redisTemplateListKey","abc");
        stringRedisTemplate.opsForValue().set("stringRedisTemplateListKey","\"{\"cat\":\"猫\",\"dog\":\"狗\"}\"");
    }
    //测试redisTemplate和stringRedisTemplate查询数据
    @GetMapping(value = "/redisTemplateAndStringRedisTemplate2")
    public void redisTemplateAndStringRedisTemplate2() {
        System.err.println("redisTemplate查询:" + redisTemplate.opsForValue().get("redisTemplateListKey"));
        System.err.println("redisTemplate查询:" + redisTemplate.opsForValue().get("stringRedisTemplateListKey"));
        System.err.println("stringRedisTemplate查询:" + stringRedisTemplate.opsForValue().get("redisTemplateListKey"));
        System.err.println("stringRedisTemplate查询:" + stringRedisTemplate.opsForValue().get("stringRedisTemplateListKey"));
    }

    //string类型添加
    @GetMapping(value = "/stringAdd")
    public void stringAdd() {
        // 添加redis 字符类型数据 strKey1
        redisTemplate.opsForValue().set("strKey1","一段话。。。");

        // 添加redis 字符类型数据 strKey2
        JSONObject json = new JSONObject();
        json.put("dog","狗");
        json.put("cat","猫");
        redisTemplate.opsForValue().set("strKey2",json.toJSONString());
    }
    //string类型查询
    @GetMapping(value = "/stringQuery")
    public void stringQuery() {
        // 通过 strKey1 获取并打印值
        System.err.println(redisTemplate.opsForValue().get("strKey1"));
        // 通过 strKey2 获取并打印值
        System.err.println(redisTemplate.opsForValue().get("strKey2"));
    }

    //hash类型添加
    @GetMapping(value = "/hashAdd")
    public void hashAdd() {
        // 通过 h1 获取值
        System.err.println(redisTemplate.opsForHash().get("hash1","key1"));
        System.err.println(redisTemplate.opsForHash().entries("hash1"));
    }
    //hash类型查询
    @GetMapping(value = "/hashQuery")
    public void hashQuery() {
        // 通过 h1 获取值
        System.err.println(redisTemplate.opsForHash().get("hash1","key1"));
        System.err.println(redisTemplate.opsForHash().entries("hash1"));
    }

    //list类型添加
    @GetMapping(value = "/listAdd")
    public void listAdd() {
        List list = new ArrayList<>();
        User user1 = new User("老赵", "123");
        User user2 = new User("老曹", "456");
        list.add(user1);
        list.add(user2);
        // 直接添加list
        redisTemplate.opsForList().leftPush("listKey",list);

        //循环添加元素
        redisTemplate.opsForList().leftPush("list1","v1");
        redisTemplate.opsForList().leftPush("list1","v2");
        redisTemplate.opsForList().leftPush("list1","v3");
    }
    //list类型查询
    @GetMapping(value = "/listQuery")
    public void listQuery() {
        System.err.println(redisTemplate.opsForList().leftPop("listKey"));
        // 通过 list1 从队列左侧取出并删除数据
        System.err.println(redisTemplate.opsForList().leftPop("list1"));
        // 通过 list1 从队列右侧取出并删除数据
        System.err.println(redisTemplate.opsForList().rightPop("list1"));
    }

    //set（无序集合）类型添加
    @GetMapping(value = "/setAdd")
    public void setAdd() {
        User user1 = new User("老赵", "123");
        User user2 = new User("老曹", "456");
        // 添加数据
        redisTemplate.opsForSet().add("set1","v1","v2","v3");
        redisTemplate.opsForSet().add("set2","v1");
        redisTemplate.opsForSet().add("set3",user1, user2);
    }
    //set（无序集合）类型查询
    @GetMapping(value = "/setQuery")
    public void setQuery() {
        // 求交集
        System.err.println(redisTemplate.opsForSet().intersect("set1","set2"));
        // 求差集
        System.err.println(redisTemplate.opsForSet().difference("set1","set2"));
        // 求并集
        System.err.println(redisTemplate.opsForSet().union("set1","set2"));
        System.err.println(redisTemplate.opsForSet().members("set3"));
    }

    //zset（有序集合）类型添加
    @GetMapping(value = "/zsetAdd")
    public void zsetAdd() {
        // 添加数据
        redisTemplate.opsForZSet().add("zset1","A",1);
        redisTemplate.opsForZSet().add("zset1","B",3);
        redisTemplate.opsForZSet().add("zset1","C",2);
        redisTemplate.opsForZSet().add("zset1","D",5);
    }
    //zset（有序集合）类型查询
    @GetMapping(value = "/zsetQuery")
    public void zsetQuery() {
        System.err.println(redisTemplate.opsForZSet().rangeByScore("zset1",1,4));
    }

    //删除key
    @GetMapping(value = "/deleteKey")
    public void deleteKey() {
        //删除key
        redisTemplate.delete("strKey1");
    }
}
