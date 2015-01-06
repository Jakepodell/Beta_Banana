/*==================================   COMMENTS   ===========================================
JAKE PODELL
CRAZY BANANA
FINAL PROJECT


//eyeraBeams.connect(g2, fruit.getXChange(), fruit.getYChange());

-------------------------------------- TO DO LIST-------------------------------------------

create unlockable powerups and a store
fix shmocks
eyera beam collisions
laser charge and aesthetics
make everything smaller instead of scaling
work on backgrounds

*/
//==================================   IMPORTS   ==============================================
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.*;
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
import java.awt.geom.*;
import java.awt.font.*;
import javax.swing.border.*;
//============================= CREATE CLASS AND VARIABLES   =====================================

public class DraggyImage extends JApplet implements KeyListener, ActionListener, MouseListener{


	Random ai;



	boolean  xMove, yMove, leftright, updown;
	public enum State {starting, selecting,playing, won, lost, shop}
	State gameState;
	boolean move, gridTime;


	int r,b,gr, lives,l, t, coins, levelRow, levelNumber, levelTime;
	int portalT, coinT, levelT,bliffT, shmockT, miniT, pangaT, pangaGifT,glooshT, slimeT, amyT, eyeraT;


	double time, stepY,stepX;
	double coinFrequency, bliffFrequency, shmockFrequency, pangaFrequency, miniFrequency, glooshFrequency, slimeFrequency, amyFrequency, eyeraFrequency;


	Timer gameTimer;

	Dimension big, square;

	Dimension coinSize, bliffSize, shmockSize, pangaSize,slimeSize,glooshSize; // GET RIDE OF THESE AND PUT THEM IN THE ACTUAL OBJECT

	AudioClip nyan, laser, coinSound, pop;

	BufferedImage banana, heart, coinImage;
	Clock info;

	myCharacter fruit;

	CoinCounter coinCount;

	LifeCounter lifeCount;

	SoundButton sound;                      //FIX THIS

	boolean soundOff;						// GET RIDE OF THESE AND PUT THEM IN THE ACTUAL OBJECT

	Color foreColor, backColor;

	LoadScreen ls;

	WinScreen win;

	MnemonicButton attempt, shopButton, reset;

	JButton goToLevelSelect, nextLevel;

	LevelSelect lev;

	Level currentLevel;

	Shape map;

	Spikes spikes;

	Soup s;

	Shop shop;
	ButtonGroup bg;
	ArrayList<ArrayList<Item>> items;


	ArrayList<Integer> laserNumbers; // Stores indexes for the lasers
	ArrayList<Boolean> shoots;       //Tells whether or not the laser should shoot. MIGHT WANT TO CHANGE TO STATE
	CopyOnWriteArrayList<Coin> ching;

	ArrayList<Image> portalImages, greenPortalImages, redPortalImages, bluePortalImages, tealPortalImages, yellowPortalImages; // GET RIDE OF THESE AND PUT THEM IN THE ACTUAL OBJECT
	CopyOnWriteArrayList<Portal> portals, redPortals, greenPortals, bluePortals, tealPortals, yellowPortals;

	CopyOnWriteArrayList<Bliff> bliffs;
	CopyOnWriteArrayList<Shmock> shmocks;
	CopyOnWriteArrayList<Panga> pangas;
	CopyOnWriteArrayList<Heart> hearts;
	CopyOnWriteArrayList<Pangaxplosion> minis;
	CopyOnWriteArrayList<Gloosh> glooshs;
	CopyOnWriteArrayList<ArrayList<Slime>> slimes;
	CopyOnWriteArrayList<Amy> amys;
	CopyOnWriteArrayList<Eyera> eyeras;
	CopyOnWriteArrayList<? extends SlidingSprite> currentTarget;
	CopyOnWriteArrayList<EyeraBeam> eyeraBeams;
	CopyOnWriteArrayList<AntiBody> antiBodies;

	ArrayList<int[]> xp;
	ArrayList<int[]> yp;


	PotionGrid pgrid;
	Graphics2D g2;
	int soupX;
	JTextPane des;
	RoundButton back;

//=============================   DEFINE VARIABLES ===================================

	public void init(){


/* try {
        BufferedWriter out = new BufferedWriter(new FileWriter("file.txt"));
            for (int i = 0; i < 4; i++) {
                out.write("test " + "\n");
            }
            out.close();
        } catch (IOException e) {}*/




		ai = new Random();
		gameTimer = new Timer(60,this);
		gameTimer.start();
		ls = new LoadScreen();
		win = new WinScreen();
		lev = new LevelSelect();
		shop = new Shop();
		back = new RoundButton(new ImageIcon("return.png"));
		add(back);
		back.setKey(KeyEvent.VK_BACK_SPACE);
		back.addMouseListener(this);
		back.addActionListener(this);
		items = shop.getButtons();
		bg = new ButtonGroup();
		for(ArrayList<Item> buttons: items){
			for(Item item: buttons){
				item.addMouseListener(this);
				item.addActionListener(this);

			}
		}
		for(int i = 0; i<lev.getButtons().size(); i++){
			lev.getButtons().get(i).addActionListener(this);
		}


//		big = new Dimension(1050,300);
		square = new Dimension(900,700);


		stepX=4.0;
		stepY=3.0;

		xMove = true;
		yMove = true;

		nyan = getAudioClip(getDocumentBase(), "Nyan_Cat.wav");
		laser = getAudioClip(getDocumentBase(), "Laser_Blast.wav");
		coinSound = getAudioClip(getDocumentBase(), "ching.wav");
		pop = getAudioClip(getDocumentBase(), "pop.wav");

		bliffFrequency = 1350;
		shmockFrequency = 5000;
		pangaFrequency = 4000;
		miniFrequency = 4000;
		glooshFrequency = 4000;
		slimeFrequency = 4000;
		amyFrequency = 4000;
		eyeraFrequency = 1350;
		coinFrequency = 1000;
		levelTime = 2000;

		bliffT=(int)bliffFrequency/gameTimer.getDelay();
		shmockT=(int)shmockFrequency/gameTimer.getDelay();
		coinT = (int)coinFrequency/gameTimer.getDelay();
		pangaT = (int)pangaFrequency/gameTimer.getDelay();
		miniT = (int)miniFrequency/gameTimer.getDelay();
		glooshT = (int)glooshFrequency/gameTimer.getDelay();
		slimeT = (int)slimeFrequency/gameTimer.getDelay();
		amyT = (int)amyFrequency/gameTimer.getDelay();
		eyeraT = (int)eyeraFrequency/gameTimer.getDelay();


		portals = new CopyOnWriteArrayList<Portal>();;
		redPortals = new CopyOnWriteArrayList<Portal>();;
		greenPortals = new CopyOnWriteArrayList<Portal>();;
		bluePortals = new CopyOnWriteArrayList<Portal>();;
		tealPortals = new CopyOnWriteArrayList<Portal>();;
		yellowPortals = new CopyOnWriteArrayList<Portal>();;

		portalImages = new ArrayList<Image>();
		yellowPortalImages =new ArrayList<Image>();
		tealPortalImages =new ArrayList<Image>();
		bluePortalImages = new ArrayList<Image>();
	    greenPortalImages = new ArrayList<Image>();
		redPortalImages =new ArrayList<Image>();

		laserNumbers = new ArrayList<Integer>();
		shoots = new ArrayList<Boolean>();


		try {
			banana = ImageIO.read(new File("goodBanana.png"));
			heart = ImageIO.read(new File("heart.png"));
	/*		spikeBottom = ImageIO.read(new File("spike1.png"));
			spikeTop = ImageIO.read(new File("spike4.png"));
			spikeLeft = ImageIO.read(new File("spike2.png"));
			spikeRight = ImageIO.read(new File("spike3.png"));
	*/		for(int x = 1; x<5; x++){
				portalImages.add(ImageIO.read(new File("p"+x+".png")));				// THESE SHOULD BE GONE
				redPortalImages.add(ImageIO.read(new File("rp"+x+".png")));
				greenPortalImages.add(ImageIO.read(new File("gp"+x+".png")));
				bluePortalImages.add(ImageIO.read(new File("bp"+x+".png")));
				tealPortalImages.add(ImageIO.read(new File("t"+x+".png")));
				yellowPortalImages.add(ImageIO.read(new File("y"+x+".png")));
			}
			for(int x = 4; x>0; x--){
				portalImages.add(ImageIO.read(new File("p"+x+".png")));
				redPortalImages.add(ImageIO.read(new File("rp"+x+".png")));
				greenPortalImages.add(ImageIO.read(new File("gp"+x+".png")));
				bluePortalImages.add(ImageIO.read(new File("bp"+x+".png")));
				tealPortalImages.add(ImageIO.read(new File("t"+x+".png")));
				yellowPortalImages.add(ImageIO.read(new File("y"+x+".png")));
			}
		}catch(IOException e){
		}

		currentTarget = new CopyOnWriteArrayList<SlidingSprite>();
		ching = new CopyOnWriteArrayList<Coin>();
		bliffs = new CopyOnWriteArrayList<Bliff>();
		shmocks = new CopyOnWriteArrayList<Shmock>();
		pangas = new CopyOnWriteArrayList<Panga>();
		minis = new CopyOnWriteArrayList<Pangaxplosion>();
		hearts = new CopyOnWriteArrayList<Heart>();
		glooshs = new CopyOnWriteArrayList<Gloosh>();
		slimes = new CopyOnWriteArrayList<ArrayList<Slime>>();
		amys = new CopyOnWriteArrayList<Amy>();
		eyeras = new CopyOnWriteArrayList<Eyera>();
		eyeraBeams = new CopyOnWriteArrayList<EyeraBeam>();
		antiBodies = new CopyOnWriteArrayList<AntiBody>();

		coinSize = new Dimension(30,30);			// THESE SHOULD BE GONE
		bliffSize = new Dimension(50,50);
		shmockSize = new Dimension(50,50);
		pangaSize = new Dimension(27,53);
		slimeSize = new Dimension(20,20);
		glooshSize = new Dimension(50,30);

		fruit = new myCharacter(banana, square, 15);
		lives = fruit.getLives();


		attempt = new MnemonicButton("attempt");
		add(attempt);
		attempt.setKey(KeyEvent.VK_ENTER);
		attempt.setKey(KeyEvent.VK_A);
		attempt.addActionListener(this);


		shopButton= new MnemonicButton("shop");
		add(shopButton);
		shopButton.setKey(KeyEvent.VK_S);
		shopButton.addActionListener(this);
		goToLevelSelect = new JButton("Select Level");
		goToLevelSelect.addActionListener(this);
		goToLevelSelect.setBackground(new Color(0,252,178));
		nextLevel = new JButton("Next Level");
		nextLevel.setBackground(new Color(0,252,178));
		nextLevel.addActionListener(this);
		reset = new MnemonicButton("reset");
		add(reset);
		reset.setKey(KeyEvent.VK_R);
		reset.setVisible(false);
		reset.addActionListener(this);

		coinCount = new CoinCounter(380,10,coins);
		lifeCount = new LifeCounter(30,30,lives);
		info = new Clock(square, levelTime, levelNumber);
		sound = new SoundButton();
		sound.addMouseListener(this);



		setContentPane(new drawingPanel());
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.green);
		setSize(square);

