import java.lang.Integer;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
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
import javax.imageio.*;
import java.applet.*;
import java.net.URL;
public class Laser3 extends JApplet{
	Image laser;
	Image laser2;
	Image laser3;
	Image laser4;
	Random ai;
	Toolkit tk;
	boolean shoot,shoot2;
	Timer t;
	AudioClip pew;
	URL songpath;
	int w,x,y,x3,y3;
	ArrayList<Rectangle> rex;
	int length, length2;
	boolean horizontal;
	int xChange;
	int yChange;
	Rectangle beam;
	int r;
	int r2;
	int r3;
	boolean right;
	int moveChangeX;
	boolean left;
	boolean up;
	boolean down;
	boolean move;
	int delay;
	enum State{shooting,charging,waiting}
	State laserState;
	public Laser3(int x2, int y2, int width, boolean horiz, boolean moove, int delay2){
		tk = Toolkit.getDefaultToolkit();

		laserState = State.waiting;
		delay = delay2;

		move= moove;

		laser = tk.getImage("cannon.png");
		laser2 = tk.getImage("cannon2.png");
		laser3 = tk.getImage("cannon3.png");
		laser4 = tk.getImage("cannon4.png");

		shoot2=false;

		rex = new ArrayList<Rectangle>();

		ai = new Random();
		x = x2;
		y = y2;
		x3=x;
		y3=y;
		length = width;
		length2 = width;
		horizontal = horiz;

		try{
			songpath = getClass().getResource("Laser_Blast.wav");
			pew = Applet.newAudioClip(songpath);
		}catch(Exception e){//("no");}


		beam = new Rectangle(0,0,100,100);
		right = true;
		left = false;
		down = true;
		up = false;
		moveChangeX=0;
	}

	public int getDelay(){
		return(delay);
	}
	public boolean isShooting(){
		if(laserState==State.shooting){
			return(true);
		}
		else{return(false);}
	}
	public void forceShoot(){
		laserState=State.shooting;
	}


	public void setChange(int xChange2, int yChange2){
			xChange= xChange2;
			yChange=yChange2;
	}
	public void drawLaser(Graphics2D g2, int xChange, int yChange){
			length = length2-60;
			g2.setColor(Color.gray);
			g2.setStroke(new BasicStroke(3));
			g2.drawOval(700,550,25,25);
			Color laserRed = new Color(255,0,0,r2);
			//(w);

			g2.setColor(laserRed);
			//	float dash1[] = {10.0f};
//
			//						g2.setStroke(new BasicStroke(1.0f,
			 //                       BasicStroke.CAP_BUTT,
			 //                       BasicStroke.JOIN_MITER,
			 //                       10.0f, dash1, 0.0f));
			// g2.setStroke(new BasicStroke(0));



//
			g2.fillOval(700,550,25,25);

			if(horizontal == true){
				g2.setColor(Color.red);
			//	g2.fillOval(x+xChange+57-(int)(w/4),y+yChange+13-(int)(w/4),(int)(w/2),(int)(w/2));
			//	g2.fillOval(length+x+xChange+56-(int)(w/4),y+yChange+14-(int)(w/4),(int)(w/2),(int)(w/2));
				g2.drawImage(laser,x+xChange,y+yChange,this);
				g2.drawImage(laser2, x+xChange+length, y+yChange, this);
				g2.setPaint(new GradientPaint(10,0,laserRed,20,0,new Color(0,0,0,0),true));
				g2.draw(getBeam());
				g2.setColor(Color.pink);
				for(int i = 0; i<rex.size(); i++){
					Rectangle rect2 = rex.get(i).getBounds();
					rect2.setLocation((int)rect2.getX()+xChange, (int)rect2.getY()+yChange);
					Rectangle rect = rex.get(i).getBounds();
					rect.setLocation((int)rect.getX()+length+xChange-60, (int)rect.getY()+yChange);
					g2.draw(rect);
					g2.draw(rect2);
				}
				if(laserState==State.shooting){
					g2.setColor(Color.red);
					g2.drawLine(x+xChange+60,y+14+yChange,x+xChange+length,y+yChange+14);
					Color brightRed = new Color(255,70,70);
					Color brighterRed  = new Color(255,100,100);
					g2.setColor(brightRed);
					g2.drawLine(x+xChange+60,y+16+yChange,x+xChange+length,y+yChange+16);
					g2.drawLine(x+xChange+60,y+12+yChange,x+xChange+length,y+yChange+12);
					g2.setColor(brighterRed);
					g2.drawLine(x+xChange+60,y+18+yChange,x+xChange+length,y+yChange+18);
					g2.drawLine(x+xChange+60,y+10+yChange,x+xChange+length,y+yChange+10);
					laserState=State.waiting;
				//	pew.play();
					rex.clear();
				}
			}
			if(horizontal ==false){
						g2.setColor(Color.red);
					//	g2.fillOval(x+xChange+14-(int)(w/4),y+yChange+57-(int)(w/4),(int)(w/2),(int)(w/2));
					//	g2.fillOval(length+x+xChange+14-(int)(w/4),y+yChange+57-(int)(w/4),(int)(w/2),(int)(w/2));
						g2.drawImage(laser3,x+xChange,y+yChange,this);
						g2.drawImage(laser4, x+xChange, y+yChange+length, this);
						g2.setPaint(new GradientPaint(0,10,laserRed,0,20,new Color(0,0,0,0),true));
						g2.draw(getBeam());
						g2.setColor(Color.pink);
						for(int i = 0; i<rex.size(); i++){
							Rectangle rect2 = rex.get(i).getBounds();
							rect2.setLocation((int)rect2.getX()+xChange, (int)rect2.getY()+yChange);
							Rectangle rect = rex.get(i).getBounds();
							rect.setLocation((int)rect.getX()+xChange, (int)rect.getY()+yChange+length-60);
							g2.draw(rect);
							g2.draw(rect2);
						}
						if(laserState==State.shooting){
							g2.setColor(Color.red);
							g2.drawLine(x+xChange+14,y+yChange+60,x+xChange+14,y+yChange+length);
							Color brightRed = new Color(255,70,70);
							Color brighterRed  = new Color(255,100,100);
							g2.setColor(brightRed);
							g2.drawLine(x+xChange+16,y+yChange+60,x+xChange+16,y+yChange+length);
							g2.drawLine(x+xChange+12,y+yChange+60,x+xChange+12,y+yChange+length);
							g2.setColor(brighterRed);
							g2.drawLine(x+xChange+18,y+yChange+60,x+xChange+18,y+yChange+length);
							g2.drawLine(x+xChange+10,y+yChange+60,x+xChange+10,y+yChange+length);
							laserState=State.waiting;
							pew.play();
							rex.clear();
							w=0;
				}/*
				x = x3+xChange;
				y = y3+yChange+30;
				//(x);
				g2.setColor(Color.red);
				g2.fillOval(x+14-(int)(w/4),y+56-(int)(w/4),(int)(w/2),(int)(w/2));
				g2.fillOval(x+14-(int)(w/4),y+30+length-(int)(w/4),(int)(w/2),(int)(w/2));
				g2.drawImage(laser3,x,y,this);
				g2.drawImage(laser4, x, y+length+30, this);
				g2.setColor(Color.pink);
				for(int i = 0; i<rex.size(); i++){

					Rectangle rect2 = rex.get(i).getBounds();
					rect2.setLocation((int)rect2.getX()+xChange, (int)rect2.getY()+yChange);
					Rectangle rect = rex.get(i).getBounds();
					rect.setLocation((int)rect.getX()+xChange, (int)rect.getY()+length+yChange-30);
					g2.draw(rect);
					g2.draw(rect2);
				}
				beam= new Rectangle(x+10,y+60,5,length);
				if(shoot2 == true){
					g2.setColor(Color.red);
					g2.drawLine(x+14,y+60,x+14,y+length);
					Color brightRed = new Color(255,70,70);
					Color brighterRed  = new Color(255,100,100);
					g2.setColor(brightRed);
					g2.drawLine(x+16,y+60,x+18,y+length+60);
					g2.drawLine(x+12,y+60,x+12,y+length+60);
					g2.setColor(brighterRed);
					g2.drawLine(x+18,y+60,x+18,y+length+60);
					g2.drawLine(x+10,y+60,x+10,y+length+60);
					shoot2 = false;
				///			pew.play();
					rex.clear();

				}
			}
		*/}
	}
	public Rectangle getBeam(){
		if(horizontal==true){
			return(new Rectangle(x+xChange+60,y+yChange+12,length,5));
		}else{
			return(new Rectangle(x+xChange+12,y+yChange+60,5,length));
		}
	}
	public void shoot(){
		laserState=State.shooting;
	}
	public void charge(){
		laserState=State.charging;
			r3=r3+4;
			r2 = r3+(int)Math.pow(r,2);
			r++;
	}
/*	public void charge(){
		if(laserState==State.charging){
			if(horizontal==true){
				if(w<15){
		//			rex.add(new Rectangle(x+57+ai.nextInt(50), y+10+ai.nextInt(50),2,2));
					w++;
					r3=r3+4;
				}
				else{


					r2 = r3+(int)Math.pow(r,2);

					r++;
					w++;
				}
				for(int i = 0; i<rex.size(); i++){
					if(rex.get(i).getX()>x+50){
						rex.get(i).setLocation((int)rex.get(i).getX()-3,(int)rex.get(i).getY());
					}if(rex.get(i).getX()<x+50){
						rex.get(i).setLocation((int)rex.get(i).getX()+3,(int)rex.get(i).getY());
					}
					if(rex.get(i).getY()>y+10){
						rex.get(i).setLocation((int)rex.get(i).getX(),(int)rex.get(i).getY()-10);
					}if(rex.get(i).getY()<y+10){
						rex.get(i).setLocation((int)rex.get(i).getX(),(int)rex.get(i).getY()+10);
					}
					if(rex.get(i).getX() == x+xChange+50 && rex.get(i).getY() ==y+yChange+10){
						rex.remove(i);
					}
				}

				if(w==30){
					w = 0;
					r =0;
					r2 =0;
					r3=0;
					laserState=State.shooting;
				}
			}
			else{
				if(w<15){
			//		rex.add(new Rectangle(x-10+ai.nextInt(50), y+40+ai.nextInt(50),2,2));
					w++;
					//("charge");
					r3=r3+4;
				}
				else{
					w++;
					//("charge");


					r2 = r3+(int)Math.pow(r,2);
					r++;
				}
				for(int i = 0; i<rex.size(); i++){
					if(rex.get(i).getX()>x+10){
						rex.get(i).setLocation((int)rex.get(i).getX()-10,(int)rex.get(i).getY());
					}if(rex.get(i).getX()<x+10){
						rex.get(i).setLocation((int)rex.get(i).getX()+10,(int)rex.get(i).getY());
					}
					if(rex.get(i).getY()>y+50){
						rex.get(i).setLocation((int)rex.get(i).getX(),(int)rex.get(i).getY()-3);
					}if(rex.get(i).getY()<y+50){
						rex.get(i).setLocation((int)rex.get(i).getX(),(int)rex.get(i).getY()+3);
					}
					if(rex.get(i).getX() == x+xChange+10 && rex.get(i).getY() ==y+yChange+50){
						rex.remove(i);
					}
				}

				if(w==30){
					w = 0;
					r=0;
					r2 = 0;
					r3=0;
					laserState=State.shooting;
				}
			}
		}
	}*/
	public void move(double yy,int height){
		int width=(int)yy;
		if(move == MOVE){
		if(horizontal){
			if(down ==true){
				y=y+10;
				moveChangeX = moveChangeX+10;
			}if(up == true){
				y=y-10;
				moveChangeX = moveChangeX-10;
			}
			if(y<0){
				up = false;
				down = true;
			}
			if(y>height-60){
				down = false;
				up = true;
			}
		}
		else{

			if(right ==true){
				x=x+10;
				moveChangeX = moveChangeX+10;
			}if(left == true){
				x=x-10;
				moveChangeX = moveChangeX-10;
			}
			if(x<0){
				left = false;
				right = true;
			}
			if(x>width-60){
				right = false;
				left = true;
			}
		}
	}}
	public int getX(){
		return(x);
	}
	public final static boolean HORIZ = true;
	public final static boolean VERT = false;
	public final static boolean MOVE = true;
	public final static boolean DONT_MOVE = false;


}
