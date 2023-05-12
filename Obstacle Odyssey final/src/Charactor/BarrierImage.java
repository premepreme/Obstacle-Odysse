package Charactor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BarrierImage {
    private BufferedImage image;
    
    public BarrierImage(String filePath) {
        try {
            image = ImageIO.read(new File("img/barrier.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
