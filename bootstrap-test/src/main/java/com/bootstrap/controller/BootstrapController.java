package com.bootstrap.controller;

import com.bootstrap.bean.Teacher;
import com.bootstrap.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试JQuery针对Bootstrap使用
 * @Author 211145187
 * @Date 2022/2/25 13:54
 **/
@RestController
@Slf4j
public class BootstrapController {
    //table表格数据缓存
    private ConcurrentHashMap<String, List<Teacher>> cache = new ConcurrentHashMap<>();

    //首页
    @GetMapping(value = "")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @PostConstruct
    public void initCacheTeacherList() {
        List<Teacher> teacherList = new ArrayList<>();
        Teacher teacher1 = new Teacher();
        teacher1.setId(1);
        teacher1.setName("周杰伦");
        teacher1.setClasses("三年二班");
        teacher1.setCollege("魔法学院");
        teacher1.setAlias("Jay Chou");
        teacher1.setSex(1);
        teacherList.add(teacher1);
        Teacher teacher2 = new Teacher();
        teacher2.setId(2);
        teacher2.setName("丽萨");
        teacher2.setClasses("三年二班");
        teacher2.setCollege("魔法学院");
        teacher2.setAlias("Lisa");
        teacher2.setSex(0);
        teacherList.add(teacher2);
        cache.put("list", teacherList);
    }


    //查询
    @GetMapping(value = "/getTeacherList")
    @ResponseBody
    public Response<List<Teacher>> getTeacherList(Model model) {
        List<Teacher> teacherList = cache.get("list");
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("flag", true);
        teacherList.stream().forEach(System.out::println);
        return Response.success(teacherList);
    }

    //新增
    @PostMapping(value = "/addTeacher")
    @ResponseBody
    public Response<Teacher> addTeacher(@RequestBody Teacher teacher) {
        List<Teacher> teacherList = cache.get("list");
        Integer maxId = 0;
        for (Teacher item : teacherList) {
            if (item.getId() > maxId) maxId = item.getId();
        }
        //模拟id自增
        teacher.setId(++maxId);
        teacherList.add(teacher);
        cache.put("list", teacherList);
        cache.get("list").stream().forEach(System.out::println);
      return Response.success(teacher);
    }

    //修改
    @PutMapping(value = "/updateTeacher")
    @ResponseBody
    public Response<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        List<Teacher> teacherList = cache.get("list");
        Teacher teacherNew = new Teacher();
        for (Teacher item : teacherList) {
            if (item.getId() ==  teacher.getId()) {
                teacherNew.setId(teacher.getId());
                teacherNew.setName(teacher.getName());
                teacherNew.setClasses(teacher.getClasses());
                teacherNew.setCollege(teacher.getCollege());
                teacherNew.setAlias(teacher.getAlias());
                teacherNew.setSex(teacher.getSex());
                teacherList.remove(item);
                teacherList.add(teacherNew);
            }
        }
        cache.put("list", teacherList);
        cache.get("list").stream().forEach(System.out::println);
        return Response.success(teacherNew);
    }

    //删除
    @DeleteMapping(value = "/deleteTeacher")
    @ResponseBody
    public Response<Teacher> deleteTeacher(@RequestParam Integer id) {
        List<Teacher> teacherList = cache.get("list");
        Iterator<Teacher> iterator = teacherList.iterator();
        while(iterator.hasNext()){
            Teacher item = iterator.next();
            if (item.getId() ==  id) {
                iterator.remove();  //注意这个地方
            }
        }
        cache.put("list", teacherList);
        cache.get("list").stream().forEach(System.out::println);
        return Response.success();
    }

}
