import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.*;
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
import java.awt.geom.*;
import java.awt.font.*;
import javax.swing.border.*;


public class Laser{


	int x1,x2,y1,y2,xChange,yChange;
	Point st, end;
	CopyOnWriteArrayList<Rectangle> charges;
	boolean move;
	enum State{shooting,charging,waiting}
	State laserState;
	Toolkit tk;
	Image l1, l2;
	AudioClip pew;
	boolean orientation;
	int step;
	int pathSize;
	Random ai;
	boolean moveCharges;
	double ovalR;
	int chargeTime;
	int period;
	int ticker;
	int length;
	double alpha;


	public Laser(int someX, int someY,int length2,boolean orientation2,boolean shouldMove,int period2,int pathSize2){
		move=shouldMove;
		laserState=State.waiting;
		orientation=orientation2;
		tk = Toolkit.getDefaultToolkit();
	//	pew = Applet.newAudioClip(getClass().getResource("Laser_Blast.wav"));
		step =2;
		pathSize=pathSize2;
		charges=new CopyOnWriteArrayList<Rectangle>();
		ai=new Random();
		chargeTime=15;
		period = period2;
		length=length2;
		if(orientation==HORIZONTAL){
			//SOMEY IS THE CENTER OF THE LASER ON THE Y AXIS
			//SOMEX IS THE LEFT OF THE LASER ON THE X AXIS
			st = new Point(someX+WIDTH, someY);
			end = new Point(someX+length-WIDTH, someY);
			x1=someX;
			y1=someY-(HEIGHT/2);
			x2=x1+length-WIDTH;
			y2=y1;
			try{
				l1 = ImageIO.read(getClass().getResource("/cannon.png"));//.getScaledInstance(150,150,0);
				l2 = ImageIO.read(getClass().getResource("/cannon2.png"));
			}catch(IOException e){}
		}

		else if(orientation==VERTICAL){
			//SOMEY IS THE TOP OF THE LASER ON THE Y AXIS
			//SOMEX IS THE CENTER OF THE LASER ON THE X AXIS
			st= new Point(someX, someY+WIDTH);
			end=new Point(someX, someY+length-WIDTH);
			x1=someX-HEIGHT/2;
			y1=someY;
			x2=x1;
			y2=y1+length-WIDTH;
			try{
				l1 = ImageIO.read(getClass().getResource("/cannon3.png"));
				l2 = ImageIO.read(getClass().getResource("/cannon4.png"));
			}catch(IOException e){}
		}

	}
	public void moveCharges(){
	//	if(ovalR<18&&ticker>=period-chargeTime-8)
		ovalR+=20.0/period;
	//	if(ticker%2==0){
		boolean remove = true;

		for(Rectangle r: charges){
			if(r.getX()>st.getX()+3){
				r.setLocation((int)r.getX()-1,(int)r.getY());//move left
				remove=false;
			}
			else if(r.getX()<st.getX()-3){
				r.setLocation((int)r.getX()+1,(int)r.getY());//move right
				remove=false;
			}
			if(r.getY()>st.getY()+3){
				r.setLocation((int)r.getX(),(int)r.getY()-1);//move up
				remove=false;
			}
			else if(r.getY()<st.getY()-3){
				r.setLocation((int)r.getX(),(int)r.getY()+1);//move down
				remove=false;
			}
			if(remove)charges.remove(charges.indexOf(r));
//		}
		}
	}
	public void shoot(){
		laserState=State.shooting;
		alpha=0;
	}
	public void shutOff(){
		laserState=State.waiting;
		//("off");
	}
	public void charge(){
		laserState=State.charging;
	}
	public int getDelay(){
		return(period);
	}
	public void addCharge(){
//		if(ticker%2==0 && laserState==State.charging){
			int rX = ai.nextInt(50)-25+(int)st.getX();
			int rY = ai.nextInt(50)-25+(int)st.getY();
			charges.add(new Rectangle(rX,rY,3,3));
//		}
	}

	public void move(){
			if(orientation==HORIZONTAL && move==MOVE){
				y1+=step;
				y2=y1;
				for(Rectangle r:charges){
					r.setLocation((int)r.getX(),(int)r.getY()+step);
				}
				if( (step>0 && y1+WIDTH/2>pathSize) ||(step<0 && y1-WIDTH/2<0) )step*=-1;
				st.setLocation(st.getX(),st.getY()+step);
				end.setLocation(end.getX(),end.getY()+step);
			}
			else if(move==MOVE){
				x1+=step;
				x2=x1;
				for(Rectangle r:charges){
					r.setLocation((int)r.getX()+step,(int)r.getY());
				}
				if( (step>0 && x1+HEIGHT/2>pathSize) ||(step<0 && x1-HEIGHT/2<0) )step*=-1;
				st.setLocation(st.getX()+step,st.getY());
				end.setLocation(end.getX()+step,end.getY());
			}
		}

