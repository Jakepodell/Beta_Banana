   //add c to applet
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class gbll extends JApplet{

	public void setCons(GridBagLayout gbl, GridBagConstraints gbc, Component c, int wx, int wy, int row, int column, int width, int height){

		gbc.gridx = column;
		gbc.gridy = row;

		gbc.gridwidth = width;
		gbc.gridheight = height;

		gbc.weightx = wx;
		gbc.weighty = wy;

		gbl.setConstraints(c, gbc);
	}
}