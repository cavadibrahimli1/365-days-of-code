
import edu.princeton.cs.algs4.Queue;

import java.util.Random;

public class Exercise_14 {
    public static Queue<Comparable> mergeQ(Queue<Comparable> a, Queue<Comparable> b) {
        Queue<Comparable> q = new Queue<>();
        while (!a.isEmpty() || !b.isEmpty()) {
            if (a.isEmpty()) q.enqueue(b.dequeue());
            else if (b.isEmpty()) q.enqueue(a.dequeue());
            else if (a.peek().compareTo(b.peek()) < 0) q.enqueue(a.dequeue());
            else q.enqueue(b.dequeue());
        }
        return q;
    }
    public static void main(String[] args) {
        Queue<Comparable> a = new Queue<>();
        Queue<Comparable> b = new Queue<>();
        int n = 20;
        int cura = 0;
        int curb = 0;
        Random r = new Random();
        while (--n > 0) {
            cura += r.nextInt(3);
            curb += r.nextInt(3);
            a.enqueue(cura);
            b.enqueue(curb);
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(mergeQ(a, b));
    }
}
