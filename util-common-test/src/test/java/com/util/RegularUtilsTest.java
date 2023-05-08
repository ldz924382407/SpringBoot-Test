package com.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配工具
 * @Author 211145187
 * @Date 2022/9/22 16:17
 **/
public class RegularUtilsTest {

    //正则匹配url地址，判断格式是否正确,要求能够匹配IP、端口号、?、=、#、等等特殊字符
    @Test
    public void regular1() {
        String regular = "(http|https):\\/\\/([\\w.!@#$%^&*()_+-=])*\\s*";
        String url1 = "http://10.161.30.176:8880/mrps/toLogin.do";
        String url3 = "ht://192.168.241.66:22402/#/login";
        String url4 = "https://blog.csdn.net/yu0_zhang0/article/details/83898819/#?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166356753316782428612369%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=166356753316782428612369&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~pc_rank_v39-4-83898819-null-null.142^v47^pc_rank_34_2,201^v3^control_2&utm_term=spring%E9%9B%86%E6%88%90%E6%97%A5%E5%BF%97%E6%A1%86%E6%9E%B6&spm=1018.2226.3001.4187";

        Pattern regex= Pattern.compile(regular);
        //获取匹配器对象  公式: regex.matcher(str)
        Matcher macher=regex.matcher(url4);
        //查找是否有满足条件的内容,继续向下查找
        System.out.println(macher.find());
    }

    //正则匹配内容，要求以"AAAA"开头，以"BBBB.enc"结尾，中间是字母和数字
    @Test
    public void regular2() {
        String regular = "(AAAA)[\\w]*(BBBB.enc)$";
        String url1 = "AAAA003064500DA8MCSBBBB.enc";
        String url2 = "AAAA003064500DA8MCSBBBB (1).enc";
        String url3 = "AAAA0030!@500DA8MCSBBBB.enc";
        String url4 = "AAAA0030！@500DA8MCSBBBB.enc";

        Pattern regex= Pattern.compile(regular);
        System.out.println("url1是否通过校验:" + regex.matcher(url1).find());
        System.out.println("url2是否通过校验:" + regex.matcher(url2).find());
        System.out.println("url3是否通过校验:" + regex.matcher(url3).find());
        System.out.println("url4是否通过校验:" + regex.matcher(url4).find());
    }

    //匹配11位手机号，要求：第一位数字为1，第二位数字是34578中的一个，接下来后9位都是0到9的数字
    @Test
    public void regular3() {
        String regular = "^1[34578]\\d{9}$";
        String str1 = "15221621619";
        String str2 = "11221621619";
        System.out.println("手机号str1校验:" + Pattern.compile(regular).matcher(str1).find());  //手机号str1校验:true
        System.out.println("手机号str2校验:" + Pattern.compile(regular).matcher(str2).find());  //手机号str2校验:false
    }

    //匹配QQ号，要求：第一个数不可能是0，目前qq最多是10位数字，最少5位
    @Test
    public void regular4() {
        String regular = "^[1-9][0-9]{4,9}";
        String str3 = "924382";
        String str4 = "0101";
        System.out.println("QQ号str3校验:" + Pattern.compile(regular).matcher(str3).find());  //QQ号str3校验:true
        System.out.println("QQ号str4校验:" + Pattern.compile(regular).matcher(str4).find());  //QQ号str4校验:false
    }

    /**
     * 需求：在字符串 ‘北京市(朝阳区)(西城区)(海淀区)’ 中，取出没有被()包裹的字符 北京市
     * 你可能有疑问，如果我正则写成这个样子.*(?=\()，为什么结果就不对？这个正则结果是：北京市(朝阳区)(西城区)
     * 答案：
     *      . 代表匹配除了换行和行结束符的任意字符
     *      * 代表匹配任意次数，保证我们得到的是北京市而不是市这个单字
     *      ? 代表对多个连续的值，要么匹配0次，要么匹配1次，最多匹配一次
     *
     *    上面说了，?在这个表达式里，表示要么不匹配，要么最多匹配一次，这就是非贪婪模式
     *    而不添加?，表达式以贪婪模式运行。什么是贪婪模式？就是尽可能多的去匹配，匹配到了还去匹配，贪得无厌，一直匹配到字符串的末尾
     *    因为字符串中有三个(，贪婪模式下会一直匹配到字符串结尾，就会得到“北京市(朝阳区)(西城区)”这个结果。
     */
    @Test
    public void regular5() {
        String regular = ".*?(?=\\()";
        String str5 = "北京市(朝阳区)(西城区)(海淀区)";
        Matcher matcher = Pattern.compile(regular).matcher(str5);
        if (matcher.find()) System.out.println("采用‘零宽断言‘，对str5提取想要内容:" + matcher.group());  //采用‘零宽断言‘，对str5提取想要内容:北京市
    }

}
