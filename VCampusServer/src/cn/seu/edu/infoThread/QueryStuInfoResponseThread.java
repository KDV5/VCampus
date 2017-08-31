package cn.seu.edu.infoThread;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class QueryStuInfoResponseThread {

	ServerReaderThread server;
	InfoData infor;
	String name;
	String stuNumber;
	String school;
	String major;
	String root;
	
	public QueryStuInfoResponseThread(ServerReaderThread server,RequestData reqData){
		this.server = server;
		this.infor = (InfoData)reqData;
		this.name =infor.getName();
		this.stuNumber =infor.getStuNumber();
		this.school =infor.getSchool();
		this.major =infor.getMajor();
		this.root =infor.getRoot();
	}
	
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		try {
			StringBuilder sql=new StringBuilder("select * from tblStu where 1=1");
			if(!stuNumber.isEmpty()){
				sql.append(" and uStuNumber like '%"+stuNumber+"%'");
			}
			if(!name.isEmpty()){
				sql.append(" and uName like '%"+name+"%'");
			}
			if(!school.isEmpty()){
				sql.append(" and uSchool like '%"+school+"%'");
			}
			if(!major.isEmpty()){
				sql.append(" and uMajor like '%"+major+"%'");
			}
			if(!root.isEmpty()){
				sql.append(" and uRoot like '%"+root+"%'");
			}
			sql.append(";");
			conAccess.pstmt = conAccess.conn.prepareStatement(sql.toString());
			ResultSet rs = conAccess.pstmt.executeQuery();
			ObjListData listData = new ObjListData("");
			while(rs.next()){
				InfoData tInfor=new InfoData("");
				tInfor.setStuNumber(rs.getString("uStuNumber"));
				tInfor.setName(rs.getString("uName"));
				tInfor.setSchool(rs.getString("uSchool"));
				tInfor.setMajor(rs.getString("uMajor"));
				tInfor.setDorm(rs.getString("uDorm"));
				tInfor.setAge(rs.getString("uAge"));
				tInfor.setRoot(rs.getString("uRoot"));
				tInfor.setTel(rs.getString("uTel"));
				tInfor.setMail(rs.getString("uMail"));
				tInfor.setAddress(rs.getString("uAddress"));
				listData.getInfoList().add(tInfor);
				}
			server.sendDataToClient(listData);
	    }catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
}
