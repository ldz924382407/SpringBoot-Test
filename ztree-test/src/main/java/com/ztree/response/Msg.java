package com.ztree.response;

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
        SUCCESS(200, "success"), FAILED(20001, "Operation failed");

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
