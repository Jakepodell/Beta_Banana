import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Shmock extends SlidingSprite{
	Image shmockImage;
	Toolkit tk;
	enum Movements{normal, slow, repel}
	Movements movement;
	int repel;
	public Shmock(Portal p,Dimension shmockSize, Shape map,int xChange2, int yChange2){
		super(p,shmockSize,map, xChange2, yChange2);
		tk = Toolkit.getDefaultToolkit();
		movement = Movements.normal;
		repel =1;
		try{
			shmockImage = ImageIO.read(getClass().getResource("/shmock2.png"));
		}catch(IOException e){}
		while(stepX==0){
			stepX = 5*ai.nextInt(10)-5;
		}
		while(stepY==0){
			stepY = 5*ai.nextInt(10)-5;
		}
	}
	public void drawShmock(Graphics g){
		g.drawImage(shmockImage,x+xChange,y+yChange, null);
	}
	public void seekCharacter(myCharacter characterImage){
		character = characterImage;
		int characterX2 = (int)character.getOrigin().getX()+50-xChange;
		int characterY2 = (int)character.getOrigin().getY()+50-yChange;

		if(movement == Movements.repel){
			if(x+(spriteSize.getHeight()/2)<characterX2+5 && x+(spriteSize.getHeight()/2)>characterX2-5){
				stepX=0;
				stepY = (int)Math.sqrt(Math.pow(stepX,2)+Math.pow(stepY,2));
			}
			else if(x+(spriteSize.getHeight()/2)>characterX2 && stepX<0){
				if(stepX==0)stepX = -1*4;
				stepX = -(stepX);
			}
			else if(x+(spriteSize.getHeight()/2)<characterX2 && stepX>0){
				if(stepX==0)stepX = 1*4;
				stepX = -(stepX);
			}
			if(y+(spriteSize.getHeight()/2)<characterY2+5 && y+(spriteSize.getHeight()/2)>characterY2-5){
				stepY=0;
				stepX = (int)Math.sqrt(Math.pow(stepX,2)+Math.pow(stepY,2));
			}
			else if(y+(spriteSize.getWidth()/2)>characterY2 && stepY<0){
				if(stepY==0)stepY = -1*4;
				stepY = -(stepY);
			}
			else if(y+(spriteSize.getWidth()/2)<characterY2 && stepY>0){
				if(stepY==0)stepY = 1*4;
				stepY = -(stepY);
			}
		}

		else{
			if(x+(spriteSize.getHeight()/2)<characterX2+5 && x+(spriteSize.getHeight()/2)>characterX2-5){
				stepX=0;
			//	stepY = (int)Math.sqrt(Math.pow(stepX,2)+Math.pow(stepY,2));
			}
			else if(x+(spriteSize.getHeight()/2)<characterX2 && stepX<=0){
				if(stepX==0)stepX = -1*4;
				stepX = -(stepX)*repel;
			}
			else if(x+(spriteSize.getHeight()/2)>characterX2 && stepX>=0){
				if(stepX==0)stepX = 1*4;
				stepX = -(stepX)*repel;
			}
			if(y+(spriteSize.getHeight()/2)<characterY2+5 && y+(spriteSize.getHeight()/2)>characterY2-5){
				stepY=0;
			//	stepX = (int)Math.sqrt(Math.pow(stepX,2)+Math.pow(stepY,2));
			}
			else if(y+(spriteSize.getWidth()/2)<characterY2 && stepY<=0){
				if(stepY==0)stepY = -1*4;
				stepY = -(stepY)*repel;
			}
			else if(y+(spriteSize.getWidth()/2)>characterY2 && stepY>=0){
				if(stepY==0)stepY = 1*4;
						stepY = -(stepY)*repel;
			}
		}
	}
	public void slow(){
		stepX%=3;
		stepY%=3;
		movement = Movements.slow;
	}
	public String moveToString(){
		return(movement.toString());
	}
	public void repel(){
		movement = Movements.repel;
	}
}
