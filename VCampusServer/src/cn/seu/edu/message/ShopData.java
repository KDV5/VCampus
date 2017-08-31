package cn.seu.edu.message;
import javax.swing.ImageIcon;

public class ShopData extends RequestData {

	private String uGoodsName;
	private int uStorage;
	private String uGoodsID;
	private String uPrice;
	private String uTime;
	private String userID;
	private String uDetail;
	private String uType;
	private String uSellerID;
	private String uRole;
	private ImageIcon icon;

	public ShopData(String request, String uGoodsName, int uStorage,
			String uGoodsID, String uPrice,String uTime,String userID) {
		super(request);
		this.uGoodsName = uGoodsName;
		this.uStorage = uStorage;
		this.uGoodsID = uGoodsID;
		this.uPrice = uPrice;
		this.uTime = uTime;
		this.setUserID(userID);
	}
	
	public String getuTime() {
		return uTime;
	}

	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	
	public ShopData(String request){
		super(request);
	}
	
	public String getuGoodsName() {
		return uGoodsName;
	}
	public void setuGoodsName(String uGoodsName) {
		this.uGoodsName = uGoodsName;
	}
	public int getuStorage() {
		return uStorage;
	}
	public void setuStorage(int uStorage) {
		this.uStorage = uStorage;
	}
	public String getuGoodsID() {
		return uGoodsID;
	}
	public void setuGoodsID(String uGoodsID) {
		this.uGoodsID = uGoodsID;
	}
	public String getuPrice() {
		return uPrice;
	}
	public void setuPrice(String uPrice) {
		this.uPrice = uPrice;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getuDetail() {
		return uDetail;
	}

	public void setuDetail(String uDetail) {
		this.uDetail = uDetail;
	}

	public String getuType() {
		return uType;
	}

	public void setuType(String uType) {
		this.uType = uType;
	}

	public String getuSellerID() {
		return uSellerID;
	}

	public void setuSellerID(String uSellerID) {
		this.uSellerID = uSellerID;
	}

	public String getuRole() {
		return uRole;
	}

	public void setuRole(String uRole) {
		this.uRole = uRole;
	}
	/*public void setIcon(Icon i)
	{
		icon=i;
	}
	public Icon getIcon(){
		return icon;
	}*/
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
}
