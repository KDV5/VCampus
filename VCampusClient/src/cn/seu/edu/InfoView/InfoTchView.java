/*
 * @(#)InfoTchView.java        2016/09/16
 *
 * Copyright (c) 2016 SEU CSE. All rights reserved.
 * SEU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package cn.seu.edu.InfoView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import cn.seu.edu.Client.Client;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.InfoData;

/**
 * This class creates a frame, which shows entered teacher's all existent information.
 * The information is read-only and not editable.
 *
 * @version 2016/09/16
 * @author  LIU SIHAO
 */
public class InfoTchView extends JFrame{
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
	private JLabel sex;
	private JLabel title;
	private JTextField title1;
	private JTextField sex1;
	private JTextField address1;
	private JTextField school1;
	private JTextField name1;
	private JTextField tel1;
	private JTextField mail1;
	private JTextField number1;
	private JTextField major1;
	private JTextField age1;
	private JTextArea ta;
	private ImageIcon icon;
	String id;
	JPanel p=new JPanel();
	Client client;
	
	/**
     * �����湹�캯��
     * 
     * @param       client   �ͻ��˶���,�������ӷ����
     * @param       id       String�ַ���,�������ݽ�ʦְ����
     * @param       jp       ������
     * @return      void
     */
	public InfoTchView(Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.p=jp;
		
		JPanel p1=new JPanel();//�������
		JPanel p3=new JPanel();
		p1.setOpaque(false);
		p3.setOpaque(false);
		p.setLayout(null);

		p1.setBounds(100, 10, 140,150);
		p1.setLayout(null);
		p1.setBorder(BorderFactory.createTitledBorder("֤����:"));
		image = new JLabel();
	    p1.add(image);
	    p.add(p1);
	    
		number=new JLabel("ְ���ţ�");
		number.setFont(new Font("������κ",Font.BOLD, 25));
		number.setBounds(300,50,150,30);
		p.add(number);
		number1=new JTextField(10);
		number1.setFont(new Font("����",Font.BOLD, 18));
		number1.setBounds(400, 50, 150, 30);
		p.add(number1);
		name=new JLabel("������");
		name.setFont(new Font("������κ",Font.BOLD, 25));
		name.setBounds(300,100,150,30);
		p.add(name);
		name1=new JTextField(10);
		name1.setFont(new Font("����",Font.BOLD, 18));
		name1.setBounds(400, 100, 150, 30);
		p.add(name1);
		
		p3.setBounds(100, 170, 800, 300);
		p3.setLayout(null);
		p3.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
		age=new JLabel("��������");
		age.setFont(new Font("������κ",Font.BOLD, 25));
		age.setBounds(20, 30, 150, 30);
		p3.add(age);
		age1=new JTextField(10);
		age1.setFont(new Font("����",Font.BOLD, 18));
		age1.setBounds(150, 30, 220, 30);
		p3.add(age1);
	    sex=new JLabel("�Ա�");
	    sex.setFont(new Font("������κ",Font.BOLD, 25));
	    sex.setBounds(40, 100, 150, 30);
		p3.add(sex);
		sex1=new JTextField(10);
		sex1.setFont(new Font("����",Font.BOLD, 18));
		sex1.setBounds(150, 100, 220, 30);
		p3.add(sex1);
		address=new JLabel("�칫סַ");
		address.setFont(new Font("������κ",Font.BOLD, 25));
		address.setBounds(20, 170, 150, 30);
		p3.add(address);
		address1=new JTextField(10);
		address1.setFont(new Font("����",Font.BOLD, 18));
		address1.setBounds(150, 170, 220, 30);
		p3.add(address1);
		tel=new JLabel("��ϵ�绰");
		tel.setFont(new Font("������κ",Font.BOLD, 25));
		tel.setBounds(20,240,150,30);
		p3.add(tel);
		tel1=new JTextField(10);
		tel1.setFont(new Font("����",Font.BOLD, 18));
		tel1.setBounds(150, 240, 220, 30);
		p3.add(tel1);
		mail=new JLabel("��������");
		mail.setFont(new Font("������κ",Font.BOLD, 25));
		mail.setBounds(400,30,150,30);
		p3.add(mail);
		mail1=new JTextField(10);
		mail1.setFont(new Font("����",Font.BOLD, 18));
		mail1.setBounds(550,30,220,30);
		p3.add(mail1);
		title=new JLabel("ְ��");
		title.setFont(new Font("������κ",Font.BOLD, 25));
		title.setBounds(420,100,150,30);
		p3.add(title);
		title1=new JTextField(10);
		title1.setFont(new Font("����",Font.BOLD, 18));
		title1.setBounds(550,100,220,30);
		p3.add(title1);
		school=new JLabel("ѧԺ");
		school.setFont(new Font("������κ",Font.BOLD, 25));
		school.setBounds(420,170,150,30);
		p3.add(school);
		school1=new JTextField(10);
		school1.setFont(new Font("����",Font.BOLD, 18));
		school1.setBounds(550,170,220,30);
		p3.add(school1);
		major=new JLabel("ϵ��");
		major.setFont(new Font("������κ",Font.BOLD, 25));
		major.setBounds(420,240,150,30);
		p3.add(major);
		major1=new JTextField(10);
		major1.setFont(new Font("����",Font.BOLD, 18));
		major1.setBounds(550,240,220,30);
		p3.add(major1);
		p.add(p3);
		
		mark=new JLabel("��ע:");
		mark.setFont(new Font("������κ",Font.BOLD, 25));
		mark.setBounds(240,530,150,30);
		ta=new JTextArea(4,60);
		ta.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
		ta.setFont(new Font("����",Font.BOLD, 18));
		ta.setBounds(320,480,400,60);
		ta.setEnabled(true);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		p.add(mark);
		p.add(ta);
		
		IdData iData = new IdData("QueryNameByID",id);
		client.sendRequestToServer(iData);  /* �ͻ��������˷��͸���ѧ�Ų�ѯ�ý�ʦ������Ϣ������ */
		InfoData inData = (InfoData)client.receiveDataFromServer(); /* �ͻ��˽�������,���첢��ʼ��InfoData���� */
		/** ��InfoData�����еĸ������Ե���Ϣ��ӳ���˽����� */
		icon =inData.getIcon();
		p1.removeAll();
		ImageIcon icon1=new ImageIcon(icon.getImage());//.getScaledInstance (140,150,Image.SCALE_DEFAULT));
		JLabel pic = new JLabel(icon1);
		pic.setBounds(10,20, 140,150);
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
		sex1.setText(inData.getSex());
		title1.setText(inData.getTitle());
		ta.setText(inData.getMark());
		/**���������ı���Ϊ���ɱ༭״̬*/
		number1.setEditable(false);
		name1.setEditable(false);
		school1.setEditable(false);
		address1.setEditable(false);
		mail1.setEditable(false);
		tel1.setEditable(false);
		major1.setEditable(false);
		age1.setEditable(false);
		title1.setEditable(false);
		sex1.setEditable(false);
		ta.setEditable(false);
		
	}
}
