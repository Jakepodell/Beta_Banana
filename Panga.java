import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.applet.*;
import java.net.URL;
public class Panga extends SlidingSprite{
	Image pangaImage;
	Image portalImage;
	Toolkit tk;
	boolean exploded;
	int state;
	enum Explosion{minis, no, hearts}
	Explosion xplode;
	public Panga(Portal p,Dimension pangaSize, Shape map, int xChange2, int yChange2){
		super(p,pangaSize,map, xChange2, yChange2);
		state = 1;
		xplode=Explosion.minis;
		tk = Toolkit.getDefaultToolkit();
		try {
			pangaImage = ImageIO.read(getClass().getResource("/Panga1.png"));
		}catch(IOException e){
		}
		exploded = false;
	}
	public void drawPanga(Graphics g){
		g.drawImage(pangaImage,x+xChange,y+yChange, null);
	}
	public Point getOrigin(){
		return(new Point(x,y));
	}
	public void explode(){
		exploded = true;
	}
	public boolean isExploded(){
		return(exploded);
	}
	public void setState(int newState){
		state = newState;
		if(state<=8){
		try{
			pangaImage=ImageIO.read(getClass().getResource("/Panga"+state+".png"));
		}catch(IOException e){}
	}
	}
	public int getState(){
		return state;
	}
	public ArrayList<Heart> pangaToHearts(Dimension appletSize, int xChange2, int yChange2){
		ArrayList<Heart> hearts = new ArrayList<Heart>();
		for(int i =1; i<7; i++){
			hearts.add(new Heart(this,new Dimension(20,20), appletSize, i, xChange2, yChange2));
		}
		return(hearts);
	}
	public String xToString(){
		return(xplode.toString());
	}
	public void setExplosion(String s){
		xplode=Explosion.valueOf(s);
	}
}
