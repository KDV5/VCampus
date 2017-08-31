package cn.seu.edu.feedbackThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

/*
 * DealWithFeedbackResponseThread
 * ����Ա����Ͷ��
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/4
 *
 */

public class DealWithFeedbackResponseThread{
	private ServerReaderThread server ;
	private FeedbackData fData;
	private int fID;            //Ͷ���¼���ID
	private String result;      //������
	private String senderID;    //Ͷ����ID
	
	public DealWithFeedbackResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.fData = (FeedbackData)reqData;
		this.fID = fData.getfID();
		this.result = fData.getfResult();
		this.senderID = fData.getId();
	}

	public void run(){
		
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select * from tblFeedback where ID = ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setInt(1,fID);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				sql = "update tblFeedback set fResult = ?  where ID = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,result);
				conAccess.pstmt.setInt(2,fID);
				conAccess.pstmt.executeUpdate();
				
			
				//��Ͷ�ߴ���ɹ�����Ͷ���߷��ͷ����ʼ�
				MailData mData = new MailData("sendMail");
				mData.setmSenderID(senderID);
				mData.setmTopic("Ͷ�ߴ�����֪ͨ");
				mData.setmContent("���ã�����"+rs.getString("fTime")+"����"+rs.getString("fType")+"��Ͷ���ѵõ���������\n"+result+"\n�������Ȩ����񴦲鿴");
				mData.setmRecipientID(rs.getString("uID"));
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mData.setmTime(format.format(date));
				server.sendDataToClient(mData);
			
			}else{
				server.sendDataToClient(new RequestData("false"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
