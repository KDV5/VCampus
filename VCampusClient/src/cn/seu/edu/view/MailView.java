package cn.seu.edu.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;

public class MailView {

	final Client client;
	String id;
	int unreadMailNum = 0;
	JPanel frame = new JPanel();
	JLabel tipLab = new JLabel();
	JButton readMyRecieveButton = new JButton("�ռ���");
	JButton readMySendButton = new JButton("�ѷ���");
	JButton writeButton = new JButton("д�ʼ�");
	
	JLabel recipientLab = new JLabel("�ռ��ˣ�");
    JTextField recipientText = new JTextField(15);
	JLabel topicLab = new JLabel("���⣺");
    JTextField topicText = new JTextField(15);
	JLabel contentLab = new JLabel("���ģ�");
	JTextArea contentText = new JTextArea(25,50);
	JLabel timeLab = new JLabel("ʱ��");
	JTextField timeText = new JTextField(15);
	
	JScrollPane scroll1=new JScrollPane();
	JScrollPane scroll2=new JScrollPane();
	JPanel write=new JPanel();
	JTable table1;
	JTable table2;
	String []content;
	int[] mailID;
	
	public MailView(Client client , String id, String num,JPanel jp){
		this.client = client;
		this.id = id;
		this.frame=jp;
		this.unreadMailNum = Integer.valueOf(num);
		tipLab.setText("���� "+unreadMailNum+" ��δ���ʼ�");
		tipLab.setBounds(20, 50, 200, 30);
		readMyRecieveButton.addActionListener(new readMyRecieveActionPerformed());
		readMySendButton.addActionListener(new readMySendActionPerformed());
		writeButton.addActionListener(new writeActionPerformed());
		
		frame.add(readMyRecieveButton);
		readMyRecieveButton.setBounds(300, 10, 100, 30);
		frame.add(readMySendButton);
		readMySendButton.setBounds(450, 10, 100, 30);
		frame.add(writeButton);
		writeButton.setBounds(600, 10, 100, 30);
		frame.add(tipLab);
		contentText.setLineWrap(true);
		
		if(id.length()==7||id.length()==8){
			client.sendRequestToServer(new IdData("libMail",id));	
			RequestData reqData =(RequestData) client.receiveDataFromServer();	
			if(reqData.getRequest().equals("sendMail")){				
				client.sendRequestToServer(reqData);
				RequestData reqData1 =(RequestData) client.receiveDataFromServer();
			}
		}
		
	}
	
	//�鿴���յ����ʼ�
	public class readMyRecieveActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			frame.removeAll();
			

			table1 = new JTable();
			table1.setEnabled(false);
			table1.setFont(new java.awt.Font("Dialog", 1, 15));
			
