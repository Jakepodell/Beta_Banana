import java.util.Random;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.applet.*;
import java.util.concurrent.*;
//================================CREATE CLASS AND VARIABLES==============================
public class myCharacter{
	BufferedImage myImage;
	int x;
	int y;
	Dimension appletSize;
	int lives, stepX, stepY, borderSize;
	boolean ghost;
	int ghostCount;
	AudioClip LaserSound;
	int xChange;
	int yChange;
	Point p;
	int slowCount;
	CopyOnWriteArrayList<AntiBody> antiBodies;
	boolean attackWithSoup;
	int xOff;
	int yOff;
	GeneralPath polygon;
	BananaLaser bl;
	Weapon[] ws;
	JFrame frame;
//=================================CONSTRUCTER=============================
	public myCharacter(BufferedImage i, Dimension applet, int border, JFrame f){
		myImage=i;
		stepX = 15;
		stepY = 15;
		lives = 5;
		appletSize = applet;
		borderSize = border;
		ghost = false;
		ghostCount=0;
		antiBodies=new CopyOnWriteArrayList<AntiBody>();
		xOff = 350;
		yOff = 250;
		bl= new BananaLaser(this);
		polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 8);
					polygon.moveTo(5+xOff, 2+yOff);
					polygon.lineTo(20+xOff, yOff);
					polygon.lineTo(75+xOff,64+yOff);
					polygon.lineTo(50+xOff,73+yOff);
					polygon.lineTo(30+xOff,73+yOff);
					polygon.lineTo(8+xOff,58+yOff);
					polygon.lineTo(xOff,40+yOff);
					polygon.lineTo(xOff,22+yOff);
	polygon.closePath();
	setStartPoint(new Point(300,500));
	frame=f;

	}
//=================================METHODS======================================
//GIVE LASER
	public void setLaser(Weapon[] weapons){
		if(weapons[0]!=null)bl.setBliffLaser(weapons[0].getDescription());
		if(weapons[1]!=null)bl.setShmockLaser(weapons[1].getDescription());
		if(weapons[2]!=null)bl.setPangaLaser(weapons[2].getDescription());
		if(weapons[3]!=null)bl.setGlooshLaser(weapons[3].getDescription());
		if(weapons[4]!=null)bl.setAmyLaser(weapons[4].getDescription());
		if(weapons[5]!=null)bl.setEyeraLaser(weapons[5].getDescription());
		ws = weapons;
	}
//DRAW
	public void drawCharacter(Graphics2D g){
		g.drawImage(myImage,x,y,null);
		bl.drawLaserIcons(g,ws,frame);
	//	//(frame.getHeight());
	}
	public void setFrame(JFrame f){
		frame=f;
	}
//MOVE
	public void move(boolean xMove, boolean yMove, boolean xAxis, boolean yAxis, boolean move){
		if(move ==true){
			if(xMove == true && xAxis ==true){
	//			x = x+(stepX);
				xChange = xChange-stepX;
			}
			else if(xMove ==false && xAxis ==true){
			//	x = x-(stepX);
				xChange = xChange+stepX;
			}
			if(yMove == true && yAxis ==true){
	//			y = (y + (stepY));
				yChange = yChange - stepY;
			}
			else if(yMove == false && yAxis ==true){
	//			y = (y - (stepY));
				yChange = yChange + stepY;
			}
		}
	}
//CHECK IF ITS TOUCHING BORDERS
	public void checkBounce(){
		if(x<=borderSize||x>=(appletSize.getWidth()-borderSize)-(myImage.getWidth())||
		   y<=borderSize||y>=700-borderSize-(myImage.getHeight())){
			loseLife();

		}
	}
//LOSE A LIFE

	public void loseLife(){
///*
if(!ghost){
		lives--;
		setGhost();
		x = 350;
		y = 250;
		xChange = (int)(((appletSize.getWidth()/2)-(myImage.getWidth()/2))-p.getX());
		yChange =(int)(((700/2)-(myImage.getHeight()/2))-p.getY());
	}
	//*/
	}

	public void outOfBounds(){
		ghostCount=0;
		x = 350;
		y = 250;
		lives--;
		setGhost();

		xChange = (int)(((900/2)-(myImage.getWidth()/2))-p.getX());
		yChange =(int)(((700/2)-(myImage.getHeight()/2))-p.getY());
	}
	public void slow(){
		slowCount = 50;
	}
	public void addLife(){
		lives++;
	}
	public void resetLifes(){
		lives=5;
	}
	public void checkSpeed(){
		if(slowCount>0){
			stepX=8;
			stepY =8;
			slowCount--;
		}
		else{
			stepX=15;
			stepY =15;
		}
	}
