package seu.edu.client.test;

import javax.xml.soap.SAAJResult;

import seu.edu.client.view.Library.MainWindow;
import seu.edu.common.SocketClient;

public class test {
	public static void main(String[] args){
		SocketClient socketClient=new SocketClient();
		MainWindow mw=new MainWindow(socketClient);
		mw.setVisible(true);
	}
}
