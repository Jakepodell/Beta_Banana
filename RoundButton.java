import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class RoundButton extends JButton implements Mnemonic{
	boolean rollingOver;
	  public RoundButton(Icon label) {

	 	super(label);

	    Dimension size = getPreferredSize();
	    size.width = size.height = Math.max(size.width, size.height);
	    setPreferredSize(size);
	    setContentAreaFilled(false);
		setBackground(Color.white);
	 }


	 public void rollingOver(){
	 	rollingOver=true;
	 }
	 public void rollingOut(){
	 	rollingOver=false;
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
		}else; //("please add your Mnemonic button before calling setKey");
	}

  protected void paintComponent(Graphics g) {
 /*   if (getModel().isRollover()) {
      g.setColor(Color.lightGray);
    } else {
      g.setColor(getBackground());
    }*/
    g.setColor(getBackground());
    g.fillOval(0, 0, getSize().width-2, getSize().height-2);
    super.paintComponent(g);
  }

// Paint the border of the button using a simple stroke.
  protected void paintBorder(Graphics g) {
    g.setColor(getForeground());
    g.drawOval(0, 0, getSize().width-1, getSize().height-1);
  }

/* Hit detection.
  Shape shape;
  public boolean contains(int x, int y) {
// If the button has changed size,
   // make a new shape object.
    if (shape == null ||
      !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0,
        getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }

// Test routine.
  public static void main(String[] args) {
// Create a button with the label "Jackpot".
    JButton button = new RoundButton("Jackpot");
    button.setBackground(Color.green);

// Create a frame in which to show the button.
    JFrame frame = new JFrame();
//    frame.getContentPane().setBackground(Color.yellow);
    frame.getContentPane().add(button);
    frame.getContentPane().setLayout(new FlowLayout());
    frame.setSize(250, 250);
    frame.setVisible(true);
  }*/
}