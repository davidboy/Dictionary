/**
 * @author David Reed
 */
public class OldRedBlackTree<K extends Comparable<K>, V> implements OldBinaryTree<K, V> {
    private class Node implements Comparable<Node> {
        private K key;
        private V value;

        private Node parent;
        private Node left, right;

        private boolean red = true;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private V findValue(K key) {
            if (key.equals(this.key)) {
                return value;
            }

            if (key.compareTo(this.key) <= 0) {
                if (left == null) {
                    return null;
                }

                return left.findValue(key);
            } else {
                if (right == null) {
                    return null;
                }

                return right.findValue(key);
            }
        }

        private void visitDebug(int level) {
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }

            System.out.println(key.toString() + " (" + (red ? "red" : "black") + ")");

            if (left != null) {
                left.visitDebug(level + 1);
            }

            if (right != null) {
                right.visitDebug(level + 1);
            }
        }

        private void insertChild(K key, V value) {
            insertChild(new Node(key, value));
        }

        private void insertChild(Node node) {
            flipIfNecessary();

            if (node.key.compareTo(this.key) < 0) {
                this.insertLeftChild(node);
            } else {
                this.insertRightChild(node);
            }
        }

        private void insertLeftChild(Node node) {
            if (left != null) {
                this.left.insertChild(node);
            } else {
                setLeftChild(node);
                node.checkForRedRed();
            }
        }

        private void insertRightChild(Node node) {
            if (right != null) {
                this.right.insertChild(node);
            } else {
                setRightChild(node);
                node.checkForRedRed();
            }
        }

        private void flipIfNecessary() {
            if (shouldFlip()) {
                this.red = !this.red;
                this.left.red = !this.left.red;
                this.right.red = !this.right.red;
            }

            if (this == head) {
                this.red = false;
            }
        }

        private boolean shouldFlip() {
            return !red && left != null && right != null && left.red && right.red;
        }

        @Override
        public int compareTo(Node other) {
            return key.compareTo(other.key);
        }

        private void setRightChild(Node node) {
            right = node;

            if (node != null) {
                right.parent = this;
            }
        }

        private void setLeftChild(Node node) {
            left = node;

            if (node != null) {
                left.parent = this;
            }
        }

        private void checkForRedRed() {
            if (parent == null || parent.parent == null) {
                return;
            }

            if (!this.red || !parent.red) {
                parent.checkForRedRed();
                return;
            }

            parent.parent.toggleColor();

            if (isOutsideGrandchild()) {
                parent.toggleColor();
                parent.parent.rotateToRaise(this.parent);
            } else {
                this.toggleColor();
                parent.rotateToRaise(this);
                parent.rotateToRaise(this);
            }

            parent.checkForRedRed();

            head.red = false;
        }

        private void rotateToRaise(Node n) {
            assert left == n || right == n;

            if (n.isLeftChild()) {
                rotateRight();
            } else {
                rotateLeft();
            }
        }

        private void rotateRight() {
            assert left != null;

            if (parent == null) {
                head = left;
                left.parent = null;
            } else {
                if (isLeftChild()) {
                    parent.setLeftChild(left);
                } else {
                    parent.setRightChild(left);
                }
            }

            Node temp = left.right;
            left.setRightChild(this);
            this.setLeftChild(temp);
        }

        private void rotateLeft() {
            assert right != null;

            if (parent == null) {
                head = right;
                right.parent = null;
            } else {
                if (isLeftChild()) {
                    parent.setLeftChild(right);
                } else {
                    parent.setRightChild(right);
                }
            }

            Node temp = right.left;
            right.setLeftChild(this);
            this.setRightChild(temp);
        }

        private boolean isLeftChild() {
            return parent.left == this;
        }

        private boolean isOutsideGrandchild() {
            if (parent.parent.left != null && parent.parent.left.left == this) {
                return true;
            }

            if (parent.parent.right != null && parent.parent.right.right == this) {
                return true;
            }

            return false;
        }

        private void toggleColor() {
            red = !red;
        }

        @Override
        public String toString() {
            return "<Node " + key.toString() + ">";
        }
    }

    private Node head;

    @Override
    public void put(K key, V value) {
        if (head == null) {
            head = new Node(key, value);
            head.red = false;
        } else {
            head.insertChild(key, value);
        }
    }

    @Override
    public V get(K key) {
        if (head == null) {
            return null;
        }

        return head.findValue(key);
    }

    @Override
    public void printDebugView() {
        if (head != null) {
            head.visitDebug(0);
        }
    }

    public static void main(String[] args) {
        OldRedBlackTree<Integer, Integer> tree = new OldRedBlackTree<>();

        for (int i : new int[]{2, 79, 83, 67, 14, 90, 23, 87}) {
            tree.put(i, i);
        }

        tree.printDebugView();
    }
}
