package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import util.bean.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * json字符串、JSNObject、JSONArray、jsonbean、list等相关的转换、过滤工具方法测试
 * @Author 211145187
 * @Date 2022/11/2 11:00
 **/
public class ListUtils {

    //json字符串转JSONObject
    @Test
    public void jsonStrConverJSONObject(){
        String str = "{\"id\":1,\"name\":\"tom\"}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);     //输出：{"name":"tom","id":1}
    }

    //json字符串转List<User>
    @Test
    public void jsonStrConverList(){
        String str = "[{\"id\":1,\"name\":\"tom\"},{\"id\":2,\"name\":\"cat\"}]";
        List<User> userList = JSONArray.parseArray(str, User.class);
        System.out.println(userList);   //输出：[User(id=1, name=tom), User(id=2, name=cat)]
    }

    //json字符串转JavaBean对象
    @Test
    public void jsonStrConverBean(){
        String str = "{\"id\":1,\"name\":\"tom\"}";
        User user = JSONObject.parseObject(str, User.class);
        System.out.println(user);   //输出：User(id=1, name=tom)
    }

    //json对象转javabean
    @Test
    public void JSONObjectConverBean(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "tom");
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        System.out.println(user);   //输出：User(id=1, name=tom)
    }

    //json对象转map
    @Test
    public void JSONObjectConverMap(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "tom");
        Map<String,String> map = JSONObject.parseObject(jsonObject.toJSONString(), Map.class);
        System.out.println(map);   //输出：{name=tom, id=1}
    }

    //List<User>转jsonArray
    @Test
    public void listConverjJsonArray(){
        List<User> list = new ArrayList<>();
        list.add(new User(1, "a"));
        list.add(new User(2, "b"));
        //错误写法，因为list.toString()输出[User(id=1, name=a), User(id=2, name=b)]。这东西无法json解析，会报错：com.alibaba.fastjson.JSONException: syntax error, pos 2, line 1, column 3[User(id=1, name=a), User(id=2, name=b)]
//        JSONArray jsonArray =JSONArray.parseArray(list.toString());
        //正确写法，简写方式
        JSONArray jsonArray =JSONArray.parseArray(JSONObject.toJSONString(list));

        //正确写法，复杂方式
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = null;
//        for (User user: list) {
//            jsonObject = new JSONObject();
//            jsonObject.put("id", user.getId());
//            jsonObject.put("name", user.getName());
//            jsonArray.add(jsonObject);
//        }
        System.out.println(jsonArray);
    }

    //jsonArray转成String[]
    @Test
    public void jsonArrayConverStringArray(){
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "100");
        jsonArray.add(1, "101");
        jsonArray.add(2, "102");
        System.out.println("jsonArray:" + jsonArray);

        String[] stringArr = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArr[i] = jsonArray.get(i).toString();
        }
        for(String str : stringArr) {
            System.out.println(str);
        }
    }

    //list根据ids数组过滤list
    @Test
    public void listFilter() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "a"));
        list.add(new User(2, "b"));
        list.add(new User(3, "c"));
        list.add(new User(4, "d"));
        list.add(new User(5, "e"));
        list.add(new User(6, "f"));
        list.add(new User(7, "g"));
        list.add(new User(8, "h"));
        list.add(new User(9, "i"));
        list.add(new User(10, "j"));

        //注意：数组类型必须使用Integer才可以，使用int会判断失败
        Integer[] arr = new Integer[]{1,2,5,6,9};
        List<User> filterList = list.stream().filter(item -> Arrays.asList(arr).contains(item.getId())).collect(Collectors.toList());
        filterList.stream().forEach(System.out::println);
    }

    /**
     * 问题：为啥使用int就判断失效，而使用Integer和String都能准确判断？
     * 答案：不能将基本数据类型转化为List列表。
     */
    @Test
    public void test1() {
        int[] arr = new int[]{1,2,5,6,9};
        System.out.println(Arrays.asList(arr).contains(1)); //结果为false
        Integer[] arr2 = new Integer[]{1,2,5,6,9};
        System.out.println(Arrays.asList(arr2).contains(1)); //结果为true
        String[] arr3 = new String[]{"1","2","5","6","9"};
        System.out.println(Arrays.asList(arr3).contains("1")); //结果为true

        //验证答案如下,把arr、arr2、arr3分别返回查看返回泛型，能够看出Arrays.asList(arr)返回的居然是List<int[]>，问题就出在这，说明list里面包含的是一个个的int[]，用这个判断ints.contains(1)，肯定为false
        List<int[]> ints = Arrays.asList(arr);
        List<Integer> integers = Arrays.asList(arr2);
        List<String> strings = Arrays.asList(arr3);
    }
}
