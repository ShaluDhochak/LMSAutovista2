package lms.autovista.com.crossleadmanagement.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class VolleySingleton {

    private static VolleySingleton instance;
    private static ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue (context);

        imageLoader = new ImageLoader (requestQueue, new ImageLoader.ImageCache () {
            private final LruCache<String, Bitmap> cache = new LruCache<String,
                    Bitmap> (20);

            @Override
            public Bitmap getBitmap (String url) {
                return cache.get (url);
            }

            @Override
            public void putBitmap (String url, Bitmap bitmap) {
                cache.put (url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance (Context context) {
        if (instance == null) {
            instance = new VolleySingleton (context);
        }
        return instance;
    }

    public static ImageLoader getImageLoader () {
        return imageLoader;
    }

    public RequestQueue getRequestQueue () {
        return requestQueue;
    }
}
