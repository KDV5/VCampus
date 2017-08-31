package cn.seu.edu.LibView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.RequestData;

public class ManInsertView {
	JTable tbl=null;
	JPanel panel=new JPanel();
	JLabel label1=new JLabel ("���");
	JTextField bBookID=new JTextField(6);
	JLabel label2=new JLabel("����");
	JTextField bBook=new JTextField(30);
	JLabel label3=new JLabel("����");
	JTextField bAuthor=new JTextField(30);
	JLabel label4=new JLabel("����");
	JTextField bNumber=new JTextField( 6);
	JLabel label5=new JLabel("�洢�ص�");
	JTextField bPlace=new JTextField(30);
	JLabel label6=new JLabel("���");
	JTextField bIntro=new JTextField();
	String bookID=null;
	String intro=null;
	String book=null;
	String author=null;
	int number=0;
	String place=null;
	Client client;
	private JPanel frame;
	public ManInsertView(Client client,JPanel jp) throws SQLException//��ʼ������
	{	
//		cont.setLayout(null);
		this.client=client;
		this.frame=jp;
    	panel.setLayout(null);
		panel.setBounds(0, 0, 1000, 700);
		panel.setOpaque(false);
		Font font1 = new Font("������κ",Font.BOLD,25);
		Font font2=new Font("����",Font.BOLD, 18);
		label1.setFont(font1);
//		label1.setEditable(false);
		label1.setBounds(100, 20, 120,40);
		label2.setFont(font1);
		label2.setBounds(100, 80, 120, 40);
		label3.setFont(font1);
		label3.setBounds(100, 140, 120, 40);
		label4.setFont(font1);
		label4.setBounds(100, 200, 120, 40);
		label5.setFont(font1);
		label5.setBounds(100, 260, 120, 40);
		label6.setFont(font1);
		label6.setBounds(100, 320, 600, 40);
		bBookID.setBounds(250, 20,400, 40);
		bBook.setBounds(250, 80, 400, 40);
		bAuthor.setBounds(250, 140, 400, 40);
		bNumber.setBounds(250, 200, 400, 40);	
		bPlace.setBounds(250, 260, 400,40);
		bIntro.setBounds(100, 370, 600, 300);	
		panel.add(label1);
		panel.add(bBookID);
		panel.add(label2);
		panel.add(bBook);
		panel.add(label3);
		panel.add(bAuthor);
		panel.add(label4);
		panel.add(bNumber);
		panel.add(label5);
		panel.add(bPlace);
		panel.add(label6);
		panel.add(bIntro);
		JButton button=new JButton("ͼ�����");
		button.setBounds(750,445,100,50);
		panel.add(button);button.addActionListener(new InsertListener());
		frame.add(panel);
    
	}
	class InsertListener  implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			try{
			bookID=bBookID.getText();
			book=bBook.getText();
			author=bAuthor.getText();	
			place=bPlace.getText();
			intro=bIntro.getText();
			if(bookID.isEmpty()||book.isEmpty()||author.isEmpty()||bNumber.getText().isEmpty()||place.isEmpty()||intro.isEmpty()){
				 JOptionPane.showMessageDialog(null, "��Ϣ����ȫ","���󾯸�",1);
			}
			else{
				number=Integer.parseInt(bNumber.getText());
				if(number>=0){
			client.sendRequestToServer(new LibraryData("insert",bookID,book,author,number,place,intro));
			RequestData req=(RequestData)client.receiveDataFromServer();
			if(req.getRequest().equals("true"))
			{
				JOptionPane.showMessageDialog(null, "�ɹ����","֪ͨ����",1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "�����쳣","���󾯸�",1);
			}	
				}
				else
				JOptionPane.showMessageDialog(null, "����������Ϣ����","���󾯸�",1);
			}
			
		
	}catch(Exception e2){
	 JOptionPane.showMessageDialog(null, "����������Ϣ����","���󾯸�",1);

}
}
	}
}
