import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Gloosh extends SlidingSprite{
	Image glooshImage;
	Toolkit tk;
	int slimeCount;
	enum Trails{slime, none, coin}
	Trails currentTrail;
	ArrayList<Slime> trail;
	public Gloosh(Portal p, Dimension glooshSize,Shape map, int xch, int ych){
		super(p, glooshSize,map,xch,ych);
		tk = Toolkit.getDefaultToolkit();
		try{
			glooshImage = ImageIO.read(getClass().getResource("/gloosh.png"));
		}catch(IOException e){}
		trail = new ArrayList<Slime>();
		stepX = (int)(stepX*1.5);
		stepX = (int)(stepX*1.5);
		currentTrail=Trails.slime;
	}
	public void drawGloosh(Graphics g){
		if(currentTrail==Trails.slime){
			for(int i =0; i<trail.size(); i++){
				trail.get(i).drawSlime(g);
			}
		}
		g.drawImage(glooshImage,x+xChange,y+yChange, null);
	}
	public void addSlime(){
		slimeCount++;
	}
	public void reduceSlime(){
			slimeCount--;
	}
	public int getSlime(){
		return(slimeCount);
	}
	public void checkSlime(){
		trail.add(new Slime(this));
		slimeCount++;
		if(slimeCount>70){
			trail.remove(0);
			slimeCount--;
		}

	}
	public int getXC(){
		return(xChange);
	}
	public int getYC(){
			return(yChange);
	}
	public ArrayList<Slime> getTrail(){
		return(trail);
	}
	public String getTrailType(){
		return(currentTrail.toString());
	}
	public void setTrailType(String s){
		currentTrail = Trails.valueOf(s);
	}
}
