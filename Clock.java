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
public class Clock{
	int x;
	int y;
	Toolkit tk;
	int level;
	int levelTime;
	Dimension appletSize;
	public Clock(Dimension appletSize2, int timeRemaining, int levelNumber){
		appletSize = appletSize2;
		x = (int)appletSize.getWidth()-280;
		y = 33;
		tk = Toolkit.getDefaultToolkit();
		level = levelNumber;
		levelTime = timeRemaining;

	}
	public void drawClock(Graphics2D g){
		g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 19));
		if(levelTime/60>9 && levelTime%60 >9){
			g.drawString("Level"+level+"  Time Left "+((int)(Math.floor(levelTime/60.0)))+":"+levelTime%60, (int)appletSize.getWidth()-280,33);
		}
		else if(levelTime/60<=9 && levelTime%60 <=9){
			g.drawString("Level"+level+"  Time Left 0"+((int)(Math.floor(levelTime/60.0)))+":0"+levelTime%60, (int)appletSize.getWidth()-280,33);
		}
		else if(levelTime/60<=9){
			g.drawString("Level"+level+"  Time Left 0"+((int)(Math.floor(levelTime/60.0)))+":"+levelTime%60, (int)appletSize.getWidth()-280,33);
		}
		else if(levelTime%60 <=9){
			g.drawString("Level"+level+"  Time Left "+((int)(Math.floor(levelTime/60.0)))+":0"+levelTime%60, (int)appletSize.getWidth()-280,33);
		}
	}
	public void setClock(int timeRemaining, int levelNumber){
		level = levelNumber;
		levelTime = timeRemaining;
	}
}