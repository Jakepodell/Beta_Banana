import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class MiniPanga extends SlidingSprite{
	Image pangaImage, newImage;
	Toolkit tk;
	public MiniPanga(Panga p,Dimension pangaSize, Dimension appletSize, int number, int xChange2, int yChange2){
		super(p,pangaSize,appletSize, number, xChange2, yChange2);
		tk = Toolkit.getDefaultToolkit();
		//pangaImage =tk.getImage("y.gif");
		try{
		if(number ==1){
			pangaImage =ImageIO.read(getClass().getResource("/PurplePanga.png"));


		}
		else if(number == 2){
			pangaImage =ImageIO.read(getClass().getResource("/OrangePanga.png"));
		}
		else if(number == 3){
			pangaImage =ImageIO.read(getClass().getResource("/PinkPanga.png"));
		}
		else if(number == 4){
			pangaImage =ImageIO.read(getClass().getResource("/BluePanga.png"));
		}
		else if(number == 5){
			pangaImage =ImageIO.read(getClass().getResource("/YellowPanga.png"));
		}
		else if(number == 6){
			pangaImage =ImageIO.read(getClass().getResource("/GreenPanga.png"));
		}
		}catch(IOException e){}
		newImage = pangaImage.getScaledInstance(18,23,0);
	}
	public void drawMiniPanga(Graphics g){
		g.drawImage(newImage,x+xChange,y+yChange, null);
	}
}
