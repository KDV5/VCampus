package seu.edu.client.test;

import javax.xml.soap.SAAJResult;

import seu.edu.client.view.Library.MainWindow;
import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;

public class test {
	public static void main(String[] args){
		SocketClient socketClient=new SocketClient();
		MainWindow mw=new MainWindow(socketClient);
		mw.setVisible(true);
//		
//		LibraryMessage lm= new LibraryMessage("ADD_BOOK", "1111", "FUCK", "FUCKER", "LB", 13, "不努力就进逼", 10, "13", "porn");
//		socketClient.sendRequestToServer(lm);
//		String t=((LibraryMessage)(socketClient.receiveDataFromServer())).getOperResult();
//		System.out.println(t);
		
//		LibraryMessage lm=new LibraryMessage("DELETE_BOOK","1111");
//		socketClient.sendRequestToServer(lm);
//		String t=((LibraryMessage)(socketClient.receiveDataFromServer())).getOperResult();
//		System.out.println(t);
		
//		LibraryMessage lmb=new LibraryMessage("EDIT_BOOK", "1111", "FUCK", "淋淋", "LB", 13, "不努力就进逼", 10, "13", "porn");
//		socketClient.sendRequestToServer(lmb);
//		String tt=((LibraryMessage)(socketClient.receiveDataFromServer())).getOperResult();
//		System.out.println(tt);
	}
}
