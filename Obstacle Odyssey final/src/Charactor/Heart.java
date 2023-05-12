package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Heart {
    private int speed;
    public int x;
    public int y;
    private Timer timeMove;
    private HeartImage heartImage;
    
    public Heart(int x, int y, int speed, JPanel page, HeartImageFactory heartImageFactory) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.heartImage = HeartImageFactory.getHeartImage("img/heart.png");
        this.move(page);
    }
    
    public void move(JPanel page) {
        this.timeMove = new Timer(speed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (x <= 0) {
                    x = (int) (1000 + (300 + Math.random() * 1000));
                }
                x -= 30;
                page.repaint();
            }
        });
        this.timeMove.start();
    }
    
    public BufferedImage getImage() {
        return heartImage.getImage();
    }
}
