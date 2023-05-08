package com.cacheable.controller;

import com.cacheable.bean.UserModel;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 练习SpringBoot本地缓存技术
 * @Author 211145187
 * @Date 2022/5/17 11:02
 **/
@RestController
@RequestMapping("/ehCache")
public class EhCacheController {
    private ConcurrentHashMap<String, List<UserModel>> cache = new ConcurrentHashMap<>();

    @PostConstruct
    public void initCacheUserModelList() {
        List<UserModel> userModelList = new ArrayList<>();
        userModelList.add(new UserModel(1, "Chris", 40, "中国上海"));
        userModelList.add(new UserModel(2, "Marry", 20, "中国北京"));
        userModelList.add(new UserModel(3, "Mike", 30, "中国广州"));
        cache.put("userModelList", userModelList);
    }

    /**
     * 缓存list
     * 固定key
     *
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "user_model", key = "'list_all'")
    public List<UserModel> list() {
        List<UserModel> userModelList = cache.get("userModelList");
        System.out.println("从数据库获取用户列表");
        userModelList.stream().forEach(System.out::println);
        return userModelList;
    }

    /**
     * 缓存find单条查询
     * 固定key
     *
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    @Cacheable(cacheNames = "user_model", key = "'find_1'")
    public UserModel find(@PathVariable int id) {
        UserModel userModel = new UserModel(1, "Chris", 40, "中国上海");
        System.out.println("从数据库获取用户");
        return userModel;
    }

    /**
     * 缓存findById单条查询
     * 依照不同的请求参数进行缓存
     * 相同参数读缓存，不同参数再缓存
     *
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    @Cacheable(cacheNames = "user_model", key = "#id")
    public UserModel findById(@PathVariable int id) {
        UserModel userModel = new UserModel(id, "Chris", 40, "中国上海");
        System.out.println("从数据库获取用户：" + id);
        return userModel;
    }

    /**
     * 修改缓存1
     * 调用这个方法，无论如何都会将结果写缓存
     *
     * @param userModel
     * @return
     */
    @PutMapping("/update")
    @CachePut(cacheNames = "user_model", key = "#userModel.id")
    public UserModel update(@RequestBody UserModel userModel) {
        userModel = new UserModel(userModel.getId(), "Jerry", 28, "哈尔滨");
        System.out.println("从缓存中修改用户，key = #userModel.id：" + userModel);
        return userModel;
    }
    /**
     * 修改缓存2
     * 调用这个方法，无论如何都会将结果写缓存
     *
     * @param userModel
     * @return
     */
    @PutMapping("/update2")
    @CachePut(cacheNames = "user_model", key = "'list_all'")
    public List<UserModel> update2(@RequestBody UserModel userModel) {
        List<UserModel> userModelList = cache.get("userModelList");
        Iterator<UserModel> iterator = userModelList.iterator();
        while(iterator.hasNext()){
            UserModel item = iterator.next();
            if (item.getId() ==  userModel.getId()) {
                iterator.remove();  //注意这个地方
            }
        }
        userModelList.add(new UserModel(userModel.getId(), userModel.getName(), userModel.getAge(), userModel.getAddress()));
        cache.put("userModelList", userModelList);
        System.out.println("从缓存中修改用户，key = 'list_all'");
        cache.get("userModelList").stream().forEach(System.out::println);
        return userModelList;
    }

    /**
     * 删除缓存
     * 调用删除key匹配的缓存
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @CacheEvict(cacheNames = "user_model", key = "#id")
    public String delete(@PathVariable int id) {
        System.out.println("从缓存删除用户：" + id);
        return "Delete User " + id + " success.";
    }
}