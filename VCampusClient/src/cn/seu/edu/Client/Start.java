package cn.seu.edu.Client;

import javax.swing.UIManager;

import cn.seu.edu.message.FeedbackData;
import cn.seu.edu.view.InfoChargeView;
import cn.seu.edu.view.LibChargeView;
import cn.seu.edu.view.ShopChargeView;
import cn.seu.edu.view.mainChargeView;
import cn.seu.edu.view.stuMainView;
import cn.seu.edu.view.tchMainView;

public class Start {

	public static void main(String[] args) {
		Client client = new Client();
		client.showLoginView();
		//new stuMainView(client,"09014101");
		//new tchMainView(client,"4200111");
		//new InfoChargeView(client,"100001");
	     //new LibChargeView(client,"140001");
		//new ShopChargeView(client,"130001");
		//new mainChargeView(client,"130001");
		
		try{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		}
		catch(Exception e){
			System.err.println("Oops! Something went wrong!");
		}


	}

}
