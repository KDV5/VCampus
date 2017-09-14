package seu.edu.server.dao.Library;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
/*	public ListMessage ListAllBooks(){
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
	*/
	
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
					pst.setString(1,"%"+lbm.getAuther()+"%");		
					break;
					}
				
				}
			ResultSet re =pst.executeQuery();
			//将搜到的图书存入数组，同时更新数据库中搜索次数
			sql= "update tblBooks set LendTimes = ? where BookID = ?";
			pst= db.conn.prepareStatement(sql);			
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			while(re.next()){		
				pst.setInt(1, re.getInt("LendTimes")+1);
				pst.setString(2, re.getString("BookID"));
				pst.executeUpdate();
				LibraryMessage b1=new LibraryMessage("SERACH_BY_KEYWORDS",re.getString("BookID"),re.getString("BookName"),re.getString("Author"),
						re.getString("Place"),re.getInt("TotalNumber"),re.getInt("Storage"),re.getString("Introduction"),re.getString("Publisher"),re.getString("Type"));
				
				//获取数据库中的图片信息
				File file = new File("Image\\BookImage\\"+re.getString("BookID")+".jpg");
				if(file.exists()){// 若该ID对应图片存在
					b1.setIcon(new ImageIcon("Image\\BookImage\\"+re.getString("BookID")+".jpg"));
				}else{// 若图片不存在
					b1.setIcon(null);
				}
				bookList.add(b1);				
			}
			return new ListMessage("Library","ListAllBooks",bookList);
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	//修改数据库中关键词热度
	/*
	 * @author yyl
	 * 9.12
	 */
	public void addHotWords(LibraryMessage lm){
		try{
			sql="select * from tblLibraryHotKeyWords where KeyWord =?";
			pst = db.conn.prepareStatement(sql);
			pst.setString(1, lm.getBookID());
			ResultSet rs=pst.executeQuery();
			//若该关键词已经数据库中，则修改其次数
			if(rs.next()){
				sql = "update tblLibraryHotKeyWords set SearchTimes = ? where KeyWord = ?";
				pst =db.conn.prepareStatement(sql);
				pst.setInt(1, rs.getInt("SearchTimes")+1);
				pst.setString(2, lm.getBookID());
				pst.executeUpdate();
			}
			//关键词不在数据库中
			else{
				sql= "insert into tblLibraryHotKeyWords(KeyWord,SearchTimes) values(?,?)";
				pst =db.conn.prepareStatement(sql);
				pst.setString(1, lm.getBookID());
				pst.setInt(2, 1);
				pst.executeUpdate();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 返回热度前五的关键词
	 * @author yyl
	 */
	public ListMessage TopFiveHotKeyword(){
		try{
			
			sql="SELECT TOP 5 * FROM tblLibraryHotKeyWords ORDER BY SearchTimes DESC";
			pst=db.conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			while(rs.next()){
				LibraryMessage b1=new LibraryMessage("GET_TOP5_KEYWORDS",rs.getString("KeyWord"));
				bookList.add(b1);	
			}
			return new ListMessage("Library","GET_TOP5_KEYWORDS",bookList);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//获取已借图书
	public ListMessage GetBorrowedBooks(String stuID){
		try{
			sql="SELECT * FROM tblLendBooks WHERE stuNumber LIKE  ?  ORDER BY LendDate ";
			pst = db.conn.prepareStatement(sql);
			pst.setString(1,stuID);	
			ResultSet rs = pst.executeQuery();
			ArrayList<BasicMessage> bookList =new ArrayList<BasicMessage>();
			while(rs.next()){		
				LibraryMessage b1=new LibraryMessage("GET_BORROWED",rs.getString("BookName"),rs.getString("BookID"),rs.getString("Author"),rs.getString("LendDate"));
				bookList.add(b1);				
			}
			return new ListMessage("Library","GET_BORROWED",bookList);
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		

	}
	
	
	
	

}

