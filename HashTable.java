import java.util.Iterator;

/**
 * @author David Reed
 */
public class HashTable<K, V extends Comparable<V>> implements Iterable<K> {
    private int size;
    private LinkedList<Entry<K, V>>[] values;

    public HashTable(int size) {
        this.size = size;

        values = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
    }

    public void put(K key, V value) {
        int hashCode = getHashCode(key);

        if (values[hashCode] == null) {
            values[hashCode] = new LinkedList<>();
        }

        // If that key already exists, just update its value.
        for (Entry<K, V> entry : values[hashCode]) {
            if (key.equals(entry.getKey())) {
                entry.setValue(value);
                return;
            }
        }

        values[hashCode].add(new Entry<>(key, value));
    }

    public V get(K key) {
        int hashCode = getHashCode(key);

        if (values[hashCode] == null) {
            return null;
        }

        for (Entry<K, V> entry : values[hashCode]) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }

    private int getHashCode(K key) {
        return Math.floorMod(key.hashCode(), size);
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int currentIndex = -1;
            private Iterator<Entry<K, V>> currentIterator = null;

            {
                findNextIterator();
            }

            private boolean findNextIterator() {
                do {
                    currentIndex++;
                    if (currentIndex > size - 1) return false;
                } while (values[currentIndex] == null);

                currentIterator = values[currentIndex].iterator();
                return true;
            }

            @Override
            public boolean hasNext() {
                return currentIterator.hasNext() || findNextIterator();
            }

            @Override
            public K next() {
                if (!currentIterator.hasNext()) {
                    findNextIterator();
                }

                return currentIterator.next().getKey();
            }
        };
    }
}
