
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.Enumeration;
import java.util.Random;
import javax.swing.border.*;
public class WeaponSelect extends JPanel{
	JPanel bp,sp,pp,gp,ap,ep;
	Weapon b1,b2,s1,s2,s3,p1,p2,p3,g1,g2,g3,a1,a2,a3,e1,e2;
	Toolkit tk;
	ButtonGroup bb,sb,pb,gb,ab,eb;
	ArrayList<ButtonGroup> bgs;
	JPanel cont;
	JPanel top;
	JLabel words;
	boolean[] bools;
	public WeaponSelect(ArrayList<Item> items){

		bools = new boolean[16];
		int ind = 0;
		for(Item item:items){
			bools[ind]=!item.isLocked();
			ind++;
		}

		top = new JPanel();
		words = new JLabel("SELECT YOUR WEAPON");
		words.setForeground(Color.white);
		words.setFont(new Font("Serif", Font.BOLD, 24));
		top.add(words);

		cont = new JPanel();
		cont.setSize(new Dimension(900,700));


	//	setVisible(true);

		tk = Toolkit.getDefaultToolkit();

		bgs= new ArrayList<ButtonGroup>();

		b1 = new Weapon(new ImageIcon(tk.getImage("fishLaser.png")),"kill",10,bools[0]);//,Weapon.BLIFF);
		b2 = new Weapon(new ImageIcon(tk.getImage("fishCoin.png")),"coin",10,bools[1]);//,Weapon.BLIFF);

		s1 = new Weapon(new ImageIcon(tk.getImage("shmockLaser.png")),"slow",10,bools[2]);//,Weapon.SHMOCK);
		s2 = new Weapon(new ImageIcon(tk.getImage("batteryReverse.png")),"repel",10,bools[3]);//,Weapon.SHMOCK);
		s3 = new Weapon(new ImageIcon(tk.getImage("mushCloud.png")),"kill",10,bools[4]);//,Weapon.SHMOCK);

		p1 = new Weapon(new ImageIcon(tk.getImage("pangaLaser.png")),"infertile",10,bools[5]);//,Weapon.PANGA);
		p2 = new Weapon(new ImageIcon(tk.getImage("noPanga.png")),"kill",10,bools[6]);//,Weapon.PANGA);
		p3 = new Weapon(new ImageIcon(tk.getImage("shopHeart.png")),"heart",10,bools[7]);//,Weapon.PANGA);

		g1 = new Weapon(new ImageIcon(tk.getImage("glooshLaser.png")),"kill",10,bools[8]);//,Weapon.GLOOSH);
		g2 = new Weapon(new ImageIcon(tk.getImage("soap.png")),"clean",10,bools[9]);//,Weapon.GLOOSH);
		g3 = new Weapon(new ImageIcon(tk.getImage("manyCoins.png")),"coins",10,bools[10]);//,Weapon.GLOOSH);

		a1 = new Weapon(new ImageIcon(tk.getImage("amyLaser.png")),"antibiotic",10,bools[11]);//,Weapon.AMY);
		a2 = new Weapon(new ImageIcon(tk.getImage("storeY.png")),"antibody",10,bools[12]);//,Weapon.AMY);
		a3 = new Weapon(new ImageIcon(tk.getImage("storeSoup.png")),"soup",10,bools[13]);//,Weapon.AMY);

		e1 = new Weapon(new ImageIcon(tk.getImage("eyeraLaser.png")),"kill",10,bools[14]);//,Weapon.EYERA);
		e2 = new Weapon(new ImageIcon(tk.getImage("skull.png")),"chain",10,bools[15]);//,Weapon.EYERA);

		bp=new JPanel();
		sp=new JPanel();
		pp=new JPanel();
		gp=new JPanel();
		ap=new JPanel();
		ep=new JPanel();

		bp.setLayout(new GridLayout(3,1,10,10));
		sp.setLayout(new GridLayout(3,1,10,10));
		pp.setLayout(new GridLayout(3,1,10,10));
		gp.setLayout(new GridLayout(3,1,10,10));
		ap.setLayout(new GridLayout(3,1,10,10));
		ep.setLayout(new GridLayout(3,1,10,10));

		bb=new ButtonGroup();
		sb=new ButtonGroup();
		pb=new ButtonGroup();
		gb=new ButtonGroup();
		ab=new ButtonGroup();
		eb=new ButtonGroup();

		bb.add(b1);
		bb.add(b2);

		sb.add(s1);
		sb.add(s2);
		sb.add(s3);

		pb.add(p1);
		pb.add(p2);
		pb.add(p3);

		gb.add(g1);
		gb.add(g2);
		gb.add(g3);

		ab.add(a1);
		ab.add(a2);
		ab.add(a3);

		eb.add(e1);
		eb.add(e2);

		bgs.add(bb);
		bgs.add(sb);
		bgs.add(pb);
		bgs.add(gb);
		bgs.add(ab);
		bgs.add(eb);

		cont.setLayout(new GridLayout(1,6,20,20));
	//	setSize(new Dimension(900,700));
		setLayout(new BorderLayout(10,10));
		add(cont, BorderLayout.CENTER);
		add(top,  BorderLayout.NORTH);
		cont.setBackground(Color.white);
		top.setBackground(new Color(153,153,153));
		top.setBorder(new LineBorder(Color.black, 3));
	//	getContentPane().
		setBackground(Color.white);

	//	for(ButtonGroup bg: bgs){
	//		for(Enumeration<AbstractButton> e= bg.getElements(); e.hasMoreElements();){
	//			Weapon butt = (Weapon)e.nextElement();
	//			butt.setDisabledIcon(new ImageIcon(tk.getImage("qmark.png")));
		//		add(butt);
		//
		//	}
	//	}

		JPanel[] ps = {bp,sp,pp,gp,ap,ep,ep};
		System.out.println(ps[5]);

		int i = 0;
		for(ButtonGroup bg: bgs){
			for(Enumeration<AbstractButton> e= bg.getElements(); e.hasMoreElements();){
				Weapon butt = (Weapon)e.nextElement();
				ps[i].add(butt);
				butt.setDisabledIcon(new ImageIcon(tk.getImage("qmark2.png")));
				int x = (new Random()).nextInt(2);
				if(x==0)butt.setEnabled(false);
				if(butt.isEnabled())butt.setSelected(true);
				UIManager.put("ToggleButton.background", new Color(204,204,204));
				UIManager.put("ToggleButton.select", new Color(51,153,102));
				SwingUtilities.updateComponentTreeUI(butt);
			//	butt.setBorder(new LineBorder(Color.black, 3));
				butt.setFocusPainted(false);
			}
			ps[i].setBackground(Color.white);
			cont.add(ps[i]);
			i++;
		}
	/*	Weapon[] weapons = getWeapons();
		for(int x =0; x<weapons.length; x++){
			System.out.println(weapons[x].getDescription());
		}*/


	}
	public void drawWeaponSelect(Graphics2D g){
		g.drawImage(tk.getImage("selectPanel"),0,0,null);
	}
//	public static void main(String[] args){
//		new WeaponSelect();
//	}

	public Weapon[] getWeapons(){
		int i =0;
		Weapon[] weapons = new Weapon[6];
		for(ButtonGroup bg: bgs){
			for(Enumeration<AbstractButton> e= bg.getElements(); e.hasMoreElements();){
				Weapon butt = (Weapon)e.nextElement();
				if(butt.isSelected())weapons[i]=butt;
			}
			i++;
		}
		return(weapons);
	}
}