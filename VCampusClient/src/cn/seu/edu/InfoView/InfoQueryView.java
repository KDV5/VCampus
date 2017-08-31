/*
 * @(#)InfoQueryView.java        2016/09/16
 *
 * Copyright (c) 2016 SEU CSE. All rights reserved.
 * SEU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.seu.edu.InfoView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import cn.seu.edu.Client.Client;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.ObjListData;

/**
 * This class creates a frame, which includes five query condition 
 * to find eligible students.
 * The frame is owned by the information manager.
 *
 * @version 2016/09/16
 * @author  LIU SIHAO
 */
public class InfoQueryView extends JFrame{
	/** The following values are used for frame creation. */
	private JLabel name;
	private JLabel school;
	private JLabel major;
	private JLabel stuNumber;
	private JLabel root;
	private JTextField root1;
	private JTextField school1;
	private JTextField name1;
	private JTextField stuNumber1;
	private JTextField major1;
	private JButton clear;
	private JButton find;
	JPanel p=new JPanel();
	Client client;
	
	/**
     * 主界面构造函数
     * 
     * @param       client   客户端对象,用来连接服务端
     * @param       jp       面板对象
     * @return      void
     */
	public InfoQueryView(Client client,JPanel jp){
		this.client = client;
		this.p=jp;
		JPanel p1=new JPanel();
		p1.setOpaque(false);
		p.setLayout(null);
		p1.setLayout(null);
		/** 查询条件模块 */
		p1.setBounds(40, 20, 920, 200);
	    p1.setBorder(BorderFactory.createTitledBorder("请输入任意查询条件"));
		stuNumber=new JLabel("学号:");
		stuNumber.setFont(new Font("华文新魏",Font.BOLD, 30));
		stuNumber.setBounds(30,40,100, 30);
		p1.add(stuNumber);
		stuNumber1=new JTextField(10);
		stuNumber1.setFont(new Font("宋体",Font.BOLD, 18));
		stuNumber1.setBounds(115, 40, 130, 30);
		p1.add(stuNumber1);
		name=new JLabel("姓名:");
		name.setFont(new Font("华文新魏",Font.BOLD, 30));
		name.setBounds(265,40, 100, 30);
		p1.add(name);
		name1=new JTextField(10);
		name1.setFont(new Font("宋体",Font.BOLD, 18));
		name1.setBounds(350, 40, 130, 30);
		p1.add(name1);
		root=new JLabel("籍贯:");
		root.setFont(new Font("华文新魏",Font.BOLD, 30));
		root.setBounds(520, 40, 100, 30);
		p1.add(root);
		root1=new JTextField(10);
		root1.setFont(new Font("宋体",Font.BOLD, 18));
		root1.setBounds(605, 40, 260, 30);
		p1.add(root1);
		school=new JLabel("学院:");
		school.setFont(new Font("华文新魏",Font.BOLD, 30));
		school.setBounds(30, 100, 100, 30);
		p1.add(school);
		school1=new JTextField(10);
		school1.setFont(new Font("宋体",Font.BOLD, 18));
		school1.setBounds(115, 100, 364, 30);
		p1.add(school1);
		major=new JLabel("专业:");
		major.setFont(new Font("华文新魏",Font.BOLD, 30));
		major.setBounds(520, 100, 100, 30);
		p1.add(major);
		major1=new JTextField(10);
	    major1.setFont(new Font("宋体",Font.BOLD, 18));
		major1.setBounds(605, 100, 260, 30);
		p1.add(major1);
		find=new JButton("查询");
		find.setBounds(300, 160, 100, 30);
		p1.add(find);
		clear=new JButton("清空");
		clear.setBounds(500, 160, 100, 30);
		p1.add(clear);
		p.add(p1);
		
		find.addActionListener(new ActionListener(){ /* 添加查询按钮事件监听 */
			public void actionPerformed(ActionEvent e){
				do_find_actionPerformed(e);
		}
		});
		clear.addActionListener(new ActionListener(){/* 添加清空按钮事件监听 */
			public void actionPerformed(ActionEvent e){
				do_clear_actionPerformed(e);
		}
		});
	}
	
	/**
     * 查询按钮的监听实现,实现对文本框信息的保存
     * 至少填写一项查询条件方可查询
     * 
     * @param       e   动作监听类对象
     * @return      void
     */
	protected void do_find_actionPerformed(ActionEvent e){
		String sStuNumber=stuNumber1.getText().trim();
		String sName=name1.getText().trim();
		String sRoot=root1.getText().trim();
		String sSchool=school1.getText().trim();
		String sMajor=major1.getText().trim();
		InfoData newStudent=new InfoData("QueryStuInfo");
		if(sName.isEmpty()&&sStuNumber.isEmpty()&&sSchool.isEmpty()&&sRoot.isEmpty()&&sMajor.isEmpty()){
			JOptionPane.showMessageDialog(this, "查询条件不能为空！","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		newStudent.setStuNumber(sStuNumber);
		newStudent.setName(sName);
		newStudent.setSchool(sSchool);
		newStudent.setMajor(sMajor);
		newStudent.setRoot(sRoot);
		client.sendRequestToServer(newStudent);
		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		if(listData.getInfoList().size()>0) {  /* 查询结果不为空,显示查询结果 */
			MyFindEndFrame frame=new MyFindEndFrame(listData.getInfoList());
		}
		
	}
	
	/**
     * 清空按钮的监听实现,实现对文本框所有输入信息的清空
     *
     * @param       e   动作监听类对象
     * @return      void
     */
	protected void do_clear_actionPerformed(ActionEvent e) {
		stuNumber1.setText("");
		name1.setText("");
		school1.setText("");
		major1.setText("");
		root1.setText("");
	}	
	
	/**
	 * This class creates a frame, which shows query end。 
	 * Those are eligible students' existent information.
	 * The frame is owned by the information manager.
	 */
	class MyFindEndFrame extends JFrame {
		public MyFindEndFrame(List<InfoData> results)
		{

			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			JScrollPane scroll = new JScrollPane(v,h); 
			p.add(scroll, BorderLayout.CENTER);
			JTable stuTal=new JTable();
			stuTal.setEnabled(false);
			stuTal.setFont(new Font("微软雅黑",Font.PLAIN,15));
			stuTal.setRowHeight(30);/*设置表体高度*/
			JTableHeader head=stuTal.getTableHeader();/*获得表头对象*/
			head.setFont(new Font("微软雅黑",Font.PLAIN,15));
			head.setPreferredSize(new Dimension(head.getWidth(),40));/*设置表头高度*/
			DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)head.getDefaultRenderer();/*获得渲染器*/
			renderer.setHorizontalAlignment(SwingConstants.CENTER);/*设置表头内容居中显示*/
			DefaultTableModel model=(DefaultTableModel) stuTal.getModel();/*获得表格模型*/
			model.setRowCount(0);/*清空表格中的数据*/
			int i=1;
			model.setColumnIdentifiers(new Object[]{"编号 ","学号 ","姓名 ","出生年月","家庭住址","联系方式","电子邮箱","籍贯","宿舍"});
			for(InfoData infor:results){  /*将数据加载到表格中*/
				model.addRow(new Object[]{i++,infor.getStuNumber(),infor.getName(),infor.getAge(),
						infor.getAddress(),infor.getTel(),infor.getMail(),infor.getRoot(),infor.getDorm()});
			}
			stuTal.setModel(model);/*应用表格模型*/
			scroll.setViewportView(stuTal);
			scroll.setBounds(40,260,920,360);
			p.add(scroll);
		}
	}

}
