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
public class LifeCounter{
	int x;
	int y;
	int lives;
	Toolkit tk;
	Image heart;
	public LifeCounter(int xCor,int yCor, int lives2){
		x = xCor;
		y = yCor;
		lives = lives2;
		tk = Toolkit.getDefaultToolkit();
		try{
			heart =ImageIO.read(getClass().getResource("/heart.png"));
		}catch(IOException e){}
	}
	public void drawLifeCounter(Graphics2D g){
		g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 23));
		g.drawString("Lives: ",x,y); //30 each
		for(int i = 0; i<lives; i++){
			g.drawImage(heart,90+x+(30*i),y-20,null);
		}
	}
	public void setLives(int lives2){
		lives = lives2;
	}
}