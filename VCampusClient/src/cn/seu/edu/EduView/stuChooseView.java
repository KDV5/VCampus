package cn.seu.edu.EduView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.CourseData;
import cn.seu.edu.message.RequestData;


public class stuChooseView {
	String stuNumb;
	Client client;
	JPanel total;
	JTabbedPane jp=new JTabbedPane(JTabbedPane.TOP);
	JPanel[] p=new JPanel[5];
	everChoose[] ec=new everChoose[5];
	Chose[] cs=new Chose[5];
	public stuChooseView(Client client,String StuNumb,JPanel jm){
		this.client = client;
		this.stuNumb = StuNumb;
		this.total=jm;
	    
	    
	    jp.setBounds(10, 0, 960, 640);
	    jp.setBackground(Color.WHITE);
	    for(int i=0;i<=4;i++){
	    	p[i]=new JPanel();
	    	p[i].setLayout(null);
	    	p[i].setBackground(Color.white);
	    	ec[i]=new everChoose(i+1);
	    	cs[i]=new Chose(i+1);
		}
	    
	    jp.add("周一",p[0]);
	    jp.add("周二",p[1]);
	    jp.add("周三",p[2]);
	    jp.add("周四",p[3]);
	    jp.add("周五",p[4]);
	    jm.add(jp,BorderLayout.CENTER);  
	    //jp.setOpaque(false);
	}
	
	
	class everChoose{
		public int day;
		JTable tbl;
		 public everChoose(int i) {
			   day=i;
			   try{
				   CourseData cData = new CourseData("everChoose");
				   cData.setDay(i);
				   cData.setStuId(stuNumb);
				    client.sendRequestToServer(cData);
				    CourseData courseData =(CourseData)client.receiveDataFromServer();
					String[] title={"时间","课程编码", "课程名称", "任课老师","选课人数"};
			
					tbl=new JTable(courseData.obj1,title){public boolean isCellEditable(int row, int column) {return false;}};
					tbl.setRowHeight(30);
					tbl.setFont(new java.awt.Font("Dialog", 1, 15));
					makeFace(tbl);
					JLabel evercho=new JLabel("已选课程:");
					evercho.setBounds(10, 0, 80, 30);
					JButton giveup=new JButton("取消选课");
					giveup.setBounds(400, 200, 150, 30);
					giveup.addActionListener(new quitListener());
					JScrollPane scroll = new JScrollPane(tbl);  
					scroll.setBounds(80, 20, 800, 160);
					 
					p[i-1].add(scroll);
					p[i-1].add(evercho);
					p[i-1].add(giveup);
			   }catch(Exception e){
				   e.printStackTrace();
			}
			   
		   }
		
		 class quitListener implements ActionListener{
			 public void actionPerformed (ActionEvent e){
				 int row=tbl.getSelectedRow();
				 Object cn=tbl.getValueAt(row, 1);
				 if(cn==null){
					 JOptionPane.showMessageDialog(null, "当前没有选课","提示对话框",1);
				 }
				 else{
					 String cournum=cn.toString();
					 try{
						 CourseData cData = new CourseData("quitChooseCourse");
						 cData.setStuId(stuNumb);
						 cData.setCourNum(cournum);
						 client.sendRequestToServer(cData);
						 RequestData reqData = (RequestData)client.receiveDataFromServer();
						}
					 catch(Exception e1){
						 System.out.println("取消选课失败");
						 }
				 }
				 try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				 p[day-1].removeAll();
				 ec[day-1]=new everChoose(day);
				 cs[day-1]=new Chose(day);
				 
			 }
		 }
	}
	
	class Chose{
		 JTable tbl;
		 public int day;
		 public  Chose(int i){
			 day=i;
			try{
				CourseData cData = new CourseData("CourseCanBeChoose");
				cData.setDay(i);
				client.sendRequestToServer(cData);
				CourseData courseData =(CourseData)client.receiveDataFromServer();
					
				String[] title={"时间","课程编码", "课程名称", "任课老师","选课人数"};
				tbl=new JTable(courseData.obj,title){public boolean isCellEditable(int row, int column) {return false;}};
				tbl.setRowHeight(30);
				tbl.setFont(new java.awt.Font("Dialog", 1, 15));
				makeFace(tbl);
				JLabel cho=new JLabel("课程选择:");
				cho.setBounds(10, 240, 80, 30);
				JButton sure=new JButton("确认选课");
				sure.setBounds(400, 550, 150, 30);
				sure.addActionListener(new sureListener());
				JScrollPane scroll = new JScrollPane(tbl);  
				scroll.setBounds(80, 260, 800, 260);
					
				p[day-1].add(scroll);
				p[day-1].add(cho);
				p[day-1].add(sure);
					
				}
				catch(Exception e){
					e.printStackTrace();
					}
			}
		 
		 class sureListener implements ActionListener{
			 public void actionPerformed (ActionEvent e){
				 int row=tbl.getSelectedRow();
				 Object cn=tbl.getValueAt(row, 1);
				 String cournum=cn.toString();
				 CourseData cData = new CourseData("sureChooseCourse");
				 cData.setStuId(stuNumb);
				 cData.setCourNum(cournum);
				 client.sendRequestToServer(cData);
				 RequestData reqData = (RequestData)client.receiveDataFromServer();
				 
				 try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				 p[day-1].removeAll();
				 ec[day-1]=new everChoose(day);
				 cs[day-1]=new Chose(day);
				}
			 
		 }
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