		gameState=State.starting;





	}
	public void start(){
	}

//==================================   DRAW SOMETHING   ======================


	public class drawingPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			setLayout(null);
			g2 = (Graphics2D)g;
			if(lives<=0)endLevel();
		//	//("lives = "+lives);
			setBackground(Color.white);



		//	if(lives>0){
				if(gameState==State.starting){	//The begining screen still has ticks left
					add(sound);
					sound.setVisible(true);
					sound.setBounds((int)square.getWidth()-50,(int)square.getHeight()-50,30,30);
					add(attempt);
					attempt.setBounds(100,450,100,40);
					attempt.setVisible(true);
					add(shopButton);
					shopButton.setBounds(175,475,100,40);
					shopButton.setVisible(true);
					ls.drawLoadScreen(g2);
					repaint();

				}else if(gameState==State.shop){

					add(shop);
					shop.drawShop(g2);
					shop.setVisible(true);

					back.setBounds(835,2,55,55);
					back.setVisible(true);
					add(back);

					int bx = 180;
					int by =165;

					for(ArrayList<Item> buttons: items){
						for(Item item: buttons){
							item.setVisible(true);
							item.setBounds(bx,by,65,55);
							add(item);
							bg.add(item);
							by+=85;
							des = item.getDescription();
							des.setBounds(296,540,300,200);
							add(des);
							if(item.getModel().isSelected()){
								item.drawTitle(g2);
								item.drawItemImage(g2);
							}

						}
						bx+=95;
						by=165;
					}

				}else if(gameState==State.selecting){
					lev.setVisible(true);
					add(lev);
					lev.setBounds(0,0,800,600);
					lev.validate();
					back.setBounds(835,2,55,55);
					back.setVisible(true);
					add(back);
				}
				else if(gameState==State.won){
					goToLevelSelect.setBounds(475,225,105,40);
					goToLevelSelect.setVisible(true);
					add(goToLevelSelect);
					nextLevel.setBounds(600,225,105,40);
					nextLevel.setVisible(true);
					add(nextLevel);
					win.drawWinScreen(g2);
				}

