import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class AntiBody extends SlidingSprite{
	Image antiImage;
	Toolkit tk;
	public AntiBody(myCharacter m, Dimension map, Dimension spriteSize2, int amount, int index, int xChange2, int yChange2){
		super(map,spriteSize2,xChange2,yChange2);
		tk = Toolkit.getDefaultToolkit();
	//	try{
//	//		antiImage =ImageIO.read(getClass().getResource("/y.png"));
	//	}catch(IOException e){}
		int angle = index*360/amount;
		stepX = (int)(18*(Math.cos(angle)));
		stepY = (int)(18*(Math.sin(angle)));
		xChange = xChange2;
		yChange = yChange2;

		x=m.getX()-xChange+50;
		y=m.getY()-yChange+50;

	}
	public void drawAntiBody(Graphics g, Image ii){
		g.drawImage(ii,x+xChange,y+yChange, null);
	}
	public void setChange(int x, int y){
		xChange=x;
		yChange=y;
	}
}
