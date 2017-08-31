package cn.seu.edu.message;

public class MailData extends RequestData {

	private String mSenderID;
	private String mSenderName;
	private String mRecipientID;
	private String mRecipientName;
	private String mTime;
	private String mTopic;
	private String mContent;
	private boolean mRead;
	private int mID;

	public MailData(String request) {
		super(request);
	}

	public String getmSenderID() {
		return mSenderID;
	}

	public void setmSenderID(String mSenderID) {
		this.mSenderID = mSenderID;
	}

	public String getmSenderName() {
		return mSenderName;
	}

	public void setmSenderName(String mSenderName) {
		this.mSenderName = mSenderName;
	}

	public String getmRecipientID() {
		return mRecipientID;
	}

	public void setmRecipientID(String mRecipientID) {
		this.mRecipientID = mRecipientID;
	}

	public String getmRecipientName() {
		return mRecipientName;
	}

	public void setmRecipientName(String mRecipientName) {
		this.mRecipientName = mRecipientName;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public String getmTopic() {
		return mTopic;
	}

	public void setmTopic(String mTopic) {
		this.mTopic = mTopic;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	public boolean ismRead() {
		return mRead;
	}

	public void setmRead(boolean mRead) {
		this.mRead = mRead;
	}

	public int getmID() {
		return mID;
	}

	public void setmID(int mID) {
		this.mID = mID;
	}
	

}
