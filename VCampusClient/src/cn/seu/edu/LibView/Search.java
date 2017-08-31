package cn.seu.edu.LibView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.LibraryData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;

public class Search implements ItemListener{
	Client client;
	String ID=null;
	String search=null;
	JTable tbl=null;
	JLabel tip=new JLabel("搜索图书");
	JPanel frame = new JPanel();
    JTextField searchBook=new JTextField(30);
	public JPanel panel1=new JPanel();
	public JPanel panel2=new JPanel();
	JRadioButton r1=new JRadioButton("按书名查询");
	JRadioButton r2=new JRadioButton("按作者查询");       
	JRadioButton r3=new JRadioButton("按书号查询");
	JButton searchButton=new JButton("搜索");
	Font fnt=new Font("华文新魏",Font.PLAIN,20);
	Font fnt2=new Font("华文行楷",Font.PLAIN,15);
	public int flag=1;
	public Search(Client client,JPanel jp) throws SQLException {
		this.client=client;
		this.frame=jp;
		panel1.setLayout(null);
 		panel1.setOpaque(false);
  		panel2.setLayout(null);
  		panel2.setOpaque(false);
		//frame.setSize(1000, 700);
//		panel.setSize(1200, 800);
    	panel1.setBounds(0,0,1000,150);
    	panel2.setBounds(0,150,1000,500);
		ButtonGroup group=new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);
        tip.setFont(fnt);
        r1.setFont(fnt);
        r2.setFont(fnt);
        r3.setFont(fnt);
        tip.setBounds(100, 20, 800,30);
        tip.setFont(new Font("华文新魏",Font.BOLD,30));
        r1.setSelected(true);
        r1.setBounds(360, 80,150, 30);
        r1.setFont(new Font("华文新魏",Font.BOLD,20));
        r2.setBounds(520, 80, 150, 30);
        r2.setFont(new Font("华文新魏",Font.BOLD,20));
        r3.setBounds(680,80, 150, 30);
        r3.setFont(new Font("华文新魏",Font.BOLD,20));
        r1.setOpaque(false);
        r2.setOpaque(false);
        r3.setOpaque(false);
		//frame.setLocation(0, 0);
		searchBook.setBounds(100, 80,250,30);
		searchBook.setFont(fnt);
        searchButton.setFont(fnt);
		searchButton.setBounds(850, 80, 80, 30);
		searchButton.addActionListener(new SearchListener());
		panel1.add(tip);
		panel1.add(searchBook);
	    panel1.add(r1);
		panel1.add(r2);
	    panel1.add(r3); 
	    panel1.add(searchButton);
//	    SearchView s=new SearchView("","uBook");
//	    panel2= s.getPanel();
	   
	    
	    tbl=new JTable(){public boolean isCellEditable(int row, int column) {return false;}};
	    DefaultTableModel model=(DefaultTableModel) tbl.getModel();//获得表格模型
		model.setRowCount(0);//清空表格中的数据
		model.setColumnIdentifiers(new Object[]{ "书号", "书名", "作者","存储位置","可借量","总量","借阅次数","简介"});		
		client.sendRequestToServer(new LibraryData("Search","uBook",""));
		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		List<LibraryData>  results = listData.getLibraryList();
		for(LibraryData infor:results){  //将数据加载到表格中
			model.addRow(new Object[]{infor.getbID(),infor.getbName(),infor.getbAuthor(),infor.getbPlace(),infor.getbStorage(),infor.getTotal(),infor.getbLendNumber(),infor.getbIntro()});
		}
		
