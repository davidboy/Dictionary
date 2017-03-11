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

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        for (int i : new int[]{2, 79, 83, 67, 14, 90, 23, 87}) {
            tree.put(i, i);
        }

        tree.printDebugView();
    }
}
