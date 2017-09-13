package seu.edu.common;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageToBufferImage {
	
	//将Image转化为BufferedImage
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) { return (BufferedImage) image; }

		image = new ImageIcon(image).getImage(); 

		//ImageIcon与Image之间的转化: ImageIcon icon = new ImageIcon(image); image = icon.getImage();

		boolean hasAlpha = false;

		BufferedImage bimage = null;

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		try {

		int transparency = Transparency.OPAQUE;

		if (hasAlpha) { transparency = Transparency.BITMASK; }

		GraphicsDevice gs = ge.getDefaultScreenDevice();

		GraphicsConfiguration gc = gs.getDefaultConfiguration();

		bimage = gc.createCompatibleImage(image.getWidth(null),

		image.getHeight(null), transparency);

		} catch (HeadlessException e) {

		}

		if (bimage == null) {

		int type = BufferedImage.TYPE_INT_RGB;

		if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }

		bimage = new BufferedImage(image.getWidth(null),image.getHeight(null), type);

		}

		Graphics g = bimage.createGraphics();

		g.drawImage(image, 0, 0, null);

		g.dispose();

		return bimage;

		}
}
