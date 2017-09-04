package seu.edu.common.message;

public class LibraryMessage extends BasicMessage{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String mtype="Library";
	
	String bookID=null;
	String bookName=null;
	String auther=null;
	String place=null;
	int totalNumber=0;// 库存总量
	int storage=0;	// 当前余量
	String introduct=null;
	String publisher=null;
	
	public LibraryMessage(String rtype,String name) {
		super(mtype,rtype);	
		this.setBookName(name);
	}
	
	public LibraryMessage(String rtype,String bookID,String bookName,String auther,String place,int totalNumber,int storage,String introduct,String publisher ){
		super(mtype,rtype);
		this.setBookID(bookID);
		this.setBookName(bookName);
		this.setAuther(auther);
		this.setIntroduct(introduct);
		this.setPublisher(publisher);
		this.setStorage(storage);
		this.setTotalNumber(totalNumber);
	}
	
	public LibraryMessage(String rtype) {
		super(mtype,rtype);			
	}

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