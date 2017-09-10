package seu.edu.client.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import seu.edu.common.SocketClient;
import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;
import seu.edu.vo.Book;

public class LibraryThread extends Thread {
	private SocketClient socketClient = null;	
	
	public LibraryThread(SocketClient sc) {
		socketClient = sc;
	}
	public void run(){
		System.out.println("选择功能——列出全部图书：");
		try {
			LibraryMessage lb=new LibraryMessage("ListAllBooks");
			socketClient.sendRequestToServer(lb);			
			ListMessage dataList=(ListMessage) socketClient.receiveDataFromServer();
			int i=0;
			for(;i<dataList.getDataList().size();i++){
				System.out.println(((LibraryMessage)(dataList.getDataList().get(i))).getBookName());
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
