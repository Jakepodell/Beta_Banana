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
import javax.swing.border.*;
import javax.imageio.*;
import java.util.Map;
import java.awt.font.*;
import javax.swing.UIManager.*;
import javax.swing.text.*;
import java.awt.geom.*;
public class Weapon extends JToggleButton{
	ImageIcon image;
	String descrip;
	int time;
	int delay;
//	int index;
	public Weapon(ImageIcon i, String s, int t, boolean unlocked){//, int in){
		super(i);
		image=i;
		descrip=s;
		setRolloverEnabled(true);
		if(!unlocked)setEnabled(false);
	//	index = in;
	time =t;
	delay=time;
	}
	 @Override
       protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(getModel().isSelected()){
			setBorder(new LineBorder(Color.black, 5));
		}
		else if(getModel().isRollover()){
			setBorder(new LineBorder(Color.black, 3));
		}
		else {
			setBorder(new LineBorder(Color.black, 1));
		}
	}
	public String getDescription(){
		return(descrip);
	}
	public ImageIcon getIcon(){
		return(image);
	}
	public int getDelay(){
		return(delay);
	}
	public int getTime(){
		return(time);
	}
	public void shoot(){
		delay=0;
	}
	public void reload(){
		if(delay<time)delay++;
	}
	public boolean isReloading(){
		return(delay<time);
	}
/*	public int getIndex(){
		return(index);
	}

	public static final int BLIFF = 1;
	public static final int SHMOCK =2;
	public static final int PANGA = 3;
	public static final int GLOOSH = 4;
	public static final int AMY = 5;
	public static final int EYERA = 6;*/
}