package Charactor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GhostImage {
    private BufferedImage image;
    
    public GhostImage(String filePath) {
        try {
            image = ImageIO.read(new File("img/ghost.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
