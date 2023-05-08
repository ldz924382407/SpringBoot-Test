package com.ztree.controller;

import com.ztree.bean.Equipment;
import com.ztree.bean.User;
import com.ztree.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试JQuery针对Ztree使用
 * @Author 211145187
 * @Date 2022/2/25 13:54
 **/
@RestController
@Slf4j
public class ZtreeController {
    //ZTree数据缓存
    private ConcurrentHashMap<String, List<Equipment>> zTreeCache = new ConcurrentHashMap<>();

    //首页
    @GetMapping(value = "")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @PostConstruct
    public void initCacheEquipmentList() {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment(0, -1, "装备"));
        equipmentList.add(new Equipment(1, 0, "陆"));
        equipmentList.add(new Equipment(2, 0, "海"));
        equipmentList.add(new Equipment(3, 0, "空"));
        equipmentList.add(new Equipment(4, 1, "东风2号"));
        equipmentList.add(new Equipment(5, 1, "坦克"));
        equipmentList.add(new Equipment(6, 2, "潜艇"));
        equipmentList.add(new Equipment(7, 2, "巡洋舰"));
        equipmentList.add(new Equipment(8, 3, "隐形飞机"));
        zTreeCache.put("equipmentList", equipmentList);
    }

    //查询ZTree
    @GetMapping(value = "/getEquipmentList")
    @ResponseBody
    public Response<List<Equipment>> getEquipmentList(Model model) {
        List<Equipment> equipmentList = zTreeCache.get("equipmentList");
        model.addAttribute("equipmentList", equipmentList);
        equipmentList.stream().forEach(System.out::println);
        return Response.success(equipmentList);
    }



    //查询
    @GetMapping(value = "/getVue")
    @ResponseBody
    public Response<User> getVue() {
        User user = new User();
        user.setName("tom");
        user.setPassword("admin123");
        log.info("--getVue--success:{}", user);
        return Response.success(user);
    }
    //新增
    @PostMapping(value = "/addVue")
    @ResponseBody
    public Response<User> addVue(@RequestBody User user) {
        log.info("--addVue--success:{}", user);
        return Response.success(user);
    }

}
