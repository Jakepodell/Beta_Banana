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
public class MnemonicButton extends JButton implements Mnemonic{
	public MnemonicButton(String s){
		super(s);
	}
	public void setKey(int mnemonic){
	KeyStroke keyS = KeyStroke.getKeyStroke(mnemonic, 0, false);

		Action keySAction = new AbstractAction(){

			public void actionPerformed(ActionEvent e){
				if(isVisible())
				doClick();
			}

		};

		if(getParent() instanceof JPanel){
			((JPanel)getParent()).getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyS, ""+mnemonic);
    		((JPanel)getParent()).getRootPane().getActionMap().put(""+mnemonic, keySAction);
		}else ;
		//("please add your Mnemonic button before calling setKey");
	}
	//	addActionListener(this);


	}
//	public void actionPerformed(ActionEvent e){
//		//("HI");
//	}

/*	  public static void main(String[] args) {
	// Create a button with the label "Jackpot".
	    final JButton button = new MnemonicButton("Jackpot");
	    button.setBackground(Color.green);

	// Create a frame in which to show the button.
	    JFrame frame = new JFrame();
	//    frame.getContentPane().setBackground(Color.yellow);
	    frame.getContentPane().add(button);
	    frame.getContentPane().setLayout(new FlowLayout());
	    frame.setSize(250, 250);
	    frame.setVisible(true);

  }
}*/

