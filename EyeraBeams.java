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
import java.awt.geom.*;
public class EyeraBeams{
	CopyOnWriteArrayList<Line2D.Double> beams;
	CopyOnWriteArrayList<Eyera> eyeras;
	public EyeraBeams(CopyOnWriteArrayList<Eyera> e){
		beams = new CopyOnWriteArrayList<Line2D.Double>();
		eyeras=e;
	}
	public void connect(Graphics2D g2, int xChange, int yChange){



		for(int i = 0; i<eyeras.size()-1; i++){

			for(int ii = i+1; ii<eyeras.size(); ii++){
				beams.add(new Line2D.Double((double)(eyeras.get(i).getOrigin().getX()+25)+xChange,(double)(eyeras.get(i).getOrigin().getY()+17)+yChange,(double)(eyeras.get(ii).getOrigin().getX()+25)+xChange,(double)(eyeras.get(ii).getOrigin().getY()+17)+yChange));
			}

		}

		g2.setStroke(new BasicStroke(3));

		for(Line2D.Double beam : beams){

			GradientPaint fading = new GradientPaint((int)beam.getX1(),(int)beam.getY1(),Color.RED,(int)(beam.getX1()+((beam.getX2()-beam.getX1())/2)),(int) (beam.getY1()+((beam.getY2()-beam.getY1())/2)),new Color(255,0,0,255));
			g2.setPaint(fading);
			g2.draw(beam);

		}
		beams.clear();
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.black);
	}
}
