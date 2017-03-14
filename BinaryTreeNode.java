/**
 * @author David Reed
 */
public abstract class BinaryTreeNode<K extends Comparable<K>, V> {
    private BinaryTree<K, V> tree;

    protected BinaryTreeNode(BinaryTree<K, V> tree) {
        this.tree = tree;
    }

    abstract public BinaryTreeNode<K, V> getParent();
    abstract public BinaryTreeNode<K, V> getLeftChild();
    abstract public BinaryTreeNode<K, V> getRightChild();

    abstract public boolean hasLeftChild();
    abstract public boolean hasRightChild();

    abstract protected void setLeftChild(BinaryTreeNode<K, V> node);
    abstract protected void setRightChild(BinaryTreeNode<K, V> node);

    abstract public boolean isLeftChild();
    abstract public boolean isRightChild();

    abstract public K getKey();
    abstract public V getValue();

    abstract public boolean isHead();
    protected void makeHead() {
        tree.setHead(this);
    }

    public V findValue(K key) {
        if (getKey().equals(key)) {
            return getValue();
        }

        if (hasLeftChild() && key.compareTo(getKey()) <= 0) {
            return getLeftChild().findValue(key);
        } else if (hasRightChild() && key.compareTo(getKey()) > 0) {
            return getRightChild().findValue(key);
        }

        return null;
    }

    public void addChild(BinaryTreeNode<K, V> node) {
        if (node.getKey().compareTo(this.getKey()) <= 0) {
            addLeftChild(node);
        } else {
            addRightChild(node);
        }
    }

    public void addLeftChild(BinaryTreeNode<K, V> node) {
        if (hasLeftChild()) {
            getLeftChild().addChild(node);
        } else {
            setLeftChild(node);
        }
    }

    public void addRightChild(BinaryTreeNode<K, V> node) {
        if (hasRightChild()) {
            getRightChild().addChild(node);
        } else {
            setRightChild(node);
        }
    }

    protected void rotateToRaise(BinaryTreeNode<K, V> n) {
        if (n.isLeftChild()) {
            rotateRight();
        } else {
            rotateLeft();
        }
    }

    private void rotateRight() {
        assert hasLeftChild();

        if (isHead()) {
            getLeftChild().makeHead();
        } else {
            if (isLeftChild()) {
                getParent().setLeftChild(getLeftChild());
            } else {
                getParent().setRightChild(getLeftChild());
            }
        }

        BinaryTreeNode<K, V> temp = getLeftChild().getRightChild();
        getLeftChild().setRightChild(this);
        this.setLeftChild(temp);
    }

    private void rotateLeft() {
        assert hasRightChild();

        if (isHead()) {
            getRightChild().makeHead();
        } else {
            if (isLeftChild()) {
                getParent().setLeftChild(getRightChild());
            } else {
                getParent().setRightChild(getRightChild());
            }
        }

        BinaryTreeNode<K, V> temp = getRightChild().getLeftChild();
        getRightChild().setLeftChild(this);
        this.setRightChild(temp);
    }

    public LinkedList<Entry<K, V>> getEntries() {
        LinkedList<Entry<K, V>> results = new LinkedList<>();

        if (hasLeftChild()) {
            results.add(getLeftChild().getEntries());
        }

        results.add(new Entry<>(getKey(), getValue()));

        if (hasRightChild()) {
            results.add(getRightChild().getEntries());
        }

        return results;
    }

    public LinkedList<Entry<K, V>> getEntriesBetween(K start, K end) {
        LinkedList<Entry<K, V>> results = new LinkedList<>();

        int thisVsStart = getKey().compareTo(start);
        int thisVsEnd = getKey().compareTo(end);

        if (thisVsStart >= 0 && thisVsEnd <= 0) {
            if (thisVsStart != 0 && hasLeftChild()) {
                results.add(getLeftChild().getEntriesBetween(start, end));
            }

            results.add(new Entry<>(getKey(), getValue()));

            if (thisVsEnd != 0 && hasRightChild()) {
                results.add(getRightChild().getEntriesBetween(start, end));
            }
        } else if (thisVsStart < 0 && hasRightChild()) {
            results.add(getRightChild().getEntriesBetween(start, end));
        } else if (thisVsEnd > 0 && hasLeftChild()) {
            results.add(getLeftChild().getEntriesBetween(start, end));
        }

        return results;
    }

    public void printDebugView() {
        printDebugView(0);
    }

    private void printDebugView(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        System.out.println(getDebugDescription());

        if (hasLeftChild()) {
            getLeftChild().printDebugView(level + 1);
        }

        if (hasRightChild()) {
            getRightChild().printDebugView(level + 1);
        }
    }

    protected String getDebugDescription() {
        return getKey().toString();
    }

    @Override
    public String toString() {
        return "<Node " + getValue() + ">";
    }
}
