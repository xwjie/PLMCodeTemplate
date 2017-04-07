package com.huawei.plm.common.beans;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	public static final int NO_PERMISSION = 2;

	private String msg = "success";

	private int code = SUCCESS;

	private T data;

	public ResultBean() {
		super();
	}
	
	public ResultBean(T data) {
		super();
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
