package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class SuitImages {
	
	//private static String path = "CardSuits" + File.separator;
	//private static String ftype = ".png";
	
	private static boolean ready = false;
	
	private static BufferedImage spade1;
	private static BufferedImage heartt;
	private static BufferedImage diamond;
	private static BufferedImage tref;
	
	public static boolean isReady() {
		return ready;
	}
	
	public static BufferedImage getDiamond() {
		return diamond;
	}
	
	public static BufferedImage getHeart() {
		return heartt;
	}
	
	public static BufferedImage getSpade() {
		return spade1;
	}
	
	public static BufferedImage getTref() {
		return tref;
	}
	
	public  static void BufferImages() throws IOException
	{
		//Merr fotot nga path-i i dhene, perkatesisht figurat me cilat do te krijohen kartat
		spade1 = ImageIO.read(new File("C:/Users/k-ves/workspace/Loja-me-letra/bin/CardSuits/spade.png"));
		heartt = ImageIO.read(new File("C:/Users/k-ves/workspace/Loja-me-letra/bin/CardSuits/heartt.png"));
		diamond = ImageIO.read(new File("C:/Users/k-ves/workspace/Loja-me-letra/bin/CardSuits/diamond.png"));
		tref = ImageIO.read(new File("C:/Users/k-ves/workspace/Loja-me-letra/bin/CardSuits/tref.png"));
		ready = true;
	}
}