	public void drawLaser(Graphics2D g2, int xChange2, int yChange2){
		xChange= xChange2;
		yChange=yChange2;
		g2.setColor(Color.red);
		if(step>0){
			g2.fillOval((int)(st.getX()+xChange-(ovalR/2)), (int)(st.getY()+yChange-(ovalR/2)), (int)ovalR,(int)ovalR);
			if(orientation==HORIZONTAL)g2.fillOval((int)(st.getX()+xChange+length-WIDTH*2-(ovalR/2)), (int)(st.getY()+yChange-(ovalR/2)), (int)ovalR,(int)ovalR);
			else g2.fillOval((int)(st.getX()+xChange-(ovalR/2)), (int)(st.getY()+yChange-(ovalR/2)+length-WIDTH*2), (int)ovalR,(int)ovalR);
		}
		else g2.fillOval((int)(st.getX()+xChange-(ovalR/2)), (int)(st.getY()+yChange-(ovalR/2))+3, (int)ovalR,(int)ovalR);
			if(orientation==HORIZONTAL)g2.fillOval((int)(st.getX()+xChange+length-WIDTH*2-(ovalR/2))+3, (int)(st.getY()+yChange-(ovalR/2)), (int)ovalR,(int)ovalR);
			else g2.fillOval((int)(st.getX()+xChange-(ovalR/2)), (int)(st.getY()+yChange-(ovalR/2)+length-WIDTH*2)+3, (int)ovalR,(int)ovalR);
		((Graphics)g2).drawImage(l1,x1+xChange,y1+yChange,null);
		g2.drawImage(l2,x2+xChange,y2+yChange,null);

		if(laserState==State.shooting){
			drawBeam(g2);
			ovalR=0;
			charges.clear();
//			//("shoot");
		}
		if(laserState==State.charging){
			addCharge();

		}
	//	moveCharges();
		drawCharges(g2);
		drawIndicator(g2);
	//	//("drawLaser called");
	//	//("painting");
	//	controlState();

	}
	public void controlState(){

		if(ticker==period-chargeTime-8){
			laserState=State.charging;
		}
		else if(ticker==period-8){
			laserState=State.waiting;
		}
		else if(ticker==period-1){
			laserState=State.shooting;
		}
		else if(ticker==period){
			ticker=0;
			ovalR=0;
			charges.clear();
			laserState=State.waiting;
			alpha=0;
		}
		ticker++;
	}


	public void drawBeam(Graphics2D g2){
		g2.setStroke(new BasicStroke(10));
		g2.setColor(Color.red);
		g2.drawLine((int)st.getX()+xChange,(int)st.getY()+yChange, (int)end.getX()+xChange, (int)end.getY()+yChange);
//		drawIndicator(g2);
//		g2.fillOval(100,100,100,100);
	}

	public void drawCharges(Graphics2D g2){
		g2.setColor(Color.pink);
		for(Rectangle r: charges){
				g2.fillOval((int)r.getX()+xChange, (int)r.getY()+yChange,5, 5);
				if(orientation==HORIZONTAL) g2.fillOval((int)r.getX()+xChange+length-WIDTH*2, (int)r.getY()+yChange,5, 5);
				else g2.fillOval((int)r.getX()+xChange, (int)r.getY()+yChange+length-WIDTH*2,5, 5);

		}
//		moveCharges();
	}

	public void drawIndicator(Graphics2D g2){
	//	if(ticker>=period-12)
		////(40.0/period);
	//	//("drawIndicator called");
		if(( (int)((14.0/period)*Math.pow(alpha+1,1.8)))<255)alpha++;
		////("alpha ="+alpha);
		////(alpha);
		////(period);
						float dash1[] = {20.0f};

											g2.setStroke(new BasicStroke(5.0f,
					                        BasicStroke.CAP_BUTT,
					                       BasicStroke.JOIN_MITER,
			                       10.0f, dash1, 0.0f));
		if(( (int)((2.0/period)*Math.pow(alpha,1.8)))<255)
		g2.setColor(new Color(255,0,0,(int)((14.0/period)*Math.pow(alpha,1.8))));  //JUST CHECK IF ITS TOO HIGH AND FIX IT
		else
		g2.setColor(new Color(255,0,0,255));
		//((int)((14.0/period)*Math.pow(alpha,1.8)));
		g2.drawLine((int)st.getX()+xChange,(int)st.getY()+yChange, (int)end.getX()+xChange, (int)end.getY()+yChange);
	}

	public Rectangle getBeam(){
		if(orientation==HORIZONTAL)
		return(new Rectangle((int)st.getX()+xChange,(int)st.getY()+yChange-5, (int)length-WIDTH*2, 10));
		else
		return(new Rectangle((int)st.getX()+xChange-5,(int)st.getY()+yChange,10 ,(int)length-WIDTH*2 ));
	}
	public String getState(){
		return(laserState.toString());
	}



	public final static boolean HORIZONTAL = true;
	public final static boolean VERTICAL = false;
	public final static boolean MOVE = true;
	public final static boolean DONT_MOVE = false;
	public final static int WIDTH = 60;
	public final static int HEIGHT = 33;
}
