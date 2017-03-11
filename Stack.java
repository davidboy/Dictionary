/**
 * @author David Reed
 */
public class Stack<T> {
    private class StackNode {
        T value;
        StackNode next;

        private StackNode(T value, StackNode next) {
            this.value = value;
            this.next = next;
        }
    }

    private StackNode head;

    public void push(T value) {
        head = new StackNode(value, head);
    }

    public T pop() {
        if (head == null) {
            return null;
        }

        T result = head.value;
        head = head.next;

        return result;
    }
}
