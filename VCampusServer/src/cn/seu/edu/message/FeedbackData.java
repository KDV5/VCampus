package cn.seu.edu.message;

public class FeedbackData extends RequestData {
	
	private String id = null;
	private String fType = null;
	private String fProblem = null;
	private String fPhone = null;
	private String fTime = null;
	private String fResult = null;
	private int fID;
	
	public FeedbackData(String request) {
		super(request);
	}
	
	public FeedbackData(String request, String id, String fType,
			String fProblem, String fPhone, String fTime) {
		super(request);
		this.id = id;
		this.fType = fType;
		this.fProblem = fProblem;
		this.fPhone = fPhone;
		this.fTime = fTime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public String getfProblem() {
		return fProblem;
	}
	public void setfProblem(String fProblem) {
		this.fProblem = fProblem;
	}
	public String getfPhone() {
		return fPhone;
	}
	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}
	public String getfTime() {
		return fTime;
	}
	public void setfTime(String fTime) {
		this.fTime = fTime;
	}

	public String getfResult() {
		return fResult;
	}

	public void setfResult(String fResult) {
		this.fResult = fResult;
	}

	public int getfID() {
		return fID;
	}

	public void setfID(int fID) {
		this.fID = fID;
	}
	

}
