/**
 * @author David Reed
 */
public abstract class BinaryTreeNode<K extends Comparable<K>, V> {
    protected BinaryTree<K, V> tree;

    public BinaryTreeNode(BinaryTree<K, V> tree) {
        this.tree = tree;
    }

    abstract public boolean hasLeftChild();
    abstract public boolean hasRightChild();

    abstract public boolean isHead();
    protected void makeHead() {
        tree.setHead(this);
    }

    abstract public BinaryTreeNode<K, V> getParent();
    abstract public BinaryTreeNode<K, V> getLeftChild();
    abstract public BinaryTreeNode<K, V> getRightChild();

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

    abstract protected void setRightChild(BinaryTreeNode<K, V> node);
    abstract protected void setLeftChild(BinaryTreeNode<K, V> node);

    abstract public K getKey();
    abstract public V getValue();

    abstract public boolean isRightChild();
    abstract public boolean isLeftChild();

    public void rotateRight() {
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

    public void rotateLeft() {
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

    public void printDebugView() {
        printDebugView(0);
    }

    protected String getDebugDescription() {
        return getKey().toString();
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

    @Override
    public String toString() {
        return "<Node " + getValue() + ">";
    }
}
