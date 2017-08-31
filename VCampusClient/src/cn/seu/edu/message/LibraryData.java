package cn.seu.edu.message;

public class LibraryData extends RequestData{
	private String id = null;
	private String bID = null;
	private String bName = null;
	private String bAuthor = null;
	private String bPlace = null;
	private int bStorage = 0;
	private int bTotal = 0;
	private String intro=null;
	private int bNumber = 0;
	private int bFlag= 0;
	private int bLendNumber = 0;
	private String bLendDate = null;
	private String bReturnDate = null;
	private String type=null;
	private String search=null;
	public Object[][] obj = new Object[50][9];
	public Object[][] perObj = new Object[50][7];
	public boolean ifLend=true;
	public LibraryData(String request, String id, String type,String search) {
		super(request);
		this.setID(id);
		this.setType(type);
		this.setSearch(search);
		}
	public LibraryData(String request, String id,int number) {
		super(request);
		this.setbID(id);
		this.setbStorage(number);
		}
	public LibraryData(String request, String id) {
		super(request);
		this.setID(id);

	}
	public LibraryData(String request, String type,String search) {
		super(request);
		this.setType(type);
		this.setSearch(search);

	}
	public LibraryData(String request, String id, String bID,String bName, String bAuthor,String bPlacce,
			int bStorage, String bLendDate,String bReturnDate) {
		super(request);
		this.setID(id);
		this.setbID(bID);
		this.setbName(bName);
		this.setbAuthor(bAuthor);
		this.setbStorage(bStorage);
		this.setbLendDate(bLendDate);
		this.setbReturnDate(bReturnDate);
	}
	
	public void setbStorage(int bStorage) {
		this.bStorage=bStorage;
		
	}
	public int getStorage() {
		return bStorage;
		
	}
	public void setbTotal(int bTotal) {
		this.bTotal=bTotal;
		
	}
	public int getTotal() {
		return bTotal;
		
	}
	public void setbNumber(int bNumber) {
		this.bNumber=bNumber;
		
	}
	public int getbNumber() {
		return bNumber;
		
	}
	public int getbFlag() {
		return bFlag;
		
	}
	public void setbFlag(int bFlag) {
		this.bFlag=bFlag;
		
	}
	
	public void setbLendNumber(int bLendNumber) {
		this.bLendNumber=bLendNumber;
		
	}
	public int getbLendNumber() {
		return bLendNumber;
		
	}
	public String getPlace() {
		return bPlace;
		
	}
	public void setbPlace(String bPlace) {
		this.bPlace=bPlace;
		
	}

	public void setType(String type) {
		this.type=type;
		
	}
	public String getType() {
		return type;
		
	}
	public void setbID(String id) {
		this.bID=id;
		
	}
	public String getbID() {
		return bID;
		
	}
	public void setSearch(String search) {
		this.search=search;
		
	}
	public String getSearch() {
		return search;
		
	}


	public void setbReturnDate(String bReturnDate) {
		this.bReturnDate=bReturnDate;
		
	}
	public String getbReturnDate() {
		return bReturnDate;
		
	}
	public String getbLendDate() {
		return bLendDate;
		
	}
	public void setbLendDate(String bLendDate) {
		this.bLendDate=bLendDate;
		
	}
	public LibraryData(String request) {
		super(request);
	}
	
	public LibraryData(String request, String bookID, String book, String author, int number, String place) {
		super(request);
		this.setbID(bookID);
		this.setbName(book);
		this.setbAuthor(author);
		this.setbNumber(number);
		this.setbPlace(place);	
	}
	public LibraryData(String  request, String bookID, String book, String author, int number,int flag) {
		super(request);
		this.setbID(bookID);
		this.setbName(book);
		this.setbAuthor(author);
		this.setbNumber(number);
		this.setbFlag(flag);
	}
	public LibraryData(String request, String bookID, String book, String author, int number, String place,
			String intro) {
		super(request);
		this.setbID(bookID);
		this.setbName(book);
		this.setbAuthor(author);
		this.setbNumber(number);
		this.setbPlace(place);	
		this.setbIntro(intro);
		
	}
	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getbName() {
		return bName;
	}

	public void setbIntro(String bIntro) {
		this.intro= bIntro;
	}
	public String getbIntro() {
		return intro;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbAuthor() {
		return bAuthor;
	}

	public void setbAuthor(String bAuthor) {
		this.bAuthor = bAuthor;
	}

	public String getbPlace() {
		return bPlace;
	}

	public void setbPassword(String bPlace) {
		this.bPlace = bPlace;
	}

	public int getbStorage() {
		return bStorage;
	}


	

}