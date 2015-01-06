
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
public class Potion{
	Image potionImage;
	Toolkit tk;
	int x;
	int y;
	int changeX;
	int changeY;
	boolean touching;
	public Potion(int x2,int y2){
		tk = Toolkit.getDefaultToolkit();
		x=x2;
		y=y2;
		touching = false;
		try{
			potionImage = ImageIO.read(getClass().getResource("potion3.png"));
		}
		catch(IOException e){}
	}
	public void drawPotion(Graphics g, int xChange, int yChange){
		changeX = xChange;
		changeY = yChange;g.drawImage(potionImage, x+xChange, y+yChange, null);

	}
	public boolean hits(Point p, Dimension d){
		Point potionPoint = new Point(x, y);
		boolean b = false;
		if(potionPoint.getX()+changeX>p.getX()-22    &&
		   potionPoint.getX()+changeX<p.getX()+d.getWidth() &&
		   potionPoint.getY()+changeY<p.getY()+d.getHeight()&&
		   potionPoint.getY()+changeY>p.getY()-50)
			{
			b = true;
		}
		return(b);
	}
	public boolean hitCharacter(myCharacter character2){
		Area ba = new Area(character2.getShape());
		Area pa = new Area(getRect());

		//if(character2.getShape().intersects(getRect()))return(true);
		pa.intersect(ba);
		return !pa.isEmpty();
	//	else return(false);

		/*	myCharacter character = character2;
			if(hits(character.getOrigin(), character.getDimension())==true){

				for(int i = 0; i<character.getCollisionBoxes().size(); i++){
					int x = ((int)(character.getCollisionBoxes().get(i).getX()));
					int y = ((int)(character.getCollisionBoxes().get(i).getY()));
					int width = (int)(character.getCollisionBoxes().get(i).getWidth());
					int height =(int)(character.getCollisionBoxes().get(i).getHeight());
					Point p = new Point(x,y);
					Dimension d = new Dimension(width,height);
					if(hits(p,d)){
						touching=true;
					}
				}
			}

			return(touching);*/
	}
	public Rectangle getRect(){
			return(new Rectangle(x+changeX-5,y+changeY-5,28,38));
	}
}
