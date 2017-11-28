package com.zdjy.bigdata.antun.web.response;



public class BaseResponse {

	private Integer code;
	private String msg;
	private Object data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public BaseResponse code(Integer code){
		this.code=code;
		return this;
	}
	
	public BaseResponse msg(String msg){
		this.msg=msg;
		return this;
	}
	public BaseResponse data(Object data){
		this.data=data;
		return this;
	}
	
	public static BaseResponse errorResponse() {
		BaseResponse baseResponse=new BaseResponse();
		baseResponse.setCode(400);
		return baseResponse;
		
	}
	public static BaseResponse errorResponse(String msg) {
		BaseResponse errorResponse=errorResponse();
		errorResponse.setMsg(msg);
		return errorResponse;
		
	}
	public static BaseResponse successResponse() {
		BaseResponse baseResponse=new BaseResponse();
		baseResponse.setCode(200);
		return baseResponse;
		
	}
	public static BaseResponse successResponse(String msg) {
		BaseResponse successResponse=successResponse();
		successResponse.setMsg(msg);
		return successResponse;
		
	}
}
