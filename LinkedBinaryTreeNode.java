/**
 * @author David Reed
 */
public class LinkedBinaryTreeNode<K extends Comparable<K>, V> extends AbstractBinaryTreeNode<K, V> {
    private LinkedBinaryTreeNode<K, V> parent;
    private LinkedBinaryTreeNode<K, V> leftChild, rightChild;

    private K key;
    private V value;

    public LinkedBinaryTreeNode(K key, V value, AbstractBinaryTree<K, V> tree) {
        super(tree);

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
    public AbstractBinaryTreeNode<K, V> getLeftChild() {
        return leftChild;
    }

    @Override
    public AbstractBinaryTreeNode<K, V> getRightChild() {
        return rightChild;
    }

    @Override
    public AbstractBinaryTreeNode<K, V> getParent() {
        return parent;
    }

    @Override
    public boolean isHead() {
        return parent == null;
    }

    @Override
    protected void makeHead() {
        super.makeHead();

        parent = null;
    }

    @Override
    protected void setRightChild(AbstractBinaryTreeNode<K, V> childNode) {
        rightChild = (LinkedBinaryTreeNode<K, V>) childNode;

        if (rightChild != null) {
            rightChild.parent = this;
        }
    }

    @Override
    protected void setLeftChild(AbstractBinaryTreeNode<K, V> childNode) {
        leftChild = (LinkedBinaryTreeNode<K, V>) childNode;

        if (leftChild != null) {
            leftChild.parent = this;
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
    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
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
