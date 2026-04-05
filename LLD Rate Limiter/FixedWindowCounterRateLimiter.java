import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounterRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, Window> store;

    public FixedWindowCounterRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.store = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String key) {
        long currentTime = System.currentTimeMillis();
        long currentWindowKey = currentTime / windowSizeInMillis;

        store.compute(key, (k, window) -> {
            if (window == null || window.windowKey != currentWindowKey) {
                return new Window(currentWindowKey, new AtomicInteger(0));
            }
            return window;
        });

        Window window = store.get(key);
        return window.count.incrementAndGet() <= maxRequests;
    }

    private static class Window {
        long windowKey;
        AtomicInteger count;

        Window(long windowKey, AtomicInteger count) {
            this.windowKey = windowKey;
            this.count = count;
        }
    }
}