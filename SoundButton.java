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
public class SoundButton extends JToggleButton{
	public SoundButton(){
		try{
			setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/soundOn.png"))));
			setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getResource("/soundTurnOff.png"))));
			setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/soundOff.png"))));
			setRolloverSelectedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/soundTurnOn.png"))));
		}catch(IOException e){}
		setBackground(new Color(0,0,0,0));
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
	}
}