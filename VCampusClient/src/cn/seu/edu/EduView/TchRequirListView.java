package cn.seu.edu.EduView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;

public class TchRequirListView {
	Client client;
	String id;
	JPanel stulist=new JPanel();
	 JTextField stunum;
	 JScrollPane scroll=new JScrollPane();
	 public TchRequirListView(Client client,String id,JPanel jp){
		 this.client = client;
		 this.id = id;
		 this.stulist=jp;
		 
		 JPanel cont=new JPanel();
		 cont.setBounds(0, 0, 900, 100);
		 cont.setLayout(null);
		 cont.setOpaque(false);
		 stulist.setLayout(null);
		 
		 JLabel num=new JLabel("������ѧ����ѧ�ţ�");
		 num.setBounds(100, 0, 300, 40);
		 num.setFont(new Font("������κ",Font.BOLD, 25));
		 cont.add(num);
		 stunum=new JTextField();
		 stunum.setFont(new Font("����",Font.BOLD, 18));
		 stunum.setBounds(260, 50,300, 30);
		 cont.add(stunum);
		 JButton sure=new JButton("ȷ��");
		 sure.setBounds(600, 50,100, 30);
		 cont.add(sure);
		 
		 stulist.add(cont);
		 
		 sure.addActionListener(new SureListener());
	 }
	 
	 class SureListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){
			String StuNumb=stunum.getText().trim();
			if(!"".equals(StuNumb)){
			CourseData courseData = new CourseData("outputStuCourList");
			courseData.setStuId(StuNumb);
			client.sendRequestToServer(courseData);
			RequestData reqData = (RequestData)client.receiveDataFromServer();
			if("true".equals(reqData.getRequest())){
				CourseData cData = (CourseData)reqData;
				String[] title={"","","", "", "", ""};
				JTable tbl=new JTable(cData.obj2,title);
				tbl.setRowHeight(125);
				tbl.setRowHeight(0,60);
				TableColumn firsetColumn = tbl.getColumnModel().getColumn(0);
				firsetColumn.setPreferredWidth(80);
				firsetColumn.setMaxWidth(80);
				firsetColumn.setMinWidth(80);
				tbl.getColumnModel().getColumn(1).setMinWidth(100);
				tbl.getColumnModel().getColumn(2).setMinWidth(100);
				tbl.getColumnModel().getColumn(3).setMinWidth(100);
				tbl.getColumnModel().getColumn(4).setMinWidth(100);
				tbl.getColumnModel().getColumn(5).setMinWidth(100);
				tbl.setEnabled(false);
				tbl.getTableHeader().setReorderingAllowed(false); 
				tbl.setFont(new java.awt.Font("Dialog", 1, 20));
				tbl.getTableHeader().setVisible(false);  
				makeFace(tbl);

				stulist.remove(scroll);
				scroll = new JScrollPane(tbl);  
				scroll.setBounds(20, 110, 940, 520);  
			
				stulist.add(scroll);
			}else if("false".equals(reqData.getRequest())){
				JOptionPane.showMessageDialog(null, "ѧ���������","��ʾ�Ի���",1);
			}
		 }else{
			 JOptionPane.showMessageDialog(null, "���벻��Ϊ��","��ʾ�Ի���",1);
		 }
		 }
	 }
	 
	 public static void makeFace(JTable table) {
		   try {
		    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		     public Component getTableCellRendererComponent(JTable table,
		       Object value, boolean isSelected, boolean hasFocus,
		       int row, int column) {
		      if (row <1)
		       setBackground(Color.white); 
		      else
		    	  setBackground(new Color(241,254,243)); 
		      if(column<1)
		    	  setBackground (Color.white); 
		      return super.getTableCellRendererComponent(table, value,
		        isSelected, hasFocus, row, column);
		     }
		    };
		    for (int i = 0; i < table.getColumnCount(); i++) {
		     table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		    }
		    tcr.setHorizontalAlignment(JLabel.CENTER);
			   table.setDefaultRenderer(Object.class, tcr);
		   } catch (Exception ex) {
		    ex.printStackTrace();
		   }
	}


}
