package display;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.File;

import Element.EleButton;
import Element.EleLabel;

public class Start extends JPanel {

		private static final long serialVersionUID = 1L;
		public long point;


		
		public Start() {
			//----
		}
		
		public Start(ActionListener main) {
			try {   
				this.setBackground(new Color(241, 98, 69));
				this.setBounds(0,0,1000,600);
				this.setFocusable(true);
				this.setLayout(null);
				
				EleLabel status = new EleLabel(" ",40,400,100,200,100);
				status.setForeground(Color.white);
				
				EleLabel showPoint = new EleLabel(" ",30,400,200,200,100);
				showPoint.setForeground(Color.white);
				
				EleButton restart = new EleButton("start",15,380,300,200,50);
				restart.addActionListener(main);		
				
				this.add(showPoint);
				this.add(status);
				this.add(restart);

			this.paintComponent(this.getGraphics());
		} catch (Exception e) {
		
}}
	@Override
		public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		try {
			this.drawBackground(g2);
		} catch (IOException e) {
			e.printStackTrace();
		}

}

private void drawBackground(Graphics2D g2) throws IOException {
	g2.drawImage(ImageIO.read(new File("img/bg.png")), 0, 0, 1000, 600, null);
}
}
        