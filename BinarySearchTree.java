/**
 * @author David Reed
 */
public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {
    @Override
    protected AbstractBinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
        return new LinkedBinaryTreeNode<>(key, value, this);
    }

    @Override
    protected String getName() {
        return "Binary Search Tree";
    }
}
