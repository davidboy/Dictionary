/**
 * @author David Reed
 */
public class AVLTreeNode<K extends Comparable<K>, V> extends LinkedBinaryTreeNode<K, V> {
    public AVLTreeNode(K key, V value, AbstractBinaryTree<K, V> tree) {
        super(key, value, tree);
    }

    @Override
    public void addLeftChild(AbstractBinaryTreeNode<K, V> node) {
        if (hasLeftChild()) {
            super.addLeftChild(node);
            return;
        }

        super.addLeftChild(node);

        if (getLeftChild().equals(node)) {
            ensureBalance();
        }
    }

    @Override
    public void addRightChild(AbstractBinaryTreeNode<K, V> node) {
        if (hasRightChild()) {
            super.addRightChild(node);
            return;
        }

        super.addRightChild(node);

        if (getRightChild().equals(node)) {
            ensureBalance();
        }
    }

    private void ensureBalance() {
        if (Math.abs(getBalanceFactor()) > 1) {
            rebalance();
        }

        if (!isHead()) {
            ((AVLTreeNode) getParent()).ensureBalance();
        }
    }

    private void rebalance() {
        if (isWeightedLeft()) {
            if (((AVLTreeNode) getLeftChild()).isWeightedRight()) {
                getLeftChild().rotateLeft();
            }

            rotateRight();
        } else {
            if (((AVLTreeNode) getRightChild()).isWeightedLeft()) {
                getRightChild().rotateRight();
            }

            rotateLeft();
        }
    }

    private int getBalanceFactor() {
        return getLeftChildHeight() - getRightChildHeight();
    }

    private boolean isWeightedLeft() {
        return getBalanceFactor() > 0;
    }

    private boolean isWeightedRight() {
        return getBalanceFactor() < 0;
    }
}
