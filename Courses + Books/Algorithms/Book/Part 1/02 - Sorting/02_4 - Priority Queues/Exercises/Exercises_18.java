import java.util.NoSuchElementException;
import java.util.Random;

public class Exercises_18 {
    private static class MaxPQ<Key extends Comparable<Key>> {
        Key[] pq;
        int n;
        public MaxPQ() {
            pq = (Key[]) new Comparable[2];
            n = 0;
        }
        public void insert(Key item) {
            if (n >= pq.length-1)
                resize(2*pq.length);
            pq[++n] = item;
            swim(n);
        }
        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("Priority queue underflow");
            Key max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n+1] = null;
            if (n > 0 && n <= (pq.length-1)/4)
                resize(pq.length/2);
            return max;
        }
        public boolean isEmpty() {
            return n == 0;
        }
        public int size() {
            return n;
        }
        private void swim(int k) {
            while (k > 1 && less(k/2, k)) {
                exch(k/2, k);
                k /= 2;
            }
        }
        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && less(j, j+1)) j++;
                if (!less(k, j)) break;
                exch(k, j);
                k = j;
            }
        }
        private void exch(int a, int b) {
            Key swap = pq[a];
            pq[a] = pq[b];
            pq[b] = swap;
        }
        private boolean less(int a, int b) {
            return pq[a].compareTo(pq[b]) < 0;
        }
        private void resize(int size) {
            Key[] newpq = (Key[]) new Comparable[size];
            if (n >= 0) System.arraycopy(pq, 1, newpq, 1, n);
            pq = newpq;
        }
        public String arrayString() {
            StringBuilder sb = new StringBuilder();
            for (Key k : pq) {
                if (k != null)
                    sb.append(k).append(" ");
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        int max = 100000;
        Random r = new Random();
        for (int t = 0; t < 1000; t++) {
            MaxPQ<Integer> pq = new MaxPQ<Integer>();
            int len = 100 + r.nextInt(100);
            for (int i = 0; i < len; i++) {
                pq.insert(r.nextInt(max));
            }
            String a = pq.arrayString();
            pq.insert(max);
            pq.delMax();
            String b = pq.arrayString();
            if (!a.equals(b)) System.out.println("Single not equal");
            pq.insert(max);
            pq.insert(max+1);
            pq.delMax();
            pq.delMax();
            b = pq.arrayString();
            if (!a.equals(b)) System.out.println("Double not equal");
        }
    }
}
