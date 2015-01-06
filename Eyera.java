import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.awt.geom.*;
public class Eyera extends SlidingSprite{
	enum Health{good, dying, dead, remove};
	Health health;
	Image eyeraImage;
	Image portalImage;
	Toolkit tk;
	boolean split;
	int state;
	int fade;
	CopyOnWriteArrayList<Eyera> brothers;
	public Eyera(Portal p,Dimension eyeraSize, Shape map, int xChange2, int yChange2){
		super(p,eyeraSize,map, xChange2, yChange2);
		health = Health.good;
		state = 1;
		tk = Toolkit.getDefaultToolkit();
		try {
			eyeraImage = ImageIO.read(getClass().getResource("/Eyera2.png"));
		}catch(IOException e){
		}
		brothers = new CopyOnWriteArrayList<Eyera>();
	//	fade=1;
	}
	public void drawEyera(Graphics g){
		if(health.toString().equalsIgnoreCase("good"))g.drawImage(eyeraImage,x+xChange,y+yChange, null);
		System.out.println(health.toString());
		if(health==Health.dying)fade+=5;
	}
	public Point getOrigin(){
		return(new Point(x,y));
	}
	public int getState(){
		return state;
	}
	public void kill(){
		health=Health.dead;
//		//("you killed it");
	}
	public void remove(){
		if(health==Health.good)
		health=Health.remove;
	}
	public void connect(Eyera e, int xChange, int yChange){
		brothers.add(e);
	}
	public String getHealth(){
		return(health.toString());
	}
	public void setHealth(String s){
		health = Health.valueOf(s);
	//	if(health==Health.dying){
	//		try {
	//		eyeraImage = ImageIO.read(getClass().getResource("/clear.png"));
	//		}catch(IOException e){
	//	}}
	}
	public int getFade(){
		return(fade);
	}
}
