package com.csv.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber implements Serializable {
    //序列号
    private int subscriberNumber;
    //密码
    private int Password;
    //别名
    private String subscriberAlias;
    //是否允许修改密码【0:Deny、1:Allow】
    private int changePassword;
    //用户类型【1:SIP Terminal、3:Audio/Video Recording Server、5:DC、6:Dispatch、7:Mobile Radio】
    private int subscriberType;
    //状态【0:Normal、1:Stunned、2:Kiled】
    private int status;
}