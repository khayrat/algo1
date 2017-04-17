import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dequeue. A double-ended queue or deque (pronounced "deck")
 * is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure.
 */

public class Deque<Item> implements Iterable<Item> {
   private class FirstToLastIterator implements Iterator<Item> {
      Node current;

      FirstToLastIterator(Node first) {
         current = first;
      }

      @Override
      public boolean hasNext() {
         return current != null;
      }

      @Override
      public Item next() {
         if (current == null) throw new NoSuchElementException();

         Item item = current.item;
         current = current.next;
         return item;
      }
   }

   private class Node {
      Item item;
      Node prev;
      Node next;
   }

   private int count;
   private Node first;
   private Node last;

   /**
    * construct an empty deque
    */
   public Deque() {

   }

   /**
    * is the deque empty?
    * @return true if empty, false otherwise
    */
   public boolean isEmpty()     {
      return count == 0;
   }

   /**
    * return the number of items on the deque
    * @return the number of items on the deque
    */
   public int size()    {
      return count;
   }

   /**
    * add the item to the front
    * @param item - the item to add
    */
   public void addFirst(Item item) {
      if (item == null) throw new NullPointerException();
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.next = oldFirst;
      if (oldFirst != null) oldFirst.prev = first;
      if (last == null) last = first;
      count++;
   }

   /**
    * add the item to the end
    * @param item the item to add
    */
   public void addLast(Item item) {
      if (item == null) throw new NullPointerException();
      Node oldLast = last;
      last = new Node();
      last.item = item;
      if (oldLast != null) oldLast.next = last;
      if (first == null) first = last;
      last.prev = oldLast;
      count++;
   }

   /**
    * remove and return the item from the front
    * @return the removed item
    */
   public Item removeFirst() {
      if (count == 0) throw new NoSuchElementException();
      Node oldFirst = first;
      Item item = oldFirst.item;
      first = oldFirst.next;
      if (first != null) first.prev = null;
      count--;
      if (count == 0) {
         last.prev = null;
         last = null;
      }
      return item;
   }

   /**
    * remove and return the item from the end
    * @return the removed item.
    */
   public Item removeLast() {
      if (count == 0) throw new NoSuchElementException();
      Node oldLast = last;
      Item item = oldLast.item;
      last = oldLast.prev;
      if (last != null) last.next = null;
      count--;
      if (count == 0) {
         first.next = null;
         first = null;
      }
      return item;
   }

   /**
    * return an iterator over items in order from front to end
    * @return the iterator
    */
   public Iterator<Item> iterator()     {
      return new FirstToLastIterator(first);
   }

   /**
    * unit testing (optional)
    * @param args
    */
   public static void main(String[] args)  {

   }
}