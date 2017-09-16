package seu.edu.vo;

import javax.swing.ImageIcon;

public class Commodity
{
	private String cName;			//��Ʒ���	
	private double cPrice;			//��Ʒ�۸�
	private int cSellerID;			//������ID
	private String cTime;			//���۵Ǽ�ʱ��
	private String cDetail;			//��Ʒϸ������
	private ImageIcon[] icon;		//��ƷͼƬ
	
	//�ղι���
	public Commodity()		
	{
		super();
	}
	//������
	public Commodity(String cName, double cPrice, int cSellerID, String cTime, String cDetail, ImageIcon[] icon)
	{
		super();
		this.cName = cName;
		this.cPrice = cPrice;
		this.cSellerID = cSellerID;
		this.cTime = cTime;
		this.cDetail = cDetail;
		this.icon = icon;
	}
	public String getcName()
	{
		return cName;
	}
	public void setcName(String cName)
	{
		this.cName = cName;
	}
	public double getcPrice()
	{
		return cPrice;
	}
	public void setcPrice(double cPrice)
	{
		this.cPrice = cPrice;
	}
	public int getcSellerID()
	{
		return cSellerID;
	}
	public void setcSellerID(int cSellerID)
	{
		this.cSellerID = cSellerID;
	}
	public String getcTime()
	{
		return cTime;
	}
	public void setcTime(String cTime)
	{
		this.cTime = cTime;
	}
	public String getcDetail()
	{
		return cDetail;
	}
	public void setcDetail(String cDetail)
	{
		this.cDetail = cDetail;
	}
	public ImageIcon[] getIcon()
	{
		return icon;
	}
	public void setIcon(ImageIcon[] icon)
	{
		this.icon = icon;
	}	
}
