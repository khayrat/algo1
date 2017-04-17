import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by glende on 17.04.17.
 *
 * takes a command-line integer k; reads in a sequence of strings from standard input
 * using StdIn.readString(); and prints exactly k of them, uniformly at random.
 * Print each item from the sequence at most once. You may assume that 0 ≤ k ≤ n,
 * where n is the number of string on standard input.
 */

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while(!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        int count = 0;
        for (String s: rq) {
            if (count++ == k) break;
            StdOut.printf("%s\n", s);
        }
    }
}
