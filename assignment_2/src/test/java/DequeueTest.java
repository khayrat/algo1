/**
 * Created by glende on 14.04.17.
 */

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class DequeueTest {
    @Test
    public void isEmptyEmptyTest() {
        Deque<Integer> dq = new Deque<>();

        Assert.assertTrue(dq.isEmpty());
    }

    @Test
    public void isEmptyAddFirstTest() {
        Deque<Integer> dq = new Deque<>();

        dq.addFirst(1);

        Assert.assertFalse(dq.isEmpty());
    }


    @Test
    public void isEmptyAddFLastTest() {
        Deque<Integer> dq = new Deque<>();

        dq.addLast(1);

        Assert.assertFalse(dq.isEmpty());
    }

    @Test
    public void addFirstRemoveFirstTest() {
        Deque<String> dq = new Deque<>();
        dq.addFirst("a");
        Assert.assertEquals(dq.removeFirst(), "a");

        Assert.assertTrue(dq.isEmpty());
        Assert.assertEquals(dq.size(), 0);
    }

    @Test
    public void addLastRemoveLastTest() {
        Deque<String> dq = new Deque<>();
        dq.addLast("a");
        Assert.assertEquals(dq.removeLast(), "a");

        Assert.assertTrue(dq.isEmpty());
        Assert.assertEquals(dq.size(), 0);
    }

    @Test
    public void addFirst2timesRemoveLastTest() {
        Deque<String> dq = new Deque<>();
        dq.addFirst("a");
        dq.addFirst("b");

        Assert.assertFalse(dq.isEmpty());
        Assert.assertEquals(dq.size(), 2);

        Assert.assertEquals(dq.removeLast(), "a");
        Assert.assertFalse(dq.isEmpty());
        Assert.assertEquals(dq.size(), 1);
    }

    @Test
    public void iteratorTest() {
        Deque<String> dq = new Deque<>();
        String[] strings = {"a", "b", "c", "d", "e", "f", "g"};
        for (String s : strings) {
            dq.addLast(s);
        }

        int i = 0;
        for (String s: dq) {
            Assert.assertEquals(s, strings[i++]);
        }

        i = 0;
        for (String s: dq) {
            Assert.assertEquals(s, strings[i++]);
        }
    }

    @Test
    public void iteratorSizeTest() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");

        // enqueue
        Deque<String> dq = new Deque<>();
        for (String s: set) dq.addFirst(s);

        Assert.assertEquals(dq.size(), set.size());

        // iterate
        int count = dq.size();

        for (String s: dq) {
            Assert.assertTrue(set.contains(s));
            set.remove(s);
            count--;
        }

        Assert.assertEquals(count, set.size());
        Assert.assertEquals(count, 0);


    }
}
