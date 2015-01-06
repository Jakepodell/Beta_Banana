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
public class LaserEyesIcon{
	Toolkit tk;
	Image icon;
	int x;
	int y;
	public LaserEyesIcon(Dimension appletSize){
		tk = Toolkit.getDefaultToolkit();
		try{
			icon = ImageIO.read(getClass().getResource("/LaserEyesIcon.png"));
		}catch(IOException e){}
		x = 25;
		y = (int)appletSize.getHeight()-75;
	}
	public void drawLaserIcon(Graphics2D g){
		g.drawImage(icon,x,y,null);
	}
}