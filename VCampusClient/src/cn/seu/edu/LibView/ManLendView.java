package cn.seu.edu.LibView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;

public class ManLendView implements ActionListener{
    Client client;
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JPanel frame=new JPanel();
    JButton button=new JButton("借书");
    JLabel idLab=new JLabel("请输入ID号");
    JTextField id=new JTextField(6);
    JTable tbl=new JTable();
    String ID=null;
    Font fnt=new Font("微软雅黑",Font.PLAIN,15);
	public ManLendView(Client client,JPanel jp) throws SQLException{
		this.client=client;
		this.frame=jp;		
		Search s=new Search(client,jp);
		 panel1=s.panel1;
		 panel2=s.panel2;
		 tbl=s.tbl;
		frame.setLayout(null);
		button.addActionListener(new LendListener());
		button.setBounds(550,140,100,30);
		frame.setSize(1000, 700);
		idLab.setBounds(100, 140, 200,30);
		idLab.setFont(new Font("华文新魏",Font.BOLD,25));
		id.setBounds(258, 140, 240, 30);
		panel1.add(button);
		panel1.setBounds(0,0,1000,200);
		panel2.setBounds(0, 200, 1000, 500);
		panel1.add(idLab);
		panel1.add(id);
		frame.add(panel1);
		frame.add(panel2);
	}
	public class LendListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==button){
				 int row=-1;
			 row=tbl.getSelectedRow();
             ID=id.getText();
			if(row!=-1&&(!ID.isEmpty())){
 				 try{
 					 Object cn=tbl.getValueAt(row, 0);
					 client.sendRequestToServer(new LibraryData("lend",(String) cn, ID));
					 RequestData rData=(RequestData)client.receiveDataFromServer();
					 if(rData.getRequest().equals("out")){
						 JOptionPane.showMessageDialog(null, "同学你的借书上限到了","错误警告",1);
					 }
					 else	 if(rData.getRequest().equals("false")){
						 JOptionPane.showMessageDialog(null, "当前书本已经借过","错误警告",1);}
					 else{
//						 System.out.print(cn+"else");
						 JOptionPane.showMessageDialog(null, "借书成功","提示框",1);
						    DefaultTableModel model=(DefaultTableModel) tbl.getModel();//获得表格模型
							model.setRowCount(0);//清空表格中的数据
							model.setColumnIdentifiers(new Object[]{ "书号", "书名", "作者","存储位置","可借量","总量","借阅次数","简介"});
							
							client.sendRequestToServer(new LibraryData("Search","uBook",""));
							ObjListData listData = (ObjListData)client.receiveDataFromServer();
							List<LibraryData>  results = listData.getLibraryList();
							for(LibraryData infor:results){  //将数据加载到表格中
								model.addRow(new Object[]{infor.getbID(),infor.getbName(),infor.getbAuthor(),infor.getbPlace(),infor.getbStorage(),infor.getTotal(),infor.getbLendNumber(),infor.getbIntro()});
							}
							
					    panel2.removeAll();
						JScrollPane scroll = new JScrollPane(tbl);
						scroll.setBounds(100,0,900,450);  
						Helper.makeFace(tbl);
						tbl.setModel(model);//应用表格模型
						scroll.setViewportView(tbl);
						panel2.add(scroll);
					   
						
					     panel2.repaint();
					     panel1.repaint();
					 }
					}
  			 catch(Exception e1){
                   e1.printStackTrace();
  					 }
 				 }
			else
				JOptionPane.showMessageDialog(null, "ID为空或未选中所需图书","错误警告",1);
			 }}
		 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
	}}