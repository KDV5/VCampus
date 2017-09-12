package seu.edu.common.message;

public class LibraryMessage extends BasicMessage{
	
	
	/**
	 * @author yyl
	 * 用来在客户端与服务器端交换信息
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
	String type=null;
	int LendTimes=0;
	
	String stuNumber=null;
	String stuName=null;
	String lendDate=null;
	
	String keyWordsType=null;
	String operResult; // 表示操作成功或失败
	
	
	
	/*
	 * @data 9.10
	 * 向服务器传递搜索信息及关键字 
	 */
	public LibraryMessage(String rtype,String keyWordsType,String keyWord) {
		super(mtype,rtype);	
		this.setKeyWordsType(keyWordsType);
		switch(this.getKeyWordsType()){
		case "BOOK_NAME" : this.setBookName(keyWord);break;
		case "AUTHOR" : this.setAuther(keyWord);
		case "BOOK_ID" : this.setBookID(keyWord);
		}
	}
	
	/*
	 * 添加图书信息
	 */
	public LibraryMessage(String rtype,String bookID,String bookName,String auther,String place,int totalNumber,int storage,String introduction,String publisher,String type){
		super(mtype,rtype);
		this.setBookID(bookID);
		this.setBookName(bookName);
		this.setAuther(auther);
		this.setPlace(place);
		this.setIntroduct(introduction);
		this.setPublisher(publisher);
		this.setStorage(storage);
		this.setTotalNumber(totalNumber);
		this.setType(type);
		this.setLendTimes(0);
	}
	
	/*
	 * 删除图书信息
	 */
	public LibraryMessage(String rtype,String BookID){
		super(mtype,rtype);
		this.setBookID(BookID);
	}
	
	/*
	 * 借书信息
	 */
	public LibraryMessage(String rtype,String BookName,String BookID,int Storage,String stuNumber,String stuName,String LendDate){
		super(mtype,rtype);
		this.setBookName(BookName);
		this.setBookID(BookID);
		this.setStuName(stuName);
		this.setStuNumber(stuNumber);
		this.setLendDate(LendDate);
		this.setStorage(Storage);
	}
	
	public LibraryMessage(String result){
		super(mtype,"");
		this.setOperResult(result);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyWordsType() {
		return keyWordsType;
	}

	public void setKeyWordsType(String keyWordsType) {
		this.keyWordsType = keyWordsType;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}


	public int getLendTimes() {
		return LendTimes;
	}

	public void setLendTimes(int lendTimes) {
		LendTimes = lendTimes;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getLendDate() {
		return lendDate;
	}

	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}
	
}