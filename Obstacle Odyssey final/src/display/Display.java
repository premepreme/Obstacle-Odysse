package display;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Display extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Dimension size = new Dimension(1000,600);
		
	public Display() {
			this.setting();
			this.startgame();
			SoundPlayer soundPlayer = new SoundPlayer();
			soundPlayer.playSound();
	}
	
	private void setting() {
		this.setTitle("Obstacle Odyssey");
		this.setSize(size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(280,100);
		this.setVisible(true);
	}


	private void removeContent() {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();
	}
	public void startgame() {
		removeContent();
		this.getContentPane().add(new Start(this));
		repaint();
	}
	
	
	public void endGame(long point) {
		removeContent();
		this.getContentPane().add(new Menu(point,this));
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("restart")) {
			removeContent();
			Game game = new Game();
			this.getContentPane().add(game);
			game.requestFocus();
		}
		else if(e.getActionCommand().equals("start")) {
			removeContent();
			Game game = new Game();
			this.getContentPane().add(game);
			game.requestFocus();
		}
	}
	
}
