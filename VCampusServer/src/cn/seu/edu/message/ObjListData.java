package cn.seu.edu.message;


import java.util.ArrayList;
import java.util.List;

public class ObjListData extends RequestData{

	private List<InfoData> infoList = new ArrayList<InfoData>();
	private List<ShopData> shopList = new ArrayList<ShopData>();
	private List<MailData> mailList = new ArrayList<MailData>();
	private List<FeedbackData> FeedbackList = new ArrayList<FeedbackData>();
	private List<LibraryData> LibraryList = new ArrayList<LibraryData>();
	
	public ObjListData(String request) {
		super(request);
	}

	public List<InfoData> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<InfoData> infoList) {
		this.infoList = infoList;
	}

	public List<ShopData> getShopList() {
		return shopList;
	}

	public void setShopList(List<ShopData> shopList) {
		this.shopList = shopList;
	}

	public List<MailData> getMailList() {
		return mailList;
	}

	public void setMailList(List<MailData> mailList) {
		this.mailList = mailList;
	}

	public List<FeedbackData> getFeedbackList() {
		return FeedbackList;
	}

	public void setFeedbackList(List<FeedbackData> feedbackList) {
		FeedbackList = feedbackList;
	}

	public List<LibraryData> getLibraryList() {
		return LibraryList;
	}

	public void setLibraryList(List<LibraryData> libraryList) {
		LibraryList = libraryList;
	}
	
}
