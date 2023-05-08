package com.util;

import com.alibaba.fastjson.JSONObject;
import com.enums.BusinessDomainEnum;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

//判断字符串类型工具
class StringUtilsTests {

    //测试方法1：使用org.apache.commons.lang3.StringUtils，判断字符串是否是数值类型,数值只能判断0-正整数，有一个缺陷,负数和浮点数未正确判断，比如-1判断为false
    @Test
    void judgeStringType()  {
        String s0 = "123";
        String s1 = "adb123";
        String s2 = "-1";
        String s3 = "0";
        String s4 = "3.1415926";
        System.out.println("判断s0是否为数值类型：" + StringUtils.isNumeric(s0)); //true
        System.out.println("判断s1是否为数值类型：" + StringUtils.isNumeric(s1)); //false
        System.out.println("判断s2是否为数值类型：" + StringUtils.isNumeric(s2)); //false
        System.out.println("判断s3是否为数值类型：" + StringUtils.isNumeric(s3)); //true
        System.out.println("判断s4是否为数值类型：" + StringUtils.isNumeric(s4)); //false
    }

    //测试方法2：采用正则表达式匹配，优点：除字符串外都能匹配
    @Test
    void judgeStringType2()  {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        String s0 = "123";
        String s1 = "adb123";
        String s2 = "-1";
        String s3 = "0";
        String s4 = "3.1415926";
        System.out.println("判断s0是否为数值类型：" + pattern.matcher(s0).matches()); //true
        System.out.println("判断s1是否为数值类型：" + pattern.matcher(s1).matches()); //false
        System.out.println("判断s2是否为数值类型：" + pattern.matcher(s2).matches()); //true
        System.out.println("判断s3是否为数值类型：" + pattern.matcher(s3).matches()); //true
        System.out.println("判断s4是否为数值类型：" + pattern.matcher(s4).matches()); //true
    }

    //自定义截取字符串
    @Test
    void substringStr()  {
        String str = "1103&ysp.wpagent.alarmname.44702";
        StringBuffer sb = new StringBuffer();
        String prefix = str.substring(0, str.indexOf("&") + 1);
        String suffix = str.substring(str.indexOf("&") + 1);
        System.out.println("前缀:" + prefix);
        System.out.println("后缀:" + suffix);
        sb.append(prefix).append(suffix);
        System.out.println("解析后完整的字符串：" + sb.toString());
    }

    //解析字符串国际化词条key，生成最终的词条
    @Test
    void parseString()  {
        String str = "1:MCS.enable\r\n0:MCS.disable\r\n";
        StringBuffer ultimatelyStr = new StringBuffer();
        if (str.contains("\r\n")) {
            String replaceStr = str.replace("\r\n", ",");
            System.out.println("replaceStr-----" + replaceStr);
            String[] splitArr = replaceStr.split(",");
            for (int i=0; i< splitArr.length; i++) {
                String[] splitItem = splitArr[i].split(":");
                ultimatelyStr.append(splitItem[0]).append(":").append(BusinessDomainEnum.transfer(splitItem[1])).append("\r\n");
            }
        }
        System.out.println(ultimatelyStr.toString());
    }

    @Test
    void parseString2()  {
        String str = "{\"userNumArray\":[\"2611111\",\"2611117\"],\"vo\":{\"flag\":2,\"userNum\":\"\",\"userType\":[0],\"enterpriseDomain\":\"mcs.com\",\"productType\":1,\"vpnId\":7,\"vpnName\":\"ldz\",\"vpnClass\":1},\"page\":{\"pageNo\":1,\"pageSize\":100},\"info\":{\"msgid\":43003,\"neid\":2124}}";
        JSONObject json = JSONObject.parseObject(str);
        System.out.println(json);
        System.out.println(json.getJSONObject("vo").getString("vpnName"));
    }
}
