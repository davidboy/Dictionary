/**
 * @author David Reed
 */
public class RedBlackTreeNode<K extends Comparable<K>, V> extends LinkedBinaryTreeNode<K, V> {
    private boolean isRed = true;

    public RedBlackTreeNode(K key, V value, boolean isRed) {
        super(key, value);

        this.isRed = isRed;
    }

    @Override
    public void addChild(BinaryTreeNode<K, V> node) {
        flipIfNecessary();

        super.addChild(node);
    }

    @Override
    public void setLeftChild(BinaryTreeNode<K, V> node) {
        super.setLeftChild(node);

        checkForRedRed();
    }

    @Override
    public void setRightChild(BinaryTreeNode<K, V> node) {
        super.setRightChild(node);

        checkForRedRed();
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

            ((RedBlackTreeNode<K, V>) getParent()).rotateToRaise(this);
            ((RedBlackTreeNode<K, V>) getParent()).rotateToRaise(this);
        }
    }

    private boolean isOutsideGrandchild() {
        BinaryTreeNode<K, V> grandparent = getParent().getParent();

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

    private void rotateToRaise(BinaryTreeNode<K, V> n) {
        if (n.isLeftChild()) {
            rotateRight();
        } else {
            rotateLeft();
        }
    }

    @Override
    protected String getDebugDescription() {
        return super.getDebugDescription() + " (" + (isRed ? "red" : "black") + ")";
    }
}
