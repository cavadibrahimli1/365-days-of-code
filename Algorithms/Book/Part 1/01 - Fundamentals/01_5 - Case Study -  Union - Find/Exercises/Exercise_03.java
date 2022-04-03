import java.util.Arrays;

public class Exercise_03 {
    public static void main(String[] args) {
        String[] input = {"9-0", "3-4", "5-8", "7-2", "2-1", "5-7", "0-3", "4-2"};
        WeightedQuickUnionUF u = new WeightedQuickUnionUF(10);
        for (String s : input) {
            System.out.print(s + " ");
            String[] a = s.split("-");
            int p = Integer.parseInt(a[0]);
            int q = Integer.parseInt(a[1]);
            u.union(p, q);
        }
        System.out.println();

        String[] worst = {"0-1", "0-2", "0-3", "0-4", "0-5", "0-6", "0-7", "0-8", "0-9"};
        u = new WeightedQuickUnionUF(10);
        for (String s : worst) {
            System.out.print(s + " ");
            String[] a = s.split("-");
            int p = Integer.parseInt(a[0]);
            int q = Integer.parseInt(a[1]);
            u.union(p, q);
        }

    }

    public static class WeightedQuickUnionUF {
        private final int[] parent;   // parent[i] = parent of i
        private final int[] size;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components
        private int access;

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            validate(p);
            access++;   // Initial while test
            while (p != parent[p]) {
                access++;   // update p
                p = parent[p];
                access++;   // while test
            }
            return p;
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            access = 0;
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                System.out.printf("%s %d\n", Arrays.toString(parent), access);
                return;
            }

            // make smaller root point to larger one
            access += 2;
            if (size[rootP] < size[rootQ]) {
                access++;
                parent[rootP] = rootQ;
                access += 2;
                size[rootQ] += size[rootP];
            } else {
                access++;
                parent[rootQ] = rootP;
                access += 2;
                size[rootP] += size[rootQ];
            }
            count--;
            System.out.printf("%s %d\n", Arrays.toString(parent), access);
        }
    }
}
