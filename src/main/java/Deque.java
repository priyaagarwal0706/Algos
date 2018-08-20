import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;
    public Deque() {
      this.size = 0;
      this.first = null;
      this.last = null;
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
          return current != null;
        }

        @Override
        public Item next() {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          Item currentItem  = current.item;
          current = current.next;
          return currentItem;
        }
    
        @Override
        public void remove() {
           throw new UnsupportedOperationException();
        }
    }
    private class Node {
        Node previous;
        Node next;
        Item item;    

    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void addFirst(Item item) {
        if (item == null) {
          throw new IllegalArgumentException();
        }
        Node  newFirst = first;
        first  = new Node();
        first.previous = null;
        first.item = item;
        if (size == 0) {
           last =  first;
         } else {
             newFirst.previous  = first;
         }
        first.next = newFirst;
        size++;
    }
    public void addLast(Item item) {
        if (item == null) {
          throw new IllegalArgumentException();
        }
        Node newLast  = last;
        last  = new Node();
        last.next = null;
        last.item = item;
        if (size() == 0) {
          first =  last;
        } else {
            newLast.next  = last;
        }
        last.previous = newLast;
        size++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) {
           throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = first.next;
        if (first != null) {
          first.previous = null;
        }
        if (size() == 1) {
            last = first;
        }
        size--;
        return oldFirst.item;
    }
    
    public Item removeLast() {
       if (isEmpty()) {
         throw new NoSuchElementException();
       }
       Node oldLast = last;
       last = last.previous;
       if (last != null) {
         last.next = null;
       }
       if (size() == 1) {
           first = last;
       }
       size--;
       return oldLast.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

}



