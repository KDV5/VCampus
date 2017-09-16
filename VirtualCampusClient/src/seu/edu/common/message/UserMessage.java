package seu.edu.common.message;

import seu.edu.vo.User;

/**
 * 
 * @author Haokai Li
 *
 */
public class UserMessage extends BasicMessage {

	public UserMessage(String rtype) {
		super(mType, rtype);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	private static final String mType = "User";
	
	private String userName = null;
	private String password = null;
	private String newPwd = null;
	private String userID = null;
	private int identity = -1;
	private int matchOrNot = -1;		// 不存在该用户返回0，密码错误返回1，成功返回2
	private int regSucc = -1;			// 注册成功返回1，已有该用户名返回0，成功返回1，学籍中不存在返回2
	private int resetSucc = -1;			// 修改密码成功返回1，原密码错误返回0

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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getIdentiy() {
		return identity;
	}

	public void setIdentiy(int identity) {
		this.identity = identity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getMatchOrNot() {
		return matchOrNot;
	}

	public void setMatchOrNot(int matchOrNot) {
		this.matchOrNot = matchOrNot;
	}

	public int getRegSucc() {
		return regSucc;
	}

	public void setRegSucc(int regSucc) {
		this.regSucc = regSucc;
	}

	public User setUser(){
		User user = new User(this.userID, this.userName, this.password ,this.identity);
		return user;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public int getResetSucc() {
		return resetSucc;
	}

	public void setResetSucc(int resetSucc) {
		this.resetSucc = resetSucc;
	}
	
}