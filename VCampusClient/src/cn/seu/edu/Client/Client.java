package cn.seu.edu.Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.UIManager;

import cn.seu.edu.message.RequestData;
import cn.seu.edu.register.Register;
//import cn.seu.edu.view.LoginView;

public class Client {
    public Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
   
	public Client(){
		try {
			socket = new Socket("localhost",4567);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ʾ��½����
	public void showLoginView(){
		//new LoginView(this);
		try{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		}
		catch(Exception e){
			System.err.println("Oops! Something went wrong!");
		}
		Register r= new Register(this);
		r.setSize(360,224);
        r.setTitle("����У԰ϵͳ");
        r.setVisible(true);
        int w = (r.getToolkit().getScreenSize().width-r.getWidth()) /2;
		int h = (r.getToolkit().getScreenSize().height-r.getHeight())/2 ;
		r.setLocation(w, h);
	}
	
	//���������������
	public void sendRequestToServer(RequestData rd){
		try {
			oos.writeObject(rd);
//			System.out.println("��������ɹ�");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//�ӷ�������������
	public Object receiveDataFromServer(){
		Object obj = null;
			try {
				obj = ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return obj;
	}

}
