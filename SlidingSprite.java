import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
//================================CREATE CLASS AND VARIABLES==============================
public class SlidingSprite{
	Image SlidingSpriteImage;
	myCharacter character;
	int x, y;
	Random ai;
	Dimension size, spriteSize;
	Toolkit tk;
	int stepX, stepY;
	boolean inside, touching;
	int borderSize = 16;
	double characterWidth, characterHeight, characterX, characterY;
	int xChange;
	int yChange;
	Shape map;
	Rectangle boundary;
	boolean remove;

//=================================CONSTRUCTER WITH NO PORTAL======================================
	public SlidingSprite(Shape map ,Pillar[] pill, Dimension spriteSize2, int xChange2, int yChange2){

		ai = new Random();
		xChange = xChange2;
		yChange = yChange2;
		x=ai.nextInt((int)map.getBounds().getWidth());
		y=ai.nextInt((int)map.getBounds().getHeight());
		while(map.contains(new Rectangle(x+xChange,y+yChange,(int)spriteSize2.getHeight(),(int)spriteSize2.getWidth()))==false){
			x=ai.nextInt((int)map.getBounds().getWidth());
			y=ai.nextInt((int)map.getBounds().getHeight());
		}
		for(int i = 0; i<pill.length; i++){
			while((map.contains(new Rectangle(x+xChange,y+yChange,100,100))==false)||(pill[i].getPillar().intersects(new Rectangle(x+xChange,y+yChange,(int)spriteSize2.getHeight(),(int)spriteSize2.getWidth()))||(pill[i].getPillar().contains(new Rectangle(x+xChange,y+yChange,(int)spriteSize2.getHeight(),(int)spriteSize2.getWidth()))))){
				x=ai.nextInt((int)(int)map.getBounds().getWidth());
				y=ai.nextInt((int)(int)map.getBounds().getHeight());
			}
		}
		size = new Dimension((int)map.getBounds().getHeight(),(int)map.getBounds().getWidth());

		while(stepX==0){
			stepX = ai.nextInt(10)-5;
		}
		while(stepY==0){
			stepY = ai.nextInt(10)-5;
		}
		spriteSize = spriteSize2;
		remove = false;

	}
//=================================CONSTRUCTER WITH NO PORTAL======================================
	public SlidingSprite(Shape map, Dimension spriteSize2, int xChange2, int yChange2){

		ai = new Random();
		xChange = xChange2;
		yChange = yChange2;
		x=ai.nextInt((int)map.getBounds().getWidth());
		y=ai.nextInt((int)map.getBounds().getHeight());
		while(map.contains(new Rectangle(x+xChange,y+yChange,(int)spriteSize2.getHeight(),(int)spriteSize2.getWidth()))==false){
			x=ai.nextInt((int)map.getBounds().getWidth());
			y=ai.nextInt((int)map.getBounds().getHeight());
		}
		size = new Dimension((int)map.getBounds().getHeight(),(int)map.getBounds().getWidth());

		while(stepX==0){
			stepX = ai.nextInt(10)-5;
		}
		while(stepY==0){
			stepY = ai.nextInt(10)-5;
		}
		spriteSize = spriteSize2;
		remove = false;

	}
//================================CONTRUCTER WITH PORTAL=========================================
	public SlidingSprite(Portal p, Dimension spriteSize2, Shape map2, int xChange2, int yChange2){
		spriteSize = spriteSize2;
		ai = new Random();
		map = map2;
		x = ((int)p.getOrigin().getX())+25;
		y = ((int)p.getOrigin().getY())+25;
		while(stepX==0){
			stepX = ai.nextInt(10)-5;
		}
		while(stepY==0){
			stepY = ai.nextInt(10)-5;
		}
		xChange = xChange2;
		yChange = yChange2;
		remove = false;

	}
//===============================CONSTRUCTER FOR MINI PANGA ======================================
	public SlidingSprite(Panga p, Dimension spriteSize2, Dimension appletSize, int number, int xChange2, int yChange2){
		spriteSize = spriteSize2;
		size = appletSize;
		x = (int)p.getOrigin().getX();
		y = (int)p.getOrigin().getY();
		if(number ==1){
			stepX = 11;
			stepY = 7;
		}
		else if(number == 2){
			stepX = 0;
			stepY = 13;
		}
		else if(number == 3){
			stepX = -11;
			stepY = 7;
		}
		else if(number == 4){
			stepX = -11;
			stepY = -7;
		}
		else if(number == 5){
			stepX = 0;
			stepY = -13;
		}
		else if(number == 6){
			stepX = 11;
			stepY = -7;
		}
		xChange = xChange2;
		yChange = yChange2;remove = false;
	}
	public SlidingSprite(Dimension spriteSize2, Dimension appletSize, int xChange2, int yChange2){
			spriteSize = spriteSize2;
			size = appletSize;
			xChange = xChange2;
			yChange = yChange2;
	}
//===============================CONSTRUCTER FOR AMY ======================================
	public SlidingSprite(Amy a, Dimension spriteSize2, Shape map2, int number, int xChange2, int yChange2){
		ai = new Random();
		spriteSize = spriteSize2;
		map = map2;

		if(number ==1){
			stepX = ai.nextInt(5)-5;
			stepY = ai.nextInt(10)-5;
			x = (int)a.getOrigin().getX();
			y = (int)a.getOrigin().getY();
		}
		else if(number == 2){
			stepX = ai.nextInt(5);
			stepY = ai.nextInt(10)-5;
			x = (int)a.getOrigin().getX()+45;
			y = (int)a.getOrigin().getY();
		}
		xChange = xChange2;
		yChange = yChange2;
		remove = false;
	}
//=========================================METHODS================================================
//DRAW
	public void drawSlidingSprite(Graphics g){
		g.drawImage(SlidingSpriteImage,x,y, null);
	}
//MOVE
	public void move(){
		x = x+stepX;
		y = y+stepY;
	}
//CHECK IF INSIDE APPLET
	public boolean isInside(){
		inside = true;
		if(map.contains(new Rectangle(x,y,(int)spriteSize.getHeight(), (int)spriteSize.getWidth()))==false){
			inside = false;
		}
		return(inside);
	}
//CHECK IF IT HITS A RECTANGLE
	public boolean hits(Point p, Dimension d){
		Point coinPoint = new Point(x+xChange, y+yChange);
		boolean b = false;
		if(coinPoint.getX()>p.getX()-spriteSize.getWidth()    &&
		   coinPoint.getX()<p.getX()+d.getWidth() &&
		   coinPoint.getY()<p.getY()+d.getHeight()&&
		   coinPoint.getY()>p.getY()-spriteSize.getHeight())
			{
			b = true;
		}
	return(b);
	}

//CHECK IF IT HITS A CHARACTER
	public boolean hitCharacter(myCharacter characterImage){
				Area ba = new Area(characterImage.getShape());
				Area pa = new Area(getRect());

				//if(character2.getShape().intersects(getRect()))return(true);
				pa.intersect(ba);
		return !pa.isEmpty();


/*		touching = false;
		character = characterImage;
		characterX = character.getOrigin().getX();
		characterY = character.getOrigin().getY();
		characterWidth = character.getDimension().getWidth();
		characterHeight = character.getDimension().getHeight();

		if(hits(character.getOrigin(), character.getDimension())==true){

			for(int i = 0; i<character.getCollisionBoxes().size(); i++){
				int x = ((int)(character.getCollisionBoxes().get(i).getX()));
				int y = ((int)(character.getCollisionBoxes().get(i).getY()));
				int width = (int)(character.getCollisionBoxes().get(i).getWidth());
				int height =(int)(character.getCollisionBoxes().get(i).getHeight());
				Point p = new Point(x,y);
				Dimension d = new Dimension(width,height);
				if(hits(p,d)&&!character.isGhost()){
					touching=true;
				}
			}
		}

		return(touching);*/
	}
/* CHECK IF IT SHOULD BOUNCE
	public void checkBounce(){
		if((map.contains(x-xChange,y-yChange)==false)&&(map.contains(x-xChange+spriteSize.getWidth()-5,y-yChange)==false)){
			stepY = -stepY;
			//("to high");
		}
		else if((map.contains(x-xChange+spriteSize.getWidth()-5,y-yChange+spriteSize.getHeight()-5)==false)&&(map.contains(x-xChange,y+yChange+spriteSize.getHeight()-5)==false)){
			stepY = -stepY;
			//("to low");
		}
		else if((map.contains(x-xChange,y-yChange)==false)&&(map.contains(x-xChange,y-yChange+spriteSize.getHeight()-5)==false)){
			stepX = -stepX;
			//("to left");
		}
		else if((map.contains(x-xChange+spriteSize.getWidth()-5,y-yChange+spriteSize.getHeight()-5)==false)&&(map.contains(x-xChange+spriteSize.getWidth()-5,y+yChange)==false)) {
			stepX = -stepX;
			//("to right");
		}
		else{
			//("no problem");
		}
	}
*///Get Coordinates
	public int getX(){
		return(x);
	}
	public int getY(){
		return(y);
	}
	public Point getOrigin(){
		return(new Point(x,y));
	}
	public void setChange( int newX, int newY){
		xChange = newX;
		yChange = newY;
	}
	public int getHeight(){
		return((int)spriteSize.getHeight());
	}
	public int getWidth(){
			return((int)spriteSize.getWidth());
	}
	public Rectangle getRect(){
		return(new Rectangle(x+xChange,y+yChange,(int)spriteSize.getHeight(),(int)spriteSize.getWidth()));
	}
	public void setRemove(){
		remove = true;
	}
	public boolean getRemove(){
		return(remove);
	}
		public double getDistance(Point a, Point b){
			double width = Math.abs(a.getX()-b.getX());
			double height= Math.abs(a.getY()-b.getY());
			double distance = Math.sqrt(Math.pow(height,2)+Math.pow(width,2));
			return(distance);
	}
}