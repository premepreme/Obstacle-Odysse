package display;

public class PointManager {
    private static PointManager instance;
    private int point;

    private PointManager() {
        // Initialize the point
        point = 0;
    }

    public static synchronized PointManager getInstance() {
        if (instance == null) {
            instance = new PointManager();
        }
        return instance;
    }

    public int getPoint() {
        return point;
    }

    public void incrementPoint() {
        point++;
    }

    public int setPoint() {
        return point = 0;
    }
}

