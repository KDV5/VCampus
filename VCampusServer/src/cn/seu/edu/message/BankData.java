package cn.seu.edu.message;

public class BankData extends RequestData {

	private String id = null;
	private String bName = null;
	private String bNumber = null;
	private String bBalance = null;
	private String bPassword = null;
	private String cardNumber = null;
	private String cardPassword = null;
	private String cardBalance = null;
	
	
	public BankData(String request, String id, String bName, String bNumber,String bBalance,
			String bPassword, String cardNumber,String cardPassword,String cardBalance) {
		super(request);
		this.setId(id);
		this.setbName(bName);
		this.setbNumber(bNumber);
		this.setbPassword(bPassword);
		this.setbBalance(bBalance);
		this.setCardNumber(cardNumber);
		this.setCardPassword(cardPassword);
		this.setCardBalance(cardBalance);
	}
	
	public BankData(String request) {
		super(request);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbNumber() {
		return bNumber;
	}

	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}

	public String getbPassword() {
		return bPassword;
	}

	public void setbPassword(String bPassword) {
		this.bPassword = bPassword;
	}

	public String getbBalance() {
		return bBalance;
	}

	public void setbBalance(String balance) {
		this.bBalance = balance;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	
}
