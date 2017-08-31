package cn.seu.edu.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Helper {
	
	//�ж��������ڸ�ʽ�Ƿ���ȷ
	public static boolean ageFormat(String t){
    	String tt="([123]\\d{3}-(([0][1-9])|([1][0-2])))";
    	Pattern p=Pattern.compile(tt);
    	Matcher m=p.matcher(t);
    	if(!m.matches()){
    		return false;
    	}else{
    		String []times=t.split("-");//�������ʱ����-�ָ�
    		Integer year=Integer.parseInt(times[0]);
    		Integer month=Integer.parseInt(times[1]);
    		if(((month<0)||(month>12))&&(year>2016)){//ĿǰΪ2016�꣬һ��12����
    			return false;
    		}else
    			return true;
    		}
	}
	
	
    //���ñ���ʽ
	public static void makeFace(JTable table) {
		try {
		    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		     public Component getTableCellRendererComponent(JTable table,
		       Object value, boolean isSelected, boolean hasFocus,
		       int row, int column) {
		      if (row % 2 == 0)
		       setBackground(Color.white); // ���������е�ɫ
		      else if (row % 2 == 1)
		       setBackground(new Color(241,254,243)); // ����ż���е�ɫ
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
		table.setRowHeight(40);
		table.setFont(new java.awt.Font("Dialog", 1, 15));
		   
		}
	
	
	//�ж��ַ����Ƿ�Ϊ����
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
}
