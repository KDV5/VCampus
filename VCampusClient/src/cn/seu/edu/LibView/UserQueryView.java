package cn.seu.edu.LibView;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class UserQueryView
{
	Client client;
    String ID;
	//JFrame frame = new JFrame("ͼ����û���ѯϵͳ");
	JTable tbl=null;
	JLabel tip=new JLabel("��ǰ�ѽ�ͼ��");
    JTextField searchBook=new JTextField(30);
	JPanel panel=new JPanel();
	Font fnt=new Font("������κ",Font.BOLD,30);
	Font fnt1=new Font("΢���ź�",Font.PLAIN,15);
	public UserQueryView(Client client,String id,JPanel jp) throws SQLException//��ʼ������
	{	
		this.client = client;
    	this.ID = id;
    	this.panel=jp;
    	
		panel.setLayout(null);
		panel.setBounds(0, 0,1000, 700);
//		panel.setBackground(Color.CYAN);	
		tip.setFont(fnt);
		tip.setBounds(100, 30, 800, 50);
		panel.add(tip);
//		ConnectAccess c=new ConnectAccess();
//		LibraryReaderConnection L=new LibraryReaderConnection();
//		L.personalQuery(s);
		tbl=new JTable(){public boolean isCellEditable(int row, int column) {return false;}};
	    DefaultTableModel model=(DefaultTableModel) tbl.getModel();//��ñ��ģ��
		model.setRowCount(0);//��ձ���е�����
		model.setColumnIdentifiers(new Object[]{"���", "����", "����","�洢λ��","�洢��","��������","��������"});
		
		client.sendRequestToServer(new LibraryData("personQuery",ID));
		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		List<LibraryData>  results = listData.getLibraryList();
		for(LibraryData infor:results){  //�����ݼ��ص������
			model.addRow(new Object[]{infor.getbID(),infor.getbName(),infor.getbAuthor(),infor.getbPlace(),infor.getbStorage(),infor.getbLendDate(),infor.getbReturnDate()});
		}
		
		JScrollPane scroll = new JScrollPane(tbl);
		scroll.setBounds(100, 100,880, 500);  
		Helper.makeFace(tbl);
		tbl.setModel(model);//Ӧ�ñ��ģ��
		scroll.setViewportView(tbl);
		panel.add(scroll);
				
	}
	public JTable getTable() {
		// TODO �Զ����ɵķ������
		return tbl;
	}

}
