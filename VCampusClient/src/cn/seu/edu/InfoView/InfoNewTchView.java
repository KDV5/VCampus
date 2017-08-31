/*
 * @(#)InfoNewTchView.java        2016/09/16
 *
 * Copyright (c) 2016 SEU CSE. All rights reserved.
 * SEU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.seu.edu.InfoView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.RequestData;

/**
 * This class creates a frame, which shows some teacher's existent information,
 * and is used to complete teacher's information by herself or himself. 
 * Information includes id, name, age, telephone, e-mail, school, major,
 * work address, image.
 * These messages will be updated into the tblTea.
 *
 * @version 2016/09/16
 * @author  LIU SIHAO
 */
public class InfoNewTchView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The following values are used for frame creation. */
	private JLabel name;
	private JLabel tel;
	private JLabel mail;
	private JLabel school;
	private JLabel major;
	private JLabel age;
	private JLabel address;	
	private JLabel number;
	private JLabel image;
	private JLabel mark;
	private JLabel title;
	private JTextField title1;
	private JTextField address1;
	private JTextField school1;
	private JTextField name1;
	private JTextField tel1;
	private JTextField mail1;
	private JTextField number1;
	private JTextField major1;
	private JTextField age1;
	private JTextArea ta;
	private JButton save;
	private JButton clear;
	private ImageIcon icon;
	private JButton choosePic;
	/** The value is used for string storage. */
	private String[] str={"女","男"};
	private static final String tip = "如2008-08-08";
	/** The value is used for JRadioButton building. */
	private JRadioButton[] radioBtn=new JRadioButton[2];
	String id;
	InfoData newTea = new InfoData("SaveStuInfo");
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	Client client;
	
	/**
     * 主界面构造函数
     * 
     * @param       client   客户端对象,用来连接服务端
     * @param       id       String字符串,用来传递教师职工号
     * @param       jp       面板对象
     * @return      void
     */
	public InfoNewTchView(Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.p=jp;
		
		
	    JPanel p2=new JPanel();
	    JPanel p3=new JPanel();
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p.setLayout(null);
		ButtonGroup grp=new ButtonGroup();
		/** 照片、职工号、姓名模块*/
		p1.setBounds(100, 10, 140,150);
		p1.setBorder(BorderFactory.createTitledBorder("证件照:"));
		image = new JLabel();
	    p1.add(image);
	    p.add(p1);
	    choosePic = new JButton("上传图片(140×150)");
	    choosePic.addActionListener(new ChoosePicListener());
	    choosePic.setBounds(100,160,140,30);
	    p.add(choosePic);
	    /** 职工号、姓名模块*/
	    number=new JLabel("职工号：");
		number.setFont(new Font("华文新魏",Font.BOLD, 25));
		number.setBounds(350,60,150,30);
		p.add(number);
		number1=new JTextField(10);
		number1.setFont(new Font("宋体",Font.BOLD, 18));
		number1.setBounds(450, 60, 150, 30);
		p.add(number1);
		name=new JLabel("姓名：");
		name.setFont(new Font("华文新魏",Font.BOLD, 25));
		name.setBounds(350,120,150,30);
		p.add(name);
		name1=new JTextField(10);
		name1.setFont(new Font("宋体",Font.BOLD, 18));
		name1.setBounds(450, 120, 150, 30);
		p.add(name1);
		/**性别模块*/
		p2.setBounds(620,80, 200, 50);
		for(int i=0;i<2;i++){
			radioBtn[i]=new JRadioButton(str[i]);
			radioBtn[i].setOpaque(false);
			grp.add(radioBtn[i]);
			p2.add(radioBtn[i]);
			p.add(p2);
		}
		/** 其他基础信息模块*/
		p3.setBounds(100, 200, 800, 300);
		p3.setLayout(null);
		p3.setBorder(BorderFactory.createTitledBorder("基础信息"));
		age=new JLabel("出生年月");
		age.setFont(new Font("华文新魏",Font.BOLD, 25));
		age.setBounds(20, 30, 150, 30);
		p3.add(age);
		age1=new JTextField(10);
		age1.setFont(new Font("宋体",Font.BOLD, 18));
		age1.setBounds(150, 30, 220, 30);
		p3.add(age1);
		address=new JLabel("办公住址");
		address.setFont(new Font("华文新魏",Font.BOLD, 25));
		address.setBounds(20, 170, 150, 30);
		p3.add(address);
		address1=new JTextField(10);
		address1.setFont(new Font("宋体",Font.BOLD, 18));
		address1.setBounds(150, 170, 220, 30);
		p3.add(address1);
		tel=new JLabel("联系电话");
		tel.setFont(new Font("华文新魏",Font.BOLD, 25));
		tel.setBounds(20,240,150,30);
		p3.add(tel);
		tel1=new JTextField(10);
		tel1.setFont(new Font("宋体",Font.BOLD, 18));
		tel1.setBounds(150, 240, 220, 30);
		p3.add(tel1);
		mail=new JLabel("电子邮箱");
		mail.setFont(new Font("华文新魏",Font.BOLD, 25));
		mail.setBounds(400,30,150,30);
		p3.add(mail);
		mail1=new JTextField(10);
		mail1.setFont(new Font("宋体",Font.BOLD, 18));
		mail1.setBounds(550,30,220,30);
		p3.add(mail1);
		title=new JLabel("职称");
		title.setFont(new Font("华文新魏",Font.BOLD, 25));
		title.setBounds(420,100,150,30);
		p3.add(title);
		title1=new JTextField(10);
		title1.setFont(new Font("宋体",Font.BOLD, 18));
		title1.setBounds(550,100,220,30);
		p3.add(title1);
		school=new JLabel("学院");
		school.setFont(new Font("华文新魏",Font.BOLD, 25));
		school.setBounds(420,170,150,30);
		p3.add(school);
		school1=new JTextField(10);
		school1.setFont(new Font("宋体",Font.BOLD, 18));
		school1.setBounds(550,170,220,30);
		p3.add(school1);
		major=new JLabel("系别");
		major.setFont(new Font("华文新魏",Font.BOLD, 25));
		major.setBounds(40,100,150,30);
		p3.add(major);
		major1=new JTextField(10);
		major1.setFont(new Font("宋体",Font.BOLD, 18));
		major1.setBounds(150,100,220,30);
		p3.add(major1);
		p.add(p3);
		/** 备注模块*/
		mark=new JLabel("备注:");
		mark.setFont(new Font("华文新魏",Font.BOLD, 25));
		mark.setBounds(240,550,150,30);
		ta=new JTextArea(4,60);
		ta.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
		ta.setFont(new Font("宋体",Font.BOLD, 18));
		ta.setBounds(320,530,400,60);
		ta.setEnabled(true);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		p.add(mark);
		p.add(ta);
		/** 按钮模块*/
		save=new JButton("保存");
		save.setBounds(400,620,100,30);
		p.add(save);
		clear=new JButton("清空");
		clear.setBounds(540,620,100,30);
		p.add(clear);

		/** 
		 * 在出生年月文本框设置焦点监听事件
		 * 获得焦点时,文本框为空
		 * 失去焦点时,文本框出现提示文本或已输入的合法文本
		 */
		age1.setEditable(true);
		if(age1.getText().trim().equals(tip)) {
    		age1.setText("");
            age1.setForeground(Color.BLACK);
         } else 
        	age1.setForeground(Color.BLACK); 
		
		age1.addFocusListener(new FocusAdapter() {   
			// @Override
	        public void focusGained(FocusEvent e) {
	        	if(age1.getText().trim().equals(tip)) {
	        		age1.setText("");
	                age1.setForeground(Color.BLACK);
	             } else 
	            	age1.setForeground(Color.BLACK); 
	        }
	       // @Override
	        public void focusLost(FocusEvent e) {
	        	 if(age1.getText().trim().equals("")) {
	                 age1.setForeground(Color.GRAY);
	                 age1.setText(tip);
	              } else
	            	 age1.setForeground(Color.BLACK);
	        }
	    });
		
		IdData iData = new IdData("QueryNameByID",id);
		client.sendRequestToServer(iData);     /* 客户端向服务端发送根据职工号查询该教师其他信息的请求 */
		InfoData inData = (InfoData)client.receiveDataFromServer(); /* 客户端接收请求,构造并初始化InfoData对象 */
		/**将InfoData对象中的各项属性的信息反映到此界面中 */
		icon =inData.getIcon();
		p1.removeAll();
		p1.setLayout(null);
		JLabel pic = new JLabel(icon,JLabel.CENTER);
		pic.setBounds(5,25, 130,150);
		p1.add(pic);
		p1.repaint();
		number1.setText(inData.getStuNumber());
		name1.setText(inData.getName());
		school1.setText(inData.getSchool());
		major1.setText(inData.getMajor());
		age1.setText(inData.getAge());
		tel1.setText(inData.getTel());
		mail1.setText(inData.getMail());
		address1.setText(inData.getAddress());
		if("女".equals(inData.getSex())) {
			radioBtn[0].setSelected(true);
		} else if("男".equals(inData.getSex())) {
			radioBtn[1].setSelected(true);
		}
		title1.setText(inData.getTitle());
		name1.setEditable(false);
		number1.setEditable(false);
		title1.setEditable(false);
		ta.setText(inData.getMark());
	
		save.addActionListener(new ActionListener() {/* 添加保存按钮事件监听 */
			public void actionPerformed(ActionEvent e) {
				do_save_actionPerformed(e);
			}
		});
		
		clear.addActionListener(new ActionListener() {/* 添加清空按钮事件监听 */
			public void actionPerformed(ActionEvent e) {
				do_clear_actionPerformed(e);
		    }
		});
		
	}
	
	/**
     * 保存按钮的监听实现,实现对文本框信息的保存
     * 必须填写所有要求填写内容且内容合法，否则保存不成功
     * 
     * @param       e   动作监听类对象
     * @return      void
     */
	protected void do_save_actionPerformed(ActionEvent e) {
		String sNumber=number1.getText().trim();
		if(sNumber.isEmpty()) {
			JOptionPane.showMessageDialog(this, "职工号不能为空！","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String sName=name1.getText().trim();
		if(sName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "姓名不能为空！","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String sSchool=school1.getText().trim();
		String sMajor=major1.getText().trim();
		String sAge=age1.getText().trim();
		if(sAge.equals(tip)) {
			JOptionPane.showMessageDialog(this, "出生年月不能为空！！","",JOptionPane.WARNING_MESSAGE);
			return;
		} else if(!Helper.ageFormat(sAge)) {
			JOptionPane.showMessageDialog(this, "出生年月为2008-08格式！","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String sTel=tel1.getText().trim();
		if(sTel.isEmpty()) {
			JOptionPane.showMessageDialog(this, "联系电话不能为空！","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String sMail=mail1.getText().trim();
		String sAddress=address1.getText().trim();
		String sMark=ta.getText().trim();
		newTea.setStuNumber(sNumber);
		newTea.setName(sName);
		newTea.setSchool(sSchool);
		newTea.setMajor(sMajor);
		newTea.setAge(sAge);
		if(radioBtn[0].isSelected())
			newTea.setSex("女");
		else if(radioBtn[1].isSelected())
			newTea.setSex("男");
		newTea.setTel(sTel);
		newTea.setMail(sMail);
		newTea.setAddress(sAddress);
		newTea.setMark(sMark);
		newTea.setRequest("SaveStuInfo");
		client.sendRequestToServer(newTea);
		RequestData reqData = (RequestData)client.receiveDataFromServer();
		if("true".equals(reqData.getRequest())) {
			JOptionPane.showMessageDialog(null, "基本信息修改成功","提示对话框",1);
		
		} else if("false".equals(reqData.getRequest())) {
			JOptionPane.showMessageDialog(null, "基本信息修改失败","提示对话框",1);
		}
	}
	
	/**
     * 清空按钮的监听实现,实现对文本框所有输入信息的清空
     *
     * @param       e   动作监听类对象
     * @return      void
     */
	protected void do_clear_actionPerformed(ActionEvent e) {
		for(int i=0;i<2;i++) {
			radioBtn[i].setSelected(false);
		}
		address1.setText("");
		school1.setText("");
		mail1.setText("");
		major1.setText("");
		age1.setText("");
		tel1.setText("");
		ta.setText("");
	}	
	 
	/**
     * This class is used to choose pictures from client file. 
     *
     */
	 public class ChoosePicListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {	
				JFileChooser chooser = new JFileChooser(); /*创建选择文件对象*/
				chooser.setDialogTitle("请选择文件");/*设置标题*/
				chooser.setMultiSelectionEnabled(true);  /*设置只能选择文件*/
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");/*定义可选择文件类型*/
				chooser.setFileFilter(filter); /*设置可选择文件类型*/
				int result = chooser.showOpenDialog(null); /*打开选择文件对话框,null可设置为你当前的窗口JFrame或Frame*/
				if (JFileChooser.APPROVE_OPTION == result) {
					File file = chooser.getSelectedFile(); /*file为用户选择的图片文件*/
					if(file!=null) {
						InputStream is;
						try {
							is = new FileInputStream(file);
							BufferedImage bi=ImageIO.read(is);
							Image image1 = (Image)bi;
			    		    icon = new ImageIcon(image1);
			    		    InfoData infoData = new InfoData("SendPicToServer");
			    		    infoData.setIcon(icon);
			    		    infoData.setStuNumber(number1.getText().trim());
			    		    client.sendRequestToServer(infoData);
			    		    RequestData reqData = (RequestData)client.receiveDataFromServer();
			    		    if("true".equals(reqData.getRequest())) {
			    		    	p1.removeAll();
			    			    p1.setLayout(null);
			    			    ImageIcon icon1=new ImageIcon(icon.getImage().getScaledInstance (140,150,Image.SCALE_DEFAULT));
				    			JLabel pic = new JLabel(icon1);
			    			    pic.setBounds(0,0,140,150);
			    			    p1.add(pic);
			    			    p1.repaint();
			    			    JOptionPane.showMessageDialog(null, "上传成功","提示对话框",1);
			    			 } else if("false".equals(reqData.getRequest())){
			    			    JOptionPane.showMessageDialog(null, "上传失败","提示对话框",1);
			    		     }
			    	     } catch (Exception e1) {
			    	    	 e1.printStackTrace();
			    	     }
				    }
				}
			}
		}

	}