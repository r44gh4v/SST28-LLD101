import java.util.HashMap;
import java.util.Map;

public class MockDatabase<K, V> implements Database<K, V> {
    private Map<K, V> store = new HashMap<>();

    @Override
    public V get(K key) {
        System.out.println("[Database] Fetching key " + key + " from DB");
        return store.get(key);
    }

    @Override
    public void put(K key, V value) {
        System.out.println("[Database] Saving key " + key + " exactly to DB");
        store.put(key, value);
    }
}