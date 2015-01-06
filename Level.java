/*
1 800x600
2 1600x600
3 600x1600x
4 1600x1600
5 1600x1600

6 1600x1200
7 1600x800
8 1570x770
9 1600x1600
10 2000x2000

11 900x900
12 1600x1600
13 1500x900
14 1600x1600
15 1200x1200

16 1600x1600
17 1200x1600
18 1600x695
19 1600x1600
20 1300x1300

21 1100x900
22 1000x900
23 1600x800
24 1200x1200
25 800x1275

26 1700x1700
27 1500x1500
28 1600x1200
29 2400x1200
30 1600x1600

*/
//import java.lang.Integer;
import java.awt.*;
//import java.awt.event.*;
//import java.awt.font.*;
//import java.awt.image.*;
//import java.awt.event.ItemListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
import java.awt.geom.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
//import java.awt.*;
//import java.awt.image.*;
//import java.awt.event.*;
import javax.swing.*;
//import javax.swing.text.*;
//import java.applet.*;
import java.io.*;
import javax.imageio.*;
public class Level{
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
	//Image background2;
	ArrayList<Point> p1, p2, p3 ,p4 ,p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30;
	ArrayList<Integer> i1, i2, i3 ,i4 ,i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30;
	ArrayList<Pillar> pill1, pill2, pill3 ,pill4 ,pill5, pill6, pill7, pill8, pill9, pill10, pill11, pill12, pill13, pill14, pill15, pill16, pill17, pill18, pill19, pill20, pill21, pill22, pill23, pill24, pill25, pill26, pill27, pill28, pill29, pill30;
	Spikes s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30;
	ArrayList<Spikes> allSpikes;
	Spikes spikes;
//	int x1, x2, x3 ,x4 ,x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28, x29, x30;
//	int[] y1, y2, y3 ,y4 ,y5, y6, y7, y8, y9, y10, y11, y12, y13, y14, y15, y16, y17, y18, y19, y20, y21, y22, y23, y24, y25, y26, y27, y28, y29, y30;
	ArrayList<int[]> xp;
	ArrayList<int[]>yp;
	ArrayList<Pillar[]>pp;
	ArrayList<Laser[]>lp;
	int[] levelTimes = new int[30];
	int[][] enFreq = new int[30][6];
	Toolkit tk;
	ArrayList<Point> startPoints;
	GeneralPath polygon;
	public Level(int levelNumber){
		lev = new LevelSelect(100);
		currentButton = lev.getButtons().get(levelNumber-1);
		buttonNumber = lev.getLevel(currentButton);
		row = lev.getRow(currentButton);
		coinFrequency = 0-((buttonNumber-1)*0.3);
		enemyFrequency = ((2350+((row-1)*2000))-(50*((buttonNumber-1)%5)));
		levelTime = 50+((buttonNumber-1)*0);
		allSpikes = new ArrayList<Spikes>();
		startPoints = new ArrayList<Point>();
		xp = new ArrayList<int[]>();
		yp = new ArrayList<int[]>();
		pp = new ArrayList<Pillar[]>();
		lp = new ArrayList<Laser[]>();
		charWidth = 50;
		charHeight = 50;

//----------------------------- LEVEL ONE --------------------------------------------
		p1 = new ArrayList<Point>();
		i1 = new ArrayList<Integer>();
	/*	for(int i = 0; i<800/25; i++){
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
*/
		createLine(0,0,800,Spikes.TOP,p1,i1);
		createLine(0,0,600,Spikes.LEFT,p1,i1);
		createLine(800,0,600,Spikes.RIGHT,p1,i1);
		createLine(0,600,800,Spikes.BOTTOM,p1,i1);
		s1 = new Spikes(p1,i1,f,f);
		allSpikes.add(s1);
		startPoints.add(new Point(400-charHeight, 300-charWidth));
		int[] x1={0,800,800,0};
		int[] y1={0,0,600,600};
		Pillar[] p1 = {new Pillar()};
		pp.add(p1);
		Laser[] l1 = {};
		lp.add(l1);
		xp.add(x1);
		yp.add(y1);
		levelTimes[0]=40;
		enFreq[0][1]=40;
				enFreq[0][2]=1;
				enFreq[0][3]=1;
				enFreq[0][4]=1;
				enFreq[0][5]=1;
		enFreq[0][0]=1;
//------------------------- LEVEL TWO ---------------------------------------------------
		p2 = new ArrayList<Point>();
		i2 = new ArrayList<Integer>();
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
		int[] x2={0,1600,1600,0};
		int[] y2={0,0,600,600};
		xp.add(x2);
		yp.add(y2);
		Pillar[] p2 = {new Pillar()};
		pp.add(p2);
		Laser[] l2 = {};
		lp.add(l2);
		levelTimes[1]=65;
		enFreq[1][1]=20;
				enFreq[1][2]=1;
				enFreq[1][3]=1;
				enFreq[1][4]=1;
				enFreq[1][5]=1;
		enFreq[1][0]=1;
//-----------------------------LEVEL 3------------------------------------------------------
		p3 = new ArrayList<Point>();
		i3 = new ArrayList<Integer>();
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
		int[] x3={0,800,800,0};
		int[] y3={0,0,1600,1600};
		xp.add(x3);
		yp.add(y3);
		Pillar[] p3 = {new Pillar()};
		pp.add(p3);
		Laser[] l3 = {};
		lp.add(l3);
		levelTimes[2]=60;
		enFreq[2][1]=10;
		enFreq[2][2]=1;
		enFreq[2][3]=1;
		enFreq[2][4]=1;
		enFreq[2][5]=1;
		enFreq[2][0]=1;
//-----------------------------LEVEL 4------------------------------------------------------
		p4 = new ArrayList<Point>();
		i4 = new ArrayList<Integer>();
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
		int[] x4={0,1600,1600,0};
		int[] y4={0,0,1600,1600};
		xp.add(x4);
		yp.add(y4);
		Pillar[] p4 = {new Pillar()};
		pp.add(p4);
		Laser[] l4 = {};
		lp.add(l4);
		levelTimes[3]=120;
		enFreq[3][1]=5;
				enFreq[3][2]=1;
				enFreq[3][3]=1;
				enFreq[3][4]=1;
				enFreq[3][5]=1;
		enFreq[3][0]=1;
//-----------------------------LEVEL 5------------------------------------------------------
		p5 = new ArrayList<Point>();
		i5 = new ArrayList<Integer>();
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
		startPoints.add(new Point(800-charHeight, 600-charWidth));
		s5 = new Spikes(p5, i5,f,f);
		allSpikes.add(s5);
		int[] x5={0,1600,1600,0};
		int[] y5={0,0,1600,1600};
		xp.add(x5);
		yp.add(y5);
		Pillar[] p5 = {new Pillar(new Point(685,685), new Dimension(215,215),true)};
		pp.add(p5);
		Laser[] l5 = {};
		lp.add(l5);
		levelTimes[4]=100;
		enFreq[4][1]=3;
				enFreq[4][2]=1;
				enFreq[4][3]=1;
				enFreq[4][4]=1;
				enFreq[4][5]=1;
		enFreq[4][0]=1;
//---------------------LEVEL6------------------------------------------
		p6=new ArrayList<Point>();
		i6=new ArrayList<Integer>();
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
		int[] x6={0,800,800,1600,1600,800,800,0};
		int[] y6={400,400,0,0,800,800,1200,1200};
		xp.add(x6);
		yp.add(y6);
		Pillar[] p6 = {new Pillar(new Point(1115-30,215-30), new Dimension(215,215),true)};
		pp.add(p6);
		Laser[] l6 = {};
		lp.add(l6);
		levelTimes[5]=100;
		enFreq[5][1]=15;
				enFreq[5][2]=30;
				enFreq[5][3]=1;
				enFreq[5][4]=1;
				enFreq[5][5]=1;
		enFreq[5][0]=1;
//---------------------LEVEL7------------------------------------------
		p7=new ArrayList<Point>();
		i7=new ArrayList<Integer>();
		for(int i=0; i<740/25; i++){
			p7.add(new Point(25*i,0));
			i7.add(Spikes.TOP);
			p7.add(new Point((1600-740)+(25*i), 0));
			i7.add(Spikes.TOP);
			p7.add(new Point(25*i,800-Spikes.HEIGHT));
			i7.add(Spikes.BOTTOM);
			p7.add(new Point((1600-740)+(25*i), 800-Spikes.HEIGHT));
			i7.add(Spikes.BOTTOM);
		}
		for(int i=0; i<140/25; i++){
			p7.add(new Point(740+(25*i), 200));
			i7.add(Spikes.TOP);
			p7.add(new Point(740+(25*i), 600-Spikes.HEIGHT));
			i7.add(Spikes.BOTTOM);
		}
		for(int i=0; i<800/25; i++){
			p7.add(new Point(0, 25*i));
			i7.add(Spikes.LEFT);
			p7.add(new Point(1600-Spikes.HEIGHT, 25*i));
			i7.add(Spikes.RIGHT);
		}
		for(int i=0; i<200/25; i++){
			p7.add(new Point(1600-740, 25*i));
			i7.add(Spikes.LEFT);
			p7.add(new Point(1600-740, 600+(25*i)));
			i7.add(Spikes.LEFT);
			p7.add(new Point(740-Spikes.HEIGHT, 25*i));
			i7.add(Spikes.RIGHT);
			p7.add(new Point(740-Spikes.HEIGHT, 600+(25*i)));
			i7.add(Spikes.RIGHT);
		}
		s7 = new Spikes(p7,i7,f,f);
		allSpikes.add(s7);
		startPoints.add(new Point(800-charHeight, 300-charWidth));
		int[] x7={0,740,740,860,860,1600,1600,860,860,740,740,0};
		int[] y7={0,0,200,200,0,0,800,800,600,600,800,800};
		xp.add(x7);
		yp.add(y7);
		Pillar[] p7 = {new Pillar(250-15,300-15,115,215,true), new Pillar(1050-15,300-15,115,215,true)};
		pp.add(p7);
		Laser[] l7 = {};
		lp.add(l7);
		levelTimes[6]=70;
		enFreq[6][1]=12;
				enFreq[6][2]=28;
				enFreq[6][3]=1;
				enFreq[6][4]=1;
				enFreq[6][5]=1;
		enFreq[6][0]=1;
//---------------------LEVEL8------------------------------------------
		p8=new ArrayList<Point>();
		i8=new ArrayList<Integer>();
		for(int i=0; i<1600/25; i++){
			createTop(i,p8,i8);
			createBottom(i,800,p8,i8);
		}
		for(int i =0; i<500/25; i++){
			p8.add(new Point(25*i, 300-Spikes.HEIGHT));
			i8.add(Spikes.BOTTOM);
			p8.add(new Point(1100+(25*i), 300-Spikes.HEIGHT));
			i8.add(Spikes.BOTTOM);
			p8.add(new Point(25*i, 500));
			i8.add(Spikes.TOP);
			p8.add(new Point(1100+(25*i), 500));
			i8.add(Spikes.TOP);
		}
		for(int i =0; i<200/25; i++){
			p8.add(new Point(500,300+(25*i)));
			i8.add(Spikes.LEFT);
			p8.add(new Point(1100-Spikes.HEIGHT, 300+(25*i)));
			i8.add(Spikes.RIGHT);
		}
		for(int i=0;i<300/25;i++){
			p8.add(new Point(0,25*i));
			i8.add(Spikes.LEFT);
			p8.add(new Point(0,(500)+25*i));
			i8.add(Spikes.LEFT);
			p8.add(new Point(1600-Spikes.HEIGHT,25*i));
			i8.add(Spikes.RIGHT);
			p8.add(new Point(1600-Spikes.HEIGHT,(500)+25*i));
			i8.add(Spikes.RIGHT);
		}
		s8 = new Spikes(p8,i8,f,f);
		allSpikes.add(s8);
		startPoints.add(new Point(800-charHeight, 300-charWidth));
		int[] x8={15,1585,1585,1085,1085,1585,1585,15,15,515,515,15};
		int[] y8={15,15,285,285,515,515,785,785,515,515,285,285};
		xp.add(x8);
		yp.add(y8);
		Pillar[] p8 = {new Pillar()};
		pp.add(p8);
		Laser[] l8 = {};
		lp.add(l8);
		levelTimes[7]=60;
		enFreq[7][1]=15;
				enFreq[7][2]=30;
				enFreq[7][3]=1;
				enFreq[7][4]=1;
				enFreq[7][5]=1;
		enFreq[7][0]=1;
//---------------------LEVEL9------------------------------------------
		p9=new ArrayList<Point>();
		i9=new ArrayList<Integer>();

		createLine(0,0,700,Spikes.TOP,p9,i9);
		createLine(900,0,700,Spikes.TOP,p9,i9);
		createLine(1600,0,700,Spikes.RIGHT,p9,i9);
		createLine(1600,900,700,Spikes.RIGHT,p9,i9);
		createLine(0,1600,700,Spikes.BOTTOM,p9,i9);
		createLine(900,1600,700,Spikes.BOTTOM,p9,i9);
		createLine(0,0,700,Spikes.LEFT,p9,i9);
		createLine(0,900,700,Spikes.LEFT,p9,i9);

		createLine(700,0,200,Spikes.RIGHT,p9,i9);
		createLine(900,0,200,Spikes.LEFT,p9,i9);
		createLine(700,200,200,Spikes.TOP,p9,i9);

		createLine(1400,700,200,Spikes.BOTTOM,p9,i9);
		createLine(1400,900,200,Spikes.TOP,p9,i9);
		createLine(1400,700,200,Spikes.RIGHT,p9,i9);

		createLine(700,1400,200,Spikes.RIGHT,p9,i9);
		createLine(900,1400,200,Spikes.LEFT,p9,i9);
		createLine(700,1400,200,Spikes.BOTTOM,p9,i9);

		createLine(0,700,200,Spikes.BOTTOM,p9,i9);
		createLine(0,900,200,Spikes.TOP,p9,i9);
		createLine(200,700,200,Spikes.LEFT,p9,i9);

		s9 = new Spikes(p9,i9,f,f);
		allSpikes.add(s9);
		startPoints.add(new Point(650-charHeight, 450-charWidth));
		int[] x9={0,700,700,900,900,1600,1600,1400,1400,1600,1600,900,900,700,700,0,0,200,200,0};
		int[] y9={0,0,200,200,0,0,700,700,900,900,1600,1600,1400,1400,1600,1600,900,900,700,700};
		xp.add(x9);
		yp.add(y9);
		Pillar[] p9 = {new Pillar(650-15,650-15,315,315,true),new Pillar(1250-15,200-15,165,165,true), new Pillar(200-15,1250-15,165,165,true),new Pillar(1250-15,1250-15,165,165,true),new Pillar(200-15,200-15,165,165,true)};
		pp.add(p9);
		Laser[] l9 = {};
		lp.add(l9);
		levelTimes[8]=115;
		enFreq[8][1]=17;
				enFreq[8][2]=17;
				enFreq[8][3]=1;
				enFreq[8][4]=1;
				enFreq[8][5]=1;
		enFreq[8][0]=1;
//---------------------LEVEL10------------------------------------------
		p10=new ArrayList<Point>();
		i10=new ArrayList<Integer>();

		createSlant(0,0,1015,5,12,p10,i10);
		createSlant(0,0,420,12,5,p10,i10);

		createSlant(2000,0,-1015,5,-12,p10,i10);
		createSlant(2000,0,-420,12,-5,p10,i10);

		createSlant(0,2000,1015,-5,12,p10,i10);
		createSlant(0,2000,420,-12,5,p10,i10);

		createSlant(2000,2000,-1015,-5,-12,p10,i10);
		createSlant(2000,2000,-420,-12,-5,p10,i10);

		s10 = new Spikes(p10,i10,f,f);
		allSpikes.add(s10);
		startPoints.add(new Point(350-charHeight, 350-charWidth));
		int[] x10={0,1000,2000,2000-417,2000,1000,0,417};
		int[] y10={0,417,0,1000,2000,2000-417,2000,1000};
		xp.add(x10);
		yp.add(y10);
		Pillar[] p10 = {new Pillar(800,800,400,400,true)};
		pp.add(p10);
		Laser[] l10 = {};
		lp.add(l10);
		levelTimes[9]=110;
		enFreq[9][1]=30;
				enFreq[9][2]=23;
				enFreq[9][3]=1;
				enFreq[9][4]=1;
				enFreq[9][5]=1;
		enFreq[9][0]=1;
//---------------------LEVEL11------------------------------------------
		p11=new ArrayList<Point>();
		i11=new ArrayList<Integer>();

		createLine(0,0,900,Spikes.TOP,p11,i11);
		createLine(0,0,900,Spikes.LEFT,p11,i11);
		createLine(900,0,900,Spikes.RIGHT,p11,i11);
		createLine(0,900,900,Spikes.BOTTOM,p11,i11);

		s11 = new Spikes(p11,i11,f,f);
		allSpikes.add(s11);
		startPoints.add(new Point(450-charHeight, 450-charWidth));
		int[] x11={0,900,900,0};
		int[] y11={0,0,900,900};
		xp.add(x11);
		yp.add(y11);
		Pillar[] p11 = {new Pillar()};
		pp.add(p11);
		Laser[] l11 = {new Laser(445,0,900,Laser.VERTICAL, Laser.DONT_MOVE,40,900)};
		lp.add(l11);
		levelTimes[10]=40;
		enFreq[10][1]=40;
				enFreq[10][2]=70;
				enFreq[10][3]=30;
				enFreq[10][4]=1;
				enFreq[10][5]=1;
		enFreq[10][0]=1;
//---------------------LEVEL12------------------------------------------
		p12=new ArrayList<Point>();
		i12=new ArrayList<Integer>();

		createLine(0,0,1600,Spikes.TOP,p12,i12);
		createLine(0,1600,1600,Spikes.BOTTOM,p12,i12);

		createLine(0,0,600,Spikes.LEFT,p12,i12);
		createLine(0,600,400,Spikes.BOTTOM,p12,i12);
		createLine(400,600,400,Spikes.LEFT,p12,i12);
		createLine(0,1000,400,Spikes.TOP,p12,i12);
		createLine(0,1000,600,Spikes.LEFT,p12,i12);

		createLine(1600,0,600,Spikes.RIGHT,p12,i12);
		createLine(1200,600,400,Spikes.BOTTOM,p12,i12);
		createLine(1200,600,400,Spikes.RIGHT,p12,i12);
		createLine(1200,1000,400,Spikes.TOP,p12,i12);
		createLine(1600,1000,600,Spikes.RIGHT,p12,i12);


		s12 = new Spikes(p12,i12,f,f);
		allSpikes.add(s12);
		startPoints.add(new Point(250-charHeight, 470-charWidth));
		int[] x12={0,1600,1600,1200,1200,1600,1600,0,0,400,400,0};
		int[] y12={0,0,600,600,1000,1000,1600,1600,1000,1000,600,600};
		xp.add(x12);
		yp.add(y12);
		Pillar[] p12 = {new Pillar(300,250,1000,150,false)
		, new Pillar(300,1250,1000,150,false)
		};
		pp.add(p12);
		Laser[] l12 = {new Laser(400,785,800,Laser.HORIZONTAL, Laser.DONT_MOVE,40,900)};
		lp.add(l12);
		levelTimes[11]=90;
		enFreq[11][1]=30;
				enFreq[11][2]=40;
				enFreq[11][3]=30;
				enFreq[11][4]=1;
				enFreq[11][5]=1;
		enFreq[11][0]=1;
//---------------------LEVEL13------------------------------------------
		p13=new ArrayList<Point>();
		i13=new ArrayList<Integer>();

		createLine(0,0,900,Spikes.TOP,p13,i13);
		createLine(0,900,900,Spikes.BOTTOM,p13,i13);
		createLine(0,0,900,Spikes.LEFT,p13,i13);
		createLine(1500,0,900,Spikes.RIGHT,p13,i13);

		createLine(900,0,300,Spikes.RIGHT,p13,i13);
		createLine(900,600,300,Spikes.RIGHT,p13,i13);

		createLine(1200,0,300,Spikes.LEFT,p13,i13);
		createLine(1200,600,300,Spikes.LEFT,p13,i13);

		createLine(900,600,300,Spikes.BOTTOM,p13,i13);
		createLine(900,300,300,Spikes.TOP,p13,i13);

		createLine(1200,900,300,Spikes.BOTTOM,p13,i13);
		createLine(1200,0,300,Spikes.TOP,p13,i13);

		s13 = new Spikes(p13,i13,f,f);
		allSpikes.add(s13);
		startPoints.add(new Point(250-charHeight, 250-charWidth));
		int[] x13={0,900,900,1200,1200,1500,1500,1200,1200,900,900,0};
		int[] y13={0,0,300,300,0,0,900,900,600,600,900,900};
		xp.add(x13);
		yp.add(y13);
		Pillar[] p13 = {new Pillar(350,350,200,200,false)};
		pp.add(p13);
		Laser[] l13 = {new Laser(0,450-15,1500,Laser.HORIZONTAL, Laser.DONT_MOVE,65,900)};
		lp.add(l13);
		levelTimes[12]=65;
		enFreq[12][1]=43;
				enFreq[12][2]=48;
				enFreq[12][3]=53;
				enFreq[12][4]=40;
				enFreq[12][5]=40;
		enFreq[12][0]=40;
//---------------------LEVEL14------------------------------------------
		p14=new ArrayList<Point>();
		i14=new ArrayList<Integer>();

		createSlant(800,0,800,5,5,p14,i14);
		createSlant(1600,800,-800,5,-5,p14,i14);
		createSlant(800,1600,-800,-5,-5,p14,i14);
		createSlant(0,800,800,-5,5,p14,i14);
	//	createSlant(800,800,-1015,-5,-12,p14,i14);

		s14 = new Spikes(p14,i14,f,f);
		allSpikes.add(s14);
		startPoints.add(new Point(799-charHeight, 550-charWidth));
		int[] x14={0,800,1600,800};
		int[] y14={800,0,800,1600};
		xp.add(x14);
		yp.add(y14);
		Pillar[] p14 = {new Pillar(400,700,200,200,false), new Pillar(1000,700,200,200,false)};
		pp.add(p14);
		Laser[] l14 = {new Laser(800-15,0,1600,Laser.VERTICAL, Laser.DONT_MOVE,60,900)};
		lp.add(l14);
		levelTimes[13]=75;
		enFreq[13][1]=35;
				enFreq[13][2]=80;
				enFreq[13][3]=55;
				enFreq[13][4]=40;
				enFreq[13][5]=40;
		enFreq[13][0]=40;
//----------------------LEVEL 15-----------------------------------------------------------------
		p15=new ArrayList<Point>();
		i15=new ArrayList<Integer>();

		createLine(0,0,400,Spikes.LEFT,p15,i15);
		createLine(300,400,400,Spikes.LEFT,p15,i15);
		createLine(0,800,400,Spikes.LEFT,p15,i15);

		createLine(0,400,300,Spikes.BOTTOM,p15,i15);
		createLine(0,800,300,Spikes.TOP,p15,i15);
		createLine(0,1200,600,Spikes.BOTTOM,p15,i15);

		createSlant(15,0,1200,5,10,p15,i15);
		createSlant(610,1195,600,-7,7,p15,i15);

		s15 = new Spikes(p15,i15,f,f);
		allSpikes.add(s15);
		startPoints.add(new Point(150-charHeight, 250-charWidth));
		int[] x15={0,1200,600,0,0,300,300,0};
		int[] y15={0,600,1200,1200,800,800,400,400};
		xp.add(x15);
		yp.add(y15);
		Pillar[] p15 = {new Pillar(250,920,100,100,false)};
		pp.add(p15);
		Laser[] l15 = {new Laser(300,600-15,900,Laser.HORIZONTAL, Laser.DONT_MOVE,50,900)};
		lp.add(l15);
		levelTimes[14]=80;
		enFreq[14][1]=90;
				enFreq[14][2]=100;
				enFreq[14][3]=30;
				enFreq[14][4]=40;
				enFreq[14][5]=40;
		enFreq[14][0]=40;
//---------------------------------------------------------------------------------------
//----------------------LEVEL 16-----------------------------------------------------------------
		p16=new ArrayList<Point>();
		i16=new ArrayList<Integer>();

		createLine(0,0,650,Spikes.TOP,p16,i16);

		createLine(650,0,500,Spikes.RIGHT,p16,i16);
		createLine(650,500,300,Spikes.TOP,p16,i16);
		createLine(950,0,500,Spikes.LEFT,p16,i16);

		createLine(950,0,650,Spikes.TOP,p16,i16);

		createLine(1600,0,400,Spikes.RIGHT,p16,i16);
		createLine(1200,400,400,Spikes.TOP,p16,i16);
		createLine(1200,400,1200,Spikes.RIGHT,p16,i16);
		createLine(400,1600,800,Spikes.BOTTOM,p16,i16);
		createLine(400,400,1200,Spikes.LEFT,p16,i16);
		createLine(0,400,400,Spikes.BOTTOM,p16,i16);
		createLine(0,0,400,Spikes.LEFT,p16,i16);

		s16 = new Spikes(p16,i16,f,f);
		allSpikes.add(s16);
		startPoints.add(new Point(350-charHeight, 350-charWidth));
		int[] x16={0,650,650,950,950,1600,1600,1200,1200,400,400,0};
		int[] y16={0,0,500,500,0,0,400,400,1600,1600,400,400};
		xp.add(x16);
		yp.add(y16);
		Pillar[] p16 = {new Pillar(800-75,920,150,150,false),new Pillar(800-75,920+175+150,150,150,false)};
		pp.add(p16);
		Laser[] l16 = {new Laser(400,650,800,Laser.HORIZONTAL, Laser.DONT_MOVE,42,900),new Laser(400,1250,800,Laser.HORIZONTAL, Laser.DONT_MOVE,62,900)};
		lp.add(l16);
		levelTimes[15]=60;
		enFreq[15][1]=45;
				enFreq[15][2]=55;
				enFreq[15][3]=60;
				enFreq[15][4]=20;
				enFreq[15][5]=40;
		enFreq[15][0]=40;
//---------------------------------------------------------------------------------------
//----------------------LEVEL 17-----------------------------------------------------------------
		p17=new ArrayList<Point>();
		i17=new ArrayList<Integer>();

		createSlant(800,0,400,3,4,p17,i17);
		createSlant(0,300,800,-3,8,p17,i17);
		createLine(0,300,1300,Spikes.LEFT,p17,i17);
		createLine(0,1600,1200,Spikes.BOTTOM,p17,i17);
		createLine(1200,300,1300,Spikes.RIGHT,p17,i17);


		s17 = new Spikes(p17,i17,f,f);
		allSpikes.add(s17);
		startPoints.add(new Point(200-charHeight, 600-charWidth));
		int[] x17={800,1200,1200,0,0};
		int[] y17={0,300,1600,1600,300};
		xp.add(x17);
		yp.add(y17);
		Pillar[] p17 = {new Pillar(350,350,600,900,true), new Pillar(65,700,185,150,false)};
		pp.add(p17);
		Laser[] l17 = {new Laser(0,300-15,1200,Laser.HORIZONTAL, Laser.DONT_MOVE,90,900),new Laser(0,900,1200,Laser.HORIZONTAL, Laser.DONT_MOVE,40,900)};
		lp.add(l17);
		levelTimes[16]=62;
		enFreq[16][1]=50;
				enFreq[16][2]=70;
				enFreq[16][3]=60;
				enFreq[16][4]=35;
				enFreq[16][5]=40;
		enFreq[16][0]=40;
//---------------------------------------------------------------------------------------
//----------------------LEVEL 18-----------------------------------------------------------------
		p18=new ArrayList<Point>();
		i18=new ArrayList<Integer>();

		createLine(0,0,1600,Spikes.TOP,p18,i18);
		createLine(1600,0,700,Spikes.RIGHT,p18,i18);
		createLine(500,700,1100,Spikes.BOTTOM,p18,i18);
		createLine(500,400,300,Spikes.LEFT,p18,i18);
		createLine(300,400,200,Spikes.BOTTOM,p18,i18);
		createLine(300,400,300,Spikes.RIGHT,p18,i18);
		createLine(0,700,300,Spikes.BOTTOM,p18,i18);
		createLine(0,0,700,Spikes.LEFT,p18,i18);

		s18 = new Spikes(p18,i18,f,f);
		allSpikes.add(s18);
		startPoints.add(new Point(750-charHeight, 250-charWidth));
		int[] x18={0,1600,1600,500,500,300,300,0};
		int[] y18={0,0,700-5,700-5,400-5,400-5,700-5,700-5};
		xp.add(x18);
		yp.add(y18);
		Pillar[] p18 = {new Pillar(1300,300,75,100,false)};
		pp.add(p18);
		Laser[] l18 = {new Laser(100,0,700,Laser.VERTICAL, Laser.DONT_MOVE,40,900)};
		lp.add(l18);
		levelTimes[17]=60;
		enFreq[17][1]=40;
				enFreq[17][2]=60;
				enFreq[17][3]=45;
				enFreq[17][4]=35;
				enFreq[17][5]=40;
		enFreq[17][0]=40;
//---------------------------------------------------------------------------------------
//----------------------LEVEL 19-----------------------------------------------------------------
		p19=new ArrayList<Point>();
		i19=new ArrayList<Integer>();

		createLine(0,0,1600,Spikes.TOP,p19,i19);
		createLine(0,0,1600,Spikes.LEFT,p19,i19);
		createSlant(0,1600,1600,-8,8,p19,i19);

		s19 = new Spikes(p19,i19,f,f);
		allSpikes.add(s19);
		startPoints.add(new Point(500-charHeight, 250-charWidth));
		int[] x19={0,1600,0};
		int[] y19={0,0,1600};
		xp.add(x19);
		yp.add(y19);
		Pillar[] p19 = {new Pillar(215,550,200,200,false),new Pillar(550,215,200,200,false)};
		pp.add(p19);
		Laser[] l19 = {new Laser(490,0,1600,Laser.VERTICAL, Laser.DONT_MOVE,70,900), new Laser(0,490,1600,Laser.HORIZONTAL,Laser.DONT_MOVE,70,900)};
		lp.add(l19);
		levelTimes[18]=73;
		enFreq[18][1]=35;
				enFreq[18][2]=60;
				enFreq[18][3]=42;
				enFreq[18][4]=30;
				enFreq[18][5]=40;
		enFreq[18][0]=40;
//----------------------LEVEL 20-----------------------------------------------------------------
		p20=new ArrayList<Point>();
		i20=new ArrayList<Integer>();

		createEllipse(1200,1200,p20,i20);

		s20 = new Spikes(p20,i20,f,f);
		allSpikes.add(s20);
		startPoints.add(new Point(450-charHeight, 250-charWidth));
		int[] x20={1200};
		int[] y20={1200};
		xp.add(x20);
		yp.add(y20);
		Pillar[] p20 = {new Pillar(450,450,300,300,false)};
		pp.add(p20);
		Laser[] l20 = {new Laser(210,0,1200,Laser.VERTICAL, Laser.DONT_MOVE,40,900),new Laser(1200-210,0,1200,Laser.VERTICAL, Laser.DONT_MOVE,40,900)};
		lp.add(l20);
		levelTimes[19]=65;
		enFreq[19][1]=60;
				enFreq[19][2]=70;
				enFreq[19][3]=55;
				enFreq[19][4]=10;
				enFreq[19][5]=40;
		enFreq[19][0]=40;
//----------------------LEVEL 21-----------------------------------------------------------------
		p21=new ArrayList<Point>();
		i21=new ArrayList<Integer>();

		createLine(0,0,1100,Spikes.TOP,p21,i21);
		createLine(0,900,1100,Spikes.BOTTOM,p21,i21);
		createLine(0,0,900,Spikes.LEFT,p21,i21);
		createLine(1100,0,900,Spikes.RIGHT,p21,i21);


		s21 = new Spikes(p21,i21,f,f);
		allSpikes.add(s21);
		startPoints.add(new Point(450-charHeight, 250-charWidth));
		int[] x21={0,1100,1100,0};
		int[] y21={0,0,900,900};
		xp.add(x21);
		yp.add(y21);
		Pillar[] p21 = {new Pillar(500,150,100,200,false),new Pillar(500,900-350,100,200,false),new Pillar(150,400,200,100,false),new Pillar(1100-350,400,200,100,false)};
		pp.add(p21);
		Laser[] l21 = {new Laser(200,0,900,Laser.VERTICAL, Laser.MOVE,60,900),new Laser(800,0,900,Laser.VERTICAL, Laser.MOVE,80,900)};
		lp.add(l21);
		levelTimes[20]=60;
		enFreq[20][1]=85;
				enFreq[20][2]=95;
				enFreq[20][3]=90;
				enFreq[20][4]=75;
				enFreq[20][5]=88;
		enFreq[20][0]=40;
//----------------------LEVEL 22-----------------------------------------------------------------
		p22=new ArrayList<Point>();
		i22=new ArrayList<Integer>();

		createLine(0,0,1000,Spikes.TOP,p22,i22);
		createLine(0,900,1000,Spikes.BOTTOM,p22,i22);
		createLine(0,0,900,Spikes.LEFT,p22,i22);
		createLine(1000,0,900,Spikes.RIGHT,p22,i22);


		s22 = new Spikes(p22,i22,f,f);
		allSpikes.add(s22);
		startPoints.add(new Point(500-charHeight, 250-charWidth));
		int[] x22={0,1000,1000,0};
		int[] y22={0,0,900,900};
		xp.add(x22);
		yp.add(y22);
		Pillar[] p22 = {new Pillar(150+25,150+25,200-50,200-50,false),new Pillar(650+25,150+25,200-50,200-50,false),new Pillar(150+25,550+25,200-50,200-50,false),new Pillar(650+25,550+25,200-50,200-50,false)};
		pp.add(p22);
		Laser[] l22 = {new Laser(500-15,0,900,Laser.VERTICAL, Laser.DONT_MOVE,60,900), new Laser(0,450-15,1000,Laser.HORIZONTAL, Laser.DONT_MOVE,50,900)};
		lp.add(l22);
		levelTimes[21]=70;
		enFreq[21][1]=50+5;
				enFreq[21][2]=65+5;
				enFreq[21][3]=70+5;
				enFreq[21][4]=40+5;
				enFreq[21][5]=60+5;
		enFreq[21][0]=40;
//----------------------LEVEL 23-----------------------------------------------------------------
		p23=new ArrayList<Point>();
		i23=new ArrayList<Integer>();

		createLine(0,0,450,Spikes.TOP,p23,i23);
		createLine(450,250,1150,Spikes.TOP,p23,i23);
		createLine(0,0,800,Spikes.LEFT,p23,i23);
		createLine(950,500,300,Spikes.LEFT,p23,i23);
		createLine(450,0,250,Spikes.RIGHT,p23,i23);
		createLine(450,500,300,Spikes.RIGHT,p23,i23);
		createLine(1600,250,550,Spikes.RIGHT,p23,i23);
		createLine(0,800,450,Spikes.BOTTOM,p23,i23);
		createLine(450,500,500,Spikes.BOTTOM,p23,i23);
		createLine(950,800,650,Spikes.BOTTOM,p23,i23);

		s23 = new Spikes(p23,i23,f,f);
		allSpikes.add(s23);
		startPoints.add(new Point(250-charHeight, 150-charWidth));
		int[] x23={0,450,450,1600,1600,950,950,450,450,0};
		int[] y23={0,0,250,250,800,800,500,500,800,800};
		xp.add(x23);
		yp.add(y23);
		Pillar[] p23 = {new Pillar(1175,450,180,180,false)};
		pp.add(p23);
		Laser[] l23 = {new Laser(0,400-15,1600,Laser.HORIZONTAL, Laser.DONT_MOVE,40,900)/*, new Laser(0,0,800,Laser.VERTICAL, Laser.MOVE,40,900)*/};
		lp.add(l23);
		levelTimes[22]=50;
		enFreq[22][1]=40-2;
				enFreq[22][2]=70-2;
				enFreq[22][3]=60-2;
				enFreq[22][4]=40-2;
				enFreq[22][5]=50-2;
		enFreq[22][0]=40;
//----------------------LEVEL 24-----------------------------------------------------------------
		p24=new ArrayList<Point>();
		i24=new ArrayList<Integer>();

		createLine(0,0,600,Spikes.TOP,p24,i24);
		createLine(0,0,600,Spikes.LEFT,p24,i24);
		createLine(600,1200,600,Spikes.BOTTOM,p24,i24);
		createLine(1200,600,600,Spikes.RIGHT,p24,i24);
		createSlant(607,7,600,8,8,p24,i24);
		createSlant(7,607,600,8,8,p24,i24);

		s24 = new Spikes(p24,i24,f,f);
		allSpikes.add(s24);
		startPoints.add(new Point(250-charHeight, 150-charWidth));
		int[] x24={0,600,1200,1200,600,0};
		int[] y24={0,0,600,1200,1200,600};
		xp.add(x24);
		yp.add(y24);
		Pillar[] p24 = {/*new Pillar(215,550,200,200,false),new Pillar(550,215,200,200,false)*/new Pillar()};
		pp.add(p24);
		Laser[] l24 = {new Laser(600,0,1200,Laser.VERTICAL, Laser.MOVE,50,900), new Laser(0,700,1200,Laser.HORIZONTAL, Laser.MOVE,50,900)};
		lp.add(l24);
		levelTimes[23]=60;
		enFreq[23][1]=45;
				enFreq[23][2]=200;
				enFreq[23][3]=95;
				enFreq[23][4]=30;
				enFreq[23][5]=50;
		enFreq[23][0]=40;
//----------------------LEVEL 25-----------------------------------------------------------------
		p25=new ArrayList<Point>();
		i25=new ArrayList<Integer>();

		createLine(0,0,800,Spikes.TOP,p25,i25);

		createLine(0,300,300,Spikes.BOTTOM,p25,i25);
		createLine(500,300,300,Spikes.BOTTOM,p25,i25);
		createLine(0,325,300,Spikes.TOP,p25,i25);
		createLine(500,325,300,Spikes.TOP,p25,i25);

		createLine(0,625,300,Spikes.BOTTOM,p25,i25);
		createLine(500,625,300,Spikes.BOTTOM,p25,i25);
		createLine(0,650,300,Spikes.TOP,p25,i25);
		createLine(500,650,300,Spikes.TOP,p25,i25);

		createLine(0,950,300,Spikes.BOTTOM,p25,i25);
		createLine(500,950,300,Spikes.BOTTOM,p25,i25);
		createLine(0,975,300,Spikes.TOP,p25,i25);
		createLine(500,975,300,Spikes.TOP,p25,i25);

		createLine(0,1275,800,Spikes.BOTTOM,p25,i25);

		createLine(0,0,300,Spikes.LEFT,p25,i25);
		createLine(300,300,25,Spikes.LEFT,p25,i25);
		createLine(0,325,300,Spikes.LEFT,p25,i25);
		createLine(300,625,25,Spikes.LEFT,p25,i25);
		createLine(0,650,300,Spikes.LEFT,p25,i25);
		createLine(300,950,25,Spikes.LEFT,p25,i25);
		createLine(0,975,300,Spikes.LEFT,p25,i25);

		createLine(800,0,300,Spikes.RIGHT,p25,i25);
		createLine(500,300,25,Spikes.RIGHT,p25,i25);
		createLine(800,325,300,Spikes.RIGHT,p25,i25);
		createLine(500,625,25,Spikes.RIGHT,p25,i25);
		createLine(800,650,300,Spikes.RIGHT,p25,i25);
		createLine(500,950,25,Spikes.RIGHT,p25,i25);
		createLine(800,975,300,Spikes.RIGHT,p25,i25);


		s25 = new Spikes(p25,i25,f,f);
		allSpikes.add(s25);
		startPoints.add(new Point(250-charHeight, 150-charWidth));
		int[] x25={0,800,800,500,500,800,800,500,500,800,800,500,500,800,800,0,0,300,300,0,0,300,300,0,0,300,300,0};
		int[] y25={0,0,300,300,325,325,625,625,650,650,950,950,975,975,1275,1275,975,975,950,950,650,650,625,625,325,325,300,300};
		xp.add(x25);
		yp.add(y25);
		Pillar[] p25 = {/*new Pillar(215,550,200,200,false),new Pillar(550,215,200,200,false)*/new Pillar()};
		pp.add(p25);
		Laser[] l25 = { new Laser(400,0,1275,Laser.VERTICAL, Laser.DONT_MOVE,60,900), new Laser(0,500,800,Laser.HORIZONTAL, Laser.MOVE,40,900)};
		lp.add(l25);
		levelTimes[24]=60;
		enFreq[24][1]=50+10;
				enFreq[24][2]=55+10;
				enFreq[24][3]=53+10;
				enFreq[24][4]=42+10;
				enFreq[24][5]=40-5;
		enFreq[24][0]=40;
//----------------------LEVEL 26-----------------------------------------------------------------
		p26=new ArrayList<Point>();
		i26=new ArrayList<Integer>();

		createLine(0,775,400,Spikes.TOP,p26,i26);
		createLine(400,400,375,Spikes.TOP,p26,i26);
		createLine(775,0,150,Spikes.TOP,p26,i26);
		createLine(925,400,375,Spikes.TOP,p26,i26);
		createLine(1300,775,400,Spikes.TOP,p26,i26);

		createLine(0,925,400,Spikes.BOTTOM,p26,i26);
		createLine(400,1300,375,Spikes.BOTTOM,p26,i26);
		createLine(775,1700,150,Spikes.BOTTOM,p26,i26);
		createLine(925,1300,375,Spikes.BOTTOM,p26,i26);
		createLine(1300,925,400,Spikes.BOTTOM,p26,i26);

		createLine(775,0,400,Spikes.LEFT,p26,i26);
		createLine(400,400,375,Spikes.LEFT,p26,i26);
		createLine(0,775,150,Spikes.LEFT,p26,i26);
		createLine(400,925,375,Spikes.LEFT,p26,i26);
		createLine(775,1300,400,Spikes.LEFT,p26,i26);

		createLine(925,0,400,Spikes.RIGHT,p26,i26);
		createLine(1300,400,375,Spikes.RIGHT,p26,i26);
		createLine(1700,775,150,Spikes.RIGHT,p26,i26);
		createLine(1300,925,375,Spikes.RIGHT,p26,i26);
		createLine(925,1300,400,Spikes.RIGHT,p26,i26);

		s26 = new Spikes(p26,i26,f,f);
		allSpikes.add(s26);
		startPoints.add(new Point(550-charHeight, 550-charWidth));
		int[] x26={400,775,775,925,925,1300,1300,1700,1700,1300,1300,925,925,775,775,400,400,0,0,400};
		int[] y26={400,400,0,0,400,400,775,775,925,925,1300,1300,1700,1700,1300,1300,925,925,775,775};
		xp.add(x26);
		yp.add(y26);
		Pillar[] p26 = {new Pillar(600,600,100,100,false),/*new Pillar(1300-350,600,100,100,false),new Pillar(600,1300-350,100,100,false)*/new Pillar(1300-350,1300-350,100,100,false)};
		pp.add(p26);
		Laser[] l26 = { new Laser(850-15,0,1700,Laser.VERTICAL, Laser.DONT_MOVE,90,900), new Laser(0,850-15,1700,Laser.HORIZONTAL, Laser.DONT_MOVE,90,900)};
		lp.add(l26);
		levelTimes[25]=40;
		enFreq[25][1]=60+50;
				enFreq[25][2]=90+90;
				enFreq[25][3]=80+50;
				enFreq[25][4]=40+40;
				enFreq[25][5]=70+40;
		enFreq[25][0]=40;
//---------------------LEVEL27------------------------------------------
		p27=new ArrayList<Point>();
		i27=new ArrayList<Integer>();

		createSlant(0,0,(int)(510*1.5),5,12,p27,i27);
		createSlant(0,0,(int)(210*1.5),12,5,p27,i27);

		createSlant((int)(1000*1.5),0,(int)(-510*1.5),5,-12,p27,i27);
		createSlant((int)(1000*1.5),0,(int)(-210*1.5),12,-5,p27,i27);

		createSlant(0,(int)(1000*1.5),(int)(510*1.5),-5,12,p27,i27);
		createSlant(0,(int)(1000*1.5),(int)(210*1.5),-12,5,p27,i27);

		createSlant((int)(1000*1.5),(int)(1000*1.5),(int)(-510*1.5),-5,-12,p27,i27);
		createSlant((int)(1000*1.5),(int)(1000*1.5),(int)(-210*1.5),-12,-5,p27,i27);

		s27 = new Spikes(p27,i27,f,f);
		allSpikes.add(s27);
		startPoints.add(new Point(350-charHeight, 350-charWidth));
		int[] x27={0,(int)(500*1.5),(int)(1000*1.5),(int)(1000*1.5-210*1.5),(int)(1000*1.5),(int)(500*1.5),0,(int)(210*1.5)};
		int[] y27={0,(int)(210*1.5),0,(int)(500*1.5),(int)(1000*1.5),(int)(1000*1.5-210*1.5),(int)(1000*1.5),(int)(500*1.5)};
		xp.add(x27);
		yp.add(y27);
		Pillar[] p27 = {new Pillar(650,465,160,550,false),new Pillar(465,650,550,160,false)};
		pp.add(p27);
		Laser[] l27 = {new Laser(500,0,1500,Laser.VERTICAL, Laser.MOVE,70,900), new Laser(0,500,1500,Laser.HORIZONTAL, Laser.MOVE,60,900)};
		lp.add(l27);
		levelTimes[26]=90;
		enFreq[26][1]=60;
				enFreq[26][2]=100;
				enFreq[26][3]=70;
				enFreq[26][4]=40;
				enFreq[26][5]=50;
		enFreq[26][0]=80;
//---------------------LEVEL28------------------------------------------
		p28=new ArrayList<Point>();
		i28=new ArrayList<Integer>();

		createLine(0,0,1000,Spikes.TOP,p28,i28);
		createLine(1000,0,400,Spikes.RIGHT,p28,i28);
		createLine(1000,400,200,Spikes.TOP,p28,i28);
		createLine(1200,0,400,Spikes.LEFT,p28,i28);
		createLine(1200,0,400,Spikes.TOP,p28,i28);
		createLine(1600,0,1200,Spikes.RIGHT,p28,i28);
		createLine(0,0,1200,Spikes.LEFT,p28,i28);
		createLine(0,1200,400,Spikes.BOTTOM,p28,i28);
		createLine(400,800,400,Spikes.LEFT,p28,i28);
		createLine(400,800,200,Spikes.BOTTOM,p28,i28);
		createLine(600,800,400,Spikes.LEFT,p28,i28);
		createLine(600,1200,1000,Spikes.BOTTOM,p28,i28);


		s28 = new Spikes(p28,i28,f,f);
		allSpikes.add(s28);
		startPoints.add(new Point(350-charHeight, 350-charWidth));
		int[] x28={0,1000,1000,1200,1200,1600,1600,600,600,400,400,0};
		int[] y28={0,0,400,400,0,0,1200,1200,800,800,1200,1200};
		xp.add(x28);
		yp.add(y28);
		Pillar[] p28 = {new Pillar(155,955,80,80,false),new Pillar(400,300,200,200,false), new Pillar(750,550,100,100,false), new Pillar(1000,700,200,200,false), new Pillar(1600-155-80,1200-955-80,80,80,false)};
		pp.add(p28);
		Laser[] l28 = {new Laser(200-15,0,1200,Laser.VERTICAL, Laser.DONT_MOVE,40,900), new Laser(1450,0,1200,Laser.VERTICAL, Laser.DONT_MOVE,40,900), new Laser(0,500,1600,Laser.HORIZONTAL, Laser.MOVE,40,900)};
		lp.add(l28);
		levelTimes[27]=90;
		enFreq[27][1]=40;
				enFreq[27][2]=70;
				enFreq[27][3]=55;
				enFreq[27][4]=45;
				enFreq[27][5]=50;
		enFreq[27][0]=60;

//---------------------LEVEL29------------------------------------------
		p29=new ArrayList<Point>();
		i29=new ArrayList<Integer>();

		createLine(0,0,480,Spikes.TOP,p29,i29);
		createSlant(480,0,240,10,6,p29,i29);
		createSlant(720,400,240,-10,6,p29,i29);
		createLine(960,0,480,Spikes.TOP,p29,i29);
		createSlant(1440,0,240,10,6,p29,i29);
		createSlant(1680,400,240,-10,6,p29,i29);
		createLine(1920,0,480,Spikes.TOP,p29,i29);
		createLine(2400,0,600,Spikes.RIGHT,p29,i29);
		createSlant(1680,1200,720,-5,6,p29,i29);
		createSlant(1440,600,240,10,4,p29,i29);
		createLine(1275,600,165,Spikes.BOTTOM,p29,i29);
		createSlant(0,1200,1300,-6,13,p29,i29);
		createLine(0,0,1250,Spikes.LEFT,p29,i29);


		s29 = new Spikes(p29,i29,f,f);
		allSpikes.add(s29);
		startPoints.add(new Point(350-charHeight, 350-charWidth));
		int[] x29={0,240*2,360*2,480*2,720*2,840*2,960*2,1200*2,1200*2,840*2,720*2,650*2,  0};
		int[] y29={0, 0,   200*2,  0,   0,   200*2, 0,     0,    300*2,600*2,300*2,300*2,600*2};
		xp.add(x29);
		yp.add(y29);
		Pillar[] p29 = {new Pillar(150,600,200,200,false),new Pillar(200,500,100,100,false), new Pillar(225,450,50,50,false),
						new Pillar(1080,200,200,200,false)
						,new Pillar(2000,200,200,200,false), new Pillar(2050,400,100,100,false), new Pillar(2075,500,50,50,false)};
		pp.add(p29);
		Laser[] l29 = {new Laser(1665,400,1200-400,Laser.VERTICAL, Laser.DONT_MOVE,60,900), new Laser(100,0,1200,Laser.VERTICAL, Laser.MOVE,60,900), new Laser(900,0,1200,Laser.VERTICAL, Laser.MOVE,60,900), new Laser(1700,0,1200,Laser.VERTICAL, Laser.MOVE,60,900), new Laser(2300,0,1200,Laser.VERTICAL, Laser.MOVE,60,900), new Laser(0,400,2400,Laser.HORIZONTAL, Laser.MOVE,60,900), new Laser(0,800,2400,Laser.HORIZONTAL, Laser.MOVE,60,900)};
		lp.add(l29);
		levelTimes[28]=125;
		enFreq[28][1]=40*2;
				enFreq[28][2]=90*2;
				enFreq[28][3]=90*2;
				enFreq[28][4]=40;
				enFreq[28][5]=40*2;
		enFreq[28][0]=40;
//---------------------LEVEL30------------------------------------------
		p30=new ArrayList<Point>();
		i30=new ArrayList<Integer>();

		createEllipse(1600,1600,p30,i30);

		s30 = new Spikes(p30,i30,f,f);
		allSpikes.add(s30);
		startPoints.add(new Point(350-charHeight, 350-charWidth));
		int[] x30={1600};
		int[] y30={1600};
		xp.add(x30);
		yp.add(y30);
		Pillar[] p30 = {new Pillar(650,465,160,550,false),new Pillar(465,650,550,160,false)};
		pp.add(p30);
		Laser[] l30 = {new Laser(0,200,1600,Laser.HORIZONTAL, Laser.MOVE,40,900), new Laser(200,0,1600,Laser.VERTICAL, Laser.MOVE,40,900)};
		lp.add(l30);
		levelTimes[29]=180;
		enFreq[29][1]=40;
				enFreq[29][2]=40;
				enFreq[29][3]=40;
				enFreq[29][4]=40;
				enFreq[29][5]=40;
		enFreq[29][0]=40;
//---------------------------------------------------------------------------------------



	spikes = allSpikes.get(levelNumber-1);
	startPoint = startPoints.get(levelNumber-1);
	tk = Toolkit.getDefaultToolkit();
	try{
		if(levelNumber<6)background = ImageIO.read(getClass().getResource("/Desert.jpg"));
		else if(levelNumber<11)background = ImageIO.read(getClass().getResource("/underWater.jpg"));
		else if(levelNumber<16) background = ImageIO.read(getClass().getResource("/circuit.jpg"));
		else if(levelNumber<21) background = ImageIO.read(getClass().getResource("/arctic2.jpg"));
		else if(levelNumber<26) background = ImageIO.read(getClass().getResource("/forest.jpg"));
		else background = ImageIO.read(getClass().getResource("/sky.jpg"));
	//	background2 = ImageIO.read(getClass().getResource("background2"+levelNumber+".png"));
	}catch(IOException e){}
	}
	public int[] getTimes(){
		return(levelTimes);
	}
	public int[][] getFreqs(){
		return(enFreq);
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
	public ArrayList<int[]> getBoundaryX(){
		return(xp);
	}
	public ArrayList<int[]> getBoundaryY(){
		return(yp);
	}
	public ArrayList<Pillar[]> getPillar(){
		return(pp);
	}
	public ArrayList<Laser[]> getLaser(){
			return(lp);
	}
//	public Image getBackground2(){
//		return(background2);
//	}
	public Point getStartPoint(){
		return(startPoint);
	}
	public void createTop(int i, ArrayList<Point> a1, ArrayList<Integer> a2){
		a1.add(new Point(25*i,0));
		a2.add(Spikes.TOP);
	}
	public void createBottom(int i, int h, ArrayList<Point> a1, ArrayList<Integer> a2){
			a1.add(new Point(25*i,h-Spikes.HEIGHT));
			a2.add(Spikes.BOTTOM);
	}
	public void createLeft(int i, ArrayList<Point> a1, ArrayList<Integer> a2){
			a1.add(new Point(0,25*i));
			a2.add(Spikes.LEFT);
	}
	public void createRight(int i, int h, ArrayList<Point> a1, ArrayList<Integer> a2){
			a1.add(new Point(h-Spikes.HEIGHT,25*i));
			a2.add(Spikes.RIGHT);
	}
	public void createLine(int startX, int startY, int length, int orientation, ArrayList<Point> a1, ArrayList<Integer> a2){
		if(orientation == Spikes.TOP){
			for(int i = 0; i<length/12; i++){
				a1.add(new Point(startX+(12*i),startY));
				a2.add(Spikes.TOP);
			}
		}
		else if(orientation == Spikes.LEFT){
			for(int i = 0; i<length/12; i++){
				a1.add(new Point(startX,startY+(12*i)));
				a2.add(Spikes.LEFT);
			}
		}
		else if(orientation == Spikes.RIGHT){
			for(int i = 0; i<length/12; i++){
				a1.add(new Point(startX-Spikes.HEIGHT,startY+(12*i)));
				a2.add(Spikes.RIGHT);
			}
		}
		else if(orientation ==Spikes.BOTTOM){
			for(int i = 0; i<length/12; i++){
				a1.add(new Point(startX+(12*i),startY-Spikes.HEIGHT));
				a2.add(Spikes.BOTTOM);
			}
		}
	}
	public void createSlant(int startX, int startY, int length, int rise, int run, ArrayList<Point> a1, ArrayList<Integer> a2){
		for(int i = 0; i<(length)/run; i++){
			a1.add(new Point(startX+(run*i)-15,startY+(rise*i)-15));
			a2.add(5);
		}
	}
	public void createEllipse(int width, int height, ArrayList<Point> a1, ArrayList<Integer> a2){
		width = width/2;
		height = height/2;
		for(int i =(-1*width); i<=width; i = i+10){
			a1.add(new Point(i+width,(height)+(int)(height*Math.sqrt((width*width)-(i*i)))/width));
			a1.add(new Point(i+width,(height)-(int)(height*Math.sqrt((width*width)-(i*i)))/width));
			a2.add(5);a2.add(5);
		}
		for(int i =(-1*height); i<=height; i = i+10){
			a1.add(new Point((width)+(int)(width*Math.sqrt((height*height)-(i*i)))/height,i+height));
			a1.add(new Point((width)-(int)(width*Math.sqrt((height*height)-(i*i)))/height,i+height));
			a2.add(5);a2.add(5);
		}

	}
}
