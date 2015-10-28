package it.isac.commons.requestresponse;

import it.isac.commons.interfaces.IData;

public class SimpleResponse {

	boolean success;
	String message;
	IData data;
	
	public SimpleResponse(){}
	
	public SimpleResponse(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(IData data) {
		this.data = data;
	}
	
}
