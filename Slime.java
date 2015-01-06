import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.awt.geom.*;
public class Slime{
	Image slimeImage;
	Toolkit tk;
	Gloosh ooze;
	Random ai;
	private final int x,y, varX, varY;
	public Slime(Gloosh g){
		tk = Toolkit.getDefaultToolkit();
		ai = new Random();
		try{
			slimeImage = ImageIO.read(getClass().getResource("/slime2.png"));
		}catch(IOException e){}
		ooze = g;
		ooze.addSlime();
		x = ooze.getX();
		y = ooze.getY();
		varX = ai.nextInt(16)+3;
		varY = ai.nextInt(16)+3;

	}
	public void setIcon(String x){
		slimeImage =tk.getImage(x);
	}
	public void drawSlime(Graphics g){
			g.drawImage(slimeImage,x+varX+ooze.getXC(),y+varY+ooze.getYC(), null);
	}
	public void drawSlime(Graphics g, int xChange, int yChange){
			g.drawImage(slimeImage,x+varX+xChange,y+varY+yChange, null);
	}
	public boolean hits(Point p, Dimension d){
			Point slimePoint = new Point(x+ooze.getXC(), y+ooze.getYC());
			boolean b = false;
			if(slimePoint.getX()>p.getX()-20    &&
			   slimePoint.getX()<p.getX()+d.getWidth() &&
			   slimePoint.getY()<p.getY()+d.getHeight()&&
			   slimePoint.getY()>p.getY()-20)
				{
				b = true;
			}
			return(b);
		}
		public boolean hitCharacter(myCharacter character2){
			Area ba = new Area(character2.getShape());
			Area pa = new Area(new Rectangle(x+ooze.getXC(), y+ooze.getYC(),20,20));

							//if(character2.getShape().intersects(getRect()))return(true);
			pa.intersect(ba);
			return !pa.isEmpty();
	/*		boolean touching =false;

				myCharacter character = character2;
				if(hits(character.getOrigin(), character.getDimension())==true){

					for(int i = 0; i<character.getCollisionBoxes().size(); i++){
						int x2 = ((int)(character.getCollisionBoxes().get(i).getX()));
						int y2 = ((int)(character.getCollisionBoxes().get(i).getY()));
						int width = (int)(character.getCollisionBoxes().get(i).getWidth());
						int height =(int)(character.getCollisionBoxes().get(i).getHeight());
						Point p = new Point(x2,y2);
						Dimension d = new Dimension(width,height);
						if(hits(p,d)){
							touching=true;
						}
					}
				}

				return(touching);*/
	}
}
