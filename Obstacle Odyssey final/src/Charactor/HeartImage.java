package Charactor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HeartImage {
    private BufferedImage image;
    
    public HeartImage(String filePath) {
        try {
            image = ImageIO.read(new File("img/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