			table1.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) { 
					{
						
						frame.removeAll();
						 int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
						 JPanel readMailFrame = new JPanel();
						 readMailFrame.setLayout(null);
						 
						 recipientText.setText((String)table1.getValueAt(row,0));
						 timeText.setText((String)table1.getValueAt(row,2));
						 topicText.setText((String)table1.getValueAt(row,1));
						 contentText.setText(content[row]);
						 
						 if("δ��".equals((String)table1.getValueAt(row,3))){
							 MailData mailData = new MailData("readMyRecieveMail");
							 mailData.setmID(mailID[row]);
							 client.sendRequestToServer(mailData);
							 table1.setValueAt( "�Ѷ�",row, 3);
							 tipLab.setText("���� "+ (--unreadMailNum) +" ��δ���ʼ�");
							
						 }
						    recipientLab.setText("������");
						    recipientLab.setBounds(100, 20, 130, 30);
							recipientLab.setFont(new Font("�����п�",Font.BOLD, 25));
							recipientText.setBounds(220, 20, 200, 30);
							recipientText.setFont(new Font("����",Font.BOLD, 18));
							timeLab.setBounds(100,80,130,30);
							timeLab.setFont(new Font("�����п�",Font.BOLD, 25));
							timeText.setBounds(220,80,200,30);
							timeText.setFont(new Font("����",Font.BOLD, 18));
							topicLab.setBounds(100,140,130,30);
							topicLab.setFont(new Font("�����п�",Font.BOLD, 25));
							topicText.setBounds(220,140,200,30);
							topicText.setFont(new Font("����",Font.BOLD, 18));
							contentLab.setBounds(100,200,100,20);
							contentLab.setFont(new Font("�����п�",Font.BOLD, 25));
							contentText.setBounds(100,260,800,300);
							contentText.setFont(new Font("����",Font.BOLD, 18));
							contentText.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
							contentText.setLineWrap(true);
							recipientText.setEditable(false);
							timeText.setEditable(false);
							topicText.setEditable(false);
							contentText.setEditable(false);
							
							readMailFrame.setLayout(null);
							readMailFrame.setBounds(0, 60, 1000, 600);
							readMailFrame.add(recipientLab);
							readMailFrame.add(recipientText);
							readMailFrame.add(timeLab);
							readMailFrame.add(timeText);
							readMailFrame.add(topicLab);
							readMailFrame.add(topicText);
							readMailFrame.add(contentLab);
							readMailFrame.add(contentText);
							//readMailFrame.setOpaque(false);
							
							frame.add(readMyRecieveButton);
							frame.add(readMySendButton);
							frame.add(writeButton);
							frame.add(readMailFrame);
							frame.repaint();

					}
				}
			});

			scroll1 = new JScrollPane(table1);  
			scroll1.setBounds(0, 100, 1000, 560);  
			
			DefaultTableModel model=(DefaultTableModel) table1.getModel();//��ñ��ģ��
			model.setRowCount(0);//��ձ���е�����
			model.setColumnIdentifiers(new Object[]{"������","�ʼ�����","ʱ��","�Ƿ��Ѷ�"});
			
			client.sendRequestToServer(new IdData("OutputMyRecieveMail",id));
			ObjListData listData = (ObjListData)client.receiveDataFromServer();
			int length = listData.getMailList().size();
			content = new String[length];
			mailID = new int[length];
			
			List<MailData>  results = listData.getMailList();
			int i = 0;
			for(MailData infor:results){  //�����ݼ��ص������
				String ifRead = "δ��";
				if(infor.ismRead()){
					ifRead = "�Ѷ�";
				}else{
					ifRead = "δ��";
				}
				content[i] = infor.getmContent();
				mailID[i] = infor.getmID();
				model.addRow(new Object[]{infor.getmSenderName(),infor.getmTopic(),infor.getmTime(),ifRead});
				i++;
			}
			
			Helper.makeFace(table1);
			table1.setModel(model);//Ӧ�ñ��ģ��
			scroll1.setViewportView(table1);

			
			frame.add(readMyRecieveButton);
			frame.add(readMySendButton);
			frame.add(writeButton);
			frame.add(tipLab);
			frame.add(scroll1);
			frame.repaint();
	
		
		}
	}
	
	//�鿴�ҷ��͵��ʼ�
	public class readMySendActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			frame.removeAll();
			
			table2 = new JTable();
			table2.setEnabled(false);
			table2.setFont(new java.awt.Font("Dialog", 1, 15));
			
			table2.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) { 
					{
						frame.removeAll();
						
						 int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
						 JPanel readMailFrame = new JPanel();
						 readMailFrame.setLayout(null);
						 
						 recipientText.setText((String)table2.getValueAt(row,0));
						 timeText.setText((String)table2.getValueAt(row,2));
						 topicText.setText((String)table2.getValueAt(row,1));
						 contentText.setText(content[row]);
						 
						    recipientLab.setText("�ռ���");  
						    recipientLab.setBounds(100, 20, 130, 30);
							recipientLab.setFont(new Font("�����п�",Font.BOLD, 25));
							recipientText.setBounds(220, 20, 200, 30);
							recipientText.setFont(new Font("����",Font.BOLD, 18));
							timeLab.setBounds(100,80,130,30);
							timeLab.setFont(new Font("�����п�",Font.BOLD, 25));
							timeText.setBounds(220,80,200,30);
							timeText.setFont(new Font("����",Font.BOLD, 18));
							topicLab.setBounds(100,140,130,30);
							topicLab.setFont(new Font("�����п�",Font.BOLD, 25));
							topicText.setBounds(220,140,200,30);
							topicText.setFont(new Font("����",Font.BOLD, 18));
							contentLab.setBounds(100,200,100,20);
							contentLab.setFont(new Font("�����п�",Font.BOLD, 25));
							contentText.setBounds(100,260,800,300);
							contentText.setFont(new Font("����",Font.BOLD, 18));
							contentText.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
							contentText.setLineWrap(true);
							recipientText.setEditable(false);
							timeText.setEditable(false);
							topicText.setEditable(false);
							contentText.setEditable(false);
							
							readMailFrame.setLayout(null);
							readMailFrame.setBounds(0, 60, 1000, 600);
							readMailFrame.add(recipientLab);
							readMailFrame.add(recipientText);
							readMailFrame.add(timeLab);
							readMailFrame.add(timeText);
							readMailFrame.add(topicLab);
							readMailFrame.add(topicText);
							readMailFrame.add(contentLab);
							readMailFrame.add(contentText);
							
							frame.add(readMyRecieveButton);
							frame.add(readMySendButton);
							frame.add(writeButton);
							frame.add(readMailFrame);
							frame.repaint();

						   
					}
				}
			});

			scroll2 = new JScrollPane(table2);  
			scroll2.setBounds(0, 100, 1000, 560);  
			
			DefaultTableModel model=(DefaultTableModel) table2.getModel();//��ñ��ģ��
			model.setRowCount(0);//��ձ���е�����
			model.setColumnIdentifiers(new Object[]{"�ռ���","�ʼ�����","ʱ��","�Է��Ƿ��Ѷ�"});
			
			client.sendRequestToServer(new IdData("OutputMySendMail",id));
			ObjListData listData = (ObjListData)client.receiveDataFromServer();
			int length = listData.getMailList().size();
			content = new String[length];
			mailID = new int[length];
			
			List<MailData>  results = listData.getMailList();
			int i = 0;
			for(MailData infor:results){  //�����ݼ��ص������
				String ifRead = "δ��";
				if(infor.ismRead()){
					ifRead = "�Ѷ�";
				}else{
					ifRead = "δ��";
				}
				content[i] = infor.getmContent();
				mailID[i] = infor.getmID();
				model.addRow(new Object[]{infor.getmRecipientName(),infor.getmTopic(),infor.getmTime(),ifRead});
				i++;
			}
			
			Helper.makeFace(table2);
			table2.setModel(model);//Ӧ�ñ��ģ��
			scroll2.setViewportView(table2);
			
			
			frame.add(readMyRecieveButton);
			frame.add(readMySendButton);
			frame.add(writeButton);
            frame.add(scroll2);
            frame.add(tipLab);
			frame.repaint();
		
		}
	}
	
	
	public class writeActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			frame.removeAll();
			recipientText.setEditable(true);
			topicText.setEditable(true);
			contentText.setEditable(true);
			recipientText.setText("");
			topicText.setText("");
			contentText.setText("");
			
			JButton queryButton = new JButton("����");
			queryButton.addActionListener(new queryActionPerformed());
	
			JButton sendButton = new JButton("����");
			sendButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(!"".equals(recipientText.getText()) && !"".equals(topicText.getText()) && !"".equals(contentText.getText())){
						MailData mData = new MailData("sendMail");
						mData.setmRecipientID(recipientText.getText().trim());
						mData.setmTopic(topicText.getText().trim());
						mData.setmContent(contentText.getText().trim());
						mData.setmSenderID(id);
						Date date = new Date();
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						mData.setmTime(format.format(date));
						client.sendRequestToServer(mData);
						RequestData reqData = (RequestData)client.receiveDataFromServer();
						if("true".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "���ͳɹ�","��ʾ�Ի���",1);
							recipientText.setText("");
							topicText.setText("");
							contentText.setText("");
						}else if("false".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�������ϵ����Ϣ","��ʾ�Ի���",1);
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "�������Ϊ��","��ʾ�Ի���",1);
					}
					
				}
			});
			
			
			recipientLab.setBounds(100, 20, 130, 30);
			recipientLab.setFont(new Font("�����п�",Font.BOLD, 25));
			recipientText.setBounds(220, 20, 200, 30);
			recipientText.setFont(new Font("����",Font.BOLD, 18));
			queryButton.setBounds(460,20,100,30);
			topicLab.setBounds(100,80,130,30);
			topicLab.setFont(new Font("�����п�",Font.BOLD, 25));
			topicText.setBounds(220,80,200,30);
			topicText.setFont(new Font("����",Font.BOLD, 18));
			contentLab.setBounds(100,150,100,20);
			contentLab.setFont(new Font("�����п�",Font.BOLD, 25));
			contentText.setBounds(100,190,800,340);
			contentText.setFont(new Font("����",Font.BOLD, 18));
			contentText.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			sendButton.setBounds(450,550,100,30);
			
			write.setLayout(null);
			write.setBounds(0, 60, 1000, 600);
			write.add(recipientLab);
			write.add(recipientText);
			write.add(queryButton);
			write.add(topicLab);
			write.add(topicText);
			write.add(contentLab);
			write.add(contentText);
			write.add(sendButton);
			write.setOpaque(false);
			
			frame.add(readMyRecieveButton);
			frame.add(readMySendButton);
			frame.add(writeButton);
			frame.add(write);
			frame.repaint();

		}
	}
	
	public class queryActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			final JFrame jFrame = new JFrame("������ϵ��"); 
		    String uName = recipientText.getText().trim();
		    if(!"".equals(uName)){
		    	MailData mData = new MailData("queryLinkman");
		    	mData.setmRecipientName(uName);
		    	client.sendRequestToServer(mData);
		    	ObjListData listData = (ObjListData)client.receiveDataFromServer();
		    	int length = listData.getInfoList().size();
		    	String[] title={"�û�ID","�û�����","Ժϵ","רҵ"};
		    	Object[][] obj = new Object[length][4]; 
		    	List<InfoData>  results = listData.getInfoList();
		    	int i = 0;
		    	 for(InfoData infor:results){  //�����ݼ��ص������
					 obj[i][0] = infor.getStuNumber();
					 obj[i][1] = infor.getName();
					 obj[i][2] = infor.getSchool();
					 obj[i][3] = infor.getMajor();
					 i++;
				 }
		    	final JTable tbl = new JTable(obj,title);
		    	tbl.setEnabled(false);
		    	tbl.setFont(new java.awt.Font("Dialog", 1, 15));
                Helper.makeFace(tbl);
		    	JScrollPane scroll = new JScrollPane(tbl);  
		    	scroll.setSize(500, 200);  
			
		    	tbl.addMouseListener(new MouseAdapter(){
		    		public void mouseClicked(MouseEvent e) { 
		    			int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
		    			recipientText.setText((String)tbl.getValueAt(row,0));
		    			jFrame.dispose();
		    		}
		    	});
		    	jFrame.getContentPane().add(scroll);
		    	jFrame.pack();
		    	jFrame.setLocationRelativeTo(null);
		    	jFrame.setVisible(true);
		    }else{
		    	JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ��","��ʾ�Ի���",1);
		    }
				
		}
	}
}
