import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by glende on 17.04.17.
 */
public class RandomizedQueueTest {
    @Test
    public void isEmptyEmptyTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        Assert.assertTrue(rq.isEmpty());
    }

    @Test
    public void isEmptyNotEmptyTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        Assert.assertTrue(rq.isEmpty());

        rq.enqueue(1);

        Assert.assertFalse(rq.isEmpty());
    }


    @Test
    public void resizeTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        int i = 0;
        while (i<1000) rq.enqueue(i++);

        Assert.assertTrue(rq.size() == i);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void sampleEmptyTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.sample();
    }

    @Test
    public void sampleOneTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        Assert.assertEquals(rq.sample(), new Integer(1));
    }

    @Test
    public void sampleMoreThanOneTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        int max = 10;
        int[] samples = new int[max];

        // enqueue
        for (int i = 0; i<max; i++) {
            samples[i] = i;
            rq.enqueue(i);
        }

        assertSamplesComplete(rq, samples);
    }

    @Test
    public void dequeueTest() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // enqueue
        for (String s: set) rq.enqueue(s);

        // dequeue
        int size = set.size();
        for (int i=0; i<size; i++) {
            String s = rq.dequeue();
            Assert.assertTrue(set.contains(s));
            set.remove(s);
        }
        Assert.assertTrue(rq.isEmpty());
    }

    @Test
    public void iteratorTest() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // enqueue
        for (String s: set) rq.enqueue(s);

        for (String s: rq) Assert.assertTrue(set.contains(s));

        Assert.assertEquals(rq.size(), set.size());

    }

    private void assertSamplesComplete(RandomizedQueue<Integer> rq, int[] samples) {
        for (int i = 0; i<samples.length; i++) {
            assertRequiredSample(rq, samples[i]);
        }
    }

    private void assertRequiredSample(RandomizedQueue<Integer> rq, int requestedSample) {
        int maxTries = 100;
        int count = 0;
        int sample = Integer.MIN_VALUE;

        while (count < maxTries) {
            sample = rq.sample();
            if (sample == requestedSample) break;
        }

        Assert.assertEquals(new Integer(sample), new Integer(requestedSample));
    }

}
