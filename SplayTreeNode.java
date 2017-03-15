/**
 * @author David Reed
 */
public class SplayTreeNode<K extends Comparable<K>, V> extends LinkedBinaryTreeNode<K, V> {
    public SplayTreeNode(K key, V value, AbstractBinaryTree<K, V> tree) {
        super(key, value, tree);
    }

    @Override
    public V findValue(K key) {
        V result = super.findValue(key);

        if (getKey().equals(key)) {
            raiseToRoot();
        }

        return result;
    }

    private void raiseToRoot() {
        while (!isHead()) {
            if (getParent().isHead()) {
                raiseOneLevel();
            } else {
                if (isOutsideGrandchild()) {
                    getParent().raiseOneLevel();
                } else {
                    raiseOneLevel();
                }

                raiseOneLevel();
            }
        }
    }
}
