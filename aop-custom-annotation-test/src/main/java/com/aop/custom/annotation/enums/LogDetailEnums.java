package com.aop.custom.annotation.enums;

public enum LogDetailEnums {
    /**
     * 开户
     */
    CREATE_ACCOUNT("CREATE_ACCOUNT", LogDetailConstants.CREATE_ACCOUNT),
    /**
     * PSTN Flag
     */
    PSTN_ENABLE_FLAG("pstnFlag_1","Enable"),
    PSTN_DISABLE_FLAG("pstnFlag_0","Disable");

    private String code;

    private String message;

    LogDetailEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getDetailByCode(String messageCode){
        try {
            for (LogDetailEnums value : LogDetailEnums.values()) {
                if (value.code.equals(messageCode)){
                    return value.message;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static LogDetailEnums getLogDetailEnum(String code){
        if (code==null){
            return null;
        }
        for (LogDetailEnums value : LogDetailEnums.values()) {
            if (code.equals(value.code)){
                return value;
            }
        }
        return null;
    }
}
