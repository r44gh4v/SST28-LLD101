import java.util.List;

public class ModuloDistributionStrategy<K, V> implements DistributionStrategy<K, V> {
    @Override
    public CacheNode<K, V> route(K key, List<CacheNode<K, V>> nodes) {
        if (nodes.isEmpty()) {
            throw new IllegalStateException("No cache nodes available for routing");
        }
        int hash = Math.abs(key.hashCode());
        int index = hash % nodes.size();
        return nodes.get(index);
    }
}