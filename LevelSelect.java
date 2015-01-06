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
public class LevelSelect extends JPanel{
	Toolkit tk;
	int count;
	GridBagLayout grid;
	GridBagConstraints gbc;
	gbll gblm;
	ArrayList<JButton> buttons;
	Map<JButton, Integer> buttonMap;
	GradientPaint gp;
	int counter;
	public LevelSelect(int z){
		buttonMap = new HashMap<JButton, Integer>(50);
		count = 0;
		grid = new GridBagLayout();
		gbc = new GridBagConstraints();
		gblm = new gbll();
		setLayout(grid);
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill = GridBagConstraints.BOTH;
		buttons = new ArrayList<JButton>();
		tk = Toolkit.getDefaultToolkit();
		setSize(new Dimension(800,600));
		setBackground(Color.black);
		gp = new GradientPaint(10, 10, Color.red, 110, 50, Color.pink);
		counter=0;
	//	z=500;
		for(int i = 0; i<6; i++){
			for(int x = 0; x<5; x++){
				count++;
				buttons.add(new JButton("Level "+count));
				JButton currentButton = buttons.get(buttons.size()-1);
				currentButton.setFont(new Font("SansSerif", Font.BOLD, 16));
				currentButton.setForeground(Color.magenta);
				currentButton.setBackground(Color.cyan);
				gblm.setCons(grid,gbc,currentButton,1,1,i,x,1,1);
				buttonMap.put(currentButton, i+1);
				add(currentButton);
				if(z<counter)currentButton.setEnabled(false);
				counter++;
			}
		}
		for(int q = 0; q<30; q++){
		}
	}
	public ArrayList<JButton> getButtons(){
		return(buttons);
	}
	public int getLevel(JButton j){
		return(buttons.indexOf(j)+1);
	}
	public int getRow(JButton j){
		return(buttonMap.get(j));
	}

}