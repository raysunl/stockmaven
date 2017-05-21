package com.sunstock.comm.base;

import java.util.HashMap;
import java.util.Map;

public class JsonResult<T> {
	
	public static String SUCCESS = "success";
	
	public static String FAIL = "fail";
	
	private String status = SUCCESS;
	
	private String msg;
	
	private T result;
	
	private Map<String, Object> property = new HashMap<String,Object>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public JsonResult(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public JsonResult() {
		this.status = SUCCESS;
	}

    public Map<String, Object> getProperty() {
        return property;
    }

    public void setProperty(Map<String, Object> property) {
        this.property = property;
    }
}
