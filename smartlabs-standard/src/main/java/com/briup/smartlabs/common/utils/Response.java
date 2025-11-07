package com.briup.smartlabs.common.utils;
import lombok.Getter;
import lombok.Setter;

//Restful风格API接口统一响应格式。
@Getter
@Setter
public class Response<T> {
	/**
	 *   code: 自定义响应状态码。
	 */
	private Integer code;
	/**
	 *   status: 自定义响应状态描述
	 */
	private String status;
	 /**
     *   data 默认响应携带数据
     */
	private T data;	
	
    private Response(T data) {
        this.code = 200;
        this.status = "ok";
        this.data = data;
    }
    /**
     *  默认的成功响应，不携带数据
     */
    private Response() {
        this(null);
    }
    /**
     *  自定义响应，不携带数据
     */
    private Response(Integer code, String status ) {
    	 this.code = code;
         this.status = status;
    }
    /**
     *  自定义响应,携带数据
     */
    private Response(Integer code, String status, T data) {
        this.status = status;
        this.code = code;
        this.data = data;
    }
    /**
     * 200成功，带数据的响应返回
     */
    public static <T> Response<T> ok(T data) {
        return new Response<>(data);
    }

    /**
     * 操作失败，返回的响应，携带数据，携带错误信息
     * 默认是500
     */
    public static <T> Response<T> error(T data) {
        Response<T> response = new Response<>(data);
        response.setCode(500);
        response.setStatus("Internal Server Error");
        response.setData(data);
        return response;
    }
    
    public static <T> Response<T> error(){
    	Response<T> response = new Response<>();
        response.setCode(ResponseCodeEnum.ERROR.getStatus());
        response.setStatus(ResponseCodeEnum.ERROR.getMsg());
        return response;
    }	
    
    public static <T> Response<T> error(ResponseCodeEnum resp) {
        Response<T> response = new Response<>();
        response.setCode(resp.getStatus());
        response.setStatus(resp.getMsg());
        return response;
    }	
    
    public static <T> Response<T> error(ResponseCodeEnum resp,T data) {
        Response<T> response = new Response<>(data);
        response.setCode(resp.getStatus());
        response.setStatus(resp.getMsg());
        return response;
    }	
    public static <T> Response<T> error(int code,String errorMsg,T data) {
        Response<T> response = new Response<>(data);
        response.setCode(code);
        response.setStatus(errorMsg);
        return response;
    }	
}