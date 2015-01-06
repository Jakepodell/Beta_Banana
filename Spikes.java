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
public class Spikes{
	Image spikeImage;
	Toolkit tk;
	Dimension appletSize;
	ArrayList<Point> points;
	ArrayList<Integer> orientation;
	int xChange;
	int yChange;
	public Spikes(ArrayList<Point> coordinates, ArrayList<Integer> ints, int xChange2, int yChange2){
		tk = Toolkit.getDefaultToolkit();
		points = coordinates;
		orientation = ints;
		yChange = yChange2;
		xChange = xChange2;
	}
	public void drawSpikes(Graphics g){
		for(int i = 0; i<points.size(); i++){
	/*		try{
				spikeImage = ImageIO.read(new File("spike"+orientation.get(i)+".png"));
			}catch(IOException e){}
	*/		if(orientation.get(i)<5){
			spikeImage = tk.getImage("spike"+"ball"+".png");
		}else{
			spikeImage=tk.getImage("Spikeball.png");
		}
			g.drawImage(spikeImage, (int)points.get(i).getX()+xChange,(int)points.get(i).getY()+yChange, null);
		}
	}
	public void setChange( int newX, int newY){
		xChange = newX;
		yChange = newY;
	}
	public boolean checkCollision(myCharacter characterImage){
		boolean touching = false;
		for(int i= 0; i<orientation.size(); i++){
			if(orientation.get(i) == TOP || orientation.get(i) == BOTTOM){
				if(characterImage.getY()<(int)points.get(i).getY()+yChange+15 &&
					characterImage.getY()+characterImage.getHeight()>(int)points.get(i).getY()+yChange &&
					characterImage.getX()<(int)points.get(i).getX()+25+xChange &&
					characterImage.getX()+characterImage.getWidth()>(int)points.get(i).getX()+xChange){
					touching = true;
				}
			}
			else if(orientation.get(i) == LEFT ||orientation.get(i) ==RIGHT){
				if(characterImage.getX()<(int)points.get(i).getX()+xChange+15 &&
					characterImage.getX()+characterImage.getWidth()>(int)points.get(i).getX()+xChange &&
					characterImage.getY()<(int)points.get(i).getY()+25+yChange &&
					characterImage.getY()+characterImage.getHeight()>(int)points.get(i).getY()+yChange){
					touching =  true;
				}
			}
		}
		return(touching);
	}
	public void checkEnemyCollision(ArrayList<Bliff> enemies){
			boolean touching = false;
			for(int i= 0; i<orientation.size(); i++){
				for(int q=0;q<enemies.size(); q++){
					if(orientation.get(i) == TOP || orientation.get(i) == BOTTOM){
						if(enemies.get(q).getY()<(int)points.get(i).getY()+yChange+15 &&
							enemies.get(q).getY()+enemies.get(q).getHeight()>(int)points.get(i).getY()+yChange &&
							enemies.get(q).getX()<(int)points.get(i).getX()+25+xChange &&
							enemies.get(q).getX()+enemies.get(q).getWidth()>(int)points.get(i).getX()+xChange){
							enemies.remove(q);
						}
					}
					else if(orientation.get(i) == LEFT ||orientation.get(i) ==RIGHT){
						if(enemies.get(q).getX()<(int)points.get(i).getX()+xChange+15 &&
							enemies.get(q).getX()+enemies.get(q).getWidth()>(int)points.get(i).getX()+xChange &&
							enemies.get(q).getY()<(int)points.get(i).getY()+25+yChange &&
							enemies.get(q).getY()+enemies.get(q).getHeight()>(int)points.get(i).getY()+yChange){
							enemies.remove(q);
						}
					}
				}
			}
	}
	public final static int TOP = 4;
	public final static int	BOTTOM = 1;
	public final static int	LEFT = 2;
	public final static int	RIGHT = 3;
	public final static int	HEIGHT = 15;
	public final static int	WIDTH = 25;
}
