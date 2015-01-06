import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class WinScreen{
	Toolkit tk;
	BufferedImage Screen;
	JButton attempt;
	public WinScreen(){
		tk = Toolkit.getDefaultToolkit();
		try {
			Screen=ImageIO.read(getClass().getResource("WinScreen.png"));
		}catch(IOException e){
		}
	}
	public void drawWinScreen(Graphics2D g){
		g.drawImage(Screen, 0,0, null);
	}
}