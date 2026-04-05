import java.util.List;

public interface DistributionStrategy<K, V> {
    CacheNode<K, V> route(K key, List<CacheNode<K, V>> nodes);
}