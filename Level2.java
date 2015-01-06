import java.lang.Integer;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.image.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.*;
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
public class Level2{
	int levelTime;
	int charWidth;
	int charHeight;
	Point startPoint;
	double coinFrequency;
	double enemyFrequency;
	int buttonNumber;
	int colorFrequency;
	int row;
	LevelSelect lev;
	JButton currentButton;
	int f;
	Dimension boundary;
	Image background;
	Image background2;
	ArrayList<Point> p1, p2, p3 ,p4 ,p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30;
	ArrayList<Integer> i1, i2, i3 ,i4 ,i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30;
	Spikes s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30;
	ArrayList<Spikes> allSpikes;
	Spikes spikes;
	ArrayList<Integer> x1, x2, x3 ,x4 ,x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30;
	ArrayList<Integer> y1, y2, y3 ,y4 ,y5, y6, y7, y8, y9, y10, y11, y12, y13, y14, y15, y16, y17, y18, y19, y20, y21, y22, y23, y24, y25, y26, y27, y28, y29, y30;
	ArrayList<ArrayList<Integer>> xp;
	ArrayList<ArrayList<Integer>>yp;
	Toolkit tk;
	ArrayList<Point> startPoints;
	GeneralPath polygon;
	public Level2(int levelNumber){
		lev = new LevelSelect();
		currentButton = lev.getButtons().get(levelNumber);
		buttonNumber = lev.getLevel(currentButton);
		row = lev.getRow(currentButton);
		coinFrequency = 1-((buttonNumber-1)*0.03);
		enemyFrequency = ((2350+((row-1)*200))-(50*((buttonNumber-1)%5)));
		levelTime = 10+((buttonNumber-1)*12);
		allSpikes = new ArrayList<Spikes>();
		startPoints = new ArrayList<Point>();
		charWidth = 50;
		charHeight = 50;
//----------------------------- LEVEL ONE --------------------------------------------
		p1 = new ArrayList<Point>();
		i1 = new ArrayList<Integer>();
		x1 = new ArrayList<Integer>();
		y1 = new ArrayList<Integer>();
		for(int i = 0; i<800/25; i++){
			p1.add(new Point(i*25,600-Spikes.HEIGHT ));
			i1.add(Spikes.BOTTOM);
			p1.add(new Point(i*25,0));
			i1.add(Spikes.TOP);
		}
		for(int i = 0; i<600/25; i++){
			p1.add(new Point(0,i*25));
			i1.add(Spikes.LEFT);
			p1.add(new Point(800-Spikes.HEIGHT, i*25));
			i1.add(Spikes.RIGHT);
		}
		s1 = new Spikes(p1,i1,f,f);
		allSpikes.add(s1);
		startPoints.add(new Point(400-charHeight, 300-charWidth));
		x1.add(0);		y1.add(0);
		x1.add(800);	y1.add(0);
		x1.add(800);	y1.add(600);
		x1.add(0);		y1.add(600);
		xp.add(x1);
		yp.add(y1);
//------------------------- LEVEL TWO ---------------------------------------------------
		p2 = new ArrayList<Point>();
		i2 = new ArrayList<Integer>();
		x2 = new ArrayList<Integer>();
		y2 = new ArrayList<Integer>();
		for(int i = 0; i<1600/25; i++){
			p2.add(new Point(25*i, 600-Spikes.HEIGHT));
			i2.add(Spikes.BOTTOM);
			p2.add(new Point(25*i, 0));
			i2.add(Spikes.TOP);
		}
		for(int i =0; i<600/25; i++){
			p2.add(new Point(0,25*i));
			i2.add(Spikes.LEFT);
			p2.add(new Point(1600-Spikes.HEIGHT, 25*i));
			i2.add(Spikes.RIGHT);
		}
		s2 = new Spikes(p2, i2,f,f);
		allSpikes.add(s2);
		startPoints.add(new Point(800-charHeight, 300-charWidth));
		x2.add(0);		y2.add(0);
		x2.add(1600);	y2.add(0);
		x2.add(1600);	y2.add(600);
		x2.add(000);	y2.add(600);
		xp.add(x2);
		yp.add(y2);
//-----------------------------LEVEL 3------------------------------------------------------
		p3 = new ArrayList<Point>();
		i3 = new ArrayList<Integer>();
		x3 = new ArrayList<Integer>();
		y3 = new ArrayList<Integer>();
		for(int i = 0; i<800/25; i++){
			p3.add(new Point(25*i, 1600-Spikes.HEIGHT));
			i3.add(Spikes.BOTTOM);
			p3.add(new Point(25*i, 0));
			i3.add(Spikes.TOP);
		}
		for(int i =0; i<1600/25; i++){
			p3.add(new Point(0,25*i));
			i3.add(Spikes.LEFT);
			p3.add(new Point(800-Spikes.HEIGHT, 25*i));
			i3.add(Spikes.RIGHT);
		}
		s3 = new Spikes(p3, i3,f,f);
		allSpikes.add(s3);
		startPoints.add(new Point(400-charHeight, 800-charWidth));
		x3.add(0);		y3.add(0);
		x3.add(800);	y3.add(0);
		x3.add(800);	y3.add(1600);
		x3.add(0);		y3.add(1600);
		xp.add(x3);
		yp.add(y3);
//-----------------------------LEVEL 4------------------------------------------------------
		p4 = new ArrayList<Point>();
		i4 = new ArrayList<Integer>();
		x4 = new ArrayList<Integer>();
		y4 = new ArrayList<Integer>();
		for(int i = 0; i<1600/25; i++){
			p4.add(new Point(25*i, 1600-Spikes.HEIGHT));
			i4.add(Spikes.BOTTOM);
			p4.add(new Point(25*i, 0));
			i4.add(Spikes.TOP);
			p4.add(new Point(0,25*i));
			i4.add(Spikes.LEFT);
			p4.add(new Point(1600-Spikes.HEIGHT, 25*i));
			i4.add(Spikes.RIGHT);
		}
		s4 = new Spikes(p4, i4,f,f);
		allSpikes.add(s4);
		startPoints.add(new Point(800-charHeight, 800-charWidth));
		x4.add(0);		y4.add(0);
		x4.add(1600);	y4.add(0);
		x4.add(1600);	y4.add(1600);
		x4.add(0);		y4.add(1600);
		xp.add(x4);
		yp.add(y4);
//-----------------------------LEVEL 5------------------------------------------------------
		p5 = new ArrayList<Point>();
		i5 = new ArrayList<Integer>();
		x5 = new ArrayList<Integer>();
		y5 = new ArrayList<Integer>();
		for(int i = 0; i<1600/25; i++){
			p5.add(new Point(25*i, 1600-Spikes.HEIGHT));
			i5.add(Spikes.BOTTOM);
			p5.add(new Point(25*i, 0));
			i5.add(Spikes.TOP);
			p5.add(new Point(0,25*i));
			i5.add(Spikes.LEFT);
			p5.add(new Point(1600-Spikes.HEIGHT, 25*i));
			i5.add(Spikes.RIGHT);
		}
		for(int i = 0; i<200/25; i++){
			p5.add(new Point(700+25*i,700-Spikes.HEIGHT));
			i5.add(Spikes.BOTTOM);
			p5.add(new Point(700-Spikes.HEIGHT, 700+25*i));
			i5.add(Spikes.RIGHT);
			p5.add(new Point(700+25*i, 900));
			i5.add(Spikes.TOP);
			p5.add(new Point(900, 700+25*i));
			i5.add(Spikes.LEFT);
		}
		startPoints.add(new Point(800-charHeight, 600-charWidth));
		s5 = new Spikes(p5, i5,f,f);
		allSpikes.add(s5);
		x5.add(0);		y5.add(0);
		x5.add(1600);	y5.add(0);
		x5.add(1600);	y5.add(1600);
		x5.add(0);		y5.add(1600);
		xp.add(x5);
		yp.add(y5);
//---------------------LEVEL6------------------------------------------
		p6=new ArrayList<Point>();
		i6=new ArrayList<Integer>();
		x6=new ArrayList<Integer>();
		y6=new ArrayList<Integer>();
		for(int i=0; i<800/25; i++){
			p6.add(new Point(i*25,400));
			i6.add(Spikes.TOP);
			p6.add(new Point(800+i*25,0));
			i6.add(Spikes.TOP);
			p6.add(new Point(i*25,1200-Spikes.HEIGHT));
			i6.add(Spikes.BOTTOM);
			p6.add(new Point(800+i*25,800-Spikes.HEIGHT));
			i6.add(Spikes.BOTTOM);
			p6.add(new Point(0,400+25*i));
			i6.add(Spikes.LEFT);
			p6.add(new Point(1600-Spikes.HEIGHT, 25*i));
			i6.add(Spikes.RIGHT);
		}
		for(int i=0;i<400/25;i++){
			p6.add(new Point(800, 25*i));
			i6.add(Spikes.LEFT);
			p6.add(new Point(800-Spikes.HEIGHT, 800+25*i));
			i6.add(Spikes.RIGHT);
		}
		s6 = new Spikes(p6,i6,f,f);
		allSpikes.add(s6);
		startPoints.add(new Point(400-charHeight, 1000-charWidth));
		x6.add(0);		y6.add(400);
		x6.add(800);	y6.add(400);
		x6.add(800);	y6.add(0);
		x6.add(1600);	y6.add(0);
		x6.add(1600);	y6.add(800);
		x6.add(800);	y6.add(800);
		x6.add(800);	y6.add(1600);
		x6.add(0);		y6.add(1600);
		xp.add(x6);
		yp.add(y6);
//---------------------------------------------------------------------------------------
	spikes = allSpikes.get(levelNumber-1);
	startPoint = startPoints.get(levelNumber-1);
	tk = Toolkit.getDefaultToolkit();
	try{
	background = ImageIO.read(getClass().getResource("/background"+levelNumber+".png"));
	background2 = ImageIO.read(getClass().getResource("/background2"+levelNumber+".png"));
	}catch(IOException e){}
	polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xp.get(levelNumber-1).size());
	polygon.moveTo(xp.get(levelNumber-1).get(0),yp.get(levelNumber-1).get(0));
	for(int i=1; i<xp.get(levelNumber-1).size();i++){
		polygon.lineTo(xp.get(levelNumber-1).get(i),xp.get(levelNumber-1).get(i));
	}
	polygon.closePath();

	}
	public int getLevelTime(){
		return(levelTime);
	}
	public double getCoinFrequency(){
		return(coinFrequency*1000);
	}
	public double getEnemyFrequency(){
		return(enemyFrequency);
	}
	public Spikes getSpikes(){
		return(spikes);
	}
	public Image getBackground(){
		return(background);
	}
	public Shape getBoundary(){
		return(polygon);
	}
	public Image getBackground2(){
		return(background2);
	}
	public Point getStartPoint(){
		return(startPoint);
	}
}
