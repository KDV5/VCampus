package cn.seu.edu.message;

import javax.swing.ImageIcon;

public class InfoData extends RequestData{
	
	private String name;
	private String tel;
	private String mail;
	private String root;
	private String dorm;
	private String address;
	private String number;
	private String stuNumber;
	private String school;
	private String major;
	private String mark;
	private String sex;
	private String age;
	private String title;
	private ImageIcon icon;
	
    public InfoData(String request) {
		super(request);
	
	}
	public void setNumber(String n)
	{
		number=n;
	}
	public String getNumber(){
		return number;
	}
	/*public void setIcon(Icon i)
	{
		icon=i;
	}
	public Icon getIcon(){
		return icon;
	}*/
	public void setMark(String m)
	{
		mark=m;
	}
	public String getMark(){
		return mark;
	}
	public void setSex(String s)
	{
		sex=s;
	}
	public String getSex(){
		return sex;
	}
	public void setStuNumber(String s)
	{
		stuNumber=s;
	}
	public String getStuNumber(){
		return stuNumber;
	}
	public void setTitle(String t)
	{
		title=t;
	}
	public String getTitle(){
		return title;
	}
	public void setSchool(String s)
	{
		school=s;
	}
	public String getSchool(){
		return school;
	}
	public void setMajor(String m)
	{
		major=m;
	}
	public String getMajor(){
		return major;
	}
	public void setAge(String a)
	{
		age=a;
	}
	public String getAge(){
		return age;
	}
	public void setName(String n)
	{
		name=n;
	}
	public String getName(){
		return name;
	}
	public void setTel(String t)
	{
		tel=t;
	}
	public String getTel(){
		return tel;
	}
	public void setMail(String m)
	{
		mail=m;
	}
	public String getMail(){
		return mail;
	}
	public void setRoot(String r)
	{
		root=r;
	}
	public String getRoot(){
		return root;
	}
	public void setDorm(String d)
	{
		dorm=d;
	}
	public String getDorm(){
		return dorm;
	}
	public void setAddress(String a)
	{
		address=a;
	}
	public String getAddress(){
		return address;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

}
