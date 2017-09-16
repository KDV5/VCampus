package seu.edu.common;

import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

/*
 * @author yyl 9.11
 * 
 * 放在common包下
 * 多线程实现面板从左滑入动画，在窗口左侧按钮响应函数中使用
 * 外部需要建立一个主Panel(即窗口中左边栏右侧、上边栏下部的浅灰色区域），并使用CardLayout布局方式承载各个功能的panel，主Panel和模块panel的尺寸应当相同
 * 
 */
public class SlidePanel {
	private JPanel mainPanel=null;
	private CardLayout card=null;
	
	/*
	 * @ param
	 * mainPanel是外部主Panel，card是采用CardLayout布局时new的card
	 */
	public SlidePanel(JPanel mainPanel,CardLayout card){
		this.mainPanel=mainPanel;
		this.card=card;
	}
	
	/*
	 * @param
	 * panelName 为card中该功能面板的名字
	 * panel为需要使用滑动动画的面板
	 */
	public void slidePanel(final String panelName,final JPanel panel){
		new Thread() {

			public void run() {

				//此时设定为从左侧滑入
				Rectangle rec = mainPanel.getBounds();// 获得当前主面板的位置信息
				int x =0-mainPanel.getWidth();
				panel.setBounds(x, 0,mainPanel.getWidth(), mainPanel.getHeight());
				
				card.show(mainPanel, panelName);

				while(x<0) {
					// 设置面板位置
					panel.setBounds(x, 0,
							mainPanel.getWidth(),
							mainPanel.getHeight());
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					x += 5;
				}				
				panel.setBounds(0, 0,mainPanel.getWidth(), mainPanel.getHeight());


			}
		}.start();
	}
}
