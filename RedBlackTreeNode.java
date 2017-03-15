/**
 * @author David Reed
 */
public class RedBlackTreeNode<K extends Comparable<K>, V> extends LinkedBinaryTreeNode<K, V> {
    private boolean isRed = true;

    public RedBlackTreeNode(K key, V value, RedBlackTree<K, V> tree, boolean isRed) {
        super(key, value, tree);

        this.isRed = isRed;
    }

    private void toggleColor() {
        isRed = !isRed;
    }

    public void makeBlack() {
        isRed = false;
    }

    @Override
    public void addChild(AbstractBinaryTreeNode<K, V> node) {
        flipIfNecessary();

        super.addChild(node);
    }

    @Override
    public void setLeftChild(AbstractBinaryTreeNode<K, V> childNode) {
        super.setLeftChild(childNode);

        if (childNode != null) {
            ((RedBlackTreeNode<K, V>) childNode).ensureBalance();
        }
    }

    @Override
    public void setRightChild(AbstractBinaryTreeNode<K, V> childNode) {
        super.setRightChild(childNode);

        if (childNode != null) {
            ((RedBlackTreeNode<K, V>) childNode).ensureBalance();
        }
    }

    private void flipIfNecessary() {
        if (shouldFlip()) {
            this.toggleColor();

            ((RedBlackTreeNode<K, V>) getLeftChild()).toggleColor();
            ((RedBlackTreeNode<K, V>) getRightChild()).toggleColor();
        }
    }

    private boolean shouldFlip() {
        if (isRed) {
            return false;
        }

        if (!hasLeftChild() || !hasRightChild()) {
            return false;
        }

        RedBlackTreeNode<K, V> leftChild = (RedBlackTreeNode<K, V>) getLeftChild();
        RedBlackTreeNode<K, V> rightChild = (RedBlackTreeNode<K, V>) getRightChild();

        return !isRed && hasLeftChild() && hasRightChild() && leftChild.isRed && rightChild.isRed;
    }

    private void ensureBalance() {
        if (isHead() || getParent().isHead()) {
            return;
        }

        RedBlackTreeNode<K, V> parent = (RedBlackTreeNode<K, V>) getParent();
        RedBlackTreeNode<K, V> grandparent = (RedBlackTreeNode<K, V>) parent.getParent();

        if (!isRed || !parent.isRed) {
            parent.ensureBalance();
            return;
        }

        grandparent.toggleColor();

        if (isOutsideGrandchild()) {
            parent.toggleColor();
            grandparent.rotateToRaise(parent);
        } else {
            toggleColor();

            parent.rotateToRaise(this);
            grandparent.rotateToRaise(this);
        }
    }

    @Override
    protected String getDebugDescription() {
        return super.getDebugDescription() + " (" + (isRed ? "red" : "black") + ")";
    }
}
