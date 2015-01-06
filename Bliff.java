import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Bliff extends SlidingSprite{
	Image bliffImage;
	Image portalImage;
	Toolkit tk;
	boolean isCoin;
	public Bliff(Portal p,Dimension bliffSize, Shape map, int xChange2, int yChange2){
		super(p,bliffSize,map,xChange2, yChange2);
		isCoin=false;
		tk = Toolkit.getDefaultToolkit();
		try{
			if(stepX>0)
			bliffImage = ImageIO.read(getClass().getResource("/bliff4.png"));
			else
			bliffImage = ImageIO.read(getClass().getResource("/bliff3.png"));
		}catch(IOException e){}

	}
	public void drawBliff(Graphics g){
		g.drawImage(bliffImage,x+xChange,y+yChange, null);
	}
	public void makeCoin(){
		isCoin=true;
	}
	public boolean isCoin(){
		return(isCoin);
	}
}
