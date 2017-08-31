package cn.seu.edu.EduView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;


import cn.seu.edu.Client.Client;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;

public class TchGradeView {

	JComboBox cb=new JComboBox();
	JScrollPane scroll = new JScrollPane(); 
	JButton su=new JButton("ȷ��"); 
	Client client;
	String id;
	JPanel jFrame = new JPanel();
	public TchGradeView(Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.jFrame=jp;
		
		IdData idata = new IdData("QueryTchCourse",id);
		client.sendRequestToServer(idata);
		CourseData courseData =(CourseData)client.receiveDataFromServer();
		for(int i = 0;i<courseData.tchCourse.length;i++){
			if(!"".equals(courseData.tchCourse[i])){
				cb.addItem(courseData.tchCourse[i]);
			}
		}
		
		cb.setPreferredSize(new Dimension(200,30));
		cb.setBounds(300, 20, 220, 30);
		su.setBounds(560,20,100,30);
		jFrame.setLayout(null);
		jFrame.add(cb);
		jFrame.add(su);
	 
	    
	    su.addActionListener(new SureListener());
	}
	class SureListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){
			 String cho=cb.getSelectedItem().toString();
			 String[] counum=cho.split("\\s+");
			 CourseData cData = new CourseData("QueryTchCourseGrade");
			 cData.setCourNum(counum[0]);
			 client.sendRequestToServer(cData);
			 CourseData courseData =(CourseData)client.receiveDataFromServer();
			 String[] title={"ѧ��", "����", "�ɼ�"};
			 JTable tbl=new JTable(courseData.obj3,title) {
				  public boolean isCellEditable(int row, int column) {
					   if (column == 2)
						   return true; 
					   else
						   return false;
					  }
					 };
			tbl.isCellSelected(0,3);
			tbl.setRowHeight(40);
			tbl.setFont(new java.awt.Font("Dialog", 1, 15));
			makeFace(tbl);
			
			jFrame.remove(scroll);
			scroll = new JScrollPane(tbl); 
			scroll.setBounds(20,80,940,540);
			
			final TableModel tableModel = tbl.getModel();
			 
			tableModel.addTableModelListener(new TableModelListener(){
			    public void tableChanged(TableModelEvent e){
			    	
			        CourseData courData = new CourseData("UpdateStuGrade");
			    	int j = e.getColumn();
			    	int i = e.getFirstRow();
			    	Object check=tableModel.getValueAt(i, 0);
			    	Object obj = tableModel.getValueAt(i, j);
			    	try{
			    		if(check!=null){
				    		int obj1=Integer.parseInt(obj.toString());
				    		if(obj1>=0&&obj1<=100){
				    			courData.setsGrade(obj1);
						    	Object num=tableModel.getValueAt(i, 0);
						    	String num1=num.toString();
						    	courData.setStuId(num1);
						    	String cho=cb.getSelectedItem().toString();
								String[] counum=cho.split("\\s+");
								courData.setCourNum(counum[0]);
								client.sendRequestToServer(courData);
								RequestData reqData = (RequestData)client.receiveDataFromServer();
								if("true".equals(reqData.getRequest())){
									JOptionPane.showMessageDialog(null, "�޸ĳɹ�","��ʾ�Ի���",1);
								}else{
									JOptionPane.showMessageDialog(null, "�޸�ʧ��","��ʾ�Ի���",1);
								}
				    		}
				    		else{
				    			JOptionPane.showMessageDialog(null, "������ĳɼ�����","��ʾ�Ի���",1);
				    			su.doClick();
				    		}
				    	}else{
				    		su.doClick();
				    	}
			    	}
			    	catch(Exception e1){
			    		JOptionPane.showMessageDialog(null, "������ĳɼ�����","��ʾ�Ի���",1);
			    		su.doClick();
			    	}
			    }
			});

			jFrame.add(scroll);
			jFrame.repaint();
	
		 }
	}
	
	public static void makeFace(JTable table) {
		   try {
		    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		     public Component getTableCellRendererComponent(JTable table,
		       Object value, boolean isSlected, boolean hasFocus,
		       int row, int column) {
		      if (row % 2 == 0)
		       setBackground(Color.white); // ���������е�ɫ
		      else if (row % 2 == 1)
		       setBackground(new Color(241,254,243)); // ����ż���е�ɫ
		      return super.getTableCellRendererComponent(table, value,
		        isSlected, hasFocus, row, column);
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


