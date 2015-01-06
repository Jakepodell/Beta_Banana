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


public class PangaLaser{
	myCharacter fruit;
	double oldDistance;
	Panga closestPanga;


	public PangaLaser(myCharacter m){
		fruit=m;
	}


	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<Panga> a){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.MAGENTA);

			for(Panga p: a){
				Point newPangaPoint = p.getOrigin();
				double newDistance = p.getDistance(fruit.getOrigin(),new Point(p.getX()+fruit.getXChange(),p.getY()+fruit.getYChange()));
				if(newDistance<oldDistance||oldDistance==0){
					oldDistance=newDistance;
					closestPanga=p;
				}
			}

			Panga p = closestPanga;
			g.drawLine(fruit.getX()+42, fruit.getY()+57, p.getX()+10+fruit.getXChange(), p.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, p.getX()+10+fruit.getXChange(), p.getY()+10+fruit.getYChange());
			a.remove(p);
			oldDistance =0;
	}





}
