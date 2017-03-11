/**
 * @author David Reed
 */
public class Set<T> {
    private HashTable<T, Boolean> table = new HashTable<>(100);

    public void add(T value) {
        table.put(value, true);
    }

    public void remove(T value) {
        // TODO: implement real removal?
        table.put(value, false);
    }

    public boolean contains(T value) {
        Boolean result = table.get(value);
        return result != null && result;
    }

    public LinkedList<T> toList() {
        LinkedList<T> result = new LinkedList<>();

        for (T value : table) {
            if (table.get(value)) {
                result.add(value);
            }
        }

        return result;
    }
}
