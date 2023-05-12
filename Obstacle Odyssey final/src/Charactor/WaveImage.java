package Charactor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class WaveImage {
    private BufferedImage image;
    
    public WaveImage(String filePath) {
        try {
            image = ImageIO.read(new File("img/mushroom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
