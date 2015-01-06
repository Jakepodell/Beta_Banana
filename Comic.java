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
public class Comic implements KeyListener, ActionListener{
	BufferedImage comic;
	double scale;
	int x;
	int y;
	Timer t;
	int ticks;
	int turn;
	JButton skip;
	enum State{fading,moving, waiting, done}
	State camera;
	Rectangle fader;
	int fade;
	public Comic(){
		try{comic=ImageIO.read(getClass().getResource("Comic2-01.jpg"));
	}catch(Exception e){}
//		setContentPane(new drawingPanel());
//		addKeyListener(this);
//		setFocusable(true);
//		setSize(new Dimension(900,700));
		scale = 1.2;
		t = new Timer(60 , this);
	//	t.start();
	//	turn=1;
		x=300;
		y=130;
//		setVisible(true);
		camera = State.waiting;
		skip = new JButton("skip");
		skip.addActionListener(this);
		skip.setBounds(650,500,60,30);
		skip.setVisible(true);
		fader = new Rectangle(0,0,900,900);
		fade=255;
	}
	public void skip(){
		turn=10;
	}
	public JButton getSkip(){
		return(skip);
	}
	public void next(){
		if(camera==State.waiting){
			turn++;
			camera = State.moving;
			//("turn = "+turn);
			//("x coordinate = "+x);
			//("y coordinate = "+y);
			//("Scale ratio = "+scale);
			//("");
		}

		else if(turn==0){
			x = 300;
			y = 130;
			scale = 1.2;
		}

		else if(turn==1){
					x = 6;
					y = 718;
					scale = 1.18;
		}

		else if(turn==2){
					x = 1150;
					y = 42;
					scale = 1.18;
		}

		else if(turn==3){
					x = 690;
					y = 19;
					scale = 1.18;
		}

		else if(turn==4){
					x = 1000;
					y = 577;
					scale = 1.04;
		}

		else if(turn==5){
					x = 20;
					y = 381;
					scale = 1.62;
		}

		else if(turn==6){
					x = 400;
					y = 609;
					scale = 1.088;
		}

		else if(turn==7){
							x = 4;
							y = 15;
							scale = 1.548;
		}
		else camera=State.done;
	}
	public String getState(){
		return(camera.toString());
	}
	public boolean isDone(){
		return(turn>7);
	}
		//public class drawingPanel extends JPanel{
		//		@Override
			public void drawComic(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			g2.scale(scale,scale);
			g2.drawImage(comic,-x,-y,null);
			if(fade>10)fade-=10;
			else fade=0;
			g2.setColor(new Color(0,0,0,fade));
			//(fade);
			g2.fill(fader);
		//	//(camera.toString());


			if(x>10&&turn==1){

						if(x>153){

							scale-=.01*2;
						}

						if(x<153)scale+=.01*2;

						y+=6*2;
						x-=3*2;

					}
					else if(turn==1)camera = State.waiting;;


					if(turn==2&&x<1130){
						if(x<570)scale-=0.01*2;
						else scale+=0.01*2;
						y-=6.5*2;
						x+=11*2;
					}
					else if(turn==2)camera = State.waiting;;


					if(turn==3&&x>700){
						y-=2;
						x-=10*2;
//						repaint();
					}
					else if(turn==3)camera = State.waiting;;


					if(turn==4&&x<1000){
						y+=9*2;
						x+=5*2;
						if(x<885)scale-=.01*2;
						else scale+=.01*2;
					}
					else if(turn==4)camera = State.waiting;;


					if(turn==5&&x>20){

								if(x>800){
									scale-=.01*2;
								}
								else {
									scale+=.01*2;

									}
									y-=2*2;
									x-=10*2;
							}
					else if(turn==5)camera = State.waiting;;

					if(turn==6&&x<400){

										//if(x>800){
											scale-=.014*2;
										//}
										//else {
										//	scale+=.01;

										//	}
											y+=6*2;
											x+=10*2;
									}
					else if(turn==6)camera = State.waiting;;



					if(turn==7&&x>10){

												  if(x>350){
													scale-=.01*2;
												}
												else {
													scale+=.01*2;

													}
													y-=9*2;
													x-=6*2;
												//(x);
											}
		else if(turn==7)camera = State.waiting;;


//		}
	}
	public void keyTyped(KeyEvent k){
	}
	public void keyPressed(KeyEvent k){

		if(k.getKeyCode()==KeyEvent.VK_E){scale+=.03;}
		if(k.getKeyCode()==KeyEvent.VK_R&&scale>0){scale-=.03;}

		if(k.getKeyCode()==KeyEvent.VK_UP&&scale>0){y-=10;}
		if(k.getKeyCode()==KeyEvent.VK_DOWN){y+=10;}
		if(k.getKeyCode()==KeyEvent.VK_RIGHT){x+=10;}
		if(k.getKeyCode()==KeyEvent.VK_LEFT&&scale>0){x-=10;}
		if(k.getKeyCode()==KeyEvent.VK_SPACE){turn++; camera = State.moving;}
	}
	public void keyReleased(KeyEvent k){
	}
	public void actionPerformed(ActionEvent e){
			turn=10;
	}
}