package seu.edu.vo;
/**
 * 
 * @author LHK
 *
 */
public class User {
	
	
	String userID;
	String userName;
	String password;
	int identity;	// 0:学生 1：教师
	
	public User(String uID, String userName, String password, int identity) {
		
		// TODO Auto-generated constructor stub
		this.identity = identity;
		this.password = password;
		this.userName = userName;
		this.userID = uID;
	}
	
	public User(){}
	
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
