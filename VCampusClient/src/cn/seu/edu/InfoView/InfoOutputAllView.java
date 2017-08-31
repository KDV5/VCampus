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
     * �����湹�캯��
     * 
     * @param       client   �ͻ��˶���,�������ӷ����
     * @param       jp       ������
     * @return      void
     */
	public	InfoOutputAllView(Client client,JPanel jp){
		this.client = client;
		this.p=jp;
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;/* ���������� */
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane scroll = new JScrollPane(v,h); 
		JTable stuTal=new JTable();
		stuTal.setFont(new Font("΢���ź�",Font.PLAIN,15));
		stuTal.setRowHeight(30);/*���ñ���߶�*/
		JTableHeader head=stuTal.getTableHeader();/*��ñ�ͷ����*/
		head.setFont(new Font("΢���ź�",Font.PLAIN,15));
		head.setPreferredSize(new Dimension(head.getWidth(),40));/*���ñ�ͷ�߶�*/
		DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)head.getDefaultRenderer();/*�����Ⱦ��*/
		renderer.setHorizontalAlignment(SwingConstants.CENTER);/*���ñ�ͷ���ݾ�����ʾ*/
		DefaultTableModel model=(DefaultTableModel) stuTal.getModel();/*��ñ��ģ��*/
		model.setRowCount(0);/*��ձ���е�����*/
		int i=1;/* ����1��ʼÿ�и�һ����� */
		model.setColumnIdentifiers(new Object[]{"���","ѧ��","����","Ժϵ","רҵ","��ͥסַ","��ϵ��ʽ","��������","����"});
		client.sendRequestToServer(new RequestData("OutputAllStuInfo"));
		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		List<InfoData>  results = listData.getInfoList();
		for(InfoData infor:results) {  /*�����ݼ��ص������*/
			model.addRow(new Object[]{i++,infor.getStuNumber(),infor.getName(),infor.getSchool(),infor.getMajor(),
					infor.getAddress(),infor.getTel(),infor.getMail(),infor.getRoot()});
		}
		stuTal.setModel(model);/*Ӧ�ñ��ģ��*/
		scroll.setViewportView(stuTal);
		scroll.setBounds(40,50,940,540);
		scroll.setOpaque(false);
		p.add(scroll);
	}
	
}
