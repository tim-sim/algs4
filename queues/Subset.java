/**
 * Queue client programm. Takes a command-line integer k, reads in a sequence of
 * N strings from standard input and prints out exactly k of them, uniformly at
 * random.
 * 
 * @author Timur Nasibullin
 * 
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> que = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            que.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(que.dequeue());
        }
    }
}
