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


public class AmyLaser{
	myCharacter fruit;
	double oldDistance;
	Amy closestAmy;


	public AmyLaser(myCharacter m){
		fruit=m;
	}


	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<Amy> a){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.CYAN);

			for(Amy am: a){
				Point newAmyPoint = am.getOrigin();
				double newDistance = am.getDistance(fruit.getOrigin(),new Point(am.getX()+fruit.getXChange(),am.getY()+fruit.getYChange()));
				if(newDistance<oldDistance||oldDistance==0){
					oldDistance=newDistance;
					closestAmy=am;
				}
			}

			Amy am = closestAmy;
			g.drawLine(fruit.getX()+42, fruit.getY()+57, am.getX()+10+fruit.getXChange(), am.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, am.getX()+10+fruit.getXChange(), am.getY()+10+fruit.getYChange());
			a.remove(am);
			oldDistance =0;
	}





}
