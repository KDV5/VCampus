package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class QueryTchCourseGradeResponseThread{
	private ServerReaderThread server ;
	private CourseData cData;
	private String courseNum;
	
	public QueryTchCourseGradeResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.cData = (CourseData)reqData;
		this.courseNum = cData.getCourNum();
	}
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			int c=0;
			PreparedStatement stin2=conAccess.conn.prepareStatement("select*from tblCourse where uCourse=? order by uStuNumb asc");
			stin2.setString(1,courseNum);
			ResultSet rs2=stin2.executeQuery();
			CourseData courData = new CourseData("");
			while(rs2.next()){
				String stuNumb=rs2.getString("uStuNumb");
				System.out.println(stuNumb);
				PreparedStatement stin3=conAccess.conn.prepareStatement("select*from tblStu where uStuNumber=? ");
				stin3.setString(1,stuNumb);
				ResultSet rs3=stin3.executeQuery();
				courData.obj3[c][0]=stuNumb;
				if(rs3.next()){
					courData.obj3[c][1]=rs3.getString("uName");
				}
				courData.obj3[c][2]=rs2.getShort("uGrade");	
				c++;
				}
			server.sendDataToClient(courData);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
