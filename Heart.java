import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Heart extends SlidingSprite{
	Image heartImage;
	Toolkit tk;
	public Heart(Panga p,Dimension pangaSize, Dimension appletSize, int number, int xChange2, int yChange2){
		super(p,pangaSize,appletSize, number, xChange2, yChange2);
		tk = Toolkit.getDefaultToolkit();
		try{
			heartImage =ImageIO.read(getClass().getResource("/heart.png"));
		}catch(IOException e){}
	}
	public void drawHeart(Graphics g){
		g.drawImage(heartImage,x+xChange,y+yChange, null);
	}
}
