package seu.edu.common.message;

import java.io.Serializable;

/*
 * 用于在客户端与服务器之前传递的请求和信息的基础类
 */

public class BasicMessage implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 表示请求由哪个模块发起
	private String moduleType=null;
	// 表示请求类型
	private String requestType =null;	
	
	BasicMessage(String mtype,String rtype){
		System.out.println("BasicMessage的构造函数");
		this.setModuleType(mtype);
		this.setRequestType(rtype);
		
	}
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}	

}
