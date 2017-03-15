/**
 * @author David Reed
 */
public class RedBlackTreeNode<K extends Comparable<K>, V> extends LinkedBinaryTreeNode<K, V> {
    private boolean isRed = true;

    public RedBlackTreeNode(K key, V value, RedBlackTree<K, V> tree, boolean isRed) {
        super(key, value, tree);

        this.isRed = isRed;
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
            ((RedBlackTreeNode<K, V>) childNode).checkForRedRed();
        }
    }

    @Override
    public void setRightChild(AbstractBinaryTreeNode<K, V> childNode) {
        super.setRightChild(childNode);

        if (childNode != null) {
            ((RedBlackTreeNode<K, V>) childNode).checkForRedRed();
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

    private void toggleColor() {
        isRed = !isRed;
    }
    public void makeBlack() {
        isRed = false;
    }

    private void checkForRedRed() {
        if (!hasGrandparent()) {
            return;
        }

        RedBlackTreeNode<K, V> parent = (RedBlackTreeNode<K, V>) getParent();
        RedBlackTreeNode<K, V> grandparent = (RedBlackTreeNode<K, V>) parent.getParent();

        if (!isRed || !parent.isRed) {
            parent.checkForRedRed();
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

    private boolean isOutsideGrandchild() {
        AbstractBinaryTreeNode<K, V> grandparent = getParent().getParent();

        if (grandparent.hasLeftChild() && grandparent.getLeftChild().getLeftChild() == this) {
            return true;
        }

        if (grandparent.hasRightChild() && grandparent.getRightChild().getRightChild() == this) {
            return true;
        }

        return false;
    }

    private boolean hasGrandparent() {
        return !isHead() && !getParent().isHead();
    }

    @Override
    protected String getDebugDescription() {
        return super.getDebugDescription() + " (" + (isRed ? "red" : "black") + ")";
    }
}
