package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Barrier {
    private int speed;
    public int x;
    public int y;
    private Timer timeMove;
    private BarrierImage barrierImage;
    
    public Barrier(int x, int y, int speed, JPanel page, BarrierImageFactory barrierImageFactory) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.barrierImage = BarrierImageFactory.getBarrierImage("img/barriert.png");
        this.move(page);
    }
    
    public void move(JPanel page) {
        this.timeMove = new Timer(speed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (x <= 0) {
                    x = (int) (1000 + (300 + Math.random() * 10000));
                }
                x -= 30;
                page.repaint();
            }
        });
        this.timeMove.start();
    }
    
    public BufferedImage getImage() {
        return barrierImage.getImage();
    }
}
