package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class UpdateStuGradeResponseThread {
	ServerReaderThread server;
	CourseData cData;
	int sGrade;
	String courseNum;
	String stuID;
	public UpdateStuGradeResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.cData = (CourseData)reqData;
		this.sGrade = cData.getsGrade();
		this.courseNum = cData.getCourNum();
		this.stuID = cData.getStuId();
	}

	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			PreparedStatement stin=conAccess.conn.prepareStatement("update tblCourse set uGrade= ? where uCourse= ? and  uStuNumb= ? ");
			stin.setInt(1,sGrade);
			stin.setString(2,courseNum);
			stin.setString(3,stuID);
			stin.executeUpdate();
			stin.close();
			server.sendDataToClient(new RequestData("true"));
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
	}
}
