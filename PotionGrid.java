import java.util.Map;
import java.util.HashMap;
import java.awt.geom.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class PotionGrid{
	int xChange;
	int yChange;
	Shape boundary;
	myCharacter banana;
	Rectangle bounds;
	ArrayList<Potion> potions;
	Pillar[] pillars;
	public PotionGrid(int xChange2, int yChange2, Shape boundary2, Pillar[] pillars2, myCharacter banana2){
		xChange =xChange2;
		yChange = yChange2;
		boundary = boundary2;
		banana = banana2;
		bounds = boundary.getBounds();
		pillars = pillars2;
		potions = new ArrayList<Potion>();
		for(int i = 0; i<(int)(bounds.getWidth())/75; i++){
			for(int ii = 0; ii<(int)(bounds.getHeight())/75;ii++){
				potions.add(new Potion(75*i+50,75*ii+50));
			}
		}
	}
	public void drawGrid(Graphics2D g2){
		for( int i = 0; i<potions.size(); i++){
			potions.get(i).drawPotion(g2, xChange, yChange);
Rectangle newPotionRec = new Rectangle((int)(potions.get(i).getRect().getX()-15),
												  (int)( potions.get(i).getRect().getY()-15),
												   (int)(potions.get(i).getRect().getWidth()+30),
												   (int)(potions.get(i).getRect().getHeight())+30);
												//   g2.draw(newPotionRec);
		}
		//g2.draw(boundary);

	}
	public void checkCollision(){
		for( int i = 0; i<potions.size(); i++){
			if(potions.get(i).hitCharacter(banana)){
				potions.remove(i);
			}
		}
	}
	public int isCollision(){
		int counter=0;
		for( int i = 0; i<potions.size(); i++){
			if(potions.get(i).hitCharacter(banana)){
				counter++;
				potions.remove(i);
			}
		}
		return(counter);
	}
	public void checkInside(){
		for( int i = 0; i<potions.size(); i++){
			int counter1=0;
			int counter2=0;
			Rectangle newPotionRec = new Rectangle((int)(potions.get(i).getRect().getX()-15-5),
												  (int)( potions.get(i).getRect().getY()-15-5),
												   (int)(potions.get(i).getRect().getWidth()+30+5),
												   (int)(potions.get(i).getRect().getHeight())+30+5);
			if(!boundary.contains(newPotionRec.getX(),newPotionRec.getY())){
				counter1++;
			}
			if(!boundary.contains(newPotionRec.getX()+newPotionRec.getWidth(),newPotionRec.getY())){
							counter2++;
			}
			if(!boundary.contains(newPotionRec.getX(),newPotionRec.getY()+newPotionRec.getHeight())){
							counter2++;
			}
			if(!boundary.contains(newPotionRec.getX()+newPotionRec.getWidth(),newPotionRec.getY()+newPotionRec.getHeight())){
							counter1++;
			}
					if(boundary.contains(potions.get(i).getRect())==false || counter1 >=2|| counter2 >=2 ||((!boundary.contains(newPotionRec.getX()+newPotionRec.getWidth(),newPotionRec.getY()))
					   && (!boundary.contains(newPotionRec.getX()+newPotionRec.getWidth(),newPotionRec.getY()+newPotionRec.getHeight()+120))))
					{
						potions.remove(i);
					}
					counter1 = 0;counter2=0;
		}
		for( int i = 0; i<potions.size(); i++){
			for(int ii =0; ii<pillars.length; ii++){
				if(pillars[ii].getPillar().contains(potions.get(i).getRect())||(pillars[ii].getPillar().intersects(potions.get(i).getRect()))){
					potions.remove(i);
				}
			}
		}
	}
	public void setChange( int newX, int newY){
			xChange = newX;
			yChange = newY;
	}
	public void setBoundary(Shape boundary2){
		boundary = boundary2;
	}
	public int potionsLeft(){
		return(potions.size());
	}
	public void clear(){
		potions.clear();
	}
}