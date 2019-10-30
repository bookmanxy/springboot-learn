package cn.faceland.springbootfilesimple.controller;

import java.io.Serializable;

public class ResultBean implements Serializable {
	private Boolean success = Boolean.TRUE;
	private Integer code = 1;
	private String alertMsg ;
	private Object data ;

	public ResultBean(Boolean success, int code, String alertMsg){
		this.success = success;
		this.code = code;
		this.alertMsg = alertMsg;
	}

	public ResultBean(){}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultBean{" +
				"success=" + success +
				", code=" + code +
				", alertMsg='" + alertMsg + '\'' +
				", data=" + data +
				'}';
	}
}
