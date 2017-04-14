import java.util.Iterator;

/**
 * Dequeue. A double-ended queue or deque (pronounced "deck")
 * is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure.
 */

public class Deque<Item> implements Iterable<Item> {
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
      return true;
   }

   /**
    * return the number of items on the deque
    * @return the number of items on the deque
    */
   public int size()    {
      return 0;
   }

   /**
    * add the item to the front
    * @param item - the item to add
    */
   public void addFirst(Item item)          {

   }

   /**
    * add the item to the end
    * @param item the item to add
    */
   public void addLast(Item item)       {

   }

   /**
    * remove and return the item from the front
    * @return the removed item
    */
   public Item removeFirst()        {
      return null;
   }

   /**
    * remove and return the item from the end
    * @return the removed item.
    */
   public Item removeLast()    {
      return null;
   }

   /**
    * return an iterator over items in order from front to end
    * @return the iterator
    */
   public Iterator<Item> iterator()     {
      return null;
   }

   /**
    * unit testing (optional)
    * @param args
    */
   public static void main(String[] args)  {

   }
}