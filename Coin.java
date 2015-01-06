import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
//import java.io.*;
public class Coin extends SlidingSprite{
	Image coinImage;
	Toolkit tk;
	Random ai;
	int value;
	public Coin(Shape map, Pillar[] pill, Dimension coinSize, int xChange2, int yChange2){
		super(map,pill, coinSize, xChange2, yChange2);
		tk = Toolkit.getDefaultToolkit();
		ai = new Random();
		int i = ai.nextInt(19);
		try{
		if(i>=4){
			coinImage = ImageIO.read(getClass().getResource("/LogoCoin.gif"));
			value = 1;
		}else if(i>=1){
			coinImage = ImageIO.read(getClass().getResource("/purpcoin.gif"));
			value = 5;
		}else{
			coinImage = ImageIO.read(getClass().getResource("/greencoin.gif"));
			value = 15;
		}
		}catch(IOException e){}
	}
	public Coin(Shape map, Dimension coinSize, int xChange2, int yChange2, Bliff b){
			super(map, coinSize, xChange2, yChange2);
			tk = Toolkit.getDefaultToolkit();
			ai = new Random();
			try{
				coinImage = ImageIO.read(getClass().getResource("/purpcoin.gif"));
			}catch(IOException e){}
			value = 5;
			x = b.getX();
			y=b.getY();
	}
public Coin(Shape map, Dimension coinSize, int xChange2, int yChange2, Gloosh b){
			super(map, coinSize, xChange2, yChange2);
			tk = Toolkit.getDefaultToolkit();
			ai = new Random();
			try{
				coinImage = ImageIO.read(getClass().getResource("/LogoCoin.gif"));
			}catch(IOException e){}
			value = 1;
			x = b.getX();
			y=b.getY();
			ai = new Random();
			stepX = ai.nextInt(3)-2;
			stepY = ai.nextInt(3)-2;
	}
	public void drawCoin(Graphics g){
		g.drawImage(coinImage,x+xChange,y+yChange, null);
	}
	public int getValue(){
		return(value);
	}

}