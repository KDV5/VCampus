package seu.edu.common.message;

import java.io.Serializable;

/*
 * �����ڿͻ����������֮ǰ���ݵ��������Ϣ�Ļ�������
 */

public class BasicMessage implements Serializable {
	
	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	// ��ʾ�������ĸ�ģ�鷢��
	private String moduleType=null;
	// ��ʾ��������
	private String requestType =null;	
	
	BasicMessage(String mtype,String rtype){
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
