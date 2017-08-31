package cn.seu.edu.infoThread;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.InfoData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.server.ServerReaderThread;

public class SendPicToServerResponseThread {

	ServerReaderThread server;
	InfoData iData;
	ImageIcon icon;
	String id;
	public SendPicToServerResponseThread(ServerReaderThread server ,RequestData reqData){
		this.server = server;
		this.iData = (InfoData)reqData;
		this.icon = iData.getIcon();
	    this.id = iData.getStuNumber();
	}
	
	public void run(){
		try {
			Image image = icon.getImage();
			BufferedImage image1 = Helper.toBufferedImage(image);
			ImageIO.write(image1, "jpg", new File("userPic\\"+id+".jpg"));
			server.sendDataToClient(new RequestData("true"));
			} catch (IOException e) {
				server.sendDataToClient(new RequestData("false"));
				e.printStackTrace();
				}
		}
}
