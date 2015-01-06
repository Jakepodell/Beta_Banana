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
public class CoinCounter{
	int x;
	int y;
	int coins;
	Toolkit tk;
	Image coinsIcon;
	public CoinCounter(int xCor,int yCor, int coinsCollected){
		x = xCor;
		y = yCor;
		coins = coinsCollected;
		tk = Toolkit.getDefaultToolkit();
		try{
			coinsIcon = ImageIO.read(getClass().getResource("coinsIcon.png"));
		}catch(IOException e){}
	}
	public void drawCoinCounter(Graphics2D g){
		g.setFont(new Font("SansSerif", Font.BOLD, 18));
		g.drawImage(coinsIcon,x,y,null);
		g.drawString((" x  "+coins), x+30, y+20);

	}
	public void setCoins(int coinsCollected){
		coins = coinsCollected;
		File f = new File("coins.txt");
		try
		{
			FileWriter fstream = new FileWriter(f);
			BufferedWriter bout = new BufferedWriter(fstream);
					bout.write(""+coins);

			bout.close();
		}
		catch (Exception x)
		{
			//System.err.println(e);
			System.err.println("Could not write to file");
		}
	}
}