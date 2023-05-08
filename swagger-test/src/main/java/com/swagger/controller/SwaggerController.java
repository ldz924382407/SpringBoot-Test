package com.swagger.controller;

import com.swagger.bean.User;
import com.swagger.response.PageResult;
import com.swagger.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用swagger,用于生成、描述、调用和可视化 RESTful 风格的 Web 服务
 * @Author 211145187
 * @Date 2022/9/19 16:31
 **/
@RestController
@RequestMapping("/swagger")
@Api(tags = "用户管理")
@Slf4j
public class SwaggerController {

    @GetMapping("/getList")
    @ApiOperation(value = "分页查询", notes = "分页查询的注意事项和备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "pageSize", value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数及格式不对"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public Response<PageResult<User>> getList(@RequestParam int pageNo, @RequestParam int pageSize){
        log.info("-getList-pageNo:{},pageSize:{}", pageNo, pageSize);
        List list = new ArrayList();
        list.add(new User(1, "tom", "tom"));
        list.add(new User(2, "jerry", "jerry"));
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPage(1);
        pageResult.setTotalCount(2);
        pageResult.setList(list);
        return Response.success(pageResult);
    }

    @PostMapping("/post")
    @ApiOperation(value = "新增用户", notes = "演示json参数是否接受成功")
    public Response postTest(@ApiParam(name = "user", value = "接收传递给后端接口的user对象参数") @RequestBody User user) {
        log.info("-postTest-user:{}", user);
        return Response.success(user);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户", notes = "演示json参数是否接受成功")
    public Response updateTest(@ApiParam(name = "user", value = "接收传递给后端接口的user对象参数") @RequestBody User user) {
        log.info("-postTest-user:{}", user);
        return Response.success(user);
    }

    //该方法最终会被隐藏掉，无法显示在可视化页面上
    @ApiIgnore
    @PutMapping("/update2")
    @ApiOperation(value = "修改用户2", notes = "演示json参数是否接受成功")
    public Response updateTest2(@ApiParam(name = "user", value = "接收传递给后端接口的user对象参数") @RequestBody User user) {
        log.info("-postTest-user:{}", user);
        return Response.success(user);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", notes = "演示json参数是否接受成功")
    public Response deleteTest(@ApiParam(name = "id", value = "接收的参数id") @PathVariable Integer id) {
        log.info("-deleteTest-id:{}", id);
        return Response.success(id);
    }
}
