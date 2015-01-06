import java.util.Random;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.applet.*;
public class Pangaxplosion{
	CopyOnWriteArrayList<MiniPanga> minis;
//	CopyOnWriteArrayList pangaArray;
	Panga p;
	public Pangaxplosion(Panga p2, int xChange2, int yChange2){
		p = p2;
		minis = new CopyOnWriteArrayList<MiniPanga>();
		for(int i = 1; i<7; i++){
			minis.add(new MiniPanga(p, new Dimension(13,18), new Dimension(1000,800), i, xChange2, yChange2));
		}
	}
	public void drawPangaxplosion(Graphics2D g){
		for(int i = 0; i<minis.size(); i++){
			minis.get(i).drawMiniPanga(g);
		}
	}
	public CopyOnWriteArrayList<MiniPanga> getMinis(){
		return(minis);
	}
}