//-------   -----------   ----------   ---------   --------   ------------   -----------

				else if(gameState==State.playing){		   //Start Game

//g2.scale(0.5,0.5);

//BOUNDARIES--------------------------------------------------------------------------------------------

					g2.setColor(Color.cyan);
					g2.setStroke(new BasicStroke(5));

					xp = currentLevel.getBoundaryX();
					yp = currentLevel.getBoundaryY();

					if(xp.get(levelNumber-1).length == 1){
						Ellipse2D.Double ellipse = new Ellipse2D.Double(fruit.getXChange(),fruit.getYChange(),xp.get(levelNumber-1)[0],yp.get(levelNumber-1)[0]);
						map = ellipse;
					}

					else{
						GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xp.get(levelNumber-1).length);
						polygon.moveTo(xp.get(levelNumber-1)[0]+fruit.getXChange(),yp.get(levelNumber-1)[0]+fruit.getYChange());
						for(int i=1; i<xp.get(levelNumber-1).length;i++){
							polygon.lineTo(xp.get(levelNumber-1)[i]+fruit.getXChange(),yp.get(levelNumber-1)[i]+fruit.getYChange());
						}
						polygon.closePath();
						map = polygon;
					}
					g2.setColor(Color.red);
					g2.setStroke(new BasicStroke(10));
					g2.draw(map);





					g2.setClip(map);
					g2.drawImage(currentLevel.getBackground(), fruit.getXChange(), fruit.getYChange(), this); // DRAW BACKGROUND
					g2.setClip(null);

					if(fruit.isGhost()){
						if((fruit.getGhostCount())%2==0){
							fruit.drawCharacter(g2);
						}
					}
					else{
						fruit.drawCharacter(g2);
					}

					//-----------------------------------------------DRAW PORTALS AND CHARACTERS
					for(AntiBody ab: antiBodies){
						ab.drawAntiBody(g2);
					}
					if(fruit.isSoupAttacking()){
						s.setBounds(map.getBounds());
						s.drawSoup(g2,fruit.getXChange()+soupX, fruit.getYChange());
						soupX+=20;
					}
					if(soupX+48>=map.getBounds().getWidth()){
						fruit.setSoupAttack(false);
						soupX=0;
					}

					for(int i= 0; i<portals.size(); i++){     //PORTALS
						portals.get(i).drawPortal(g2);
					}

					for(int i= 0; i<redPortals.size(); i++){
						redPortals.get(i).drawPortal(g2);
					}

					for(int i = 0; i<greenPortals.size(); i++){
						greenPortals.get(i).drawPortal(g2);
					}

					for(int i = 0; i<bluePortals.size(); i++){
						bluePortals.get(i).drawPortal(g2);
					}

					for(int i = 0; i<tealPortals.size(); i++){
						tealPortals.get(i).drawPortal(g2);
					}

					for(int i = 0; i<yellowPortals.size(); i++){
						yellowPortals.get(i).drawPortal(g2);
					}


					for(int i = 0; i<ching.size(); i++){     //COINS
						ching.get(i).drawCoin(g2);
					}
					for(Heart h: hearts){
						h.drawHeart(g2);
					}
					for(int i = 0; i<bliffs.size(); i++){     //ENEMIES
						bliffs.get(i).drawBliff(g2);
					}

					for(int i = 0; i<shmocks.size(); i++){
						shmocks.get(i).drawShmock(g2);
					}

					for(int i = 0; i<pangas.size(); i++){
						pangas.get(i).drawPanga(g2);
					}

					for(int i = 0; i<minis.size(); i++){
						minis.get(i).drawPangaxplosion(g2);
					}

					for(int i = 0; i<glooshs.size(); i++){
						glooshs.get(i).drawGloosh(g2);
					}

					for(int i = 0; i<amys.size(); i++){
						amys.get(i).drawAmy(g2);
					}


					if(eyeraBeams.size()>0){
						for(EyeraBeam beam : eyeraBeams){
							if(beam.getRemove())
								eyeraBeams.remove(beam);
							else{
								beam.setChange(fruit.getXChange(), fruit.getYChange());
								beam.drawEyeraBeam(g2);
							}
						}
					}


					for(Eyera e: eyeras){
					if(e.getHealth().equalsIgnoreCase("dead")){
						eyeras.remove(e);
			//			//("killed");
					}
					else if(e.getHealth().equalsIgnoreCase("remove"))
						eyeras.remove(e);
					else{
						e.drawEyera(g2);
			//			//(e.getHealth());
					}}



					g2.setStroke(new BasicStroke(1));
					g2.setColor(Color.black);


					for(int i = 0; i<slimes.size(); i++){ //GLOOSH SLIMES
						for(int ii = 0; ii<slimes.get(i).size();ii++){
							slimes.get(i).get(ii).drawSlime(g2,fruit.getXChange(), fruit.getYChange());
							if(slimes.get(i).get(ii).hitCharacter(fruit)==true){
								fruit.slow();
							}
						}
					}
			//COLLSISION BOXES
					ArrayList<Rectangle> rect = new ArrayList<Rectangle>();
//					for(int i = 0; i<fruit.getCollisionBoxes().size(); i++){
						g2.draw(fruit.getShape());
				//	}

					setBackground(/*new Color(r,gr,b)*/ Color.black);
					fruit.shootLasers(g2,currentTarget);
					fruit.checkSpeed();
					currentTarget= new CopyOnWriteArrayList<SlidingSprite>();

			//DRAW LASER----------------------------------------------------------------------------
					g2.setColor(foreColor);
					g2.setFont(new Font("SansSerif", Font.BOLD, 20));
				//	for(int i =0; i <currentLevel.getLaser().get(levelNumber-1).length; i++){
//						currentLevel.getLaser().get(levelNumber-1)[i].drawLaser(g2,fruit.getXChange(),fruit.getYChange());

				//		float dash1[] = {10.0f};
//
				///		g2.setStroke(new BasicStroke(1.0f,
                  //      BasicStroke.CAP_BUTT,
                  //      BasicStroke.JOIN_MITER,
                  //      10.0f, dash1, 0.0f));

       //                 g2.draw(currentLevel.getLaser().get(levelNumber-1)[i].getBeam());
                 //       g2.setStroke(new BasicStroke(2));
                 for(Laser laz : currentLevel.getLaser().get(levelNumber-1)){
						laz.move();
					//	laz.addCharge();
					//	laz.controlState();
						laz.drawLaser(g2,fruit.getXChange(), fruit.getYChange());
					}

			//SPIKES-----------------------------------------------------------------------

		//			spikes.drawSpikes(g2);

			//PILLARS---------------------------------------------------------------------

					ArrayList<Pillar[]> pill = currentLevel.getPillar();

					for(int i =0; i < pill.get(levelNumber-1).length; i++){
						pill.get(levelNumber-1)[i].drawPillar(g2,fruit.getXChange(),fruit.getYChange());
					}

					g2.draw(currentLevel.getPillar().get(levelNumber-1)[0].getPillar());

			//TOP THINGIES---------------------------------------------------------------------

					g2.setColor(foreColor);

					//g2.draw(currentLevel.getLaser().get(levelNumber-1)[1].getBeam());

					coinCount.drawCoinCounter(g2);
					coinCount.setCoins(coins);

					lifeCount.drawLifeCounter(g2);
					lifeCount.setLives(lives);

					info.setClock(levelTime, levelNumber);
					info.drawClock(g2);

//					lie.drawLaserIcon(g2);

					g2.setColor(new Color(0,0,0,110));

//					if(shootable == false){
//						g2.fillRect(25,725,50,50);
//					}

//					g2.fillRect(25,(int)square.getHeight()-75,50,filmheight);



					//POTION GRID------------------------------------------------------------------------------------------------------

					if(gridTime){
						pgrid.setChange(fruit.getXChange(), fruit.getYChange());
						pgrid.setBoundary(map);
						pgrid.drawGrid(g2);
						pgrid.checkCollision();
						pgrid.checkInside();
				//		for(int i =0; i <currentLevel.getLaser().get(levelNumber-1).length; i++){
					//		currentLevel.getLaser().get(levelNumber-1)[i].move((map.getBounds().getWidth()),((int)(map.getBounds().getHeight())));
					//	}
					}

					//DEFINE FREQUENCIES-------------------------------------------------------------------------------------------------


					//SET PORTAL CHANGES-------------------------------------------------------------------------------------------------------

					for(int x = 0; x<portals.size(); x++){
						portals.get(x).setChange(fruit.getXChange(), fruit.getYChange());
					}

					for(int x = 0; x<greenPortals.size(); x++){
						greenPortals.get(x).setChange(fruit.getXChange(), fruit.getYChange());
					}

					for(int x = 0; x<redPortals.size(); x++){
						redPortals.get(x).setChange(fruit.getXChange(), fruit.getYChange());
					}

					for(int x = 0; x<bluePortals.size(); x++){
						bluePortals.get(x).setChange(fruit.getXChange(), fruit.getYChange());
					}

					for(int x = 0; x<tealPortals.size(); x++){
						tealPortals.get(x).setChange(fruit.getXChange(), fruit.getYChange());
					}

					for(int x = 0; x<yellowPortals.size(); x++){
						yellowPortals.get(x).setChange(fruit.getXChange(), fruit.getYChange());
					}



	/*				for(int i = 0; i<(getWidth()/spikeBottom.getWidth()); i++){
						g2.drawImage(spikeBottom, spikeBottom.getWidth()*i, getHeight()-spikeBottom.getHeight(), this);
						g2.drawImage(spikeTop, spikeTop.getWidth()*i,0, this);
					}
					for(int i = 0; i<(getHeight()/spikeLeft.getHeight()); i++){
						g2.drawImage(spikeLeft, 0 ,spikeLeft.getHeight()*i, this);
						g2.drawImage(spikeRight, getWidth()-spikeRight.getWidth(), spikeRight.getHeight()*i, this);
					}
	/*				if(create == true){
						xplode = new Pangaxplosion(pangas.get(0));
						explode = true;
						pangas.remove(0);
						create = false;
					}
					if(explode ==true){
						xplode.drawPanagaxplosion(g2);
					}
	*/			}
		//	}
