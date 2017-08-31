package cn.seu.edu.feedbackThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

/*
 * AdministratorCheckFeedbackResponseThread
 * ����Ա�鿴Ͷ��
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */

public class AdministratorCheckFeedbackResponseThread {
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;  //����ԱID
	private String uType;
	
	public AdministratorCheckFeedbackResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			//�жϹ���Ա���
			if(userID.startsWith("10")){
				uType = "��Ϣ����";
			}else if(userID.startsWith("11")){
				uType = "����";
			}else if(userID.startsWith("12")){
				uType = "����";
			}else if(userID.startsWith("13")){
				uType = "�̵�";
			}else if(userID.startsWith("14")){
				uType = "ͼ���";
			}else if(userID.startsWith("15")){
				uType = "ʳ��";
			}else if(userID.startsWith("16")){
				uType = "����";
			}else if(userID.startsWith("17")){
				uType = "�������г�";
			}else if(userID.startsWith("18")){
				uType = "��ѧ¥";
			}else if(userID.startsWith("19")){
				uType = "����";
			}
			
			String sql = "select * from tblFeedback where fType = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,uType);
			ResultSet rs = conAccess.pstmt.executeQuery();
			
			ObjListData listData = new ObjListData("true");
	
			while(rs.next()){
				FeedbackData fData = new FeedbackData("checkFeedback");
				fData.setfType(rs.getString("fType"));
				fData.setfProblem(rs.getString("fProblem"));
				fData.setfPhone(rs.getString("fPhone"));
				fData.setfTime(rs.getString("fTime"));
				String result = rs.getString("fResult");
				if("".equals(result)|| null == result){
				    fData.setfResult("δ����");
				}else{
					fData.setfResult(result);
				}
				fData.setfID(rs.getInt("ID"));
				listData.getFeedbackList().add(fData);
				
			}
			server.sendDataToClient(listData);
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
	}
}
