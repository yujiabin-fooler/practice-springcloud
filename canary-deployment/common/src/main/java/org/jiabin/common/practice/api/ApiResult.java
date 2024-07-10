package org.jiabin.common.practice.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * API统一返回对象
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 8004487252556526569L;

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;
    public static <T> ApiResult<T> success(){
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(),ApiCode.SUCCESS.getValue(),null);
    }
    public static <T> ApiResult<T> success(T data){
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(),ApiCode.SUCCESS.getValue(),data);
    }
    public static <T> ApiResult<T> success(T data,String msg){
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(),msg,data);
    }
    public static  <T> ApiResult<T>  fail(){
        return new ApiResult<T>(ApiCode.ERROR.getCode(),ApiCode.ERROR.getValue(),null);
    }
    public static  <T> ApiResult<T>  fail(String msg){
        return new ApiResult<T>(ApiCode.ERROR.getCode(),msg,null);
    }
    public static  <T> ApiResult<T>  fail(int code,String msg){
        return new ApiResult<T>(code,msg,null);
    }
    public boolean isSuccess(){
        return ApiCode.SUCCESS.getCode() == this.code ;
    }
    public boolean isFail(){
        return !isSuccess();
    }
}