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


public class GlooshLaser{
	myCharacter fruit;
	double oldDistance;
	Gloosh closestGloosh;


	public GlooshLaser(myCharacter m){
		fruit=m;
	}


	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<Gloosh> a){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.BLACK);

			for(Gloosh gl: a){
				Point newGlooshPoint = gl.getOrigin();
				double newDistance = gl.getDistance(fruit.getOrigin(),new Point(gl.getX()+fruit.getXChange(),gl.getY()+fruit.getYChange()));
				if(newDistance<oldDistance||oldDistance==0){
					oldDistance=newDistance;
					closestGloosh=gl;
				}
			}

			Gloosh gl = closestGloosh;
			g.drawLine(fruit.getX()+42, fruit.getY()+57, gl.getX()+10+fruit.getXChange(), gl.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, gl.getX()+10+fruit.getXChange(), gl.getY()+10+fruit.getYChange());
			a.remove(gl);
			oldDistance =0;
	}





}
