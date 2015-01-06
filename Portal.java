import java.awt.geom.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Portal{
	Image portalImage;
	Toolkit tk;
	Random ai;
	int x;
	int y;
	int state;
	boolean openned;
	ArrayList<Image> portalImages;
	int xChange;
	int yChange;
	Rectangle boundary;
	Shape area;
	public Portal(Shape map,Pillar[] pill, ArrayList<Image> portals, int xChange2, int yChange2){
		tk = Toolkit.getDefaultToolkit();
		ai = new Random();
		boundary = map.getBounds();
		area = map;
		xChange = xChange2;
		yChange = yChange2;
		x=ai.nextInt((int)boundary.getWidth());
		y=ai.nextInt((int)boundary.getHeight());
		while((area.contains(new Rectangle(x+xChange,y+yChange,100,100))==false)){
						x=ai.nextInt((int)boundary.getWidth());
						y=ai.nextInt((int)boundary.getHeight());
			}
		for(int i = 0; i<pill.length; i++){
			while((area.contains(new Rectangle(x+xChange,y+yChange,100,100))==false)||(pill[i].getPillar().intersects(new Rectangle(x+xChange,y+yChange,100,100))||(pill[i].getPillar().contains(new Rectangle(x+xChange,y+yChange,100,100))))){
				x=ai.nextInt((int)boundary.getWidth());
				y=ai.nextInt((int)boundary.getHeight());
			}
		}
		state = 0;
		openned = false;
		portalImages = portals;
		portalImage = portalImages.get(state);

	}
	public void nextState2(){
		state++;
		portalImage = portalImages.get(state);
	}
	public int getState(){
		return(state);
	}
	public void drawPortal(Graphics g){
		g.drawImage(portalImage,x+xChange,y+yChange, null);
//		g.drawRect(x+xChange,y+yChange,100,100);
	}
	public Point getOrigin(){
		return(new Point(x,y));
	}
	public void Open(){
		openned = true;
	}
	public boolean isOpenned(){
		return(openned);
	}
	public void setChange( int newX, int newY){
		xChange = newX;
		yChange = newY;
	}

}