package cn.seu.edu.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;

public class FeedbackView implements ItemListener {
	Client client;
	String id;
	JPanel frame = new JPanel();
	JPanel panel = new JPanel();
	
	String comboBox[] = {"�̵�","����","ͼ���","����","��Ϣ����","ʳ��","�������г�","����","��ѧ¥","����"};
	JComboBox jcb = new JComboBox(comboBox);
	
	JLabel typeLabel = new JLabel("��ѡ����ҪͶ�ߵ����");
	JLabel proLabel = new JLabel("�������������ǣ�");
	JTextArea proText = new JTextArea(25,50);
	JLabel phoneLabel = new JLabel("������ϵ��ʽ�ǣ�");
	JTextField phoneText = new JTextField(15);	
	JButton sureButton = new JButton("�ύ");
	JButton myFeedbackButton = new JButton("�鿴�ҵ�Ͷ�߼�¼");
	JLabel myFeedbackLab = new JLabel("�ҵ�Ͷ�߼�¼��");
	JLabel feedbackLab = new JLabel("�ӵ���Ͷ�߼�¼��");
	JLabel returnFeedbackLab = new JLabel("�޸Ĵ��������ɷ�����Ͷ����");
	
	String feedbackType = null;
	String feedbackProblem = null;
	String feedbackPhone = null;
	String feedbackTime = null;
	TableModel tableModel;
	int[] fID ;
	String result = "δ����";
	JTable tbl;
	
	public FeedbackView(final Client client ,final String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.frame=jp;
		panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 700);
		
