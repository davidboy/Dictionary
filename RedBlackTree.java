/**
 * @author David Reed
 */
public class RedBlackTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {
    @Override
    protected AbstractBinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
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

    @Override
    public void delete(K key) throws DeleteNotImplementedException {
        throw new DeleteNotImplementedException();
    }
}
