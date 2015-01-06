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
import javax.swing.border.*;
import javax.imageio.*;
import java.util.Map;
import java.awt.font.*;
import javax.swing.UIManager.*;
import javax.swing.text.*;
import java.awt.geom.*;
public class Item extends JToggleButton{
	String title;
	String image;
	String description;
	int price;
	Font titleFont;
	boolean rollingOver;
	JTextPane descrip;
	Image itemImage;
	Graphics2D g2d;
	Image newImage;
	boolean unlocked;
	public Item(String i, String t, String d, int p){
		image=i;
		title=t;
		description = d;
		price = p;
		unlocked=false;
		try{
			itemImage = ImageIO.read(getClass().getResource(image));
		}catch(IOException e){}

		newImage =itemImage.getScaledInstance(130,110,0);

		setIcon(new ImageIcon(itemImage));
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setContentAreaFilled(false);
		setOpaque(false);
		setBorderPainted(false);
		setRolloverEnabled(true);
		titleFont= new Font("PT Sans", Font.BOLD, 24);


			descrip = new JTextPane();
			descrip.setLayout(new GridLayout());
			descrip.setOpaque(false);
			descrip.setVisible(false);
			descrip.setEditable(false);

			StyledDocument doc = descrip.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();


			try{
				doc.insertString(0,description,center);
			}catch(BadLocationException ble){}

			sabm jefe = new sabm();
			jefe.setFont(center, "Serif", 16);
			jefe.setColor(center, Color.black, false);
			jefe.setStyle(center, true, false, false);
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			descrip.setParagraphAttributes(center,true);
	}
/*	public Item(){
		setVisible(true);
				setBackground(new Color(0,0,0,0));
				setContentAreaFilled(false);
				setOpaque(false);
				setBorderPainted(false);
				setRolloverEnabled(true);
				titleFont= new Font("PT Sans", Font.BOLD, 24);
		setEnabled(false);
	}*/
	public void unlock(){
		unlocked=true;
		File f = new File("weapons.jake");
				try
				{
					FileWriter fstream = new FileWriter(f,true);
					BufferedWriter bout = new BufferedWriter(fstream);
							bout.write(title+"\r\n");

					bout.close();
				}
				catch (Exception x)
				{
					//System.err.println(e);
					System.err.println("Could not write to file");
		}
	}
	public void unlock(boolean notTrue){
		unlocked=true;
	}
	public boolean isLocked(){
		return(!unlocked);
	}
	public String getTitle(){
		return(title);
	}
	public int getPrice(){
		return(price);
	}
	public void drawItemImage(Graphics2D g){
		//g.scale(2,2);
		g.drawImage(newImage, 90,500,null);
		//g.scale(0.5,0.5);
	}
	 @Override
        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(getModel().isSelected()){
				setBorderPainted(true);
				setBorder(new LineBorder(Color.WHITE, 5));
				descrip.setVisible(true);
			}
			else if(rollingOver){
				setBorderPainted(true);
				setBorder(new LineBorder(Color.WHITE, 2));
				//("shudda worls");
				descrip.setVisible(false);
			}

			else {
				setBorderPainted(false);
				descrip.setVisible(false);
			}
		}
		public void drawTitle(Graphics g){
			g.setColor(Color.black);

			Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
			fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			g.setFont(new Font("Serif",Font.BOLD,28).deriveFont(fontAttributes));
			g.drawString(title,448-(g.getFontMetrics().stringWidth(title))/2,530);
	//		g.drawRect(300,510-g.getFontMetrics().getHeight(),g.getFontMetrics().stringWidth(title),g.getFontMetrics().getHeight());
	//		//(g.getFontMetrics().stringWidth(title));
		}
		public JTextPane getDescription(){


			return(descrip);
		}

		public void rollingOver(){
			rollingOver=true;
		}
		public void rollingOut(){
			rollingOver=false;
		}
}