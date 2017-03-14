public abstract class AbstractTree<K extends Comparable<K>, V> {
    private BinaryTreeNode<K, V> head;

    abstract protected BinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot);

    public void put(K key, V value) {
        if (head == null) {
            head = createNode(key, value, true);
        } else {
            head.addChild(createNode(key, value, false));
        }
    }

    public V get(K desiredKey) {
        BinaryTreeNode<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(desiredKey)) {
                return currentNode.getValue();
            }

            if (desiredKey.compareTo(currentNode.getKey()) <= 0) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
        }

        return null;
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

    protected void setHead(BinaryTreeNode<K, V> head) {
        this.head = head;
    }

    protected BinaryTreeNode<K, V> getHead() {
        return head;
    }
}