	/*	tbl.setRowHeight(50);
//		tbl.setEnabled(false);
		tbl.setShowVerticalLines (false);
		tbl.setShowHorizontalLines(false);
		tbl.setFont(fnt);*/
	JScrollPane scroll = new JScrollPane(tbl);
	scroll.setBounds(100,0,900,450);  
	Helper.makeFace(tbl);
	tbl.setModel(model);//应用表格模型
	scroll.setViewportView(tbl);
	panel2.add(scroll);
	tbl.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e) { 
			{
				if(e.getClickCount()==2){
					panel2.removeAll();
			        JLabel t1=new JLabel("书本ID");
			        t1.setFont(new Font("华文新魏",Font.BOLD, 22));
			        t1.setBounds(100, 20, 120, 30);
			        JLabel t2=new JLabel("书本名称");
			        t2.setFont(new Font("华文新魏",Font.BOLD, 22));
			        t2.setBounds(500, 20, 120, 30);
			        JLabel t3=new JLabel("书本作者");
			        t3.setFont(new Font("华文新魏",Font.BOLD, 22));
			        t3.setBounds(100, 90, 120, 30);
			        JLabel t4=new JLabel("书本存储位置");
			        t4.setBounds(500, 90, 160, 30);
			        t4.setFont(new Font("华文新魏",Font.BOLD, 22));
			        JLabel a1=new JLabel("书本可借量");
			        a1.setFont(new Font("华文新魏",Font.BOLD, 22));
			        a1.setBounds(100, 160, 140, 30);
			        JLabel a2=new JLabel("书本总量");
			        a2.setFont(new Font("华文新魏",Font.BOLD, 22));
			        a2.setBounds(500,160, 120, 30);
			        JLabel a3=new JLabel("书本借阅次数");
			        a3.setFont(new Font("华文新魏",Font.BOLD, 22));
			        a3.setBounds(100,220, 150, 30);
			        JLabel a4=new JLabel("书本简介");
			        a4.setBounds(100, 270,150, 30);
			        a4.setFont(new Font("华文新魏",Font.BOLD, 22));
			        int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
			        Object cellVal0=tbl.getValueAt(row,0); 
                    Object cellVal1=tbl.getValueAt(row,1); 
                    Object cellVal2=tbl.getValueAt(row,2); 
                    Object cellVal3=tbl.getValueAt(row,3);
                    Object cellVal4=tbl.getValueAt(row,4); 
                    Object cellVal5=tbl.getValueAt(row,5); 
                    Object cellVal6=tbl.getValueAt(row,6); 
                    Object cellVal7=tbl.getValueAt(row,7);
                    JTextArea t5=new JTextArea(cellVal0.toString());
			        t5.setBounds(245, 20, 220, 40);
			        t5.setFont(new Font("宋体",Font.BOLD, 18));
			        t5.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        t5.setEditable(false);
			        JTextArea t6=new JTextArea(cellVal1.toString());
			        t6.setBounds(640, 20, 220, 40);
			        t6.setFont(new Font("宋体",Font.BOLD, 18));
			        t6.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        t6.setLineWrap(true);
			        t6.setEditable(false);
			        JTextArea t7=new JTextArea(cellVal2.toString());
			        t7.setBounds(245, 90, 220, 40);
			        t7.setFont(new Font("宋体",Font.BOLD, 18));
			        t7.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        t7.setEditable(false);
			        JTextArea t8=new JTextArea(cellVal3.toString());
			        t8.setBounds(640, 90, 220, 40);
			        t8.setFont(new Font("宋体",Font.BOLD, 18));
			        t8.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        t8.setLineWrap(true);
			        t8.setEditable(false);
			        JTextArea a5=new JTextArea(cellVal4.toString());
			        a5.setBounds(245,160,220,40);
			        a5.setFont(new Font("宋体",Font.BOLD, 18));
			        a5.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        a5.setEditable(false);
			        JTextArea a6=new JTextArea(cellVal5.toString());
			        a6.setBounds(640, 160, 220, 40);
			        a6.setFont(new Font("宋体",Font.BOLD, 18));
			        a6.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        a6.setLineWrap(true);
			        a6.setEditable(false);
			        JTextArea a7=new JTextArea(cellVal6.toString());
			        a7.setBounds(245, 220, 220, 40);
			        a7.setFont(new Font("宋体",Font.BOLD, 18));
			        a7.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        a7.setEditable(false);
			        JTextArea a8=new JTextArea(cellVal7.toString());
			        a8.setBounds(100,300,750,160);
			        a8.setFont(new Font("宋体",Font.BOLD, 18));
			        a8.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
			        a8.setLineWrap(true);
			        a8.setEditable(false);
			        panel2.add(t5,new Integer(200));
			        panel2.add(t6,new Integer(200));
			        panel2.add(t7,new Integer(200));
			        panel2.add(t8,new Integer(200));
			        panel2.add(t1,new Integer(200));
			        panel2.add(t2,new Integer(200));
			        panel2.add(t3,new Integer(200));
			        panel2.add(t4,new Integer(200));
			        panel2.add(a5,new Integer(200));
			        panel2.add(a6,new Integer(200));
			        panel2.add(a7,new Integer(200));
			        panel2.add(a8,new Integer(200));
			        panel2.add(a1,new Integer(200));
			        panel2.add(a2,new Integer(200));
			        panel2.add(a3,new Integer(200));
			        panel2.add(a4,new Integer(200));		
			        panel1.add(tip);
					panel1.add(searchBook);
				    panel1.add(r1);
					panel1.add(r2);
				    panel1.add(r3); 
				    panel1.add(searchButton);
			        panel1.repaint();		        
			        panel2.repaint();
			        
				}
			}
		}
    });
	   frame.add(panel1);
	   frame.add(panel2);
	    r1.addItemListener(this);;  
        r2.addItemListener(this);
        r3.addItemListener(this);
