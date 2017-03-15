/**
 * @author David Reed
 */
public class AVLTree <K extends Comparable<K>, V> extends BinaryTree<K, V> {
    @Override
    protected BinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
        return new AVLTreeNode<>(key, value, this);
    }

    @Override
    protected String getName() {
        return "AVL Tree";
    }
}
