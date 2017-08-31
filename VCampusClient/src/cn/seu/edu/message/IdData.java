package cn.seu.edu.message;

public class IdData extends RequestData {
	private String id = null;

	public IdData(String request, String id) {
		super(request);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
