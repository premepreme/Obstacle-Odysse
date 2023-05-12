package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Player{
	public int x ;
	public int y;
	public int health=5;
	public int dType;
	public static int KNIGHT = 0,BUFF=1;
	public static int speed=90;
	public static int height=90; //change words
	
	public Player() {
		
	}
	
	public Player(int x,int y, int dType) {
		this.x=x;
		this.y=y;
		this.dType = dType;
	}
	
	 // Fixed time
	 public void jump(JPanel page) {
		this.y -= height;
		page.repaint();
		//--- fall ---
		Timer timer =new Timer(450,new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
		   y += height;
		   page.repaint();
		 }
		});
		timer.setRepeats(false);
		timer.start();
	   }
	  
	   // Double jump
	   public void doubleJump(JPanel page) {
		this.y -= 2*height;
		page.repaint();
		//--- fall ---
		Timer timer =new Timer(450,new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
		   y += 2*height;
		   page.repaint();
		 }
		});
		timer.setRepeats(false);
		timer.start();
	   }

	// public void jump(JPanel page) {
	// 	this.y -= speed;
	// 	page.repaint();
	// 	//--- fall ---
	// 	Timer timer =new Timer(450,new ActionListener() {
	// 		public void actionPerformed(ActionEvent e) {
	// 				y += speed;
	// 				page.repaint();
	// 		}
	// 	});
	// 	timer.setRepeats(false);
	// 	timer.start();
	// }
	
	public BufferedImage getImage(int type_char) {
		BufferedImage image = null;
		try {
			if (type_char == 0) {
				image = ImageIO.read(new File("img/knight.png"));
			}
			if (type_char == 1){
				image = ImageIO.read(new File("img/buff.png"));
			}
			if (type_char == 2){
				image = ImageIO.read(new File("img/slide.png"));
			}
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

}
