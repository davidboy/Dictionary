/**
 * @author David Reed
 */
public class RedBlackTree<K extends Comparable<K>, V> extends BinaryTree<K, V> {
    @Override
    protected BinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
        return new RedBlackTreeNode<>(key, value, this, !isRoot);
    }

    @Override
    protected String getName() {
        return "Red Black Tree";
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);

        ((RedBlackTreeNode<K, V>) getHead()).makeBlack();
    }
}