//-------- ------- ------- ------- ------ ------ --------- --------- -------- ------- ------ ------
			else if(gameState==State.lost){
				add(reset);
				reset.setBounds(400,325,100,50);
				reset.setVisible(true);
			//	//("reset");
			//	setSizeBig();
				sound.setVisible(false);
			//	gameTimer.stop();
				nyan.stop();
				setBackground(Color.black);
				Font loseFont = new Font("Serif", Font.BOLD, 100);
				g2.setFont(loseFont);
				g2.setColor(Color.red);
				g2.drawString("YOU", 600,300);
				g2.drawString("LOSE", 575,500);
			//	g2.setColor(Color.white);
			//	g2.drawString("You collected "+coins+" coins.", 380,175);
			//	g2.drawString("You lasted "+(int)time+" seconds. Better luck next time", 275,225);
			//	gameState = State.starting;
				fruit.reset();
				g.drawImage(getImage(getDocumentBase(), "LoseScreen.png"),0,0,this);
				repaint();

			}
		}


//====================================   KEY FUNCTIONS ===============================

		}public void keyTyped(KeyEvent e){
		}public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER && reset.isVisible())reset.doClick();
			if(gameTimer.isRunning()==true){
				if(e.getKeyCode()==KeyEvent.VK_UP && fruit.getY()>0){
					move = true;
					yMove = false;
					updown = true;
					leftright = false;
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN&& fruit.getY()<getHeight()-fruit.getHeight()){
					move = true;
					yMove = true;
					updown = true;
					leftright = false;
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT && fruit.getX()>0){
					move = true;
					xMove = false;
					updown = false;
					leftright = true;
					}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT && fruit.getX()<getWidth()-fruit.getWidth()){
					move = true;
					xMove = true;
					updown = false;
					leftright = true;
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_A && bliffs.size()>0 /*&& shootable ==true*/){
				currentTarget = bliffs;
			}
			if(e.getKeyCode()==KeyEvent.VK_S && shmocks.size()>0 /*&& shootable ==true*/){
				currentTarget = shmocks;
			}
			if(e.getKeyCode()==KeyEvent.VK_D && pangas.size()>0 /*&& shootable ==true*/){
				currentTarget = pangas;
			}
			if(e.getKeyCode()==KeyEvent.VK_F && glooshs.size()>0 /*&& shootable ==true*/){
				currentTarget = glooshs;
			}
			if(e.getKeyCode()==KeyEvent.VK_G && amys.size()>0 /*&& shootable ==true*/){
				currentTarget = amys;
			}
			if(e.getKeyCode()==KeyEvent.VK_H && eyeras.size()>0 /*&& shootable ==true*/){
				currentTarget = eyeras;
			}
		}public void keyReleased(KeyEvent e){
		}
//======================================CONTROL SOUND BUTTON====================
		public void mouseClicked(MouseEvent m){
		}public void mousePressed(MouseEvent m){

			if(m.getSource()==back){

				shop.setVisible(false);
				for(ArrayList<Item> buttons: items){
					for(Item item: buttons){
						des=item.getDescription();
						des.setVisible(false);
						item.getModel().setSelected(false);
						item.setSelected(false);
						item.setVisible(false);
						bg.clearSelection();
					}
				}
				back.setVisible(false);
				gameState=State.starting;
				back.setBounds(835,2,55,55);
				lev.setVisible(false);
				repaint();
			}

			if(sound.isSelected()){		//when sound buttton is toggled, loop background
				nyan.loop();
			}
			else{
				nyan.stop();
			}
			sound.setRolloverEnabled(false);  //stop rollover graphics when toggled
			requestFocus(true);               //get back to game

		}public void mouseReleased(MouseEvent m){
		}public void mouseEntered(MouseEvent m){

			if(m.getSource() instanceof Item){
				if(((Item)m.getSource()).getModel().isRollover()){
					((Item)m.getSource()).rollingOver();
				}
			}

			else if(m.getSource()==back)
				back.setBackground(Color.lightGray);

			sound.setRolloverEnabled(true);   //restart rollover graphics
		}
		public void mouseExited(MouseEvent m){
			if(m.getSource() instanceof Item){
				if(!((Item)m.getSource()).getModel().isRollover()){
					((Item)m.getSource()).rollingOut();
				}
			}

			else if(m.getSource()==back)
				back.setBackground(Color.white);
		}
