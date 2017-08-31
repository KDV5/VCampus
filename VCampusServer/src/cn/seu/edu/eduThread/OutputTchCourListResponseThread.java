package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class OutputTchCourListResponseThread {
	private ServerReaderThread server ;
	private IdData iData;
	private String tchId;
	
	public OutputTchCourListResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.iData = (IdData)reqData;
		this.tchId =iData.getId();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			String sql = "select*from tblTea where uId=?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,tchId);
			ResultSet rs = conAccess.pstmt.executeQuery();
			if(rs.next()){
				String teaName = rs.getString("uName");
				PreparedStatement stin1=conAccess.conn.prepareStatement("select*from tblCourMessage where uCourTeacher=? ");
				stin1.setString(1,teaName);
				ResultSet rs1=stin1.executeQuery();
				
				CourseData cData = new CourseData("");
				cData.obj2[0][1]="��һ";
				cData.obj2[0][2]="�ܶ�";
				cData.obj2[0][3]="����";
				cData.obj2[0][4]="����";
				cData.obj2[0][5]="����";
				cData.obj2[1][0]="<html>"+"����<br>1��2��";
				cData.obj2[2][0]="<html>"+"����<br>3��4��";
				cData.obj2[3][0]="<html>"+"����<br>1��2��";
				cData.obj2[4][0]="<html>"+"����<br>3��4��";
				cData.obj2[5][0]="<html>"+"����<br>1��2��";
				while(rs1.next()){
					int time=rs1.getInt("uTime");
					cData.obj2[time%10][time/10]="<html>"+rs1.getString("uCourNumb")+"<br>"+rs1.getString("uCourName")+"<br>��ѧ¥"+rs1.getString("uCourPlace");
				
						}
				server.sendDataToClient(cData);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
