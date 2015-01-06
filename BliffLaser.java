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


public class BliffLaser{
	myCharacter fruit;
	double oldDistance;
	Bliff closestBliff;


	public BliffLaser(myCharacter m){
		fruit=m;
	}


	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<Bliff> a){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.BLUE);

			for(Bliff b: a){
				Point newBliffPoint = b.getOrigin();
				double newDistance = b.getDistance(fruit.getOrigin(),new Point(b.getX()+fruit.getXChange(),b.getY()+fruit.getYChange()));
				if(newDistance<oldDistance||oldDistance==0){
					oldDistance=newDistance;
					closestBliff=b;
				}
			}

			Bliff b = closestBliff;
			g.drawLine(fruit.getX()+42, fruit.getY()+57, b.getX()+10+fruit.getXChange(), b.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, b.getX()+10+fruit.getXChange(), b.getY()+10+fruit.getYChange());
			a.remove(b);
			oldDistance =0;
	}





}
