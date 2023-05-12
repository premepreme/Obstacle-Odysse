package Charactor;
import java.util.HashMap;

public class BarrierImageFactory {
    private static final HashMap<String, BarrierImage> barrierImages = new HashMap<>();

    public static BarrierImage getBarrierImage(String path) {
        BarrierImage barrierImage = barrierImages.get(path);
        if (barrierImage == null) {
            barrierImage = new BarrierImage(path);
            barrierImages.put(path, barrierImage);
        }
        return barrierImage;
    }
}