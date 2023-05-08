package com.aop.custom.annotation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * merge fromn iot
 *
 * @param <T>
 * @Author: Mickey
 * @Date: 2019/9/9 {TIME}
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Response<T> implements Serializable {

        private int code;
        private String msg;
        private T info;

        private Response(Msg msg) {
            this(msg, null);
        }

        private Response(Msg msg, T info) {
            this.code = msg.getCode();
            this.msg = msg.getMsg();
            this.info = info;
        }

        private Response(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        /**
         * Return to success
         *
         * @param <T>
         *            Return info
         * @return <T> Success
         */
        public static <T> Response<T> success() {
            return success(null);
        }

        /**
         * Return to success
         *
         * @param t
         *            Return info
         * @param <T>
         *            Return generic
         * @return <T> Return entity
         */
        public static <T> Response<T> success(T t) {
            return new Response<>(Msg.SUCCESS, t);
        }

        /**
         * Return failed
         *
         * @param msg
         *            result enumeration
         * @param <T>
         *            Return info
         * @return <T> Return entity
         */
        public static <T> Response<T> error(Msg msg) {
            return new Response<>(msg);
        }

        /**
         * Return failed
         *
         * @param code
         *            Error code
         * @param msg
         *            Error message
         * @param <T>
         *            Return info
         * @return <T> Return entity
         */
        public static <T> Response<T> error(int code, String msg) {
            return new Response<>(code, msg);
        }

        /**
         *
         * @author luke liu
         * @param <T>
         *            t
         * @param msg
         *            error msg
         * @return error result
         **/
        public static <T> Response<T> error(String msg) {
            return new Response(msg);
        }

        /**
         * Determine whether response is successful in returning
         *
         * @return Success
         */
        public boolean responseSuccess() {
            if (this.code == Msg.SUCCESS.getCode()) {
                return true;
            }
            return false;
        }

        private Response(String msg) {
            this.code = Msg.FAILED.getCode();
            this.msg = msg;
            this.info = null;
        }
}
