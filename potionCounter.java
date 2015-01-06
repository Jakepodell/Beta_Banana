import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.applet.*;
import java.io.*;
import javax.imageio.*;
public class potionCounter{
	int x;
	int y;
	int num;
	int den;
	Image potionIcon;
	boolean set;
	int collected;
	int diff;
	public potionCounter(int xCor,int yCor){
		x = xCor;
		y = yCor;
		num =0;
		try{
			potionIcon = ImageIO.read(getClass().getResource("potion3.png"));
		}catch(IOException e){}
	}
	public void drawPotionCounter(Graphics2D g){
		if(collected<num){
			//("collected="+collected);
			//("num="+num);

			if(diff<(num-collected)){
				den-=(num-collected)-diff;
				diff=(num-collected);
			}num-=(num-collected);


		}
		g.setFont(new Font("SansSerif", Font.BOLD, 22));
		g.drawImage(potionIcon,x,y,null);
		g.drawString((num+" / "+den), x+30, y+20);

	}
	public void collect(){
		num++;
	}
	public void setNum(int i){
		num=i;
	}
	public void setPotionCount(int pot){
		den=pot;
	}
		public void clear(){
			set=false;
			den=0;
			collected=0;
		}
		public void ready(){
			set=true;
	}
	public boolean isSet(){
		return(set);
	}
	public boolean noDen(){
		return(den==0);
	}
	public void addCollected(int pot){
		collected+=pot;
	}
	public int getCollected(){
		return(collected);
	}
	public void setDen(int left){
		den=collected+left;
		if(left==0)den=num;
	}
}