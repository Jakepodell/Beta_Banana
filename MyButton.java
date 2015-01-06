import java.util.Map;
import java.util.HashMap;
import java.awt.geom.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
public class MyButton extends JButton implements ActionListener{

	MyButton(String text) {
		super(text);
//		addActionListener(this);
		setOpaque(false);
		setContentAreaFilled(false);
	}

	public void actionPerformed(ActionEvent e) {
		// button as beeen clicked I change it's color
		setBackground(Color.RED);
	}
	public void paintComponent(Graphics g) {
		Rectangle r = getBounds();
		int x = r.x + 20;
		int y = r.y + 20;
		int width = r.width - 40;
		int height = r.height- 40;
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
		x += 2;
		y += 2;
		width -= 4;
		height -= 4;
		g.setColor(getBackground());
		g.fillOval(x, y, width, height);
		g.setColor(getForeground());
		y += (height / 2) - 10;
		g.drawString(getText(), x, y);
	}
}
