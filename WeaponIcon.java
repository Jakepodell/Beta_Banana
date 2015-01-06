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
import java.util.Enumeration;
import java.util.Random;
import javax.swing.border.*;
public class WeaponIcon{
	public static void drawIcon(Graphics2D g, Weapon[] weapons, double height){

		if(weapons[0]!=null)
		g.drawImage(weapons[0].getIcon().getImage(),25,(int)height-100,null);
		if(weapons[1]!=null)g.drawImage(weapons[1].getIcon().getImage(),100,(int)height-100,null);
		if(weapons[2]!=null)g.drawImage(weapons[2].getIcon().getImage(),175,(int)height-100,null);
		if(weapons[3]!=null)g.drawImage(weapons[3].getIcon().getImage(),250,(int)height-100,null);
		if(weapons[4]!=null)if(weapons[0]!=null)g.drawImage(weapons[4].getIcon().getImage(),325,(int)height-100,null);
		if(weapons[5]!=null)g.drawImage(weapons[5].getIcon().getImage(),400,(int)height-100,null);

		g.setColor(new Color(150,150,150,100));

		if(weapons[0]!=null)g.fillRect(25,(int)height-100,55,(int)((55.0/weapons[0].getTime())*(weapons[0].getTime()-weapons[0].getDelay())));
		if(weapons[1]!=null)g.fillRect(100,(int)height-100,55,(int)((55.0/weapons[1].getTime())*(weapons[1].getTime()-weapons[1].getDelay())));
		if(weapons[2]!=null)g.fillRect(175,(int)height-100,55,(int)((55.0/weapons[2].getTime())*(weapons[2].getTime()-weapons[2].getDelay())));
		if(weapons[3]!=null)g.fillRect(250,(int)height-100,55,(int)((55.0/weapons[3].getTime())*(weapons[3].getTime()-weapons[3].getDelay())));
		if(weapons[4]!=null)g.fillRect(325,(int)height-100,55,(int)((55.0/weapons[4].getTime())*(weapons[4].getTime()-weapons[4].getDelay())));
		if(weapons[5]!=null)g.fillRect(400,(int)height-100,55,(int)((55.0/weapons[5].getTime())*(weapons[5].getTime()-weapons[5].getDelay())));

		g.setColor(new Color(250,250,250,75));

		if(weapons[0]!=null)if(weapons[0].isReloading())g.fillRect(25,(int)height-100,55,54);
		if(weapons[1]!=null)if(weapons[1].isReloading())g.fillRect(100,(int)height-100,55,54);
		if(weapons[2]!=null)if(weapons[2].isReloading())g.fillRect(175,(int)height-100,55,54);
		if(weapons[3]!=null)if(weapons[3].isReloading())g.fillRect(250,(int)height-100,55,54);
		if(weapons[4]!=null)if(weapons[4].isReloading())g.fillRect(325,(int)height-100,55,54);
		if(weapons[5]!=null)if(weapons[5].isReloading())g.fillRect(400,(int)height-100,55,54);

		for(Weapon w: weapons){
			if(w!=null)w.reload();
		}
//		//("HEIGHT = "+((int)height-100));

	}
}