package Charactor;
import java.util.HashMap;

public class GhostImageFactory {
    private static final HashMap<String, GhostImage> ghostImages = new HashMap<>();

    public static GhostImage getGhostImage(String path) {
        GhostImage ghostImage = ghostImages.get(path);
        if (ghostImage == null) {
            ghostImage = new GhostImage(path);
            ghostImages.put(path, ghostImage);
        }
        return ghostImage;
    }
}