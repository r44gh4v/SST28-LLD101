import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, Deque<Long>> store;

    public SlidingWindowCounterRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.store = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String key) {
        long currentTime = System.currentTimeMillis();
        store.putIfAbsent(key, new LinkedList<>());

        Deque<Long> timestamps = store.get(key);
        synchronized (timestamps) {
            while (!timestamps.isEmpty() && currentTime - timestamps.peekFirst() >= windowSizeInMillis) {
                timestamps.pollFirst();
            }
            if (timestamps.size() < maxRequests) {
                timestamps.addLast(currentTime);
                return true;
            }
            return false;
        }
    }
}