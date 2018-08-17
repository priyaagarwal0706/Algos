
public class LinkedListQueue {
    
    private Node first = null;
    private Node last = null;
    
    private class Node {
        String text;
        Node next;
    }
    
    
    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.text = item;
        last.next = null;
        if(isEmpty()) first = last;
        else last.next = oldLast;
    }
    
    public String dequeue() {
        String text = first.text ;
        first = first.next;
        if(isEmpty()) {
            last = null;
        }
        return text ;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
}
