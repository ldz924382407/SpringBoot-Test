package com.swagger.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Msg
 *
 * @author:
 * @date: 2020-06-18
 */
@AllArgsConstructor
@Getter
public enum Msg {

    /**
     * 请求类型错误
     */
    SUCCESS(200, "success"), FAILED(20001, "Operation failed"),
    EXCUTE_ERROR(16001, "Service invocation exception, please try again later"),
    UNKNOW_ERROR(16002, "unknown error"),
    PARAM_ERROR(16003, "request param error"),
    DATA_SAVE_FAIL(16004, "data save fail"),
    ID_IS_NOT_EXIST(16005, "id isn't exist"),
    QUERY_RESULT_IS_EMPTY(16006, "request result is null"),
    NAME_IS_EXIST(16007, "name already exist"),
    ID_IS_NOT_EXIST_DATA(16008, "There is no data for the current ID"),
    DATASOURCE_NOT_EXIST(16009, "datasource not exist"),
    JDBC_EXCEPTION(16010, "jdbc connection or search exception"),
    TASK_HAS_BEEN_STOPPED(16011, "task has been stopped");

    private int code;
    private String msg;

    private static final Map<Integer, Msg> MAP = new HashMap<>();

    static {
        for (Msg msgEnum : Msg.values()) {
            MAP.put(msgEnum.code, msgEnum);
        }
    }

    /**
     * 通过code获取枚举
     *
     * @param code
     *            code
     * @return 枚举
     */
    public static Msg getByCode(int code) {
        return MAP.get(code);
    }
}
