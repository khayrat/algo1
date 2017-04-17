import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by glende on 17.04.17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    /**
     * is the queue empty?
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the queue
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (size == items.length) resize((size+1) * 2);
        items[size++] = item;
    }

    private void resize(int capacity) {
        Item[] oldItems = items;
        items = (Item[]) new Object[capacity];

        for (int i=0; i<size; i++) items[i] = oldItems[i];
    }

    /**
     * remove and return a random item
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(size);
        Item item = items[idx];

        reorder(idx);
        size--;

        if (size < (items.length / 4)) resize((items.length / 2));

        return item;
    }

    private void reorder(int idx) {
        items[idx] = items[size-1];
        // avoid loitering
        items[size-1] = null;
    }

    /**
     * return (but do not remove) a random item
     * @return
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(size);
        return items[idx];
    }


    private class RandomizeQueueIterator implements Iterator<Item> {
        private int size;
        private Item[] items;

        private RandomizeQueueIterator() {
            this.size = RandomizedQueue.this.size;
            items = (Item[]) new Object[size];
            for (int i=0; i<size; i++) items[i] = RandomizedQueue.this.items[i];
        }

        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public Item next() {
            if (size == 0) throw new NoSuchElementException();
            int idx = StdRandom.uniform(size);
            Item item = items[idx];

            // reorder
            items[idx] = items[size-1];

            size--;
            return item;
        }
    }

    /**
     * return an independent iterator over items in random order
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator();
    }
}
