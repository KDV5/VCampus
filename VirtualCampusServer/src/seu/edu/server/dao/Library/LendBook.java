package seu.edu.server.dao.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seu.edu.common.DBHelper;
import seu.edu.common.message.LibraryMessage;



public class LendBook {
	public LibraryMessage lendBook(LibraryMessage libMessage){
		DBHelper db=new DBHelper();
		PreparedStatement pst;
		db.getConnection();
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			String sql = "select * from tblLendBooks  where stuNumber = ?";
			pst = db.conn.prepareStatement(sql);
			pst.setString(1, libMessage.getStuNumber());
			ResultSet rs=pst.executeQuery();
			   
			int counter=0;
			while(rs.next()){   
					  counter++; 
					  }   
				  if(counter>=10)//最多只能10本书
				  {
					  return new LibraryMessage("LEND_BOOKS_FULL");//借书已满
				  }
				  else{
				sql = "select *from tblLendBooks  where stuNumber=? and BookID=?";
				pst = db.conn.prepareStatement(sql);
				pst.setString(1, libMessage.getStuNumber());
				pst.setString(2, libMessage.getBookID());
				rs=pst.executeQuery();
				int  tmpCounter=0;   
				  while(rs.next()){//判断是否结果这本书
					  tmpCounter++;
					  }   
				if(tmpCounter==0){		    
				    //可借数量减1
					sql = "update tblBooks set Storage=(Storage-1) where BookID=?";
					pst = db.conn.prepareStatement(sql);
					pst.setString(1, libMessage.getBookID());
				    pst.executeUpdate();
				    //借阅次数加一
				    sql = "update tblBooks set LendTimes=(LendTimes+1) where BookID=?";
					pst = db.conn.prepareStatement(sql);
					pst.setString(1, libMessage.getBookID());
				    pst.executeUpdate();
				    String lendDate= dateFormat.format(new Date());
				    String returnDate=getPreMonth(lendDate);
				    //借阅记录存入借书表
					sql="insert into tblLendBooks (BookName,BookID,stuNumber,stuName,LendDate,Return) values(?,?,?,?,?,?)";
				    pst = db.conn.prepareStatement(sql);
				    pst.setString(1, libMessage.getBookName());
				    pst.setString(2, libMessage.getBookID());
				    pst.setString(3, libMessage.getStuName());
				    pst.setString(4, libMessage.getStuNumber());
				    pst.setString(5, returnDate);
				    pst.setBoolean(6, false);
				    pst.executeUpdate();
				    return new LibraryMessage("LEND_BOOK_SUCCEED");//借书成功
				    
				    }
				else
					return new LibraryMessage("LEND_BOOK_TWICE");//结束重复
				}		    

		}catch(SQLException e){
			e.printStackTrace();			
			return new LibraryMessage("DELETE_BOOK_FAILED");
			
			}
	}

	//获得系统日期
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
