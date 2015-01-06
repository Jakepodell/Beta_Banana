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


public class EyeraLaser{
	myCharacter fruit;
	double oldDistance;
	Eyera closestEyera;


	public EyeraLaser(myCharacter m){
		fruit=m;
	}


	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<Eyera> a){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.YELLOW);

			for(Eyera e: a){
				Point newEyeraPoint = e.getOrigin();
				double newDistance = e.getDistance(fruit.getOrigin(),new Point(e.getX()+fruit.getXChange(),e.getY()+fruit.getYChange()));
				if(newDistance<oldDistance||oldDistance==0){
					oldDistance=newDistance;
					closestEyera=e;
				}
			}

			Eyera e = closestEyera;
			g.drawLine(fruit.getX()+42, fruit.getY()+57, e.getX()+10+fruit.getXChange(), e.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, e.getX()+10+fruit.getXChange(), e.getY()+10+fruit.getYChange());
			a.remove(e);
			oldDistance =0;
	}





}
