package cn.seu.edu.shopView;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.ObjListData;
import cn.seu.edu.message.ShopData;

public class OutputHistoryView extends Thread {

	Client client;
	String id;
	JPanel p=new JPanel();
	public OutputHistoryView(Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.p=jp;
		
		p.setBounds(20,20,900,600);
		p.setLayout(null);
		
		JTable table = new JTable();
		table.setEnabled(false);
	    JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(0,0,900,600);

		DefaultTableModel model=(DefaultTableModel) table.getModel();//��ñ��ģ��
		model.setRowCount(0);//��ձ���е�����
		model.setColumnIdentifiers(new Object[]{"��ƷID","��Ʒ��","�۸�","����ʱ��","����"});
		IdData iData = new IdData("shopHistory",id);
		client.sendRequestToServer(iData);
		ObjListData listData = (ObjListData)client.receiveDataFromServer();
		
		
		List<ShopData>  results = listData.getShopList();
		for(ShopData infor:results){  //�����ݼ��ص������
			model.addRow(new Object[]{infor.getuGoodsID(),infor.getuGoodsName(),infor.getuPrice(),infor.getuTime(),infor.getuRole()});
		}
		
		Helper.makeFace(table);
		table.setRowHeight(30);
		table.setFont(new java.awt.Font("Dialog", 1, 15));
		table.setModel(model);//Ӧ�ñ��ģ��
		scroll.setViewportView(table);
		
		
		p.add(scroll);
	}
}
