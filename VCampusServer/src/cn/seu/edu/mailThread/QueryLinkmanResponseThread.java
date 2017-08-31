package cn.seu.edu.mailThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

/*
 * QueryLinkmanResponseThread
 * 查找联系人
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/6
 *
 */
public class QueryLinkmanResponseThread {
	private ServerReaderThread server ;
	private MailData mData;
	private String uName;
	
	public QueryLinkmanResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.mData = (MailData)reqData;
		this.uName = mData.getmRecipientName();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			
			
			//查询学生表
			String sql = "select * from tblStu where uName like ? or uStuNumber like ? or uSchool like ? or uMajor like ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,"%"+uName+"%");
			conAccess.pstmt.setString(2,"%"+uName+"%");
			conAccess.pstmt.setString(3,"%"+uName+"%");
			conAccess.pstmt.setString(4,"%"+uName+"%");
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("true");
			
			while(rs.next()){
				InfoData infoData = new InfoData("true");
				infoData.setStuNumber(rs.getString("uStuNumber"));
				infoData.setName(rs.getString("uName"));
				infoData.setSchool(rs.getString("uSchool"));
				infoData.setMajor(rs.getString("uMajor"));
				listData.getInfoList().add(infoData);
			}
			
			
			//查询教师表
			String sql2 = "select * from tblTea where uName like ? or uId like ? or uSchool like ? or uMajor like ?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql2);
			conAccess.pstmt.setString(1,"%"+uName+"%");
			conAccess.pstmt.setString(2,"%"+uName+"%");
			conAccess.pstmt.setString(3,"%"+uName+"%");
			conAccess.pstmt.setString(4,"%"+uName+"%");
			ResultSet rs2 = conAccess.pstmt.executeQuery();
			while(rs2.next()){
				InfoData infoData = new InfoData("true");
				infoData.setStuNumber(rs2.getString("uId"));
				infoData.setName(rs2.getString("uName"));
				infoData.setSchool(rs2.getString("uSchool"));
				infoData.setMajor(rs2.getString("uMajor"));
				listData.getInfoList().add(infoData);				
			}			
			server.sendDataToClient(listData);
	
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new ObjListData("false"));
		}
	}
}
