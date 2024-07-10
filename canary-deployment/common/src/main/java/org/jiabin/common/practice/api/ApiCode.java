package org.jiabin.common.practice.api;

public enum ApiCode {

    SUCCESS(200,"成功！"),
    ERROR(500,"服务器异常！"),
    TOKEN_NULL(401,"用户未登录,或者token已失效！"),
    PUBLIC_SUCCESS(2000,"自定义成功！"),
    PUBLIC_ERROR(5000,"自定义异常！"),
    USER_IS_NULL(1404,"用户不存在！"),
    AUTO_LOGIN_USER_IS_NULL(2404,"自动登录用户不存在！"),
    ;
    ApiCode(int code, String value){
        this.code=code;
        this.value=value;
    }
    private int code;
    private String value;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
