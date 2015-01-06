import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class Shop extends JPanel{
	Toolkit tk;
	BufferedImage shopImage;
	Item b1,b2,s1,s2,s3,p1,p2,p3,g1,g2,g3,a1,a2,a3,e1,e2;
	ArrayList<Item> buttons1;
	ArrayList<Item> buttons2;
	ArrayList<Item> buttons3;
	ArrayList<Item> buttons4;
	ArrayList<Item> buttons5;
	ArrayList<Item> buttons6;
	ArrayList<ArrayList<Item>> buttons;
	public Shop(){
		setLayout(new FlowLayout());
		buttons1 = new ArrayList<Item>();
		buttons2 = new ArrayList<Item>();
		buttons3 = new ArrayList<Item>();
		buttons4 = new ArrayList<Item>();
		buttons5 = new ArrayList<Item>();
		buttons6 = new ArrayList<Item>();
		buttons = new ArrayList<ArrayList<Item>>();
		tk = Toolkit.getDefaultToolkit();
		try {
			shopImage = ImageIO.read(getClass().getResource("/shopReal.png"));
		}catch(IOException e){
		}

		buttons1.add(new Item("/fishLaser.png", "FISH LASER", "Destroys Bliff fish", 200));
		buttons1.add(new Item("/fishCoin.png", "FISH TO COIN CONVERTER", "turns Bliff fish into purple coins", 300));

		buttons2.add(new Item("/shmockLaser.png", "SHMOCK LASER", "slows shmocks.", 250));
		buttons2.add(new Item("/batteryReverse.png", "BATTERY REVERSER", "changes Shmock Direction", 500));
		buttons2.add(new Item("/mushCloud.png", "HEAT CAPACITOR OVERLOAD", "Destroys Shmocks", 700));

		buttons3.add(new Item("/pangaLaser.png", "PANGA LASER", "Destroys Pangas", 350));
		buttons3.add(new Item("/noPanga.png", "REMOVE PANGAS", "Makes Pangas Infertiles", 500));
		buttons3.add(new Item("/shopHeart.png", "MINI TO HEART CONVERTER", "Pangas make hearts", 1500));

		buttons4.add(new Item("/glooshLaser.png", "GLOOSH LASER", "Destroys Glooshs", 250));
		buttons4.add(new Item("/soap.png", "ACT CLEANER UPPER","Destroys the Gloosh slime trail" , 150));
		buttons4.add(new Item("/manyCoins.png", "COIN TRAIL", "Turns slime trail into trail of coinsn", 1450));

		buttons5.add(new Item("/amyLaser.png", "AMY LASER", "Destroys 1 amy", 400));
		buttons5.add(new Item("/storeY.png", "ANITBODIES", "Sends a ring of amy-destroying antibodies", 650));
		buttons5.add(new Item("/storeSoup.png", "CHICKEN NOODLE PAIN", "the good-ol' fasioned way to get rid of bacteria", 1200));

		buttons6.add(new Item("/eyeraLaser.png", "EYERA LASER", "destroys eyeras", 500));
		buttons6.add(new Item("/skull.png", "PURIFIED DEATH", "creates an Eyera-destroying chain reaction", 2000));

		buttons.add(buttons1);
		buttons.add(buttons2);
		buttons.add(buttons3);
		buttons.add(buttons4);
		buttons.add(buttons5);
		buttons.add(buttons6);

	}
	public void drawShop(Graphics2D g){
		g.drawImage(shopImage, 0,20, null);

	}
	public ArrayList<ArrayList<Item>> getButtons(){
		return(buttons);
	}
}