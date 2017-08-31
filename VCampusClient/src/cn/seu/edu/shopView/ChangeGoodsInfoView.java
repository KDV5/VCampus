package cn.seu.edu.shopView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.shopView.BuyGoodsView.BrowseListener;
import cn.seu.edu.shopView.BuyGoodsView.BuyListener;
import cn.seu.edu.shopView.BuyGoodsView.SearchListener;

public class ChangeGoodsInfoView {
	Client client;
	String id;
	JPanel frame = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JTextField text;
	JLabel lab;
	JTable table;
	TableModel tableModel;
	
	public ChangeGoodsInfoView(final Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.frame=jp;
		
		frame.setLayout(null);
    	frame.setSize(1000,700);	
    	
		panel2.setBounds(20,20,900,300);
		panel2.setLayout(null);
		JLabel Text=new JLabel("������Ʒ���Բ�ѯ���޸�");
		Text.setFont(new Font("������κ",Font.BOLD, 25));
		Text.setBounds(10,10,850,30);
		text=new JTextField(30);
		text.setFont(new Font("����",Font.BOLD, 18));
		text.setBounds(10,50,250,30);
		JButton searchGoods=new JButton("����");
		searchGoods.addActionListener(new SearchListener());
		searchGoods.setBounds(300,50,100,30);
		JButton deleteButton=new JButton("ɾ����Ʒ");
		deleteButton.addActionListener(new DeleteListener());
		deleteButton.setBounds(400, 600, 100, 30);

		lab = new JLabel("����Ϊ�����ڳ��۵�ȫ����Ʒ���޸ĺ��Զ�����");
		lab.setFont(new Font("������κ",Font.BOLD, 25));
		lab.setBounds(30,120,900,40);
		
		panel1.setBounds(30,170,900,400);
		panel1.setOpaque(false);
		panel2.add(text);
		panel2.add(Text);
		panel2.add(searchGoods);
		panel2.setOpaque(false);
		frame.add(lab);
		frame.add(deleteButton);


		    panel1.setLayout(null);
		    String[] title={"��ƷID","��Ʒ��","�۸�","�����","������Ϣ"};
		    ShopData sData = new ShopData("shopSearch");
			 sData.setuGoodsName("");
			 sData.setuSellerID(id);
			 client.sendRequestToServer(sData);
			 ObjListData listData = (ObjListData)client.receiveDataFromServer();
			 int length = listData.getShopList().size();
			 Object[][] obj = new Object[length][5];  
			 List<ShopData>  results = listData.getShopList();
			 int i = 0;
			 for(ShopData infor:results){  //�����ݼ��ص������
				 obj[i][0] = infor.getuGoodsID();
				 obj[i][1] = infor.getuGoodsName();
				 obj[i][2] = infor.getuPrice();
				 obj[i][3] = infor.getuStorage();
				 obj[i][4] = infor.getuDetail();
				 i++;
			 }
					
		    table=new JTable(obj,title){
		    public boolean isCellEditable(int row, int column) {
				   if (column == 0)
					   return false; 
				   else
					   return true;
				  }
				 };
		    table.setEnabled(true);
		    tableModel = table.getModel();
		    tableModel.addTableModelListener(new ChangeListener());
			    		   
		    JScrollPane scroll = new JScrollPane(table);
		    scroll.setBounds(0,0,900,400);
			
			Helper.makeFace(table);
			table.setRowHeight(30);
			table.setFont(new java.awt.Font("Dialog", 1, 15));
			scroll.setViewportView(table);
			scroll.setBounds(0,0,900,400);
    		panel1.add(scroll);
    		
		
    	frame.add(panel1);
		frame.add(panel2);
		frame.setVisible(true);
	}
	
	class SearchListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){
		
			 panel1.removeAll();
			 lab.setText("����Ϊ�������������۳�����Ʒ���޸ĺ��Զ�����");
			 String[] title={"��ƷID","��Ʒ��","�۸�","�����","������Ϣ"};
			    ShopData sData = new ShopData("shopSearch");
				 sData.setuGoodsName(text.getText().trim());
				 sData.setuSellerID(id);
				 client.sendRequestToServer(sData);
				 ObjListData listData = (ObjListData)client.receiveDataFromServer();
				 int length = listData.getShopList().size();
				 Object[][] obj = new Object[length][5];  
				 List<ShopData>  results = listData.getShopList();
				 int i = 0;
				 for(ShopData infor:results){  //�����ݼ��ص������
					 obj[i][0] = infor.getuGoodsID();
					 obj[i][1] = infor.getuGoodsName();
					 obj[i][2] = infor.getuPrice();
					 obj[i][3] = infor.getuStorage();
					 obj[i][4] = infor.getuDetail();
					 i++;
				 }
				 table = new JTable(obj,title){
					    public boolean isCellEditable(int row, int column) {
							   if (column == 0)
								   return false; 
							   else
								   return true;
							  }
							 };
					    table.setEnabled(true);
					    tableModel = table.getModel();
					    tableModel.addTableModelListener(new ChangeListener());
				
				 
				JScrollPane scroll = new JScrollPane(table);
			    scroll.setBounds(0,0,900,400);
				Helper.makeFace(table);
				table.setRowHeight(30);
				table.setFont(new java.awt.Font("Dialog", 1, 15));
				
				scroll.setViewportView(table);
	    		panel1.add(scroll);
	    		panel1.repaint();

	    	
				
		 }
	}
	
	class DeleteListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){
			 int row=table.getSelectedRow();
			 Object cn=table.getValueAt(row, 0);
			 int option=JOptionPane.showConfirmDialog(null, "ȷ��ɾ������Ʒ", "��ʾ�Ի���", JOptionPane.YES_NO_OPTION);
				switch (option) {
			    case JOptionPane.YES_OPTION: 
			    	IdData iData = new IdData("DeleteGoods",cn.toString());
			    	client.sendRequestToServer(iData);
			    	RequestData reqData = (RequestData)client.receiveDataFromServer();
					if("true".equals(reqData.getRequest())){
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�","��ʾ�Ի���",1);
						//frame.dispose();
						frame.removeAll();
						try {
				            Thread.sleep(500);
				        } catch (InterruptedException ee) {
				            ee.printStackTrace();
				        }
						new ChangeGoodsInfoView(client,id,frame);
					}else if("false".equals(reqData.getRequest())){
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��","��ʾ�Ի���",1);
					}
			    case JOptionPane.NO_OPTION:
			    	break;				    
			    	}

		 }
	}
	class ChangeListener implements TableModelListener{
		  public void tableChanged(TableModelEvent e){

		        ShopData shopData = new ShopData("UpdateGoods");
		        int row = e.getFirstRow();
		        String uGoodsID = tableModel.getValueAt(row, 0).toString();
		        String uGoodsName = tableModel.getValueAt(row, 1).toString();
		        String uPrice = tableModel.getValueAt(row, 2).toString();
		        String uStorage = tableModel.getValueAt(row, 3).toString();
		        String uDetail = tableModel.getValueAt(row, 4).toString();
		        shopData.setuGoodsID(uGoodsID);
		        shopData.setuGoodsName(uGoodsName);
		        shopData.setuPrice(uPrice);
		        shopData.setuStorage(Integer.parseInt(uStorage));
		        shopData.setuDetail(uDetail);
		        client.sendRequestToServer(shopData);
		        RequestData reqData = (RequestData)client.receiveDataFromServer();
				if("true".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�","��ʾ�Ի���",1);
				}else if("false".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "�޸�ʧ��","��ʾ�Ի���",1);
				}
		  }
	}
}
