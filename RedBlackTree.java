/**
 * @author David Reed
 */
public class RedBlackTree<K extends Comparable<K>, V> extends BinaryTree<K, V> {
    @Override
    protected BinaryTreeNode<K, V> createNode(K key, V value, boolean isRoot) {
        return new RedBlackTreeNode<>(key, value, !isRoot);
    }

    @Override
    protected String getName() {
        return "Red Black Tree";
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        for (int i = 1; i <= 4; i++) {
            tree.put(i, i);
        }

        tree.printDebugView();
    }
}
