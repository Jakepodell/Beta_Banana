import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Amy extends SlidingSprite{
//	Buffered
	Image amyImage;
	Image portalImage;
	Toolkit tk;
	boolean split;
	int state;
	BufferedImage a1,a2,a3,a4,a5,a6;
	BufferedImage[] amyImages= new BufferedImage[6];
	public Amy(Portal p,Dimension pangaSize, Shape map, int xChange2, int yChange2){
		super(p,pangaSize,map, xChange2, yChange2);
		state = 1;
		tk = Toolkit.getDefaultToolkit();
		try {
			a1 = ImageIO.read(new File("ameiba1.png"));
			a2 = ImageIO.read(new File("ameiba2.png"));
			a3 = ImageIO.read(new File("ameiba3.png"));
			a4 = ImageIO.read(new File("ameiba4.png"));
			a5 = ImageIO.read(new File("ameiba5.png"));
			a6 = ImageIO.read(new File("ameiba6.png"));
		}catch(IOException e){
		}
		amyImages = new BufferedImage[]{a1,a2,a3,a4,a5,a6};
		amyImage = a1;
		split = false;
		amyImage = tk.getImage("ameiba1.png");
	}
	public Amy(Amy a, Dimension pangaSize, Shape map, int number, int xChange2, int yChange2){
		super(a,pangaSize,map,number,xChange2,yChange2);
		state = 1;
		tk = Toolkit.getDefaultToolkit();
		try {
			a1 = ImageIO.read(new File("ameiba1.png"));
			a2 = ImageIO.read(new File("ameiba2.png"));
			a3 = ImageIO.read(new File("ameiba3.png"));
			a4 = ImageIO.read(new File("ameiba4.png"));
			a5 = ImageIO.read(new File("ameiba5.png"));
			a6 = ImageIO.read(new File("ameiba6.png"));
		}catch(IOException e){
		}
		amyImages = new BufferedImage[]{a1,a2,a3,a4,a5,a6};
		amyImage = a1;
		split = false;
		try{
			amyImage =ImageIO.read(getClass().getResource("/ameiba1.png"));
		}catch(IOException e){}
	}
	public void drawAmy(Graphics g){
		g.drawImage(amyImage,x+xChange,y+yChange, null);
	}
	public Point getOrigin(){
		return(new Point(x,y));
	}
	public void split(){
		split = true;
	}
	public boolean isSplit(){
		return(split);
	}
	public void setState(int newState){
		state = newState;
		if(state>40){
			state = state-40;
			amyImage=amyImages[state-1];
			//try{
				//amyImage =ImageIO.read(getClass().getResource("/ameiba"+state+".png"));
			//}catch(IOException e){}
			state=state+40;
		}
	}
	public int getState(){
		return state;
	}
}
