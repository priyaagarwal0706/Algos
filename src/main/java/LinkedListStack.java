
public class LinkedListStack {
    
    private Node recent = null;
    
    private class Node {
        String text;
        Node next;
    }
    
    public void push(String item) {
       Node oldRecent = recent;
       recent = new Node();
       recent.text = item;
       recent.next = oldRecent;
    }
    
    public String pop() {
        String itemText = recent.text;
        recent = recent.next;
        return itemText;
    }
    
    public boolean isEmpty() {
        return recent == null;
    }
}
