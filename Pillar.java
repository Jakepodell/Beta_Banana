import java.util.Map;
import java.util.HashMap;
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
public class Pillar{
	Image spikeImage;
	Toolkit tk;
	Point start;
	int radius;
	Dimension size;
	int xChange;
	int yChange;
	int bigxChange;
	int bigyChange;
	GeneralPath polygon;
	Ellipse2D.Double ellipse;
	boolean shape;
	public Pillar(Point start2, Dimension size2, boolean rectangle){
		tk = Toolkit.getDefaultToolkit();
		start = start2;
		size = size2;
		shape = rectangle;
	}
	public Pillar(int x, int y, int width, int height, boolean rectangle){
			tk = Toolkit.getDefaultToolkit();
			start = new Point(x,y);
			size = new Dimension(width,height);
			shape = rectangle;
	}
	public Pillar(){
		start = new Point(0,0);
		size = new Dimension(0,0);
		shape = true;
	}
	public void drawPillar(Graphics2D g,int xChange2, int yChange2){
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(10));
		if(shape==true){
		bigyChange = yChange2 + (int)start.getY();;
		bigxChange = xChange2 + (int)start.getX();
	/*	for(int i = 0; i<size.getWidth(); i=i+25){
			spikeImage = tk.getImage("spike"+Spikes.BOTTOM+".png");
			g.drawImage(spikeImage, i+bigxChange,bigyChange, null);
			spikeImage = tk.getImage("spike"+Spikes.TOP+".png");
			g.drawImage(spikeImage, i+bigxChange, (int)size.getHeight()+bigyChange, null);
		}
		for(int i = 0; i<size.getHeight(); i=i+25){
			spikeImage = tk.getImage("spike"+Spikes.RIGHT+".png");
			g.drawImage(spikeImage, bigxChange,i+bigyChange, null);
			spikeImage = tk.getImage("spike"+Spikes.LEFT+".png");
			g.drawImage(spikeImage, (int)size.getWidth()+bigxChange, i+bigyChange, null);
		}
		g.setColor(Color.black);
//		g.fillRect(bigxChange+15, bigyChange+15, (int)size.getWidth()-15, (int)size.getHeight()-15);*/

		polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,4);
		polygon.moveTo(bigxChange,bigyChange);
		polygon.lineTo(bigxChange,(int)size.getHeight()+bigyChange+15);
		polygon.lineTo((int)size.getWidth()+bigxChange+15,(int)size.getHeight()+bigyChange+15);
		polygon.lineTo((int)size.getWidth()+bigxChange+15,bigyChange);
		polygon.closePath();
	//	g.setColor(Color.cyan);
		g2.draw(polygon);
	}
	else{

		/*for(int i = (int)-r; i<=r; i=i+10){
			point.add(new Point(i, (int)Math.sqrt((r*r)-(i*i))));
			orientation.add(5);
			point.add(new Point(i, -(int)Math.sqrt((r*r)-(i*i))));
			orientation.add(5);
			point.add(new Point(-(int)Math.sqrt((r*r)-(i*i)),i ));
			orientation.add(5);
			point.add(new Point((int)Math.sqrt((r*r)-(i*i)),i ));
			orientation.add(5);
		}
		*/
		double a = size.getWidth()/2;
		double b = size.getHeight()/2;
		double h = start.getX()+a;
		double k = start.getY()+b;
		bigyChange = yChange2 + (int)k;;
		bigxChange = xChange2 + (int)h;

/*		for(int i = (int)-a; i<=a; i=i+10){
			spikeImage = tk.getImage("Spikeball"+".png");
/*bottom/	g.drawImage(spikeImage, i+bigxChange,(int)(bigyChange+(b*Math.sqrt((a*a)-(i*i)))/a), null);
/*top/	//	g.drawImage(spikeImage, i+bigxChange,(int)(bigyChange-(b*Math.sqrt((a*a)-(i*i)))/a), null);
		}
		for(int i = (int)-b; i<=b; i=i+10){
/*right///	g.drawImage(spikeImage, (int)(bigxChange+(a*Math.sqrt((b*b)-(i*i)))/b),i+bigyChange, null);
/*left///	g.drawImage(spikeImage, (int)(bigxChange-(a*Math.sqrt((b*b)-(i*i)))/b),i+bigyChange, null);
		}
		g.setColor(Color.black);
		//g.fillRect(bigxChange+15, bigyChange+15, (int)size.getWidth()-15, (int)size.getHeight()-15);*/
		ellipse = new Ellipse2D.Double(start.getX()+xChange2, start.getY()+yChange2, size.getWidth()+25, size.getHeight()+25);
		polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,4);
		polygon.moveTo(bigxChange,bigyChange);
		polygon.lineTo(bigxChange,(int)size.getHeight()+bigyChange+15);
		polygon.lineTo((int)size.getWidth()+bigxChange+15,(int)size.getHeight()+bigyChange+15);
		polygon.lineTo((int)size.getWidth()+bigxChange+15,bigyChange);
		polygon.closePath();
		//g.setColor(Color.cyan);
		g2.draw(ellipse);
	}}
	public void setChange( int newX, int newY){
		xChange = newX;
		yChange = newY;
	}
	public Shape getPillar(){
		if(shape==RECTANGLE){
			return(polygon);
		}
		else{
			return(ellipse);
		}
	}
	public final static boolean RECTANGLE= true;
	public final static boolean	OVAL = false;
}