//		frame.add(panel);
//		frame.pack();
// 		frame.setVisible(true);
 //		frame.setLocationRelativeTo(null); 
	}	
		public void itemStateChanged(ItemEvent e){
			if(e.getSource()==r1){
				flag=1;
			}
			else if(r2.isSelected()){
				flag=2;
			}
			else if(r3.isSelected()){
				flag=3;	
			}
		}

		public class SearchListener implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{	
				search=searchBook.getText();
				JPanel panelReturn=new JPanel();
				if(flag==1)
				{
					try {
						 new SearchView(search,"uBook");

				} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(flag==2)
				{
					try {
						 new SearchView(search,"uAuthor");
				} catch (SQLException e1) {
						e1.printStackTrace();
						}
					}
				else if(flag==3){
					try {
						 new SearchView(search,"uBookID");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		}
		}
		public class SearchView{
//		 	   JTable tbl=null;
			   String returnID=null;
		    String sea;
		    String type;
			public SearchView(String s,String type) throws SQLException//初始化函数
			{	this.sea=s;
			    this.type=type;
			    
//			    tbl=new JTable(){public boolean isCellEditable(int row, int column) {return false;}};
			    DefaultTableModel model=(DefaultTableModel) tbl.getModel();//获得表格模型
				model.setRowCount(0);//清空表格中的数据
				model.setColumnIdentifiers(new Object[]{ "书号", "书名", "作者","存储位置","可借量","总量","借阅次数","简介"});
				
				client.sendRequestToServer(new LibraryData("Search",type,s));
				ObjListData listData = (ObjListData)client.receiveDataFromServer();
				List<LibraryData>  results = listData.getLibraryList();
				for(LibraryData infor:results){  //将数据加载到表格中
					model.addRow(new Object[]{infor.getbID(),infor.getbName(),infor.getbAuthor(),infor.getbPlace(),infor.getbStorage(),infor.getTotal(),infor.getbLendNumber(),infor.getbIntro()});
				}
			    
				tbl.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) { 
						{
							if(e.getClickCount()==2){
								   panel2.removeAll();
						        JLabel t1=new JLabel("书本ID");
						        t1.setFont(new Font("华文新魏",Font.BOLD, 20));
						        t1.setBounds(100, 20, 120, 35);
						        JLabel t2=new JLabel("书本名称");
						        t2.setFont(new Font("华文新魏",Font.BOLD, 20));
						        t2.setBounds(500, 20, 120, 35);
						        JLabel t3=new JLabel("书本作者");
						        t3.setFont(new Font("华文新魏",Font.BOLD, 20));
						        t3.setBounds(100, 90, 120, 35);
						        JLabel t4=new JLabel("书本存储位置");
						        t4.setBounds(500, 90, 160, 35);
						        t4.setFont(new Font("华文新魏",Font.BOLD, 20));
						        JLabel a1=new JLabel("书本可借量");
						        a1.setFont(new Font("华文新魏",Font.BOLD, 20));
						        a1.setBounds(100, 160, 140, 35);
						        JLabel a2=new JLabel("书本总量");
						        a2.setFont(new Font("华文新魏",Font.BOLD, 20));
						        a2.setBounds(500,160, 120, 35);
						        JLabel a3=new JLabel("书本借阅次数");
						        a3.setFont(new Font("华文新魏",Font.BOLD, 20));
						        a3.setBounds(100,220, 150, 35);
						        JLabel a4=new JLabel("书本简介");
						        a4.setBounds(100, 270,150, 35);
						        a4.setFont(new Font("华文新魏",Font.BOLD, 20));
						        int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
						        Object cellVal0=tbl.getValueAt(row,0); 
			                    Object cellVal1=tbl.getValueAt(row,1); 
			                    Object cellVal2=tbl.getValueAt(row,2); 
			                    Object cellVal3=tbl.getValueAt(row,3);
			                    Object cellVal4=tbl.getValueAt(row,4); 
			                    Object cellVal5=tbl.getValueAt(row,5); 
			                    Object cellVal6=tbl.getValueAt(row,6); 
			                    Object cellVal7=tbl.getValueAt(row,7);
			                    JTextArea t5=new JTextArea(cellVal0.toString());
						        t5.setBounds(220, 20, 220, 45);
						        t5.setFont(new Font("宋体",Font.BOLD, 18));
						        t5.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        t5.setEditable(false);
						        JTextArea t6=new JTextArea(cellVal1.toString());
						        t6.setBounds(640, 20, 200, 45);
						        t6.setFont(new Font("宋体",Font.BOLD, 18));
						        t6.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        t6.setLineWrap(true);
						        t6.setEditable(false);
						        JTextArea t7=new JTextArea(cellVal2.toString());
						        t7.setBounds(220, 90, 220, 45);
						        t7.setFont(new Font("宋体",Font.BOLD, 18));
						        t7.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        t7.setEditable(false);
						        JTextArea t8=new JTextArea(cellVal3.toString());
						        t8.setBounds(640, 90, 200, 45);
						        t8.setFont(new Font("宋体",Font.BOLD, 18));
						        t8.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        t8.setLineWrap(true);
						        t8.setEditable(false);
						        JTextArea a5=new JTextArea(cellVal4.toString());
						        a5.setBounds(220,160,220,45);
						        a5.setFont(new Font("宋体",Font.BOLD, 18));
						        a5.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        a5.setEditable(false);
						        JTextArea a6=new JTextArea(cellVal5.toString());
						        a6.setBounds(640, 160, 200, 45);
						        a6.setFont(new Font("宋体",Font.BOLD, 18));
						        a6.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        a6.setLineWrap(true);
						        a6.setEditable(false);
						        JTextArea a7=new JTextArea(cellVal6.toString());
						        a7.setBounds(240, 220, 200, 45);
						        a7.setFont(new Font("宋体",Font.BOLD, 18));
						        a7.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        a7.setEditable(false);
						        JTextArea a8=new JTextArea(cellVal7.toString());
						        a8.setBounds(100,300,750,160);
						        a8.setFont(new Font("宋体",Font.BOLD, 18));
						        a8.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
						        a8.setLineWrap(true);
						        a8.setEditable(false);
						        panel2.add(t5,new Integer(200));
						        panel2.add(t6,new Integer(200));
						        panel2.add(t7,new Integer(200));
						        panel2.add(t8,new Integer(200));
						        panel2.add(t1,new Integer(200));
						        panel2.add(t2,new Integer(200));
						        panel2.add(t3,new Integer(200));
						        panel2.add(t4,new Integer(200));
						        panel2.add(a5,new Integer(200));
						        panel2.add(a6,new Integer(200));
						        panel2.add(a7,new Integer(200));
						        panel2.add(a8,new Integer(200));
						        panel2.add(a1,new Integer(200));
						        panel2.add(a2,new Integer(200));
						        panel2.add(a3,new Integer(200));
						        panel2.add(a4,new Integer(200));		
						        panel1.add(tip);
								panel1.add(searchBook);
							    panel1.add(r1);
								panel1.add(r2);
							    panel1.add(r3); 
							    panel1.add(searchButton);
						        panel1.repaint();		        
						        panel2.repaint();
						        
							}
						}
					}
			    });
				
 				JScrollPane scroll = new JScrollPane(tbl);
 				scroll.setBounds(100,0,900,450);  
 				Helper.makeFace(tbl);
 				tbl.setModel(model);//应用表格模型
 				scroll.setViewportView(tbl);
 				panel2.removeAll();
 				panel2.add(scroll);
				panel2.repaint();
				panel1.repaint();
			}
			public void Update(int row)
			{
				returnID=(String) tbl.getValueAt(row, 1);
			}
			public JTable getTable() {
				
				return tbl;}
		public JPanel getFrame() {
				
				return frame;}
		}
}