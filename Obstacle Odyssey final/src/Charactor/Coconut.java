package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Coconut {
        public int speed;
        public int x;
        public int y;
        Timer timeMove;
        private CoconutImage coconutImage;

        public Coconut(int x,int y,int speed,JPanel page, CoconutImageFactory coconutImageFactory) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.coconutImage = CoconutImageFactory.getCoconutImage("img/coconut.png");
            this.move(page);
        }

        public void move(JPanel page) {
            this.timeMove = new Timer(speed, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (x <= 0) {
                        x = (int) (2000 + (300 + Math.random() * 2000));
                    }
                    x -= 30;
                    page.repaint();
                }
            });
            this.timeMove.start();
        }

        public BufferedImage getImage() {
            return coconutImage.getImage();
        }
}
