import java.util.Iterator;

/**
 * @author David Reed
 */
public interface OldBinaryTree<K extends Comparable<K>, V> {
    void put(K key, V value);
    V get(K key);

    void printDebugView();
}
