package cn.seu.edu.LibView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;

public class ManDeleteView implements ItemListener ,ActionListener {
	JPanel frame = new JPanel();
	int flag=1;
	JTable tbl=null;
	JPanel panel=new JPanel();
	String bookID=null;
	String book=null;
	String author=null;
	int number=0;
    Client client; 
	JLabel label1=new JLabel("书号:");
	JTextField bBookID=new JTextField(6);
	JLabel label2=new JLabel("书名:");
	JTextField bBook=new JTextField(30);
	JLabel label3=new JLabel("作者:");
	JTextField bAuthor=new JTextField(30);
	JLabel label4=new JLabel("数量:");
	JTextField bNumber=new JTextField(30);
	JRadioButton r1=new JRadioButton("用户挂失");
	JRadioButton r2=new JRadioButton("管理员挂失");     
	JButton button=new JButton("挂失");
	public ManDeleteView(Client client,JPanel jp) throws SQLException//初始化函数
	{	
		this.client=client;
		this.frame=jp;
		
		ButtonGroup group=new ButtonGroup();
        group.add(r1);
        group.add(r2);
        r1.setSelected(true);
        r1.setOpaque(false);
        r2.setOpaque(false);
        panel.setLayout(null);
		panel.setBounds(0, 0, 1000,700);
		Font font = new Font("华文新魏",Font.BOLD,32);
		Font font1=new Font("宋体",Font.BOLD, 18);
		label1.setFont(font);
		label1.setBounds(190, 60, 150, 30);
		label2.setFont(font);
		label2.setBounds(190, 160, 150, 30);
		label3.setFont(font);
		label3.setBounds(190, 260, 150, 30);
		label4.setFont(font);
		label4.setBounds(190, 360, 150, 30);
		r1.setBounds(280,480,150,50);
		r2.setBounds(400,480,150,50);
		bBookID.setBounds(250, 110, 250, 30);
		bBookID.setFont(font1);
		bBook.setBounds(250, 210, 250, 30);
		bBook.setFont(font1);
		bAuthor.setBounds(250, 310, 250, 30);
		bAuthor.setFont(font1);
		bNumber.setBounds(250, 410, 250, 30);
		bNumber.setFont(font1);
		panel.add(label1);
		panel.add(bBookID);
		panel.add(label2);
		panel.add(bBook);
		panel.add(label3);
		panel.add(bAuthor);
		panel.add(label4);
		panel.add(bNumber);
//		panel.add(r1);
//		panel.add(r2);
     	r1.addItemListener(this);
		r2.addItemListener(this);
		button.setBounds(550,530,150,30);
		button.addActionListener(new DeleteListener());
		panel.add(button);
		panel.setOpaque(false);
//		ConnectAccess c=new ConnectAccess();
//		LibraryReaderConnection L=new LibraryReaderConnection();
//		L.personalQuery(s);
//		panel.add(scroll);
		frame.add(panel);
  
	} 
	public void itemStateChanged(ItemEvent e){
		if(e.getSource()==r1){
			flag=1;
		}
		else {
			flag=2;
		}
	}
	class DeleteListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			 bookID=bBookID.getText();
			 book=bBook.getText();
			 author=bAuthor.getText();
			 String s=bNumber.getText();
			 if(bookID.isEmpty()||book.isEmpty()||author.isEmpty()||s.isEmpty()){
				 JOptionPane.showMessageDialog(null, "输入信息不完整","错误警告",1);}
			 else{
				 try{
				 number=Integer.parseInt(s);
				 if(number>0){
			 client.sendRequestToServer(new LibraryData("delete",bookID,book,author,number,flag));
             RequestData rData=(RequestData)client.receiveDataFromServer();
             if(rData.getRequest().equals("true")){
            	 JOptionPane.showMessageDialog(null, "成功挂失该书本","通知",1);} 	 	 
             else if(rData.getRequest().equals("no"))
             {
            	 JOptionPane.showMessageDialog(null, "信息有误或该书本不存在","错误警告",1);}    	 
             
             else if(rData.getRequest().equals("false")){
            	 JOptionPane.showMessageDialog(null, "程序出错","错误警告",1);}	 
				 }
				 else
					 JOptionPane.showMessageDialog(null, "输入数量信息有误","错误警告",1); 
					 
		}catch(Exception e1){
		JOptionPane.showMessageDialog(null, "输入信息有误","错误警告",1);
		}
			 }
				
		}
		
			
			// TODO 自动生成的方法存根
			
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}
	

}
