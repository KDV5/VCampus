package cn.seu.edu.LibView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
public class ManReturnView {
	JPanel frame = new JPanel();
	JPanel panel=new JPanel();
	JLabel idLab=new JLabel("输入ID查看个人借书情况");
	JTextField id=new JTextField(8);
	JButton queryButton=new JButton("查询");
	JButton button=new JButton("还书");
	JTable tbl=null;
	String ID=null;
	JScrollPane scroll=null;
	Client client;
	JPanel panel1=new JPanel();
	public ManReturnView(Client client,JPanel jp) throws SQLException//初始化函数
	{	
			this.client=client;
			this.frame=jp;
			
		    panel.setBounds(0,0,1000,200);
		    panel1.setBounds(0,200,1000,500);
		    panel1.setLayout(null);
		    panel.setLayout(null);
		    panel.setOpaque(false);
		    panel1.setOpaque(false);
//		    ConnectAccess c=new ConnectAccess();
//		    LibraryReaderConnection L=new LibraryReaderConnection();
//		    L.personalQuery(s);
		    Font font1 = new Font("华文新魏",Font.BOLD,32);
		    idLab.setBounds(100, 50, 800, 50);
		    idLab.setFont(font1);
		    //idLab.setEditable(false);
		    id.setBounds(100, 120, 340, 30);
		    queryButton.setBounds(520, 120, 100, 30);
		    panel.add(id);
		    panel.add(idLab);
		    panel.add(queryButton);
		    queryButton.addActionListener(new ReturnListener());
	    	button.addActionListener(new ReturnListener());
	    	panel.add(button);
	    	button.setBounds(680, 120, 100, 30);
	    	button.addActionListener(new ReturnListener());
	    	frame.add(panel);
	    	frame.add(panel1);
           }
class ReturnListener implements ActionListener{
	 public void actionPerformed (ActionEvent e){
		 if(e.getSource()==queryButton){
		    	 panel1.removeAll();
		    	  ID=id.getText();
		    	  tbl=new JTable(){public boolean isCellEditable(int row, int column) {return false;}};
		  	    DefaultTableModel model=(DefaultTableModel) tbl.getModel();//获得表格模型
		  		model.setRowCount(0);//清空表格中的数据
		  		model.setColumnIdentifiers(new Object[]{"书号", "书名", "作者","存储位置","存储量","借书日期","还书日期"});
		  		
		  		client.sendRequestToServer(new LibraryData("personQuery",ID));
		  		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		  		List<LibraryData>  results = listData.getLibraryList();
		  		for(LibraryData infor:results){  //将数据加载到表格中
		  			model.addRow(new Object[]{infor.getbID(),infor.getbName(),infor.getbAuthor(),infor.getbPlace(),infor.getbStorage(),infor.getbLendDate(),infor.getbReturnDate()});
		  		}
			
		  	    scroll = new JScrollPane(tbl);
				scroll.setBounds(80,20, 880, 400);  
				Helper.makeFace(tbl);
				tbl.setModel(model);//应用表格模型
				scroll.setViewportView(tbl);
		    	panel1.add(scroll);
		    	panel1.repaint();
		    	panel.repaint();
		 }
		 if(e.getSource()==button){
		 int row=-1;
	     row = tbl.getSelectedRow();
//		 System.out.print(row);
	     if(row!=-1){
		 Object cn=tbl.getValueAt(row, 0);
		 if(cn==null){
			 JOptionPane.showMessageDialog(null, "当前没有借书","错误警告",1);
			 }
		 else if(ID==null){
			 JOptionPane.showMessageDialog(null, "没有输入ID","错误警告",1);	 }
		 else{
		     	 panel1.removeAll();
				 client.sendRequestToServer(new LibraryData("return",cn.toString(), ID));
//				 client.sendRequestToServer(new LibraryData("personQuery",ID));
	                RequestData rData=(RequestData)client.receiveDataFromServer();
	                if(rData.getRequest().equals("true")){
	                	JOptionPane.showMessageDialog(null, "还书成功","提示框",1);	
	           		 try {
	 		            Thread.sleep(500);
	 		        } catch (InterruptedException e1) {
	 		            // TODO Auto-generated catch block
	 		            e1.printStackTrace();
	 		        }
	           		tbl=new JTable(){public boolean isCellEditable(int row, int column) {return false;}};
			  	    DefaultTableModel model=(DefaultTableModel) tbl.getModel();//获得表格模型
			  		model.setRowCount(0);//清空表格中的数据
			  		model.setColumnIdentifiers(new Object[]{"书号", "书名", "作者","存储位置","存储量","借书日期","还书日期"});
			  		
			  		client.sendRequestToServer(new LibraryData("personQuery",ID));
			  		ObjListData listData = (ObjListData)client.receiveDataFromServer();
			  		List<LibraryData>  results = listData.getLibraryList();
			  		for(LibraryData infor:results){  //将数据加载到表格中
			  			model.addRow(new Object[]{infor.getbID(),infor.getbName(),infor.getbAuthor(),infor.getbPlace(),infor.getbStorage(),infor.getbLendDate(),infor.getbReturnDate()});
			  		}
				
			  	    scroll = new JScrollPane(tbl);
					scroll.setBounds(80,20, 880, 400);  
					Helper.makeFace(tbl);
					tbl.setModel(model);//应用表格模型
					scroll.setViewportView(tbl);
					panel1.removeAll();
			    	panel1.add(scroll);
			    	panel1.repaint();
			    	panel.repaint();
	       		
	                }
	                else if(rData.getRequest().equals("false")){
	                	JOptionPane.showMessageDialog(null, "还书失败","错误警告",1);	
	                }
	                
		 }
		
		 }
			 }
		 }
	 }

}
