/**
 * @author David Reed
 */
public class AVLTree <K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {
    @Override
    protected AbstractBinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
        return new AVLTreeNode<>(key, value, this);
    }

    @Override
    protected String getName() {
        return "AVL Tree";
    }
}
