/*
 * @(#)InfoOutputAllView.java        2016/09/16
 *
 * Copyright (c) 2016 SEU CSE. All rights reserved.
 * SEU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.seu.edu.InfoView;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import cn.seu.edu.Client.Client;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;

/**
 * This class creates a frame, which shows all students' existent information.
 * The frame is owned by the information manager.
 *
 * @version 2016/09/16
 * @author  LIU SIHAO
 */
public class InfoOutputAllView extends JFrame{
	Client client;
	JPanel p=new JPanel();
	
	/**
     * 主界面构造函数
     * 
     * @param       client   客户端对象,用来连接服务端
     * @param       jp       面板对象
     * @return      void
     */
	public	InfoOutputAllView(Client client,JPanel jp){
		this.client = client;
		this.p=jp;
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;/* 滚动条设置 */
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane scroll = new JScrollPane(v,h); 
		JTable stuTal=new JTable();
		stuTal.setFont(new Font("微软雅黑",Font.PLAIN,15));
		stuTal.setRowHeight(30);/*设置表体高度*/
		JTableHeader head=stuTal.getTableHeader();/*获得表头对象*/
		head.setFont(new Font("微软雅黑",Font.PLAIN,15));
		head.setPreferredSize(new Dimension(head.getWidth(),40));/*设置表头高度*/
		DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)head.getDefaultRenderer();/*获得渲染器*/
		renderer.setHorizontalAlignment(SwingConstants.CENTER);/*设置表头内容居中显示*/
		DefaultTableModel model=(DefaultTableModel) stuTal.getModel();/*获得表格模型*/
		model.setRowCount(0);/*清空表格中的数据*/
		int i=1;/* 表格从1开始每行给一个编号 */
		model.setColumnIdentifiers(new Object[]{"编号","学号","姓名","院系","专业","家庭住址","联系方式","电子邮箱","籍贯"});
		client.sendRequestToServer(new RequestData("OutputAllStuInfo"));
		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		List<InfoData>  results = listData.getInfoList();
		for(InfoData infor:results) {  /*将数据加载到表格中*/
			model.addRow(new Object[]{i++,infor.getStuNumber(),infor.getName(),infor.getSchool(),infor.getMajor(),
					infor.getAddress(),infor.getTel(),infor.getMail(),infor.getRoot()});
		}
		stuTal.setModel(model);/*应用表格模型*/
		scroll.setViewportView(stuTal);
		scroll.setBounds(40,50,940,540);
		scroll.setOpaque(false);
		p.add(scroll);
	}
	
}
