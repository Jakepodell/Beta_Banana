import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class LoadScreen{
	Toolkit tk;
	BufferedImage Screen;
	JButton attempt;
//	Image waving;
	public LoadScreen(){
		tk = Toolkit.getDefaultToolkit();
		try {
			Screen =ImageIO.read(getClass().getResource("/LoadScreen.png"));
		}catch(IOException e){
		}
		attempt = new JButton("attempt");
		//waving = tk.createImage("bob.gif");
	}
	public void drawLoadScreen(Graphics2D g){
		g.drawImage(Screen, 0,0, null);
	}
}