		//����Ա��Ȩ��������
		if(id.length() == 6){
			client.sendRequestToServer(new IdData("administratorCheckFeedback",id));
			ObjListData listData = (ObjListData)client.receiveDataFromServer();
			 int length = listData.getFeedbackList().size();
			 Object[][] obj = new Object[length][5];  
			 fID = new int[length];
			 List<FeedbackData>  results = listData.getFeedbackList();
			 int i = 0;
			 for(FeedbackData infor:results){  //�����ݼ��ص������
				 obj[i][0] = infor.getfType();
				 obj[i][1] = infor.getfProblem();
				 obj[i][2] = infor.getfPhone();
				 obj[i][3] = infor.getfTime();
				 obj[i][4] = infor.getfResult();
				 fID[i] = infor.getfID();
				 i++;
			 }
			
		
			String[] title={"Ͷ�����","Ͷ������","��ϵ�绰","Ͷ��ʱ��","������"};
			tbl = new JTable(obj,title) {
				  public boolean isCellEditable(int row, int column) {
					   if (column == 4)
						   return true; 
					   else
						   return false;
					  }
					 };
					 
			
		    
			
			tbl.isCellSelected(0,5);
			tbl.setRowHeight(40);
			tbl.setFont(new java.awt.Font("Dialog", 1, 15));
			Helper.makeFace(tbl);
			JScrollPane scroll = new JScrollPane(tbl);  
			scroll.setBounds(30,80,900,550);  

			tableModel = tbl.getModel();
			tbl.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) { 
						{
							
								 int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
								 Object rs=tbl.getValueAt(row,4); 
								 result = rs.toString();
							
						}
					}
			});
			tableModel.addTableModelListener(new TableModelListener(){
			    public void tableChanged(final TableModelEvent e1){
			    	   
			    			int j = e1.getColumn();
					    	int i = e1.getFirstRow();
			    			Object obj = tableModel.getValueAt(i, j);
					    	String fResult = (String)obj;
					    	if(!result.equals(fResult)){
					    		FeedbackData feedbackData = new FeedbackData("DealWithFeedback");
					    		feedbackData.setfResult(fResult);
					    		feedbackData.setfID(fID[i]);
					    		feedbackData.setId(id);
					    		client.sendRequestToServer(feedbackData);
					    		RequestData reqData = (RequestData)client.receiveDataFromServer();
					    		if("sendMail".equals(reqData.getRequest())){
					    			client.sendRequestToServer(reqData);
					    			RequestData rData = (RequestData)client.receiveDataFromServer();
					    			if("true".equals(rData.getRequest())){
					    				JOptionPane.showMessageDialog(null, "�����ɹ�","��ʾ�Ի���",1);
					    			}else if("false".equals(reqData.getRequest())){
					    				JOptionPane.showMessageDialog(null, "����ʧ��","��ʾ�Ի���",1);
					    			}
					    		}else if("false".equals(reqData.getRequest())){
					    			JOptionPane.showMessageDialog(null, "����ʧ��","��ʾ�Ի���",1);
					    		}
					    	}
			    		}
			    	});
			    	
			returnFeedbackLab.setBounds(350,30, 300, 30);
			feedbackLab.setBounds(30, 30, 300, 30);
			feedbackLab.setFont(new Font("�����п�",Font.BOLD, 25));
			frame.add(feedbackLab);
			frame.add(returnFeedbackLab);
			frame.add(scroll);
			
			
		//ѧ��/��ʦ��Ȩ��������
		}else{		
		proText.setLineWrap(true);
		sureButton.addActionListener(new sureActionPerformed());
		myFeedbackButton.addActionListener(new myFeedbackActionPerformed());
		jcb.addItemListener(this);
		jcb.setSelectedItem("����");
	
		typeLabel.setBounds(100,20,300,30);
		typeLabel.setFont(new Font("�����п�",Font.BOLD, 25));
		jcb.setBounds(400,20,200,30);
		proLabel.setBounds(100,80,300,30);
		proLabel.setFont(new Font("�����п�",Font.BOLD, 25));
		proText.setBounds(100,120,800,340);
		proText.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
		phoneLabel.setBounds(100, 500, 300, 30);
		phoneLabel.setFont(new Font("�����п�",Font.BOLD, 25));
		phoneText.setBounds(300, 500, 200, 30);
		sureButton.setBounds(540, 550, 100, 30);
		myFeedbackButton.setBounds(660, 550, 200, 30);
		
		panel.add(typeLabel);
		panel.add(jcb);
	
		panel.add(proLabel);
		panel.add(proText);
		panel.add(phoneLabel);
		panel.add(phoneText);
		panel.add(sureButton);
		panel.add(myFeedbackButton);
		}
		panel.setOpaque(false);
		frame.add(panel); 
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			feedbackType = (String)e.getItem();
		}
	}

	
	public class sureActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			if(feedbackType != null && !"".equals(proText.getText())){
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				feedbackTime = format.format(date);
				feedbackProblem = proText.getText().trim();
				feedbackPhone = phoneText.getText().trim();
				client.sendRequestToServer(new FeedbackData("feedback",id,feedbackType,feedbackProblem,feedbackPhone,feedbackTime));
				RequestData reqData = (RequestData)client.receiveDataFromServer();
				if("true".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "�ύ�ɹ��������ĵȴ��������","��ʾ�Ի���",1);
					proText.setText("");
					phoneText.setText("");
					jcb.setSelectedItem("����");
				}else if("false".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "�ύʧ��","��ʾ�Ի���",1);
				}
			}else{
				JOptionPane.showMessageDialog(null, "����Ͷ������Ͷ������","��ʾ�Ի���",1);
			}
		}
	}
	
	public class myFeedbackActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			panel.removeAll();
			
			client.sendRequestToServer(new IdData("myFeedback",id));
			ObjListData listData = (ObjListData)client.receiveDataFromServer();
			 int length = listData.getFeedbackList().size();
			 Object[][] obj = new Object[length][5];  
			 List<FeedbackData>  results = listData.getFeedbackList();
			 int i = 0;
			 for(FeedbackData infor:results){  //�����ݼ��ص������
				 obj[i][0] = infor.getfType();
				 obj[i][1] = infor.getfProblem();
				 obj[i][2] = infor.getfPhone();
				 obj[i][3] = infor.getfTime();
				 obj[i][4] = infor.getfResult();
				 i++;
			 }
			
		
			String[] title={"Ͷ�����","Ͷ������","��ϵ�绰","Ͷ��ʱ��","������"};
			JTable tbl = new JTable(obj,title);
			tbl.setRowHeight(40);
			tbl.setEnabled(false);
			tbl.setFont(new java.awt.Font("Dialog", 1, 15));
			Helper.makeFace(tbl);
			JScrollPane scroll = new JScrollPane(tbl);  
			scroll.setBounds(30, 80, 920, 550); 
			
			myFeedbackLab.setBounds(30, 30, 300, 30);
			myFeedbackLab.setFont(new Font("�����п�",Font.BOLD, 25));
			panel.add(myFeedbackLab);
			panel.add(scroll);
			frame.repaint();
	     
		}
	}
	
}
