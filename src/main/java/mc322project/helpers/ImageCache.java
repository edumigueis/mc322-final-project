package mc322project.helpers;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

// Design pattern: singleton
public class ImageCache {
    private static ImageCache instance;
    private final Map<String, Image> cache;

    private ImageCache() {
        cache = new HashMap<>();
    }

    public static synchronized ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    public Image getImage(String url) {
        return cache.get(url);
    }

    public void putImage(String url, Image image) {
        cache.put(url, image);
    }

    public boolean containsImage(String url) {
        return cache.containsKey(url);
    }

    public void clearCache() {
        cache.clear();
    }
}
