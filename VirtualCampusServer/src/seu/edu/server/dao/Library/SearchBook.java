package seu.edu.server.dao.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seu.edu.common.DBHelper;
import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;
/*
 * @auther yinyinglun
 * @date 9.6
 */
public class SearchBook {
	DBHelper db=new DBHelper();
	LibraryDAO ld=new LibraryDAO();
	PreparedStatement pst;
	String sql;
	
	//列出所有书籍
	public ListMessage ListAllBooks(){
		//System.out.println("进入ListAllBooks函数");
		ResultSet re=ld.searchBook();
		try{
			System.out.println("请求内容 ：列出全部图书");			
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			System.out.println("进入while循环");
			while(re.next()){				

				LibraryMessage b1=new LibraryMessage("ListAllBooks",re.getString("BookID"),re.getString("BookName"),re.getString("Author"),
						re.getString("Place"),re.getInt("TotalNumber"),re.getInt("Storage"),re.getString("Introduction"),re.getString("Publisher"),re.getString("Type"),re.getInt("LendTimes"));
				
				bookList.add(b1);				
			}
			
			return new ListMessage("Library","ListAllBooks",bookList);
			
		}catch(Exception e){
			return null;
		}			
	}
	
	public ListMessage SearchByKeyWords(LibraryMessage lbm){
		//Connection conn=getConnection();
		try{
			switch(lbm.getKeyWordsType()){
				case "BOOK_ID":{
					sql="SELECT * FROM tblBooks WHERE BookID LIKE  ?  ORDER BY REPLACE(BookID,'"+lbm.getBookID()+"','')";
					pst = db.conn.prepareStatement(sql);
					pst.setString(1,"%"+lbm.getBookID()+"%");	
					break;
					}
				case "BOOK_NAME":{
					//sql="SELECT * FROM tblBooks WHERE namess LIKE  ?  ORDER BY REPLACE(namess,'"+lbm.getBookName()+"','')";
					sql="SELECT * FROM tblBooks WHERE BookName LIKE  ?  ORDER BY REPLACE(BookName,?,'')";
					pst = db.conn.prepareStatement(sql);
					pst.setString(1,"%"+lbm.getBookName()+"%");		
					pst.setString(2,lbm.getBookName());		
					break;
					}
				case "AUTHOR":{
					sql="SELECT * FROM tblBooks WHERE Author LIKE  ?  ORDER BY REPLACE(Author,'"+lbm.getAuther()+"','')";
					pst = db.conn.prepareStatement(sql);
					pst.setString(1,"%"+lbm.getBookName()+"%");		
					break;
					}	
				}
			ResultSet re =pst.executeQuery();
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			while(re.next()){				

				LibraryMessage b1=new LibraryMessage("ListAllBooks",re.getString("BookID"),re.getString("BookName"),re.getString("Author"),
						re.getString("Place"),re.getInt("TotalNumber"),re.getInt("Storage"),re.getString("Introduction"),re.getString("Publisher"),re.getString("Type"),re.getInt("LendTimes"));
				
				bookList.add(b1);				
			}
			return new ListMessage("Library","ListAllBooks",bookList);
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}

}

