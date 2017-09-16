package seu.edu.common.message;

import java.util.ArrayList;

public class ListMessage extends BasicMessage {
	private ArrayList<BasicMessage> dataList=null;
	
	public ArrayList<BasicMessage> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<BasicMessage> dataList) {
		this.dataList = dataList;
	}

	public ListMessage(String mtype, String rtype, ArrayList<BasicMessage> al){
		super(mtype,rtype);
		this.setDataList(al);
	}
	
}
