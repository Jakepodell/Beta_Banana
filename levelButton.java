import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class levelButton extends JApplet implements MouseListener{
	JButton butt;
	GradientPaint gp;
	MyButton hello;
	public void init(){
		butt = new JButton("hi");
		butt.setOpaque(false);
	butt.setContentAreaFilled(false);

//	addMouseListener(this);
hello = new MyButton("hello");hello.addMouseListener(this);
Color blue = new Color(0,0,200);
		setContentPane(new drawingPanel());
		setFocusable(true);
		setSize(401,201);
		gp = new GradientPaint(0, 0, blue, 200, 100, Color.cyan);
	}
	public class drawingPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
	//		butt.setSize(200,100);
	hello.setBounds(0,0,400,200);
	super.paintComponent(g);add(new MyButton("hello"));
			setLayout(new GridLayout(1,1));
			Graphics2D g2 = (Graphics2D)g;
			g2.setPaint(gp);
			Color darkblue = new Color(0,0,150);
			Color darkcyan = new Color(110, 180, 200);
			Color blue = new Color(0,0,200);
			if(!hello.isSelected()){ gp = new GradientPaint(0, 0, blue, 200, 100, Color.cyan);}
			if(hello.isSelected()){ gp = new GradientPaint(0, 0, darkblue, 200, 100, darkcyan);}
	//		add(butt);
			g2.fillRect(0,0,400,200);
add(hello);
			repaint();

		}
	}
	public void mouseClicked(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mousePressed(MouseEvent e){
		hello.setSelected(true);
		repaint();
	}
	public void mouseExited(MouseEvent e){
	}
	public void mouseReleased(MouseEvent e){
		hello.setSelected(false);
		repaint();

	}
}