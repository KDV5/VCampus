package cn.seu.edu.LibraryThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

public class LendThread  {
	private ServerReaderThread server ;
	private LibraryData lData;
	private String userID=null;
	private String bookID=null;
	Date date = new Date();
 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	public LendThread(ServerReaderThread server, RequestData reqData) {
		this.server = server;
		this.lData = (LibraryData)reqData;
		this.bookID=lData.getType();
		this.userID = lData.getSearch();
	}
	public void run(){
		ConnectAccess conAccess = new ConnectAccess();
		String sql=null;
	 try {
		   sql = "select *from tblBookUsers  where uID=?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			ResultSet rs=conAccess.pstmt.executeQuery();
			int counter=0;
			  while(rs.next())   
			  {   	  counter++;   	  }   
			  if(counter>=8)
			  {
				  server.sendDataToClient(new RequestData("out"));
			  }
			  else{
			sql = "select *from tblBookUsers  where uID=? and uBookID=?";
			conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			conAccess.pstmt.setString(1,userID);
			conAccess.pstmt.setString(2,bookID);
		    rs=conAccess.pstmt.executeQuery();
			int   tmpCounter=0;   
			  while(rs.next())   
			  {   			  tmpCounter++;   			  }   
			if(tmpCounter==0){
				sql = "update tblLibraryBooks set uStorage=(uStorage-1) where uBookID=?";
			    conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			    conAccess.pstmt.setString(1,bookID);
			    conAccess.pstmt.executeUpdate();
			    sql = "update tblLibraryBooks set uLendNumber=(uLendNumber+1) where uBookID=?";
			    conAccess.pstmt = conAccess.conn.prepareStatement(sql);
			    conAccess.pstmt.setString(1,bookID);
			    conAccess.pstmt.executeUpdate();
			    String lendDate= dateFormat.format(new Date());
			    String returnDate=getPreMonth(lendDate);
 		        sql="insert into tblBookUsers (uID,uBookID,uLendDate,uReturnDate) values(?,?,?,?)";
		        conAccess.pstmt =conAccess.conn.prepareStatement(sql); 
		        conAccess.pstmt.setString(1,userID);
		        conAccess.pstmt.setString(2,bookID);
      	        conAccess.pstmt.setString(3,lendDate);
     	        conAccess.pstmt.setString(4,returnDate);
 		     	conAccess.pstmt.executeUpdate(); 
 			server.sendDataToClient(new RequestData("true"));
			}
			else
				server.sendDataToClient(new RequestData("false"));		}	
 	}catch (SQLException e) {
 			e.printStackTrace();
 		}		
	}
	public static String getPreMonth(String repeatDate) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        Date date = null;   
        try{   
            date = sdf.parse(repeatDate);//初始日期   
        }catch(Exception e){  
        }   
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,1);
        String strDate = sdf.format(c.getTime());   
        return strDate;
}
}
