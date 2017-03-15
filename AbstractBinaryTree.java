public abstract class AbstractBinaryTree<K extends Comparable<K>, V> {
    private AbstractBinaryTreeNode<K, V> head;

    abstract protected AbstractBinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot);

    public void put(K key, V value) {
        if (head == null) {
            head = createNode(key, value, true);
        } else {
            head.addChild(createNode(key, value, false));
        }
    }

    public V get(K desiredKey) {
        if (head == null) {
            return null;
        }

        return head.findValue(desiredKey);
    }

    public LinkedList<Entry<K, V>> getEntries() {
        if (head == null) {
            return new LinkedList<>();
        }

        return head.getEntries();
    }

    public LinkedList<Entry<K, V>> getEntriesBetween(K start, K end) {
        if (head == null) {
            return new LinkedList<>();
        }

        return head.getEntriesBetween(start, end);
    }

    abstract protected String getName();

    public void printDebugView() {
        if (head == null) {
            System.out.println("<Empty " + getName() + ">");
        } else {
            head.printDebugView();
        }
    }

    protected void setHead(AbstractBinaryTreeNode<K, V> head) {
        this.head = head;
    }

    protected AbstractBinaryTreeNode<K, V> getHead() {
        return head;
    }
}
