import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {
    private String nodeId;
    private int capacity;
    private Map<K, V> cacheData;
    private EvictionPolicy<K> evictionPolicy;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<K> evictionPolicy) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.cacheData = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public V get(K key) {
        if (cacheData.containsKey(key)) {
            evictionPolicy.keyAccessed(key);
            return cacheData.get(key);
        }
        return null;
    }

    public void put(K key, V value) {
        if (cacheData.containsKey(key)) {
            cacheData.put(key, value);
            evictionPolicy.keyAccessed(key);
        } else {
            if (cacheData.size() >= capacity) {
                K evictedKey = evictionPolicy.evictKey();
                if (evictedKey != null) {
                    System.out.println("[CacheNode " + nodeId + "] Capacity reached. Evicted LRU key: " + evictedKey);
                    cacheData.remove(evictedKey);
                }
            }
            cacheData.put(key, value);
            evictionPolicy.keyAccessed(key);
        }
        System.out.println("[CacheNode " + nodeId + "] Stored key: " + key);
    }

    public String getNodeId() {
        return nodeId;
    }
}