package cn.seu.edu.message;

import java.io.Serializable;

public class RequestData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String request = null;
	
	public RequestData(String request) {
		this.setRequest(request);
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
}
