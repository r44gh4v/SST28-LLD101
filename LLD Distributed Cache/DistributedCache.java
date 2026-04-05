import java.util.List;

public class DistributedCache<K, V> {
    private List<CacheNode<K, V>> cacheNodes;
    private DistributionStrategy<K, V> distributionStrategy;
    private Database<K, V> database;

    public DistributedCache(List<CacheNode<K, V>> cacheNodes, DistributionStrategy<K, V> distributionStrategy, Database<K, V> database) {
        this.cacheNodes = cacheNodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        System.out.println("[Init] Booted Distributed Cache with " + cacheNodes.size() + " nodes.");
    }

    public void put(K key, V value) {
        database.put(key, value);
        CacheNode<K, V> designatedNode = distributionStrategy.route(key, cacheNodes);
        designatedNode.put(key, value);
    }

    public V get(K key) {
        CacheNode<K, V> designatedNode = distributionStrategy.route(key, cacheNodes);
        V value = designatedNode.get(key);

        if (value == null) {
            System.out.println("[DistributedCache] Cache Miss for key: " + key + ". Rerouting to database fallback.");
            value = database.get(key);
            if (value != null) {
                designatedNode.put(key, value);
            }
        } else {
            System.out.println("[DistributedCache] Cache Hit for key: " + key + " exactly on " + designatedNode.getNodeId());
        }

        return value;
    }
}