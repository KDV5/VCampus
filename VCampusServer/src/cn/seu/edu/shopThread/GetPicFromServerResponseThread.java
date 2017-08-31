package cn.seu.edu.shopThread;

import java.io.File;

import javax.swing.ImageIcon;

import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;
import cn.seu.edu.server.ServerReaderThread;

public class GetPicFromServerResponseThread{
	ServerReaderThread server;
	ShopData sData;
	String ID;
	ImageIcon icon;
	public GetPicFromServerResponseThread(ServerReaderThread server,RequestData reqData){
		this.server= server;
		this.sData = (ShopData)reqData;
		this.ID=sData.getuGoodsID();
		this.icon=sData.getIcon();
		
	}
	public void run(){
		File file = new File("GoodsImage\\"+ID+".jpg");
		System.out.print(ID);
		if(file.exists()){
			sData.setIcon(new ImageIcon("GoodsImage\\"+ID+".jpg"));
			System.out.print("22");
		}else{
			sData.setIcon(null);
			System.out.print("33");
		}
		
		server.sendDataToClient(sData);
	}
}
