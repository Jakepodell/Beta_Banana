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
public class InvincibleGauge{
	int xCor;
	int yCor;
	int height;
	int width;
	double yRect;
	int xRect;
	double hRect;
	int time;
	public InvincibleGauge(int x,int y, int time2){
		xCor = x;
		yCor = y;
		height = 85;
		hRect = 75;
		width = 30;
		xRect = xCor+5;
		yRect = yCor+5;
		time = time2;
	}
	public void drawInvinGauge(Graphics2D g){
		BasicStroke thick = new BasicStroke(2);
		Stroke defStroke = g.getStroke();
		g.setStroke(thick);
		g.drawRect(xCor, yCor, width, height);
		g.drawRect(xCor+3, yCor+3, width-6, height-6);
	//width of drawable area is 20, height is 75; five in five down
		g.setStroke(defStroke);
		g.setColor(Color.red);
		g.fillRect(xRect, (int)yRect, 20, (int)hRect);
		g.setFont(new Font("SansSerif", Font.BOLD, 18));
		g.drawString("GHOST MODE", xCor-42, yCor+108);
	}
	public void checkInvinGauge(){
		if(hRect < 1){
			hRect=75;
			yRect=yCor+5;
		}
		double step = 75.0/(((double)time)/50.0);
		yRect = yRect + step;
		hRect = hRect - step;
	}

}