//================================   TIMER FUNCTIONS =================================

		public void repaint(){
		//	super();
	//		//("repaint");
		}
		public static void main(String[] args){
			new DraggyImage();
		}
		public void actionPerformed(ActionEvent e){

			if(e.getSource() instanceof Item){
				repaint();}

			if(e.getSource()==attempt){
				gameState=State.selecting;
				//gameState = State.shop;
				attempt.setVisible(false);
				shopButton.setVisible(false);
				gameTimer.stop();
				repaint();
			}
			if(e.getSource()==shopButton){
				//gameState=State.selecting;
				gameState = State.shop;
				attempt.setVisible(false);
				shopButton.setVisible(false);
				repaint();
			}
			if(e.getSource()==reset){
				gameState=State.starting;
				repaint();
				reset.setVisible(false);
			}

			if(e.getSource()==goToLevelSelect){
				gameState=State.selecting;
				goToLevelSelect.setVisible(false);
				nextLevel.setVisible(false);
			}

			if(e.getSource()==nextLevel){
				levelNumber++;
				startLevel(levelNumber);

			}

			if(e.getSource()==back){

							shop.setVisible(false);
							for(ArrayList<Item> buttons: items){
								for(Item item: buttons){
									des=item.getDescription();
									des.setVisible(false);
									item.getModel().setSelected(false);
									item.setSelected(false);
									item.setVisible(false);
									bg.clearSelection();
								}
							}
							back.setVisible(false);
							gameState=State.starting;
							lev.setVisible(false);
							repaint();
			}


			if(e.getSource()instanceof JButton &&((JButton)e.getSource()).getParent()==lev){
				levelNumber = lev.getButtons().indexOf((JButton)e.getSource())+1;
				startLevel(levelNumber);
			}

			if(e.getSource()==gameTimer && gameState==State.playing){
				t++;
			//	setSize(square);
				time=time+(gameTimer.getDelay()/1000.0); //redefine time
	//FRUIT
				boolean out = false;
				Area ba = new Area(fruit.getShape());

				for(AntiBody ab: fruit.getAntiBodies()){
					antiBodies.add(ab);
				}
				fruit.clearAntiBodies();
				for(AntiBody ab: antiBodies){
					ab.move();
					ab.setChange(fruit.getXChange(), fruit.getYChange());
					for(Pillar pill : currentLevel.getPillar().get(levelNumber-1)){
						if(!map.contains(ab.getRect()) || pill.getPillar().intersects(ab.getRect()))
						antiBodies.remove(ab);
					}
				}



//				for(Rectangle collisionBox : fruit.getCollisionBoxes()){

					for(Pillar pill : currentLevel.getPillar().get(levelNumber-1)){
						ba = new Area(fruit.getShape());
						Area pa = new Area(pill.getPillar());
						ba.intersect(pa);
						if(!ba.isEmpty())out = true;
						//if(!map.contains(collisionBox) || pill.getPillar().intersects(collisionBox)){

					//	}
					}
					Area ma = new Area(map);
					ba = new Area(fruit.getShape());
											ba.subtract(ma);
						if(!ba.isEmpty())out = true;
		//		}
				if(out){fruit.outOfBounds();}

//				for(Rectangle collisionBox : fruit.getCollisionBoxes()){
					int laserAmount = currentLevel.getLaser().get(levelNumber-1).length;
					for(Laser laz : currentLevel.getLaser().get(levelNumber-1)){
						if(laz.getState().equalsIgnoreCase("shooting") && fruit.getShape().intersects(laz.getBeam())){
							fruit.loseLife();
						}
		//				else //(laz.isShooting());
					}
		//		}

				fruit.checkGhost(1500);
				lives = fruit.getLives();
				if(fruit.isGhost()==false ||fruit.getGhostCount()>7)fruit.move(xMove, yMove, leftright, updown, move);
		//		spikes.setChange(fruit.getXChange(), fruit.getYChange());

	//MOVE COINS

				for(Coin c: ching){
					c.move();
					c.setChange(fruit.getXChange(), fruit.getYChange());
					for(Pillar p : currentLevel.getPillar().get(levelNumber-1)){
						if((!map.contains(c.getRect())) || p.getPillar().intersects(c.getRect())){
							c.setRemove();
						}
					}
					if(c.getRemove()==true){
						ching.remove(c);
					}
				}
				for(Coin c: ching){
					if(c.hitCharacter(fruit) && !fruit.isGhost()){
						coins= coins+c.getValue();
						ching.remove(c);
						coinSound.play();
					}
				}

				for(Heart h: hearts){
									h.move();
									h.setChange(fruit.getXChange(), fruit.getYChange());
									for(Pillar p : currentLevel.getPillar().get(levelNumber-1)){
										if((!map.contains(h.getRect())) || p.getPillar().intersects(h.getRect())){
											h.setRemove();
										}
									}
									if(h.getRemove()){
										hearts.remove(h);
									}
								}
								for(Heart h: hearts){
									if(h.hitCharacter(fruit) && !fruit.isGhost()){
										fruit.addLife();
										hearts.remove(h);
										//coinSound.play();
									}
				}

	//CREATE COIN & CHANGE COLOR
				if(t%coinT==0){

					ching.add(new Coin(map,currentLevel.getPillar().get(levelNumber-1), coinSize, fruit.getXChange(), fruit.getYChange()));
					changeColor();

				}

	//CREATE PORTAL
				if(t%bliffT==0){

					if(t%2==0)portals.add(new Portal(map,currentLevel.getPillar().get(levelNumber-1), portalImages, fruit.getXChange(), fruit.getYChange() ));
				}

	//REMOVE PORTAL

				for(Portal p: portals){

					if(t%2==0){

						p.nextState2();

						if(p.getState()>=7){
							portals.remove(p);
						}

						else{

							if(p.getState()==3){
								bliffs.add(new Bliff(p, bliffSize, map, fruit.getXChange(), fruit.getYChange()));  //create bliff
							}

						}

					}
				}
	//MOVE BLIFFS
				for(Bliff b:bliffs){
					if(b.isCoin()){
						b.setRemove();
						ching.add(new Coin(map, coinSize, fruit.getXChange(), fruit.getYChange(), b));
					}
					b.move();
					b.setChange(fruit.getXChange(), fruit.getYChange());
					for(Pillar p: currentLevel.getPillar().get(levelNumber-1)){
						if((!map.contains(b.getRect())) || p.getPillar().intersects(b.getRect())){
							b.setRemove();
						}
					}
				}
				for(Bliff b: bliffs){
					for(Laser laz : currentLevel.getLaser().get(levelNumber-1)){
						if(b.getRect().intersects(laz.getBeam()) && l>=30){
							b.setRemove();
						}
					}
					if(b.getRemove()){
						bliffs.remove(b);
					}
				}
				for(Bliff b: bliffs){
					if(b.hitCharacter(fruit)){
						bliffs.remove(b);
						fruit.loseLife();
					}
				}

	//CREATE REDPORTAL
				if(levelRow>1){
					if(t%shmockT==0){
						redPortals.add(new Portal(map,currentLevel.getPillar().get(levelNumber-1), redPortalImages, fruit.getXChange(), fruit.getYChange()));
					}

	//REMOVE REDPORTAL
					for(Portal red: redPortals){
						if(t%2==0){
							if(red.getState()>=7){
								redPortals.remove(red);
							}
							else{
								if(red.getState()==3){
									shmocks.add(new Shmock(red, shmockSize, map, fruit.getXChange(), fruit.getYChange()));  //create shmock
								}
								red.nextState2();
							}
						}
					}
				}
	// MOVE SHMOCKS
				for(Shmock s:shmocks){
					s.seekCharacter(fruit);
					s.move();
					s.setChange(fruit.getXChange(), fruit.getYChange());
					for(Pillar p: currentLevel.getPillar().get(levelNumber-1)){
						if(!map.contains(s.getRect()) || p.getPillar().intersects(s.getRect())){
							s.setRemove();
						}
					}
				}
				for(Shmock s:shmocks){
					for(Laser laz: currentLevel.getLaser().get(levelNumber-1)){
						if(s.getRect().intersects(laz.getBeam()) && l>=30){
							s.setRemove();
						}
					}
					if(s.getRemove()){
						shmocks.remove(s);
					}
				}
				for(Shmock s: shmocks){
					if(s.hitCharacter(fruit)){
						shmocks.remove(s);
						fruit.loseLife();
					}
				}
	//CREATE GREENPORTAL
				if(levelRow>2){
					if(t%pangaT==0){
						greenPortals.add(new Portal(map,currentLevel.getPillar().get(levelNumber-1), greenPortalImages, fruit.getXChange(), fruit.getYChange()));
					}
	//REMOVE GREENPORTAL
					if(t%2==0){
						for(Portal green: greenPortals){

							if(green.getState()>=7){
								greenPortals.remove(green);
							}
							else{
								if(green.getState()==3){
									pangas.add(new Panga(green, pangaSize, map, fruit.getXChange(), fruit.getYChange()));  //create panga
								}
								green.nextState2();
							}
						}
					}
				}
	// MOVE PANGAS
				for(Panga p : pangas){
					p.move();
					p.setChange(fruit.getXChange(), fruit.getYChange());
					for(Pillar pill: currentLevel.getPillar().get(levelNumber-1)){
						if(!map.contains(p.getRect()) || pill.getPillar().intersects(p.getRect())){
							p.setRemove();
						}
					}
				}
				for(Panga p: pangas){
					for(Laser laz :currentLevel.getLaser().get(levelNumber-1)){
						if(p.getRect().intersects(laz.getBeam()) && laz.getState().equalsIgnoreCase("shooting")){
							p.setRemove();
						}
					}
					if(p.getRemove()){
						pangas.remove(p);
					}
				}
				for(Panga p: pangas){
					if(p.getState()>=9){
						if(p.xToString().equalsIgnoreCase("minis")){
						explodePanga(p);
		//				//("explode");
					}
						else if(p.xToString().equalsIgnoreCase("no")){
			//				//("no");
						}
						else{
							for(Heart h: p.pangaToHearts(square,fruit.getXChange(), fruit.getYChange())){
								hearts.add(h);
							}
							pangas.remove(p);
						}
					}
					else if(t%3==0 && p.xToString().equalsIgnoreCase("minis")){
						p.setState(p.getState()+1);
					}
				}

				for(Panga p: pangas){
					if(p.hitCharacter(fruit)){
						pangas.remove(p);
						fruit.loseLife();
					}
				}
	// MOVE MINI PANGAS
				for(Pangaxplosion ms : minis){
					for(MiniPanga m:ms.getMinis()){
						m.move();
						m.setChange(fruit.getXChange(), fruit.getYChange());
						for(Pillar p: currentLevel.getPillar().get(levelNumber-1)){
							if(!map.contains(m.getRect()) || p.getPillar().intersects(m.getRect())){
								m.setRemove();
							}
						}
					}
					for(MiniPanga m:ms.getMinis()){
						for(Laser laz:currentLevel.getLaser().get(levelNumber-1)){
							if(m.getRect().intersects(laz.getBeam()) && laz.getState().equalsIgnoreCase("shooting")){
								m.setRemove();
							}
						}
						if(m.getRemove()){
							ms.getMinis().remove(m);
						}
					}
					for(MiniPanga m: ms.getMinis()){
						if(m.hitCharacter(fruit)){
							ms.getMinis().remove(m);
							fruit.loseLife();
						}
					}
				}

				/////////////////////////////////////////////////////CONTINUE TO CLEAN USING FOR EACH STATEMENTS
	//CREATE BLUEPORTAL

				if(levelRow>3){
					if(t%glooshT==0){
						bluePortals.add(new Portal(map,currentLevel.getPillar().get(levelNumber-1), bluePortalImages, fruit.getXChange(), fruit.getYChange() ));
					}

	//REMOVE BLUEPORTAL

					for(Portal b: bluePortals){

						if(t%2==0){

							b.nextState2();

							if(b.getState()>=7){
								bluePortals.remove(b);
							}

							else{

								if(b.getState()==3){
									glooshs.add(new Gloosh(b, glooshSize, map, fruit.getXChange(), fruit.getYChange()));
								}

							}

						}
					}
	//MOVE GLOOSHS
					for(Gloosh g: glooshs){
						g.move();

						for(Slime s : g.getTrail()){

							if(s.hitCharacter(fruit)&&!g.getTrailType().equalsIgnoreCase("coin")){
								fruit.slow();
							}

						}

						g.checkSlime();
						g.setChange(fruit.getXChange(), fruit.getYChange());

						for(Pillar p : currentLevel.getPillar().get(levelNumber-1)){

							if(!map.contains(g.getRect()) || p.getPillar().intersects(g.getRect())){
								g.setRemove();
							}

						}
					}

					for(Gloosh g: glooshs){
						for(Laser laz: currentLevel.getLaser().get(levelNumber-1)){
							if(g.getRect().intersects(laz.getBeam()) && laz.getState().equalsIgnoreCase("shooting")){
								g.setRemove();
							}
						}

						if(g.getRemove()){
							if(g.getTrailType().equalsIgnoreCase("slime")){
								for(Slime s: g.getTrail()){
									s.setIcon("slime3.png");
								}
								slimes.add(g.getTrail());
							}
								glooshs.remove(g);
						}
						else if(g.getTrailType().equalsIgnoreCase("coin")&& t%3==0){
							ching.add(new Coin(map, coinSize, fruit.getX(), fruit.getY(), g));
						}
					}

					for(Gloosh g: glooshs){

						if(g.hitCharacter(fruit)&& !g.getTrailType().equalsIgnoreCase("coin")){
						//	g.setRemove();
							fruit.slow();
						}

					}

					for(ArrayList<Slime> ss: slimes){

						if(ss.size()>0){
							ss.remove(0);

						}else{
							slimes.remove(ss);
						}

					}

					for(ArrayList<Slime> ss: slimes){

						for(Slime s: ss){

							if(s.hitCharacter(fruit)){
								fruit.slow();
							}
						}
					}
				}

	//CREATE TEALPORTAL

				if(levelRow>4){

					if(t%amyT==0){
						tealPortals.add(new Portal(map,currentLevel.getPillar().get(levelNumber-1), tealPortalImages, fruit.getXChange(), fruit.getYChange() ));
					}

	//REMOVE TEALPORTAL

					for(Portal p: tealPortals){

						if(t%2==0){
							p.nextState2();

							if(p.getState()>=7){
								tealPortals.remove(p);
							}

							else{

								if(p.getState()==3){
									amys.add(new Amy(p, glooshSize, map, fruit.getXChange(), fruit.getYChange()));
								}
							}
						}
					}

	// MOVE AMYS

					for(Amy a : amys){

						a.move();
						a.setChange(fruit.getXChange(), fruit.getYChange());

						for(Pillar p:  currentLevel.getPillar().get(levelNumber-1)){

							if(!map.contains(a.getRect()) || p.getPillar().intersects(a.getRect())){
								a.setRemove();
							}

						}
					}

					for(Amy a : amys){
						for(AntiBody ab : antiBodies){
							if(ab.getRect().intersects(a.getRect()))
							a.setRemove();
						}
					}

					for(Amy a : amys){

						for(Laser laz: currentLevel.getLaser().get(levelNumber-1)){
							if(a.getRect().intersects(laz.getBeam()) && laz.getState().equalsIgnoreCase("shooting")){
								a.setRemove();
							}
						}

						if(a.getRemove()){
							amys.remove(a);
						}

					}

					for(Amy a : amys){

						if(a.getState()>=26){
							amys.add(new Amy(a,glooshSize,map,1,fruit.getXChange(), fruit.getYChange()));
							amys.add(new Amy(a,glooshSize,map,2,fruit.getXChange(), fruit.getYChange()));
							amys.remove(a);
						}

						else //if(t%3==0){
							{a.setState(a.getState()+1);
						}
					}

					for(Amy a : amys){
						if(a.hitCharacter(fruit)){
							amys.remove(a);
							fruit.loseLife();
						}

					}
					for(Amy a: amys){
						if(fruit.isSoupAttacking()){
							if(a.getRect().intersects(s))
							amys.remove(a);
						}
					}
				}
	//CREATE YELLOWPORTAL

				if(levelRow>5){

					if(t%(eyeraT)==0){
						yellowPortals.add(new Portal(map,currentLevel.getPillar().get(levelNumber-1), yellowPortalImages, fruit.getXChange(), fruit.getYChange() ));
					}

	//REMOVE YELLOWPORTAL

					for(Portal p: yellowPortals){

						if(t%2==0){
							p.nextState2();

							if(p.getState()>=7){
								yellowPortals.remove(p);
							}

							else{
								if(p.getState()==3){
									eyeras.add(new Eyera(p, glooshSize, map, fruit.getXChange(), fruit.getYChange()));

									if(eyeras.size()>0){
										for(Eyera er: eyeras.subList(0,eyeras.size()-1)){
											if(er.getHealth().equalsIgnoreCase("good")){
		/*draw the eyerabeams*/					eyeraBeams.add(new EyeraBeam(er, eyeras.get(eyeras.size()-1), fruit.getXChange(), fruit.getYChange()));
											}
										}
									}
								}
							}
						}
					}
	//MOVE EYERAS

					for(Eyera eye: eyeras){
						eye.move();
						eye.setChange(fruit.getXChange(), fruit.getYChange());

						for(Pillar p: currentLevel.getPillar().get(levelNumber-1)){

							if(!map.contains(eye.getRect())|| p.getPillar().intersects(eye.getRect())){
								eye.setRemove();
							}
						}
					}

					for(Eyera eye: eyeras){

						for(Laser laz : currentLevel.getLaser().get(levelNumber-1)){

							if(eye.getRect().intersects(laz.getBeam()) && laz.getState().equalsIgnoreCase("shooting")){
								eye.setRemove();
							}

						}

						if(eye.getRemove()){
							eyeras.get(eyeras.indexOf(eye)).remove();
						}
					}

					for(Eyera eye: eyeras){

						if(eye.hitCharacter(fruit)&&eye.getHealth().equalsIgnoreCase("good")){
							eyeras.get(eyeras.indexOf(eye)).remove();
							fruit.loseLife();
						}


					}

				//	eyeraBeams.clear();
				//	if(eyeras.size()>0&&eyeras.get(0).getHealth().equalsIgnoreCase("good"))
				//	eyeras.get(0).setHealth("dying");
				//	if(eyeras.size()>1){

				//		for(Eyera e1 : eyeras.subList(0,eyeras.size()-1)){

					//		for(Eyera e2 : eyeras.subList(eyeras.indexOf(e1)+1, eyeras.size())){



						for(EyeraBeam l : eyeraBeams){
							Area eba = new Area(l);
							ba=new Area(fruit.getShape());
							ba.intersect(eba);
//							for(Rectangle r: fruit.getCollisionBoxes()){

								if(!eba.isEmpty()&&l.getHealth().equalsIgnoreCase("good"))fruit.loseLife();
//							}
						}

					}

	// LASER EYES ICON
/*				if(shootable == false && filmheight == 0){
					filmheight = 50;
				}
				else if(shootable == false && filmheight > 1){
					filmheight --;
				}
				else if(shootable == false && filmheight ==1){
					shootable = true;
					filmheight = 0;
				}
	*/// COUNTDOWN AND LASERS
				if(t%20==0){
					levelTime--;
				}
				if(levelTime ==0){
					endLevel();
				}

				for(Laser laz : currentLevel.getLaser().get(levelNumber-1)){
					if(t%laz.getDelay()==0)laz.shoot();
					if(t%laz.getDelay()-2==0)laz.shutOff();
					if(t%laz.getDelay()<=10&&t%laz.getDelay()>=-10)laz.addCharge();
					if(t%laz.getDelay()+5==0)laz.shutOff();
					laz.moveCharges();
					laz.drawCharges(g2);
					//(laz.getState());
					}/*
					laz.setChange(fruit.getXChange(),fruit.getYChange());
					laz.charge();
					if(t%laz.getDelay()==0){
						laz.shoot();
					}

				}/*
				}
				if(currentLevel.getLaser().get(levelNumber-1).length>=0){
				}if(currentLevel.getLaser().get(levelNumber-1).length>=1){
					if(t%100==0){
						shoot2 = true;
					}
				}if(currentLevel.getLaser().get(levelNumber-1).length>=2){
					if(t%100==50){
						shoot3 = true;
					}
				}
				if(l>=30){
					shoot2 = false;
					laser.play();
					l=0;
				}
				if(l2>=30){
					shoot3 = false;
					laser.play();
					l2=0;
				}

				if(shoot2==true){
					l++;
					currentLevel.getLaser().get(levelNumber-1)[0].shoot();
				}
				if(shoot3==true){
					l2++;
					currentLevel.getLaser().get(levelNumber-1)[1].shoot();
				}*/

		}
		repaint();
	}

