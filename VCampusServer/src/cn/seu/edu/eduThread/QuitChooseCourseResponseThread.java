package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class QuitChooseCourseResponseThread {
	private ServerReaderThread server ;
	private CourseData courseData;
	private String stuID;
	private String cournum;
	
	public QuitChooseCourseResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.courseData = (CourseData)reqData;
		this.stuID = courseData.getStuId();
		this.cournum = courseData.getCourNum();
	}
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			PreparedStatement stin1=conAccess.conn.prepareStatement("delete from tblCourse where uStuNumb= ? and uCourse=?");
			stin1.setString(1,stuID);
			stin1.setString(2,cournum);
			stin1.executeUpdate();
			PreparedStatement stin2=conAccess.conn.prepareStatement("update tblCourMessage set uTotal=uTotal-1 where uCourNumb=?");
			stin2.setString(1,cournum);
			stin2.executeUpdate();	
			server.sendDataToClient(new RequestData("true"));
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
		
	}
}
