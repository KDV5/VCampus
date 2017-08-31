package cn.seu.edu.message;


public class LoginData extends RequestData{

	private String userName = null;
    private String userPassword = null;
    private String newPassword ;
 
    
  

	public LoginData(String request, String userName, String userPassword) {
		super(request);
		this.setUserName(userName);
		this.setUserPassword(userPassword);
	}
	
	public LoginData(String request) {
		super(request);
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