//=========================================   OTHER METHODS   ==========================================
	public void changeColor(){
		r=ai.nextInt(255);
		b=ai.nextInt(255);
		gr=ai.nextInt(255);
		float[] backHSB = new float[3];
		float[] foreHSB = new float[3];
		Color.RGBtoHSB(r,gr,b,backHSB);
		backColor = new Color(Color.HSBtoRGB(backHSB[0],0.99f,0.99f));
		if(backHSB[0]<0.5){
			foreHSB[0] = backHSB[0]+0.5f;
		}else{
			foreHSB[0] = backHSB[0]-0.5f;
		}
		foreColor = new Color(Color.HSBtoRGB(foreHSB[0],0.99f,0.99f));
		repaint();
	}
	public void setSizeBig(){
		setSize(big);
	}
	public void setT(){
		bliffT=(int)Math.ceil(bliffFrequency/gameTimer.getDelay());
		shmockT=(int)Math.ceil(shmockFrequency/gameTimer.getDelay());
		pangaT=(int)Math.ceil(pangaFrequency/gameTimer.getDelay());
		coinT = /*(int)coinFrequency/gameTimer.getDelay();*/1;
		miniT = (int)Math.ceil(miniFrequency/gameTimer.getDelay());
		glooshT = (int)Math.ceil(miniFrequency/gameTimer.getDelay());
		slimeT = (int)Math.ceil(miniFrequency/gameTimer.getDelay());
		amyT = (int)Math.ceil(miniFrequency/gameTimer.getDelay());
		eyeraT = (int)Math.ceil(miniFrequency/gameTimer.getDelay());
		//("startingT");
	}
	public void explodePanga(Panga p){
		minis.add(new Pangaxplosion(p, fruit.getX(), fruit.getY()));
		pangas.remove(p);
		pop.play();
	}
	public void endLevel(){
		soundOff = sound.isSelected();
		gameTimer.stop();
		t = 0;
		move = false;
		if(lives>0)gameState=State.won;
		else gameState=State.lost;
		fruit.reset();
		lives=fruit.getLives();
//		setSize(new Dimension(800,600));
		sound.setVisible(false);
		nyan.stop();
//		shoot2=false;
//		shoot3=false;
		int chingsize = ching.size();
		int bliffsize = bliffs.size();
		int shmocksize = shmocks.size();
		int pangasize = pangas.size();
		int minisize = minis.size();
		int glooshsize = glooshs.size();
		int amysize = amys.size();
		int eyerasize = eyeras.size();
		int portalsize = portals.size();
		int greenportalsize = greenPortals.size();
		int redportalsize = redPortals.size();
		int blueportalsize = bluePortals.size();
		int tealportalsize = tealPortals.size();
		int yellowportalsize = yellowPortals.size();
		for(int i = 0; i<chingsize; i++){
			ching.remove(0);
		}
		/*for(int i = 0; i<bliffsize; i++){
			bliffs.remove(0);
		}
		for(int i = 0; i<shmocksize; i++){
			shmocks.remove(0);
		}
		for(int i = 0; i<pangasize; i++){
			pangas.remove(0);
		}
		for(int i = 0; i<minisize; i++){
			minis.remove(0);
		}
		for(int i = 0; i<glooshsize; i++){
			glooshs.remove(0);
		}
		for(int i = 0; i<amysize; i++){
			amys.remove(0);
		}
		for(int i = 0; i<eyerasize; i++){
			eyeras.remove(0);
		}
		for(int i = 0; i<portalsize; i++){
			portals.remove(0);
		}
		for(int i = 0; i<greenportalsize; i++){
			greenPortals.remove(0);
		}
		for(int i = 0; i<redportalsize; i++){
			redPortals.remove(0);
		}
		for(int i = 0; i<blueportalsize; i++){
			bluePortals.remove(0);
		}
		for(int i = 0; i<tealportalsize; i++){
			tealPortals.remove(0);
		}
		for(int i = 0; i<yellowportalsize; i++){
					yellowPortals.remove(0);
		}*/
		bliffs.clear();
		shmocks.clear();
		pangas.clear();
		glooshs.clear();
		amys.clear();
		eyeras.clear();
		minis.clear();
		slimes.clear();
		eyeraBeams.clear();
		portals.clear();
		redPortals.clear();
		greenPortals.clear();
		bluePortals.clear();
		yellowPortals.clear();
		tealPortals.clear();
		ching.clear();
		hearts.clear();
		antiBodies.clear();fruit.setSoupAttack(false);
	}
	public void startLevel(int levNum){
			back.setVisible(false);
			levelRow = lev.getRow(lev.getButtons().get(levelNumber-1));

			levelNumber = levNum;currentLevel = new Level(levelNumber);

							levelTime = currentLevel.getLevelTime();

							lev.setVisible(false);
							gameState=State.playing;
							nextLevel.setVisible(false);
							goToLevelSelect.setVisible(false);
							gameState=State.playing;
							gameTimer.start();
							if(sound.isSelected()==false){
								nyan.loop();
							}
					//		changeColor();
							sound.setSelected(soundOff);
							spikes = currentLevel.getSpikes();
							fruit.setStartPoint(currentLevel.getStartPoint());
							xp = currentLevel.getBoundaryX();
							yp = currentLevel.getBoundaryY();
							if(xp.get(levelNumber-1).length == 1){
								Ellipse2D.Double ellipse = new Ellipse2D.Double(fruit.getXChange(),fruit.getYChange(),fruit.getXChange()+xp.get(levelNumber-1)[0],fruit.getYChange()+yp.get(levelNumber-1)[0]);
								map = ellipse;
								s = new Soup(map.getBounds());
	//							//(map.getBounds());
							}
							else{
								GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xp.get(levelNumber-1).length);
								polygon.moveTo(xp.get(levelNumber-1)[0]+fruit.getXChange(),yp.get(levelNumber-1)[0]+fruit.getYChange());
								for(int i=1; i<xp.get(levelNumber-1).length;i++){
									polygon.lineTo(xp.get(levelNumber-1)[i]+fruit.getXChange(),yp.get(levelNumber-1)[i]+fruit.getYChange());
								}
								polygon.closePath();
								map = polygon;
								s = new Soup(map.getBounds());
				//				//(map.getBounds());
							}
							pgrid = new PotionGrid(fruit.getXChange(),fruit.getYChange(),map,currentLevel.getPillar().get(levelNumber-1),fruit);
							gridTime = true;
							laserNumbers.clear();
							for(int index = 0; index< currentLevel.getLaser().get(levelNumber-1).length; index++){
								laserNumbers.add(0);
								shoots.add(false);
				}
	//		double factor = (1.0/(map.getBounds().getHeight()*map.getBounds().getWidth()/480000));
	//		factor=10;


						//				level = "Level "+(levelNumber+1);

										coinFrequency = currentLevel.getCoinFrequency();//*0.5*factor;
										bliffFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
										//(bliffFrequency);
										shmockFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
										pangaFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
										miniFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
										slimeFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
										glooshFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
										amyFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
							eyeraFrequency = currentLevel.getEnemyFrequency()*(.2);//*(.2)*factor;
							setT();
			pgrid = new PotionGrid(fruit.getXChange(),fruit.getYChange(),map,currentLevel.getPillar().get(levelNumber-1),fruit);
			gridTime = true;
			laserNumbers.clear();
			for(int index = 0; index< currentLevel.getLaser().get(levelNumber-1).length; index++){
				laserNumbers.add(0);
				shoots.add(false);
			}
	}

}