//RETURN LIVES
	public int getLives(){
		return(lives);
	}
//CHANGE THE X VALUE
	public void addX(int newX){
		x= x+newX;
	}
//CHANGE THE Y VAULE
	public void addY(int newY){
		y = y+newY;
	}
//GET THE Y VALUE
	public int getY(){
		return(y);
	}
//GET THE X VALUE
	public int getX(){
		return(x);
	}
//GET THE ORIGIN POINT
	public Point getOrigin(){
		return(new Point(x,y));
	}
//GET THE CHARACTER DIMENSIONS
	public Dimension getDimension(){
		return(new Dimension(myImage.getWidth(), myImage.getHeight()));
	}
//GET THE WIDTH
	public int getWidth(){
		return(myImage.getWidth());
	}
	public int getHeight(){
		return(myImage.getHeight());
	}
///RETURN AN ARRAYLIST OF RECTANGLES SURROUNDING IMAGE
	public Shape getShape(){
		return(polygon);
	}
		public ArrayList<Rectangle> getCollisionBoxes(){
			ArrayList<Rectangle> a = new ArrayList<Rectangle>();
			a.add(new Rectangle(354,250,10,19));
			a.add(new Rectangle(355,269,18,4));
			a.add(new Rectangle(351,273,55,34));
			a.add(new Rectangle(355,306,70,20));
			a.add(new Rectangle(358,320,84,6));
			a.add(new Rectangle(364,326,84,14));
			a.add(new Rectangle(383,340,61,10));
			return(a);
	}








// ACTIVATE GHOST MODE
	public void setGhost(){
		ghost = true;
	}
// CHECK IF IT IS GHOST
	public void checkGhost(int time){
		if(ghost==true){
			ghostCount++;
			if(ghostCount>=(time/50)){ //change 50 pt be game timer delay
				ghostCount=0;
				ghost =false;
			}
		}
	}
// RETURN IF GHOST MODE HAS BEEN ACTIVATED
	public boolean isGhost(){
		return(ghost);
	}
// GET GHOSTCOUNT
	public int getGhostCount(){
		return(ghostCount);
	}
// SHOOT LASERS
	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<? extends SlidingSprite> a){
//		if(currentLaser==Laser.bliff){
			bl.shootLasers(g,a,ws);

/*			currentLaser=Laser.none;
		}
		else if(currentLaser==Laser.shmock){
			sl.shootLasers(g,a);
			currentLaser=Laser.none;
		}
		else if(currentLaser==Laser.panga){
			pl.shootLasers(g,a);
			currentLaser=Laser.none;
		}
		else if(currentLaser==Laser.gloosh){
			gl.shootLasers(g,a);
			currentLaser=Laser.none;
		}
		else if(currentLaser==Laser.amy){
			al.shootLasers(g,a);
			currentLaser=Laser.none;
		}
		else if(currentLaser==Laser.Eyera){
			el.shootLasers(g,a);
			currentLaser=Laser.none;
		}
	}
	public void setLaser(String enemy){
		currentLaser = Laser.valueOf(enemy);
	}
*/
}
public void makeAntibodies(){
	for(int i=0; i<72; i++){
		antiBodies.add(new AntiBody(this,new Dimension(20,20),new Dimension(2000,2000),72,i,xChange,yChange));
	}
}
public CopyOnWriteArrayList<AntiBody> getAntiBodies(){
	return(antiBodies);
}
public void clearAntiBodies(){
	antiBodies.clear();
}
public boolean isSoupAttacking(){
	return(attackWithSoup);
}
public void setSoupAttack(boolean b){
	attackWithSoup=b;
}
// RESET
	public void reset(){
		x = (int)(900/2)-(myImage.getWidth()/2);
		y = (int)(700/2)-(myImage.getHeight()/2);
		lives = 5;
		ghost = false;
		slowCount=0;
	}
	public int getXChange(){
		return(xChange);
	}
	public int getYChange(){
		return(yChange);
	}
	public void setStartPoint(Point start){
		p = start;
		x = 350;
		y = 250;
		xChange = (int)(((900/2)-(myImage.getWidth()/2))-p.getX());
		yChange =(int)(((700/2)-(myImage.getHeight()/2))-p.getY());
	}
	public int XCHANGE = xChange;
}

//=================================EXTRA======================================
/*public void checkBounce(){

}*/