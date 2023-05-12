package Charactor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CoconutImage {
    private BufferedImage image;
    
    public CoconutImage(String filePath) {
        try {
            image = ImageIO.read(new File("img/coconut.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
