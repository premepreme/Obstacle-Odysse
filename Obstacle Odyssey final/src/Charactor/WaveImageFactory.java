package Charactor;
import java.util.HashMap;

public class WaveImageFactory {
    private static final HashMap<String, WaveImage> waveImages = new HashMap<>();

    public static WaveImage getWaveImage(String path) {
        WaveImage waveImage = waveImages.get(path);
        if (waveImage == null) {
            waveImage = new WaveImage(path);
            waveImages.put(path, waveImage);
        }
        return waveImage;
    }
}