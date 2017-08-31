package cn.seu.edu.shopView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.MailData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;


public class BuyGoodsView {
	Client client;
	String id;
	JPanel frame = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JTextField text;
	JLabel lab;
	JTable table;
	
	public BuyGoodsView(final Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.frame=jp;	
    	
		panel2.setBounds(20,20,900,300);
		panel2.setLayout(null);
		JLabel Text=new JLabel("输入商品名以查询并购买");
		Text.setFont(new Font("华文新魏",Font.BOLD, 25));
		Text.setBounds(10,0,850,30);
		text=new JTextField(30);
		text.setFont(new Font("宋体",Font.BOLD, 18));
		text.setBounds(10,50,200,30);
		JButton searchGoods=new JButton("搜索");
		searchGoods.addActionListener(new SearchListener());
		searchGoods.setBounds(230,50,100,30);
		JButton browse=new JButton("浏览商品");
		browse.addActionListener(new BrowseListener());
		browse.setBounds(360,50,100,30);
		JButton buyButton=new JButton("购买");
		buyButton.addActionListener(new BuyListener());
		buyButton.setBounds(450, 600, 100, 30);
		lab = new JLabel("为您推荐最新上架的商品：");
		lab.setFont(new Font("华文新魏",Font.BOLD, 25));
		lab.setBounds(30,120,900,40);
		
		panel1.setBounds(30,170,900,400);
		panel1.setOpaque(false);
		panel2.add(browse);
		panel2.add(text);
		panel2.add(Text);
		panel2.add(searchGoods);
		panel2.setOpaque(false);
		frame.add(lab);
		frame.add(buyButton);

		    panel1.setLayout(null);
		    table=new JTable(){
		    	public boolean isCellEditable(int row, int column) {
						   return false;
					  }
		    };
		    table.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) { 
					{
						if(e.getClickCount()==2){
							JFrame f = new JFrame("详细信息");
							f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					    	f.setSize(600,600);
					        f.setVisible(true);
					        f.setLayout(null);
					        f.setResizable(false);
					        JPanel p=new JPanel();
					        p.setLayout(null);
					        f.getContentPane().add(p);
					        p.setBounds(0, 0, 600, 600); 
					       
					        JLabel t1=new JLabel("商品ID");
					        t1.setFont(new Font("华文新魏",Font.BOLD, 25));
					        t1.setBounds(300, 10, 100, 30);
					        JLabel t2=new JLabel("商品名");
					        t2.setFont(new Font("华文新魏",Font.BOLD, 25));
					        t2.setBounds(300, 90, 100, 30);
					        JLabel t3=new JLabel("商品价格");
					        t3.setFont(new Font("华文新魏",Font.BOLD, 25));
					        t3.setBounds(300, 180, 150, 30);
					        JLabel t4=new JLabel("卖家介绍");
					        t4.setBounds(100, 280, 150, 30);
					        t4.setFont(new Font("华文新魏",Font.BOLD, 25));
					        int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
					        Object cellVal0=table.getValueAt(row,1); 
                            Object cellVal1=table.getValueAt(row,2); 
                            Object cellVal2=table.getValueAt(row,3); 
                            Object cellVal3=table.getValueAt(row,4);
                            
                            ShopData sData=new ShopData("GetPicFromServer");
					        sData.setuGoodsID(cellVal0.toString());
					        
					        client.sendRequestToServer(sData);
					        ShopData shopData = (ShopData)client.receiveDataFromServer();
					        ImageIcon icon=shopData.getIcon();
							JLabel pic = new JLabel(icon,JLabel.CENTER);
							
							pic.setBounds(30,20, 200,200);
							p.add(pic);
							p.repaint();
							
                            JTextArea t5=new JTextArea(cellVal0.toString());
					        t5.setBounds(300, 40, 200, 40);
					        t5.setFont(new Font("宋体",Font.BOLD, 18));
					        t5.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
					        t5.setEditable(false);
					        JTextArea t6=new JTextArea(cellVal1.toString());
					        t6.setBounds(300, 120, 200, 40);
					        t6.setFont(new Font("宋体",Font.BOLD, 18));
					        t6.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
					        t6.setLineWrap(true);
					        t6.setEditable(false);
					        JTextArea t7=new JTextArea(cellVal2.toString());
					        t7.setBounds(300, 210, 200, 40);
					        t7.setFont(new Font("宋体",Font.BOLD, 18));
					        t7.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
					        t7.setEditable(false);
					        JTextArea t8=new JTextArea(cellVal3.toString());
					        t8.setBounds(100, 320, 400, 200);
					        t8.setFont(new Font("宋体",Font.BOLD, 18));
					        t8.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
					        t8.setLineWrap(true);
					        t8.setEditable(false);
					        p.add(t5,new Integer(200));
					        p.add(t6,new Integer(200));
					        p.add(t7,new Integer(200));
					        p.add(t8,new Integer(200));
					        p.add(t1,new Integer(200));
					        p.add(t2,new Integer(200));
					        p.add(t3,new Integer(200));
					        p.add(t4,new Integer(200));
					        
					        JLabel back=new JLabel(new ImageIcon("main5.png"));
					        back.setBounds(0, 0, 600, 600);
					        p.add(back, new Integer(100));
					        f.setLocationRelativeTo(null);

						}
					}
				}
		    });
		    table.setEnabled(true);
		    JScrollPane scroll = new JScrollPane(table);
		    scroll.setBounds(0,0,900,400);

			DefaultTableModel model=(DefaultTableModel) table.getModel();//获得表格模型
			model.setRowCount(0);//清空表格中的数据
			model.setColumnIdentifiers(new Object[]{"卖家ID","商品ID","商品名","价格","描述信息","类型 "});
			
			 ShopData sData = new ShopData("OutputNewGoods");
			 sData.setuSellerID(id);
			 client.sendRequestToServer(sData);
			 ObjListData listData = (ObjListData)client.receiveDataFromServer();
			
			
			List<ShopData>  results = listData.getShopList();
			for(ShopData infor:results){  //将数据加载到表格中
				model.addRow(new Object[]{infor.getuSellerID(),infor.getuGoodsID(),infor.getuGoodsName(),infor.getuPrice(),infor.getuDetail(),infor.getuType()});
			}
			
			Helper.makeFace(table);
			table.setRowHeight(30);
			table.setFont(new java.awt.Font("Dialog", 1, 15));
			table.setModel(model);//应用表格模型
			scroll.setViewportView(table);
			scroll.setBounds(0,0,900,400);
    		panel1.add(scroll);
    		panel1.repaint();
    		
		
    	frame.add(panel1);
		frame.add(panel2);
	}
	
	class SearchListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){
		
			 panel1.removeAll();
			 lab.setText("双击查看商品具体信息：");
			    JScrollPane scroll = new JScrollPane(table);
			    scroll.setBounds(0,0,900,400);

				DefaultTableModel model=(DefaultTableModel) table.getModel();//获得表格模型
				model.setRowCount(0);//清空表格中的数据
				model.setColumnIdentifiers(new Object[]{"卖家ID","商品ID","商品名","价格","描述信息","类型 "});
				
				 String goodsName = text.getText().trim();
				 ShopData sData = new ShopData("shopSearch");
				 sData.setuGoodsName(goodsName);
				 sData.setuSellerID("");
				 client.sendRequestToServer(sData);
				 ObjListData listData = (ObjListData)client.receiveDataFromServer();
				
				
				List<ShopData>  results = listData.getShopList();
				for(ShopData infor:results){  //将数据加载到表格中
					model.addRow(new Object[]{infor.getuSellerID(),infor.getuGoodsID(),infor.getuGoodsName(),infor.getuPrice(),infor.getuDetail(),infor.getuType()});
				}
				
				Helper.makeFace(table);
				table.setModel(model);//应用表格模型
				scroll.setViewportView(table);
	    		panel1.add(scroll);
	    		panel1.repaint();

				
		 }
	}
	
	class BrowseListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){ 
			 panel1.removeAll();
			 lab.setText("双击查看商品具体信息：");
			    JScrollPane scroll = new JScrollPane(table);
			    scroll.setBounds(0,0,900,400);

				DefaultTableModel model=(DefaultTableModel) table.getModel();//获得表格模型
				model.setRowCount(0);//清空表格中的数据
				model.setColumnIdentifiers(new Object[]{"卖家ID","商品ID","商品名","价格","描述信息","类型 "});
			
				
				 String goodsName = "";
				 ShopData sData = new ShopData("shopSearch");
				 sData.setuGoodsName(goodsName);
				 sData.setuSellerID("");
				 client.sendRequestToServer(sData);
				 ObjListData listData = (ObjListData)client.receiveDataFromServer();
				
				
				List<ShopData>  results = listData.getShopList();
				for(ShopData infor:results){  //将数据加载到表格中
					model.addRow(new Object[]{infor.getuSellerID(),infor.getuGoodsID(),infor.getuGoodsName(),infor.getuPrice(),infor.getuDetail(),infor.getuType()});
				}
				
				Helper.makeFace(table);
				table.setModel(model);//应用表格模型
				scroll.setViewportView(table);
	    		panel1.add(scroll);
	    		panel1.repaint();
		 }
		 
	 }
	
	class BuyListener implements ActionListener{
		public void actionPerformed (ActionEvent e){
			int row=table.getSelectedRow();
			Object cn0=table.getValueAt(row, 1);
			Object cn1=table.getValueAt(row, 2);
			Object cn2=table.getValueAt(row, 3);
			Object cn3=table.getValueAt(row, 4);
			String password = JOptionPane.showInputDialog("请输入一卡通密码：\n"); 
			try{
				if("".equals(password)){
					JOptionPane.showMessageDialog(null, "密码不能为空","提示对话框",1);
					return;
				}else if(password == null){
					return;
				}else{
					BankData bData = new BankData("CheckCardPassword");
					bData.setbPassword(password);
					bData.setId(id);
					client.sendRequestToServer(bData);
					RequestData rData = (RequestData)client.receiveDataFromServer();
					if("true".equals(rData.getRequest())){
						ShopData sData = new ShopData("buyGoods");
			    		sData.setuGoodsID(cn0.toString());
			    		sData.setuGoodsName(cn1.toString());
			    		sData.setuPrice(cn2.toString());
			    		sData.setuDetail(cn3.toString());
			    		sData.setUserID(id);
			    		Date date=new Date();
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=format.format(date);
						sData.setuTime(time);
						client.sendRequestToServer(sData);
						RequestData reqData = (RequestData)client.receiveDataFromServer();
						if("sendMail".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "购买成功","提示对话框",1);
							client.sendRequestToServer(reqData);
							
							RequestData requestData = (RequestData)client.receiveDataFromServer();
						}else if("MoneyNotEnough".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "一卡通余额不足，请充值","提示对话框",1);
						}else if("goodsNotEnough".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "库存不足，无法购买","提示对话框",1);
						}else if("false".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "购买失败","提示对话框",1);
						}
					}else{
						JOptionPane.showMessageDialog(null, "密码错误","提示对话框",1);
					}
					
				}
				
			}catch(Exception ee){
				ee.printStackTrace(); 
			}
		}
	}
}
