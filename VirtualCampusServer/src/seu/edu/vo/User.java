package seu.edu.vo;

public class User {
	String userID;
	String userName;
	String password;
	int identity;			// 0:学生 1：教师
	
	/**
	 * 构造函数
	 * @param uID
	 * @param userName
	 * @param password
	 * @param identity
	 */
	public User(String uID, String password, int identity) {
		
		// TODO Auto-generated constructor stub
		this.userID = uID;
		this.identity = identity;
		this.password = password;
		
	}
	
	public User(){
		
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIdentity() {
		return identity;
	}
	
	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
