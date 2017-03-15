/**
 * @author David Reed
 */
public class SplayTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {
    @Override
    protected AbstractBinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
        return new SplayTreeNode<>(key, value, this);
    }

    @Override
    protected String getName() {
        return "Splay Tree";
    }
}
