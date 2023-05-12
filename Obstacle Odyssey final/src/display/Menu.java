package display;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.File;
import Element.Element;
import Element.EleButton;
import Element.EleLabel;

public class Menu extends JPanel {

		private static final long serialVersionUID = 1L;
		public long point;
		
		public Menu() {
			//----
		}
		
		public Menu(long point,ActionListener main) {
			try {
					this.point = point;
					this.setBackground(new Color(241, 98, 69));
					this.setBounds(0,0,1000,600);
					this.setFocusable(true);
					this.setLayout(null);
					
					EleLabel status = new EleLabel("You Died!",40,400,100,200,100);
					status.setForeground(Color.white);
					
					EleLabel showPoint = new EleLabel("Total : "+this.point,30,400,200,200,100);
					showPoint.setForeground(Color.white);
										
					EleButton restart = new EleButton("restart",15,380,300,200,50);
					restart.addActionListener(main);		
					
					this.add(showPoint);
					this.add(status);
					this.add(restart);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		@Override
public void paint(Graphics g) {
        try {
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            this.drawBackground(g2);
			g2.setFont(Element.getFont(10));
			g2.setColor(Color.decode("#ffffff"));
			g2.drawString("TOTAL SCORE ", 400, 40);
			g2.setColor(Color.decode("#21ef80"));
			g2.drawString(" " + point, 550, 40);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
private void drawBackground(Graphics2D g2) throws IOException {
        g2.drawImage(ImageIO.read(new File("img/end2.png")),0,0,1000,600, null);

}
		
}
