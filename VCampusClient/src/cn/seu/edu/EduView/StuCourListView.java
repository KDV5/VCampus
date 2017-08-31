package cn.seu.edu.EduView;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.CourseData;

public class StuCourListView {
	Client client;
	String StuNumb;
	public StuCourListView(Client client,String StuNumb,JPanel jp){
		this.client = client;
		this.StuNumb = StuNumb;
		CourseData cData = new CourseData("outputStuCourList");
		cData.setStuId(StuNumb);
		client.sendRequestToServer(cData);
		CourseData courseData =(CourseData)client.receiveDataFromServer();
		String[] title={"","","", "", "", ""};
		JTable tbl=new JTable(courseData.obj2,title);
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
		
		JScrollPane scroll = new JScrollPane(tbl);  
		scroll.setSize(960, 600);  
		
		jp.add(scroll);
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
