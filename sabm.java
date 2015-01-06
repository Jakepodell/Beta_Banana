//This file is a Simple Attribute Set Manager
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
public class sabm extends JApplet{

	public void setFont(MutableAttributeSet a, String family, int s){ //Sets the Font Family and Size
		StyleConstants.setFontFamily(a,family);
		StyleConstants.setFontSize(a,s);
	}
	public void setFont(MutableAttributeSet a, String family){ //When only the family is to be changed
			StyleConstants.setFontFamily(a,family);
	}
	public void setFont(MutableAttributeSet a, int s){ //When only the size is to be changed
				StyleConstants.setFontSize(a,s);
	}


	public void setColor(MutableAttributeSet a, Color f, Color b){ //Changes the Colors, f being Foreground and b being Background
		StyleConstants.setForeground(a,f);
		StyleConstants.setBackground(a,b);
	}
	public void setColor(MutableAttributeSet a, Color c, boolean b){ //When only one Color is to be changed. Boolean is whether or not to apply color to background
		if(b==true){
			StyleConstants.setBackground(a,c);
		}
		else if(b==false){
			StyleConstants.setForeground(a,c);
		}
	}


	public void setStyle(MutableAttributeSet a, boolean b, boolean i, boolean u){ //Sets whether or not texts should be bold, italic, and/or underlined.
		StyleConstants.setBold(a,b);
		StyleConstants.setItalic(a,i);
		StyleConstants.setUnderline(a,u);
	}



}


/*

static void setStrikeThrough(MutableAttributeSet a, boolean b)
static void setSubscript(MutableAttributeSet a, boolean b)
static void setSuperscript(MutableAttributeSet a, boolean b)

setAlignment(MutableAttributeSet a, int align)

static void setFirstLineIndent(MutableAttributeSet a, float i)
static void setLeftIndent(MutableAttributeSet a, float i)
static void setRightIndent(MutableAttributeSet a, float i)

static void setLineSpacing(MutableAttributeSet a, float i)
static void setSpaceAbove(MutableAttributeSet a, float i)
static void setSpaceBelow(MutableAttributeSet a, float i)
*/
