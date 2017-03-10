/**
 * @author David Reed
 */
public class LinkedBinaryTreeNode<K extends Comparable<K>, V> extends BinaryTreeNode<K, V> {
    private LinkedBinaryTreeNode<K, V> parent;
    private LinkedBinaryTreeNode<K, V> leftChild, rightChild;

    private K key;
    private V value;

    public LinkedBinaryTreeNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean hasLeftChild() {
        return leftChild != null;
    }

    @Override
    public boolean hasRightChild() {
        return rightChild != null;
    }

    @Override
    public BinaryTreeNode<K, V> getLeftChild() {
        return leftChild;
    }

    @Override
    public BinaryTreeNode<K, V> getRightChild() {
        return rightChild;
    }

    @Override
    public BinaryTreeNode<K, V> getParent() {
        return parent;
    }

    @Override
    public boolean isHead() {
        return parent == null;
    }

    @Override
    protected void makeHead() {
        // TODO: set head in containing tree
        parent = null;
    }

    @Override
    protected void setRightChild(BinaryTreeNode<K, V> node) {
        if (node != null) {
            assert node instanceof LinkedBinaryTreeNode;
        }

        LinkedBinaryTreeNode<K, V> childNode = (LinkedBinaryTreeNode<K, V>) node;
        rightChild = childNode;

        if (childNode != null) {
            childNode.parent = this;
        }
    }

    @Override
    protected void setLeftChild(BinaryTreeNode<K, V> node) {
        if (node != null) {
            assert node instanceof LinkedBinaryTreeNode;
        }

        LinkedBinaryTreeNode<K, V> childNode = (LinkedBinaryTreeNode<K, V>) node;
        leftChild = childNode;

        if (childNode != null) {
            childNode.parent = this;
        }
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public boolean isRightChild() {
        return parent.rightChild == this;
    }

    @Override
    public boolean isLeftChild() {
        return parent.leftChild == this;
    }
}
