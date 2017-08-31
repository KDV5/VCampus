package cn.seu.edu.LibraryThread;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.seu.edu.access.ConnectAccess;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.Server;
import cn.seu.edu.server.ServerReaderThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;  
	import java.util.Date;  
	import java.util.Timer;  
	public class Taskthread  {  
		private ServerReaderThread server ;
		private IdData idData;
		private String userID;
		
		public Taskthread(ServerReaderThread server, RequestData reqData) {
			this.server = server;
			this.idData = (IdData)reqData;
			this.userID = idData.getId();
		}
	            public void run() {  	 
                        StringBuffer s1= new StringBuffer("您所借的图书中");
                        StringBuffer s2=new StringBuffer("您所借的图书中");
                        StringBuffer s3=new StringBuffer("您所借的图书中");
                        int p1=0;
                        int p2=0;
                        int p3=0;
                        MailData mData = new MailData("sendMail");
                        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
	   	            	ConnectAccess conAccess = new ConnectAccess();
	     	        		try {
	     	        			Date date =new Date();
	     	        			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
		   	        			SimpleDateFormat dsf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   	        			String now=df.format(date); 
		   	        			String nowtime=dsf.format(date);
	     	        		String sql = "select * from tblMail where mRecipientID=? and mTime like ? and mTopic=?";
	     	        		conAccess.pstmt = conAccess.conn.prepareStatement(sql);
   							conAccess.pstmt .setString(1, userID);
   							conAccess.pstmt .setString(2, "%"+now+"%");
   							conAccess.pstmt .setString(3, "还书提醒");	
   							ResultSet rs = conAccess.pstmt.executeQuery();
   							if(!rs.next()){
	   	        			sql = "select * from tblBookUsers where uID=?";
	   							conAccess.pstmt = conAccess.conn.prepareStatement(sql);
	   							conAccess.pstmt .setString(1, userID);
	   	        	        rs = conAccess.pstmt.executeQuery();
	   	        			while(rs.next()){
	   	        					java.util.Date date1= myFormatter.parse(rs.getString("uReturnDate")); 
	   	        					java.util.Date mydate= myFormatter.parse(now); 
	   	        					long day=(date1.getTime()-mydate.getTime())/(24*60*60*1000); 
	   	        					if (day<0){
//	   	        						s1.append("《"+rs.getString("uBookID")+"》");
	   	        						p1++;
	   	        					}
	   	        					if(day==0){
	//   	        						s2.append("《"+rs.getString("uBookID")+"》");
	   	        						p2++;
	   	        					}
	   	        					if(day==1){
	//   	        						s3.append("《"+rs.getString("uBookID")+"》");
	   	        						p3++;
	   	        					}
	   	                      }
	   	        			s1.append("共"+p1+"本书超期未还");
	   	        			s2.append("共"+p2+"本书到期");
	   	        			s3.append("共"+p3+"本书还有1日到期");
	   	        			if(p1>0 || p2>0 || p3>0){
	   	        				mData.setmRecipientID(userID);
	   	        				mData.setmSenderID("140001");
	   	        				mData.setmTopic("还书提醒");
	   	        				mData.setmTime(nowtime);
	   	        				
	   	        			    StringBuffer s4 = new StringBuffer("您好，\n");
	   	        			    if(p1>0){
	   	        			    	s4.append(s1+"\n请尽快归还");
	   	        			    }
	   	        			    if(p2>0){
	   	        			    	s4.append(s2+"\n请尽快归还");
	   	        			    }
	   	        			    if(p3>0){
	   	        			    	s4.append(s3+"\n请注意还书时间");
	   	        			    }	   	        			   
	   	        			    mData.setmContent(s4.toString());
	   	        			    server.sendDataToClient(mData);
	   	        			}else{
	   	        				server.sendDataToClient(new RequestData("no"));
	    	        		} }
   							else
   								server.sendDataToClient(new RequestData("false"));
   								}catch (SQLException | ParseException e) {
	   						// TODO 自动生成的 catch 块
	   						e.printStackTrace();
	   						server.sendDataToClient(new RequestData("false"));
	   					}   }
	   						
	   						
	             
	        }
	  
	
