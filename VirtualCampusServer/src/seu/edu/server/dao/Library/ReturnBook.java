package seu.edu.server.dao.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seu.edu.common.DBHelper;
import seu.edu.common.message.LibraryMessage;



public class ReturnBook {
	public LibraryMessage returnBook(LibraryMessage libMessage){
		DBHelper db=new DBHelper();
		PreparedStatement pst;
		db.getConnection();
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		    String sql = "update tblBooks set Storage=(Storage+1) where BookID=?";
		    pst = db.conn.prepareStatement(sql);
		    pst.setString(1, libMessage.getBookID());
		    pst.executeUpdate();
		    
		    String lendDate= dateFormat.format(new Date());
		    String returnDate=getPreMonth(lendDate);
		    sql="insert into tblLendBooks (ReturnDate,Return) values(?,?)";
		    pst = db.conn.prepareStatement(sql);
		    pst.setString(1, returnDate);
		    pst.setBoolean(2, true);
		    pst.executeUpdate();
		    
		    return new LibraryMessage("RETURN_BOOK_SUCCEED");//借书成功
    

		}catch(SQLException e){
			e.printStackTrace();			
			return new LibraryMessage("RETURN_BOOK_FAILED");
			
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
