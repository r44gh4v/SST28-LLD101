import java.util.HashMap;
import java.util.Map;

class Node<K> {
    K key;
    Node<K> prev;
    Node<K> next;

    public Node(K key) {
        this.key = key;
    }
}

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private Node<K> head;
    private Node<K> tail;
    private Map<K, Node<K>> mapper;

    public LRUEvictionPolicy() {
        this.mapper = new HashMap<>();
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void keyAccessed(K key) {
        if (mapper.containsKey(key)) {
            removeNode(mapper.get(key));
            addNodeToTail(mapper.get(key));
        } else {
            Node<K> newNode = new Node<>(key);
            mapper.put(key, newNode);
            addNodeToTail(newNode);
        }
    }

    @Override
    public K evictKey() {
        if (head.next == tail) {
            return null;
        }
        Node<K> lruNode = head.next;
        removeNode(lruNode);
        mapper.remove(lruNode.key);
        return lruNode.key;
    }

    private void removeNode(Node<K> node) {
        Node<K> prevNode = node.prev;
        Node<K> nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addNodeToTail(Node<K> node) {
        Node<K> preTail = tail.prev;
        preTail.next = node;
        node.prev = preTail;
        node.next = tail;
        tail.prev = node;
    }
}