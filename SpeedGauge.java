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
public class SpeedGauge{
	int xCor;
	int yCor;
	int height;
	int width;
	public SpeedGauge(int x,int y){
		xCor = x;
		yCor = y;
		height = 85;
		width = 30;
	}
	public void drawSpeed(Graphics2D g){
		BasicStroke thick = new BasicStroke(2);
		Stroke defStroke = g.getStroke();
		g.setStroke(thick);
		g.drawRect(xCor, yCor, width, height);
	//	g.setStroke(defStroke);
		g.drawRect(xCor+3, yCor+3, width-6, height-6);
	//width of drawable area is 20, height is 75; five in five down
		g.setStroke(defStroke);
		g.setColor(Color.red);
		g.fillRect(xCor+5, yCor+42, 20, 38);
	}
}