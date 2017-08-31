package cn.seu.edu.message;

public class CourseData extends RequestData {
	
	public Object[][] obj = new Object[16][5];
	private int day;
	private String stuId;
	private String courNum;
	public Object[][] obj1=new Object[5][5];
	public Object[][] obj2 = new Object[6][6];
	public String[] tchCourse = new String[20];
	private String courseName;
	public Object[][] obj3 = new Object[30][3]; 
	private int sGrade;
	
	public CourseData(String request) {
		super(request);
	
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getCourNum() {
		return courNum;
	}

	public void setCourNum(String courNum) {
		this.courNum = courNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getsGrade() {
		return sGrade;
	}

	public void setsGrade(int sGrade) {
		this.sGrade = sGrade;
	}

	

}
