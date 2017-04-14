/**
 * Created by glende on 14.04.17.
 */

import org.testng.Assert;
import org.testng.annotations.Test;

public class DequeueTest {
    @Test
    public void isEmptyEmptyTest() {
        Deque<Integer> dq = new Deque<>();

        Assert.assertTrue(dq.isEmpty());
    }

    //@Test
    public void isEmptyAddFirstTest() {
        Deque<Integer> dq = new Deque<>();

        dq.addFirst(1);

        Assert.assertFalse(dq.isEmpty());
    }


    //@Test
    public void isEmptyAddFLastTest() {
        Deque<Integer> dq = new Deque<>();

        dq.addLast(1);

        Assert.assertFalse(dq.isEmpty());
    }
}
