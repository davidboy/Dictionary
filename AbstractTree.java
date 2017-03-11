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

    public LinkedList<Entry<K, V>> getItemsBetween(K start, K end) {
        LinkedList<Entry<K, V>> results = new LinkedList<>();

        Stack<BinaryTreeNode<K, V>> nodesToSearch = new Stack<>();

        for (BinaryTreeNode<K, V> node = head; node != null; node = nodesToSearch.pop()) {
            K nodeKey = node.getKey();

            int nodeVsStart = nodeKey.compareTo(start);
            int nodeVsEnd = nodeKey.compareTo(end);

            if (nodeVsStart >= 0 && nodeVsEnd <= 0) {
                results.add(new Entry<>(nodeKey, node.getValue()));

                if (nodeVsStart != 0 && node.hasLeftChild()) {
                    nodesToSearch.push(node.getLeftChild());
                }

                if (nodeVsEnd != 0 && node.hasRightChild()) {
                    nodesToSearch.push(node.getRightChild());
                }
            } else if (nodeVsStart < 0 && node.hasRightChild()) {
                nodesToSearch.push(node.getRightChild());
            } else if (nodeVsEnd > 0 && node.hasLeftChild()) {
                nodesToSearch.push(node.getLeftChild());
            }
        }

        return results;
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
