package Charactor;
import java.util.HashMap;

public class CoconutImageFactory {
    private static final HashMap<String, CoconutImage> coconutImages = new HashMap<>();

    public static CoconutImage getCoconutImage(String path) {
        CoconutImage coconutImage = coconutImages.get(path);
        if (coconutImage == null) {
            coconutImage = new CoconutImage(path);
            coconutImages.put(path, coconutImage);
        }
        return coconutImage;
    }
}
