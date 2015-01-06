
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Writing extends JFrame implements KeyListener{
	int clicks;
	JLabel l;
	BananaApplication ba;
	public static void main(String[] args){
		new BananaApplication();
//		new Writing();

	}
	public Writing()
	{
		setVisible(true);
		setFocusable(true);
		addKeyListener(this);
	}
	public void keyPressed(KeyEvent k){
		clicks++;
		File f = new File("clicks.txt");
		try
		  	 		{
						//("worked");
		  	 		 	FileWriter fstream = new FileWriter(f);
		  	 	     	BufferedWriter out = new BufferedWriter(fstream);
								out.write("clicks = "+clicks);

						out.close();
		  	 		}
		  	 		catch (Exception e)
		  	 		{
						//System.err.println(e);
		     			System.err.println("Could not write to file");
		 	}

	}
	public void keyReleased(KeyEvent k){
	}
	public void keyTyped(KeyEvent k){
	}
}