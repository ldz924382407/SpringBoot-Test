package com.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 211145187
 * @Date 2022/2/23 09:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description= "返回响应数据")
public class User {
    @ApiModelProperty(name = "id", value = "用户ID")
    private Integer id;
    //姓名
    @ApiModelProperty(name = "name", value = "用户姓名")
    private String name;
    //密码
    @ApiModelProperty(name = "password", value = "用户密码")
    private String password;
}
