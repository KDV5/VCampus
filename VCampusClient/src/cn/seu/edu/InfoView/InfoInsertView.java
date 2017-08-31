/*
 * @(#)InfoInsertView.java        2016/09/16
 *
 * Copyright (c) 2016 SEU CSE. All rights reserved.
 * SEU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.seu.edu.InfoView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import cn.seu.edu.Client.Client;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.RequestData;

/**
 * This class creates a frame, which is used to add new students and teachers
 * by the information manager. Information includes id, name, title(teacher).
 * Add new students into the tblStu, and add new teachers into the tblTea.
 *
 * @version 2016/09/16
 * @author  LIU SIHAO
 */
public class InfoInsertView extends JFrame implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The following eight values are used for frame creation. */
	private JLabel name;
	private JLabel id;
	private JLabel title;
	private JTextField title1;
	private JTextField name1;
	private JTextField id1;
	private JButton clear;
	private JButton save;
	/** The value is used for string storage. */
	private String[] str={"教师","学生"};
	private static final String tip = "教授/副教授/讲师";
	/** The value is used for JRadioButton building. */
	private JRadioButton[] radioBtn=new JRadioButton[2];
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p=new JPanel();
	Client client;
	
	/**
     * 主界面构造函数
     */
	
	public InfoInsertView(Client client,JPanel jp){
		this.client = client;
		this.p=jp;
		p.setLayout(null);     /* 面板使用绝对布局  */
		p1.setOpaque(false);
		p2.setOpaque(false);
		
		p1.setBounds(200, 50, 250, 70);
		p1.setBorder(BorderFactory.createTitledBorder("教师/学生选择:"));
		ButtonGroup grp=new ButtonGroup();
		for(int i=0;i<2;i++) {  /* 添加教师学生单选按钮 */
			radioBtn[i]=new JRadioButton(str[i]);
			radioBtn[i].setOpaque(false);
			p1.add(radioBtn[i]);
			grp.add(radioBtn[i]);
			radioBtn[i].addItemListener(this);
		}
		p.add(p1);
		
		p2.setBounds(200, 135, 650, 300);
		p2.setLayout(null);
		p2.setBorder(BorderFactory.createTitledBorder("教师/学生信息新建:"));
		id=new JLabel("职工号/学号:");
		id.setFont(new Font("华文新魏",Font.BOLD, 25));
		id.setBounds(70, 60, 200, 30);
		p2.add(id);
		id1=new JTextField(10);
		id1.setFont(new Font("宋体",Font.BOLD, 18));
		id1.setBounds(300, 60, 240, 30);
		p2.add(id1);
		name=new JLabel("姓名:");
		name.setFont(new Font("华文新魏",Font.BOLD, 25));
		name.setBounds(100, 120, 150, 30);
		p2.add(name);
		name1=new JTextField(10);
		name1.setFont(new Font("宋体",Font.BOLD, 18));
		name1.setBounds(300, 120, 240, 30);
		p2.add(name1);
		title=new JLabel("职称:");
		title.setFont(new Font("华文新魏",Font.BOLD, 25));
		title.setBounds(100, 180, 150, 30);
		p2.add(title);
		title1=new JTextField(10);
		title1.setFont(new Font("宋体",Font.BOLD, 18));
		title1.setBounds(300, 180, 240, 30);
		p2.add(title1);
		p.add(p2);
		
		save=new JButton("保存");
		save.setBounds(400,500,100,30);
		p.add(save);
		clear=new JButton("清空");
		clear.setBounds(520,500,100,30);
		p.add(clear);
		
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
     * 此函数是教师学生选择项发生改变时的回调事件，根据选项不同设置职称框的不同状态
     * 若选择教师类，则职称文本框可编辑且显示默认提示
     * 若选择学生类，则职称文本框为不可编辑状态
     * 
     * @param       e   项目事件类对象
     * @return      void
     */

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == radioBtn[0]) {
			title1.setEditable(true);
	    	title1.setText(tip);
			title1.setForeground(Color.GRAY);
			title1.addFocusListener(new FocusAdapter() {
				// @Override
				public void focusGained(FocusEvent e) {
					if(title1.getText().trim().equals(tip)) {
						title1.setText("");
				        title1.setForeground(Color.BLACK);
				        }
					}
				// @Override
				public void focusLost(FocusEvent e) {
					if(title1.getText().trim().equals("")) {
						title1.setForeground(Color.GRAY);
						title1.setText(tip);
						}
					}
				});
		} else {
			title1.setText("");
			title1.setEditable(false);
		}
	}
	
	 /**
     * 保存按钮的监听实现,实现对文本框信息的保存
     * 必须选择教师职工类型,否则保存不成功
     * 必须填写所有可填内容且内容合法，否则保存不成功
     * 
     * @param       e   动作监听类对象
     * @return      void
     */

	protected void do_save_actionPerformed(ActionEvent e) {
		String sId=id1.getText().trim();
		String sName=name1.getText().trim();
		String sTitle=title1.getText().trim();
		InfoData newSorT = new InfoData("InfoInsert");
		if(sId.isEmpty()&&sName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "职工号/学号和姓名均不能为空！","",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!radioBtn[0].isSelected()&&!radioBtn[1].isSelected()) {
			JOptionPane.showMessageDialog(this, "请选择教师/学生类型！","",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			if(radioBtn[0].isSelected() && sId.length()==7) {//新建教师
				if(!sTitle.equals("教授")&&!sTitle.equals("副教授")&&!sTitle.equals("讲师")) {
					JOptionPane.showMessageDialog(this, "教师职称须为教授或副教授或讲师！","",
							JOptionPane.WARNING_MESSAGE);
					return;
					}
				newSorT.setStuNumber(sId);
				newSorT.setName(sName);
				newSorT.setTitle(sTitle);
				client.sendRequestToServer(newSorT);
				RequestData reqData = (RequestData)client.receiveDataFromServer();
				if("true".equals(reqData.getRequest())) {
					JOptionPane.showMessageDialog(null, "保存成功","提示对话框",1);
				} else if("false".equals(reqData.getRequest())) {
					JOptionPane.showMessageDialog(null, "保存失败","提示对话框",1);
				} else if("exist".equals(reqData.getRequest())) {
					JOptionPane.showMessageDialog(null, "此人已存在","提示对话框",1);
				}
			} else if(radioBtn[1].isSelected() && sId.length()==8) {//新建学生
				newSorT.setName(sName);
				newSorT.setStuNumber(sId);
				newSorT.setTitle("");
				title1.setText("");
				client.sendRequestToServer(newSorT);
				RequestData reqData = (RequestData)client.receiveDataFromServer();
				if("true".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "保存成功","提示对话框",1);
				} else if("false".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "保存失败","提示对话框",1);
				} else if("exist".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "此人已存在","提示对话框",1);
				}
			} else {
				JOptionPane.showMessageDialog(null, "输入学号/职工号不合法","提示对话框",1);
				}
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
		name1.setText("");
		id1.setText("");
		if(radioBtn[0].isSelected()) {
			title1.setText("");}
	}	
		
}
