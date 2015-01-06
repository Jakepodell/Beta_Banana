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
import java.awt.geom.*;
public class EyeraBeam extends Line2D.Double{
	Eyera firstEyera;
	Eyera secondEyera;
	int x1;
	int x2;
	int y1;
	int y2;
	double fadePercent;
	boolean remove;
	enum Health{good, bad}
	Health health;
	public EyeraBeam(Eyera e1, Eyera e2, int xChange, int yChange){
		if(e2.getHealth().equalsIgnoreCase("dying")){
			firstEyera=e2;
			secondEyera=e2;
		}
		else{
			firstEyera=e1;
			secondEyera=e2;
		}
		setLine(firstEyera.getOrigin().getX()+25+xChange,firstEyera.getOrigin().getY()+17+yChange,secondEyera.getOrigin().getX()+25+xChange,secondEyera.getOrigin().getY()+17+yChange);
		x1 = (int)this.getX1();
		x2 = (int)this.getX2();
		y1 = (int)this.getY1();
		y2 = (int)this.getY2();
		fadePercent=.01;
		health = Health.good;
	}
	public void drawEyeraBeam(Graphics2D g){
		    g.setStroke(new BasicStroke(5));
			 //fade = new GradientPaint(x1,y1,new Color(0,0,0,0),x1+(int)((x2-x1)*fadePercent),y1+(int)((y2-y1)*fadePercent),new Color(0,0,0,0));

			if(firstEyera.getHealth().equalsIgnoreCase("dying")){
				fadePercent=(double)firstEyera.getFade()/100;
				health=Health.bad;

			}
			if(fadePercent==0){
				fadePercent=0.01;
			}
	//		//("FadePercent "+fadePercent);
			if(fadePercent>=0.85)fadePercent=1;
			else{
				Color fadingRed =new Color(255,0,0,(int)((1-fadePercent)*255));
				Color fadingGreen= new Color(0,255,0,(int)((1-fadePercent)*255));
				Color clear=new Color(0,0,0,0);
				Color[] colors = {clear, Color.red,Color.green};
				float[] dist = {(float)(fadePercent/4.0),(float)(fadePercent),1f};

				if(fadePercent<.01)fadePercent=.01;
				if(x2==x1)x2=x1+1;
				LinearGradientPaint fade = new LinearGradientPaint(x1,y1,x1+(int)Math.ceil(((x2-x1)*fadePercent*3)),y1+(int)Math.ceil(((y2-y1)*fadePercent*3)),dist,colors);
				g.setPaint(fade);
			}
			//g.setPaint(fade);
			if(fadePercent==0){
				g.setColor(Color.green);
		//		//("FadePercent "+fadePercent);
			}
			else{
		//		//("FadePercent "+fadePercent);

			}
	//		g.setColor(Color.red);
			if(secondEyera.getHealth().equalsIgnoreCase("dying"))
			setRemove();
			else if(fadePercent==1||firstEyera.getHealth().equalsIgnoreCase("dead")){
				firstEyera.kill();
				secondEyera.setHealth("dying");
		//		//("killing");
				setRemove();
			}
			else if(firstEyera.getHealth().equalsIgnoreCase("remove") || secondEyera.getHealth().equalsIgnoreCase("remove"))
			setRemove();
			else
			g.draw(this);


		g.setStroke(new BasicStroke(1));
		g.setColor(Color.black);
	}
	public void setChange(int x, int y){
		setLine(firstEyera.getOrigin().getX()+25+x,firstEyera.getOrigin().getY()+17+y,secondEyera.getOrigin().getX()+25+x,secondEyera.getOrigin().getY()+17+y);
		x1 = (int)this.getX1();
		x2 = (int)this.getX2();
		y1 = (int)this.getY1();
		y2 = (int)this.getY2();
		if(secondEyera.getHealth().equalsIgnoreCase("dying")&&firstEyera.getHealth().equalsIgnoreCase("good")){
					Eyera e3 = firstEyera;
					Eyera e4 = secondEyera;
					firstEyera=e4;
					secondEyera=e3;
		}
	}
	public void setRemove(){
		remove=true;
	}
	public boolean getRemove(){
		return(remove);
	}
	public String getHealth(){
		return(health.toString());
	}
//	public Rectangle getRect(
}
