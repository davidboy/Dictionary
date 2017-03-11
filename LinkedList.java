import java.util.Collections;
import java.util.Iterator;

/**
 * @author David Reed
 */
public class LinkedList<E> implements Iterable<E> {
    private class LinkedListIterator implements Iterator<E> {

        private Node current;

        private LinkedListIterator(Node head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            Node result = current;
            current = current.next;

            return result.element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        if (head == null) {
            return Collections.emptyIterator();
        }

        return new LinkedListIterator(head);
    }

    private class Node {
        E element;
        Node next;

        private Node(E element) {
            this.element = element;
        }
    }

    private Node head;

    public void add(E element) {
        if (head == null) {
            head = new Node(element);
            return;
        }

        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = new Node(element);
    }

    public int length() {
        int length = 0;
        for (E ignored : this) {
            length++;
        }

        return length;
    }
}
