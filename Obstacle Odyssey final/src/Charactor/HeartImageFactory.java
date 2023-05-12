package Charactor;
import java.util.HashMap;

public class HeartImageFactory {
    private static final HashMap<String, HeartImage> heartImages = new HashMap<>();

    public static HeartImage getHeartImage(String path) {
        HeartImage heartImage = heartImages.get(path);
        if (heartImage == null) {
            heartImage = new HeartImage(path);
            heartImages.put(path, heartImage);
        }
        return heartImage;
    }
}