package queues;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] sampleArray;
    public RandomizedQueue()  {
        this.size = 0;
        this.sampleArray = (Item[]) new Object[1];
    }
                      
    private class RandomQueueIterator implements Iterator<Item> {
        private int count;
        private final int[] tempArray;
        
        public RandomQueueIterator() {
            tempArray = StdRandom.permutation(size);
        }
        
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Item next() {
            if (count == size) {
                throw new NoSuchElementException("No more element to iterate");
            }
            return sampleArray[tempArray[count++]];
        }
        @Override
        public void remove() {
           throw new UnsupportedOperationException();
        }
    }
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = sampleArray[i];
        }
        sampleArray = temp;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size()  {
            return size;
    }
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            resize(1);
        }
        if (size == sampleArray.length) {
            resize(2*sampleArray.length);
        }
        sampleArray[size++] = item;
        
    }
    public Item dequeue()  {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        Item lastItem = sampleArray[index];
        sampleArray[index] = sampleArray[--size];
        sampleArray[size] = null;
        if (size == sampleArray.length/4) {
            resize(sampleArray.length/2);
        }
        return lastItem;
    }
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return sampleArray[StdRandom.uniform(size)];
    }
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }
                        
}
