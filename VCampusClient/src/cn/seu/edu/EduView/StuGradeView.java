package cn.seu.edu.EduView;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;

public class StuGradeView {

	Client client;
	String StuNumb;
	public StuGradeView(Client client, String id,JPanel tj){
		this.client = client;
		this.StuNumb = id;
		IdData idata = new IdData("queryStuGrade",StuNumb);
		client.sendRequestToServer(idata);
		CourseData courseData =(CourseData)client.receiveDataFromServer();
		String[] title={"课程编码", "课程名称", "任课老师", "成绩"};
		JTable tbl=new JTable(courseData.obj,title);
		tbl.setRowHeight(40);
		tbl.setEnabled(false);
		tbl.setFont(new java.awt.Font("Dialog", 1, 15));
		makeFace(tbl);
		JScrollPane scroll = new JScrollPane(tbl);  
		scroll.setSize(960, 600);  
		
		tj.add(scroll);
		
	}
	
	public static void makeFace(JTable table) {
		   try {
		    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		     public Component getTableCellRendererComponent(JTable table,
		       Object value, boolean isSelected, boolean hasFocus,
		       int row, int column) {
		      if (row % 2 == 0)
		       setBackground(Color.white); // 设置奇数行底色
		      else if (row % 2 == 1)
		       setBackground(new Color(241,254,243)); // 设置偶数行底色
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
