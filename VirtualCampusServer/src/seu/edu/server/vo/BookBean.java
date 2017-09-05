package seu.edu.server.vo;

import java.io.Serializable;

//书籍实体类
/* 因英伦 9、1 */
public class BookBean implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		String bookID=null;
		String bookName=null;
		String auther=null;
		String place=null;
		int totalNumber=0;// 库存总量
		int storage=0;	// 当前余量
		String introduct=null;
		String publisher=null;
		
		public BookBean(String ID,String name,String auther,String place,String introduct,
				String publisher,int totalNumber,int storage){
			
			System.out.println("Book构造开始");
			setBookID(ID);
			setBookName(name);
			setIntroduct(introduct);
			setAuther(auther);
			setPlace(place);
			setPublisher(publisher);
			setStorage(storage);
			setTotalNumber(totalNumber);
			System.out.println("Book构造结束");
			
		}
		
		public BookBean() {
			// TODO 自动生成的构造函数存根
		}
		
		
		
		/**
		 * 
		 * @return String
		 */
		public String getBookID() {
			return bookID;
		}
		
		
		public void setBookID(String bookID) {
			this.bookID = bookID;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getAuther() {
			return auther;
		}
		public void setAuther(String auther) {
			this.auther = auther;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		public int getTotalNumber() {
			return totalNumber;
		}
		public void setTotalNumber(int totalNumber) {
			this.totalNumber = totalNumber;
		}
		public int getStorage() {
			return storage;
		}
		public void setStorage(int storage) {
			this.storage = storage;
		}
		public String getIntroduct() {
			return introduct;
		}
		public void setIntroduct(String introduct) {
			this.introduct = introduct;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
			
		

}