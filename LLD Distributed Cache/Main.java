import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database<String, String> mysqlDatabase = new MockDatabase<>();
        DistributionStrategy<String, String> moduloStrategy = new ModuloDistributionStrategy<>();

        List<CacheNode<String, String>> nodes = new ArrayList<>();
        nodes.add(new CacheNode<>("Node-1", 2, new LRUEvictionPolicy<>()));
        nodes.add(new CacheNode<>("Node-2", 2, new LRUEvictionPolicy<>()));
        nodes.add(new CacheNode<>("Node-3", 2, new LRUEvictionPolicy<>()));

        DistributedCache<String, String> cacheSystem = new DistributedCache<>(nodes, moduloStrategy, mysqlDatabase);

        cacheSystem.put("User1", "data-v1");
        cacheSystem.put("User2", "data-v2");
        cacheSystem.put("User3", "data-v3");
        cacheSystem.put("User4", "data-v4");

        System.out.println("Fetching User1: " + cacheSystem.get("User1"));
        System.out.println("Fetching User3: " + cacheSystem.get("User3"));
        System.out.println("Fetching User10: " + cacheSystem.get("User10"));

        System.out.println("\nFilling capacity for LRU behavior.");
        cacheSystem.put("A", "Alpha");
        cacheSystem.put("B", "Beta");
        cacheSystem.put("C", "Charlie");
        cacheSystem.put("D", "Delta");

        cacheSystem.get("A");
    }
}