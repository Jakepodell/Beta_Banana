import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.io.*;
public class Soup extends Rectangle{
	int soups;
	int extra;
	int x;
	int y;
	Image im;
	public Soup(Rectangle mapBounds){
		soups =(int) Math.floor(mapBounds.getHeight()/48.0);
		extra = (int) mapBounds.getHeight()%48;
		setRect(mapBounds.getX()+(extra/2), mapBounds.getY(), 48, mapBounds.getHeight()-(extra/2));
		try{
			im=ImageIO.read(getClass().getResource("/soup.png"));
		}catch(IOException e){}
	}
	public void drawSoup(Graphics2D g2, int x, int y){
		for(int i =0; i<soups; i++){
				g2.drawImage(im, x, y+i*48, null);
		}
		g2.draw(this);
		setLocation(x,y);
	}
	public void setBounds(Rectangle mapBounds){
		setSize(48,(int)mapBounds.getHeight());
	}

}