//============================================EXTRA==================================================
/*		x=x+(int)stepX;
			y=y+(int)stepY;



//---------------------------------------------   HIT A BOUNDARY  ------------------------------------------

			if(x<=0||x>=getWidth()-imageSize){
				stepX = (-accelX*stepX);        //reverse direction and accelerate
				stepY = (accelY*stepY);
				hits++;
				if(x<0){				// if its to far right
					x=0;
				}
				else if(x>getWidth()-imageSize){//if its to far left
					x = getWidth()-imageSize;
				}
			}
			if(y<=0||y>=getHeight()-imageSize){// see above, apply to y coordinates
				stepY = (-accelY*stepY);
				stepX = (accelX*stepX);
				hits++;
				if(y<0){
					y=0;
				}
				else if(y>getHeight()-imageSize){
					y = getHeight()-imageSize;
				}
			}
timer game timer = 10;
previous timer coin create timer was equal to 50;
int++
if int = 5;

					g2.setFont(new Font("SansSerif", Font.BOLD, 28));
					g2.drawString("Use the Keyboard to move Mr. Banana across the screen and keep ",100,100);
					g2.drawString("him away from the boarders. You have 5 lives. Have fun! :D ", 100,150);
					g2.drawString("Seconds till go-time: "+startTicks, 100, 200);


/*CREATE MINIPANGAS
			if( ((t-((portalT/2)+pangaGifT))%(miniT)==0)&&	//if T- half the t of a portalGifAnimation is an increment of bliffT
			(t>=pangaGifT+miniT) && (pangas.size()>0)){          // and more than blifT + half the t of a portalGifAnimation has passed
				else{
					int q = 0;
					minis.add(new Pangaxplosion(pangas.get(0)));
					pangas.remove(0);										//open that portal
					//("e");
				}
			}
 // CREATE PANGA
			if(levelRow>2){
				if(((t-(portalT/2))%(pangaT)==0)&&	//if T- half the t of a portalGifAnimation is an increment of bliffT
				(t>=(portalT/2)+pangaT)){          // and more than blifT + half the t of a portalGifAnimation has passed
					int q = 0;
					while(greenPortals.get(q).isOpenned()==true){					//find the lowest unoppenned portal
						q++;
					}
					for(int i = 0; i<shmocksPerPortal; i++){						// shoot gifs into it
						pangas.add(new Panga(greenPortals.get(q), pangaSize, square));
					}
					greenPortals.get(q).Open();										//open that portal
				}
			}

//REMOVE REDPORTAL
				if(((t-portalT)%shmockT==0)&&(t>=shmockT+portalT)){
					redPortals.remove(0);
				}
			}
*/
/*CREATE BLIFF

			if(((t-(portalT/2))%(bliffT)==0)&&	//if T- half the t of a portalGifAnimation is an increment of bliffT
			(t>=(portalT/2)+bliffT)){          // and more than blifT + half the t of a portalGifAnimation has passed
				int q = 0;
				while(portals.get(q).isOpenned()==true){					//find the lowest unoppenned portal
					q++;
				}
				for(int i = 0; i<bliffsPerPortal; i++){						// shoot gifs into it
					bliffs.add(new Bliff(portals.get(q), bliffSize, square));
				}
				portals.get(q).Open();										//open that portal
			}

// CREATE SHMOCK
			if(levelRow>1){
				if(((t-(portalT/2))%(shmockT)==0)&&	//if T- half the t of a portalGifAnimation is an increment of bliffT
				(t>=(portalT/2)+shmockT)){          // and more than blifT + half the t of a portalGifAnimation has passed
					int q = 0;
					while(redPortals.get(q).isOpenned()==true){					//find the lowest unoppenned portal
						q++;
					}
					for(int i = 0; i<shmocksPerPortal; i++){						// shoot gifs into it
						shmocks.add(new Shmock(redPortals.get(q), shmockSize, square));
					}
					redPortals.get(q).Open();										//open that portal
				}
			}


				/*			levelRow = lev.getRow(lev.getButtons().get(levelNumber-1));
							//(levelRow);
			//				level = "Level "+(levelNumber+1);
							currentLevel = new Level(levelNumber);
							coinFrequency = currentLevel.getCoinFrequency()*0.5;
							bliffFrequency = currentLevel.getEnemyFrequency()*(.9);
							shmockFrequency = currentLevel.getEnemyFrequency();
							pangaFrequency = currentLevel.getEnemyFrequency()*(3.0/2.0);
							miniFrequency = currentLevel.getEnemyFrequency()*(3.0/2.0);
							slimeFrequency = currentLevel.getEnemyFrequency()*(3.0/2.0);
							glooshFrequency = currentLevel.getEnemyFrequency()*(3.0/2.0);
							amyFrequency = currentLevel.getEnemyFrequency()*(3.0/6.0);
							eyeraFrequency = currentLevel.getEnemyFrequency()*(.9);
							levelTime = currentLevel.getLevelTime();
							setT();
							selectLevel=false;
							congrats = false;
							nextLevel.setVisible(false);
							goToLevelSelect.setVisible(false);
							goTime=true;
							gameTimer.start();
							if(sound.isSelected()==false){
								nyan.loop();
							}
							changeColor();
							sound.setSelected(soundOff);
							spikes = currentLevel.getSpikes();
							fruit.setStartPoint(currentLevel.getStartPoint());
							xp = currentLevel.getBoundaryX();
							yp = currentLevel.getBoundaryY();
							if(xp.get(levelNumber-1).length == 1){
								Ellipse2D.Double ellipse = new Ellipse2D.Double(fruit.getXChange(),fruit.getYChange(),fruit.getXChange()+xp.get(levelNumber-1)[0],fruit.getYChange()+yp.get(levelNumber-1)[0]);
								map = ellipse;
							}
							else{
								GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xp.get(levelNumber-1).length);
								polygon.moveTo(xp.get(levelNumber-1)[0]+fruit.getXChange(),yp.get(levelNumber-1)[0]+fruit.getYChange());
								for(int i=1; i<xp.get(levelNumber-1).length;i++){
									polygon.lineTo(xp.get(levelNumber-1)[i]+fruit.getXChange(),yp.get(levelNumber-1)[i]+fruit.getYChange());
								}
								polygon.closePath();
								map = polygon;
							}
							pgrid = new PotionGrid(fruit.getXChange(),fruit.getYChange(),map,currentLevel.getPillar().get(levelNumber-1),fruit);
							gridTime = true;
							laserNumbers.clear();
							for(int index = 0; index< currentLevel.getLaser().get(levelNumber-1).length; index++){
								laserNumbers.add(0);
								shoots.add(false);
				}


*/

