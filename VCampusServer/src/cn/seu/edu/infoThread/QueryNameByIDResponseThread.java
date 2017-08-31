package cn.seu.edu.infoThread;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class QueryNameByIDResponseThread {
	private ServerReaderThread server ;
	private IdData idData;
	private String userID;
	
	public QueryNameByIDResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.idData = (IdData)reqData;
		this.userID = idData.getId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			if(userID.length() == 8){
				String sql = "select * from tblStu where uStuNumber = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,userID);
				ResultSet rs = conAccess.pstmt.executeQuery();
				InfoData infoData = new InfoData("");
				if(rs.next()){
					
					infoData.setName(rs.getString("uName"));
					infoData.setSchool(rs.getString("uSchool"));
					infoData.setMajor(rs.getString("uMajor"));
					infoData.setDorm(rs.getString("uDorm"));
					infoData.setAge(rs.getString("uAge"));
					infoData.setRoot(rs.getString("uRoot"));
					infoData.setTel(rs.getString("uTel"));
					infoData.setMail(rs.getString("uMail"));
					infoData.setAddress(rs.getString("uAddress"));
					infoData.setSex(rs.getString("uSex"));
					infoData.setMark(rs.getString("uMark"));
					infoData.setStuNumber(userID);
					File file =new File("userPic\\"+userID+".jpg");    
					
					if (file.exists() )
					{
						infoData.setIcon(new ImageIcon("userPic\\"+userID+".jpg"));
						}else{
							infoData.setIcon(null);
						}
					server.sendDataToClient(infoData);
				}
			}else if(userID.length() == 7){
				String sql = "select * from tblTea where uId = ?";
				conAccess.pstmt = conAccess.conn.prepareStatement(sql);
				conAccess.pstmt.setString(1,userID);
				ResultSet rs = conAccess.pstmt.executeQuery();
				InfoData infoData = new InfoData("");
				if(rs.next()){
					infoData.setName(rs.getString("uName"));
					infoData.setSchool(rs.getString("uSchool"));
					infoData.setMajor(rs.getString("uMajor"));
					infoData.setAge(rs.getString("uAge"));
					infoData.setTel(rs.getString("uTel"));
					infoData.setMail(rs.getString("uMail"));
					infoData.setAddress(rs.getString("uAddress"));
					infoData.setSex(rs.getString("uSex"));
					infoData.setMark(rs.getString("uMark"));
					infoData.setTitle(rs.getString("uTitle"));
					infoData.setStuNumber(userID);
					File file =new File("userPic\\"+userID+".jpg");    
					//如果文件夹不存在则创建    
					if (file.exists() )
					{
						infoData.setIcon(new ImageIcon("userPic\\"+userID+".jpg"));
						}else{
							infoData.setIcon(null);
						}
					server.sendDataToClient(infoData);
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
