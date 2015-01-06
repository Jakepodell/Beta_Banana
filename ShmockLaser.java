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


public class ShmockLaser{
	myCharacter fruit;
	double oldDistance;
	Shmock closestShmock;


	public ShmockLaser(myCharacter m){
		fruit=m;
	}


	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<Shmock> a){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.GREEN);

			for(Shmock s: a){
				Point newShmockPoint = s.getOrigin();
				double newDistance = s.getDistance(fruit.getOrigin(),new Point(s.getX()+fruit.getXChange(),s.getY()+fruit.getYChange()));
				if(newDistance<oldDistance||oldDistance==0){
					oldDistance=newDistance;
					closestShmock=s;
				}
			}

			Shmock s = closestShmock;
			g.drawLine(fruit.getX()+42, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
			a.remove(s);
			oldDistance =0;
	}





}
