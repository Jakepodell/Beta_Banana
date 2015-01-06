import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class BananaLaser{
	myCharacter fruit;
	double oldDistance;
	SlidingSprite closestSprite;
	enum Target{none, bliff, shmock, panga, gloosh, amy, eyera}
	enum bliffMods{kill,coin}
	bliffMods bliffMod;
	enum ShmockMods{slow,repel,kill}
	ShmockMods shmockMod;
	enum PangaMods{infertile, kill, heart}
	PangaMods pangaMod;
	enum GlooshMods{clean, kill, coins}
	GlooshMods glooshMod;
	enum AmyMods{antibiotic, antibody, soup}
	AmyMods amyMod;
	enum EyeraMods{kill,chain}
	EyeraMods eyeraMod;
	Target currentTarget;


	public BananaLaser(myCharacter m){
		fruit=m;
		currentTarget = Target.none;
		bliffMod = bliffMods.coin;
		shmockMod = ShmockMods.repel;
		pangaMod = PangaMods.infertile;
		glooshMod=GlooshMods.coins;
		amyMod=AmyMods.soup;
		eyeraMod=EyeraMods.chain;
	}
	public void drawLaserIcons(Graphics2D g, Weapon[] weapons, JFrame frame){
		g.setColor(Color.gray);
		double height = frame.getHeight();
		int ih = (int)height;
		g.setStroke(new BasicStroke(4));
		g.drawRect(25+2,ih-100+2,50,50);
		g.drawRect(100+2,ih-100+2,50,50);
		g.drawRect(175+2,ih-100+2,50,50);
		g.drawRect(250+2,ih-100+2,50,50);
		g.drawRect(325+2,ih-100+2,50,50);
		g.drawRect(400+2,ih-100+2,50,50);
	//	g.scale(.2,.2);

		WeaponIcon.drawIcon(g,weapons, frame.getHeight());

//		g.scale(5,5);
	}


	public void setBliffLaser(String value){
		bliffMod = bliffMods.valueOf(value);
	}
	public void setShmockLaser(String value){
			shmockMod = ShmockMods.valueOf(value);
	}
	public void setPangaLaser(String value){
			pangaMod = PangaMods.valueOf(value);
	}
	public void setGlooshLaser(String value){
			glooshMod = GlooshMods.valueOf(value);
	}
	public void setAmyLaser(String value){
			amyMod = AmyMods.valueOf(value);
	}
	public void setEyeraLaser(String value){
			eyeraMod = EyeraMods.valueOf(value);
	}

	public void shootLasers(Graphics2D g, CopyOnWriteArrayList<? extends SlidingSprite> c, Weapon[] weapons){
		boolean exists = false;
		if(c.size()>0 && c.get(0) instanceof Bliff && weapons[0]!=null){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.blue);

			for(SlidingSprite sl: c){
				Bliff b = (Bliff)sl;
				Point newBliffPoint = b.getOrigin();
				double newDistance = b.getDistance(fruit.getOrigin(),new Point(b.getX()+fruit.getXChange(),b.getY()+fruit.getYChange()));
				if((newDistance<oldDistance||oldDistance==0)&& !weapons[0].isReloading()){
					oldDistance=newDistance;
					closestSprite=b;
					exists=true;
				}
			}
			if(exists==true){
			Bliff b = (Bliff)closestSprite;
			weapons[0].shoot();
			g.drawLine(fruit.getX()+42, fruit.getY()+57, b.getX()+10+fruit.getXChange(), b.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, b.getX()+10+fruit.getXChange(), b.getY()+10+fruit.getYChange());
			if(bliffMod==bliffMods.coin)
			((Bliff)c.get(c.indexOf(b))).makeCoin();
			else
			c.remove(b);
			oldDistance =0;
			}
			exists = false;
		}

		else if(c.size()>0 && c.get(0) instanceof Shmock&& weapons[1]!=null){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.GREEN);

			for(SlidingSprite sl: c){
				Shmock s = (Shmock)sl;
				Point newShmockPoint = s.getOrigin();
				double newDistance = s.getDistance(fruit.getOrigin(),new Point(s.getX()+fruit.getXChange(),s.getY()+fruit.getYChange()));
				if((newDistance<oldDistance||oldDistance==0)&&s.moveToString().equalsIgnoreCase("normal")&& !weapons[1].isReloading()){
					oldDistance=newDistance;
					closestSprite=s;
					exists=true;
				}
			}
			if(exists==true){
			Shmock s = (Shmock)closestSprite;
			weapons[1].shoot();
			g.drawLine(fruit.getX()+42, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
			g.drawLine(fruit.getX()+65, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
			if(shmockMod==ShmockMods.slow)
			((Shmock)c.get(c.indexOf(s))).slow();
			else if(shmockMod==ShmockMods.repel)
			((Shmock)c.get(c.indexOf(s))).repel();
			else
			c.remove(s);
			oldDistance =0;
			}
			exists = false;
		}

		else if(c.size()>0 && c.get(0) instanceof Panga&& weapons[2]!=null){
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.MAGENTA);

					for(SlidingSprite sl: c){
						Panga s = (Panga)sl;
						Point newShmockPoint = s.getOrigin();
						double newDistance = s.getDistance(fruit.getOrigin(),new Point(s.getX()+fruit.getXChange(),s.getY()+fruit.getYChange()));
						if((newDistance<oldDistance||oldDistance==0)&& s.xToString().equalsIgnoreCase("minis")&& !weapons[2].isReloading()){
							oldDistance=newDistance;
							closestSprite=s;
							exists=true;
						}
					}
					if(exists==true){
					Panga s = (Panga)closestSprite;
					weapons[2].shoot();
					g.drawLine(fruit.getX()+42, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
					g.drawLine(fruit.getX()+65, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
								if(pangaMod==PangaMods.infertile)
										((Panga)c.get(c.indexOf(s))).setExplosion("no");
										else if(pangaMod==PangaMods.heart)
									((Panga)c.get(c.indexOf(s))).setExplosion("hearts");
					else
					c.remove(s);
					oldDistance =0;
			}exists = false;
		}

		else if(c.size()>0 && c.get(0) instanceof Gloosh&& weapons[3]!=null){

					g.setStroke(new BasicStroke(2));
					g.setColor(Color.white);

					for(SlidingSprite sl: c){
						Gloosh s = (Gloosh)sl;
						Point newShmockPoint = s.getOrigin();
						double newDistance = s.getDistance(fruit.getOrigin(),new Point(s.getX()+fruit.getXChange(),s.getY()+fruit.getYChange()));
					//	//("activated");
						if((newDistance<oldDistance||oldDistance==0) && s.getTrailType().equalsIgnoreCase("slime")&& !weapons[3].isReloading()){
							oldDistance=newDistance;
							closestSprite=s;
							exists=true;
						}
					}
					if(exists){
					Gloosh s = (Gloosh)closestSprite;
					weapons[3].shoot();
					g.drawLine(fruit.getX()+42, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
					g.drawLine(fruit.getX()+65, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
					if(glooshMod==GlooshMods.clean)
					((Gloosh)c.get(c.indexOf(s))).setTrailType("none");
					else if(glooshMod==GlooshMods.coins)
					((Gloosh)c.get(c.indexOf(s))).setTrailType("coin");
					else
					c.remove(s);
					oldDistance =0;

			}
			exists = false;
		}

		else if(c.size()>0 && c.get(0) instanceof Amy&& weapons[4]!=null){
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.cyan);

					for(SlidingSprite sl: c){
						Amy s = (Amy)sl;
						Point newShmockPoint = s.getOrigin();
						double newDistance = s.getDistance(fruit.getOrigin(),new Point(s.getX()+fruit.getXChange(),s.getY()+fruit.getYChange()));
						if(newDistance<oldDistance||oldDistance==0&& !weapons[4].isReloading()){
							oldDistance=newDistance;
							closestSprite=s;
						}
					}

					if(amyMod==AmyMods.antibiotic&& weapons[4]!=null){
						Amy s = (Amy)closestSprite;
						weapons[4].shoot();
						g.drawLine(fruit.getX()+42, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
						g.drawLine(fruit.getX()+65, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
						c.remove(s);
						oldDistance =0;
					}
					else if(amyMod==AmyMods.antibody&& weapons[4]!=null&& !weapons[4].isReloading()){
						weapons[4].shoot();
						fruit.makeAntibodies();
						oldDistance=0;

					}
					else if(weapons[4]!=null&& !weapons[4].isReloading()){weapons[4].shoot(); fruit.setSoupAttack(true);}
					oldDistance =0;
				}

		else if(c.size()>0 && c.get(0) instanceof Eyera&& weapons[5]!=null){
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.yellow);
			//		//(" should shootingt");



					for(SlidingSprite sl: c){
						Eyera s = (Eyera)sl;
			//			//(" should shootingt2");
						Point newShmockPoint = s.getOrigin();
						double newDistance = s.getDistance(fruit.getOrigin(),new Point(s.getX()+fruit.getXChange(),s.getY()+fruit.getYChange()));
						if((newDistance<oldDistance||oldDistance==0)&& s.getHealth().equalsIgnoreCase("good")&& !weapons[5].isReloading()){
							oldDistance=newDistance;
							closestSprite=s;
							exists=true;
			//				//(" should shootingt3");
						}
					}
					if(exists){
					Eyera s = (Eyera)closestSprite;
					weapons[5].shoot();
					g.drawLine(fruit.getX()+42, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
					g.drawLine(fruit.getX()+65, fruit.getY()+57, s.getX()+10+fruit.getXChange(), s.getY()+10+fruit.getYChange());
					if(eyeraMod==EyeraMods.chain){
						((Eyera)c.get(c.indexOf(s))).setHealth("dying");
			//			//("shootingt");
					}
					else
					c.remove(s);
					oldDistance =0;
			}
			exists = false;
		}
	}
}
