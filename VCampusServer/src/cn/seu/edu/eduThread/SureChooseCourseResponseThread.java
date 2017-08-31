package cn.seu.edu.eduThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class SureChooseCourseResponseThread {
	private ServerReaderThread server ;
	private CourseData courseData;
	private String stuNumb;
	private String cournum;
	
	public SureChooseCourseResponseThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.courseData = (CourseData)reqData;
		this.stuNumb = courseData.getStuId();
		this.cournum = courseData.getCourNum();
	}
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		int time = 0;
		try {
			PreparedStatement stin = conAccess.conn.prepareStatement("select * from tblCourse where uStuNumb= ? and uCourse=?");
			stin.setString(1,stuNumb);
			stin.setString(2,cournum);
			ResultSet rs=stin.executeQuery();
			//判断是否已经选过该课程
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "当前课程已经选过","提示对话框",1);
			}
			else{
				PreparedStatement stin1=conAccess.conn.prepareStatement("select * from tblCourMessage where uCourNumb=?");
				stin1.setString(1,cournum);
				ResultSet rs1=stin1.executeQuery();
				int people=0;
				if(rs1.next()){
					time=rs1.getInt("uTime");
					people=rs1.getInt("uTotal");
					}
				stin1.close();
				//判断该班级的人数是否已满
				if(people==30){
					JOptionPane.showMessageDialog(null, "当前班级已满","提示对话框",1);	
				}
				else{
					PreparedStatement stin2=conAccess.conn.prepareStatement("select * from tblCourse where uStuNumb= ? and uTime=?");
					stin2.setString(1,stuNumb);
					stin2.setInt(2,time);
					ResultSet rs2=stin2.executeQuery();
					//判断该时间段是否已经有别的课程
					if(rs2.next()){
						String evename=rs2.getString("uCourse");
						PreparedStatement stin3=conAccess.conn.prepareStatement("delete from tblCourse where uStuNumb= ? and uTime=?");
						stin3.setString(1,stuNumb);
						stin3.setInt(2,time);
						stin3.executeUpdate();
						stin3.close();
						
						PreparedStatement stin4=conAccess.conn.prepareStatement("update tblCourMessage set uTotal=uTotal-1 where uCourNumb=?");
						stin4.setString(1,evename);
						stin4.executeUpdate();
						stin4.close();
						
						PreparedStatement stin5=conAccess.conn.prepareStatement("insert into tblCourse (uStuNumb,uTime,uCourse) values(?,?,?)");
						stin5.setString(1,stuNumb);
						stin5.setInt(2, time);
						stin5.setString(3,cournum);
						stin5.executeUpdate();
						stin5.close();
						
						PreparedStatement stin6=conAccess.conn.prepareStatement("update tblCourMessage set uTotal=uTotal+1 where uCourNumb=?");
						stin6.setString(1,cournum);
						stin6.executeUpdate();
						stin6.close();
						
					}
					else{
						PreparedStatement stin7=conAccess.conn.prepareStatement("insert into tblCourse (uStuNumb,uTime,uCourse) values(?,?,?)");
						stin7.setString(1,stuNumb);
						stin7.setInt(2, time);
						stin7.setString(3,cournum);
						stin7.executeUpdate();
						stin7.close();
						
						PreparedStatement stin8=conAccess.conn.prepareStatement("update tblCourMessage set uTotal=uTotal+1 where uCourNumb=?");
						stin8.setString(1,cournum);
						stin8.executeUpdate();
						stin8.close();
					}
					
				}

			}
			stin.close();
			server.sendDataToClient(new RequestData("true"));
		} catch (SQLException e) {
			e.printStackTrace();
			server.sendDataToClient(new RequestData("false"));
		}
		
	}
}
