public class Exercise_34 {
    public static void main(String[] args) {
        int n = 10000000;
        int runs = 100000;
        HotOrCold h = new HotOrCold(n);

        double guesses = 0;
        int guesses_max = 0;
        for (int i = 0; i < runs; i++) {
            h.reset();
            if (h.play() < 0) System.out.println("Fail");
            guesses += h.guesses;
            if (h.guesses > guesses_max) guesses_max = h.guesses;
        }

        System.out.printf("lg(%d) = %6.3f\n", n, Math.log(n) / Math.log(2));
        System.out.printf("Average guesses: %6.3f\n", guesses / runs);
        System.out.printf("Max guesses: %d\n", guesses_max);
    }

    public static class HotOrCold {
        int N;
        int secret;
        int guesses;

        public HotOrCold(int n) {
            N = n;
            reset();
        }

        // Set secret to new number, set guesses to zero
        public void reset() {
            secret = (int) Math.ceil(Math.random() * N);
            guesses = 0;
        }

        // Try to guess secret number between 1 and N
        public int play() {
            int lo = 1;
            int hi = N;
            while (hi >= lo) {
                int mid = lo + (hi - lo) / 2;
                int g = guess(mid);
                if (g > 0) hi = mid - 1;
                else if (g < 0) lo = mid + 1;
                else return mid;
            }
            return -1;
        }

        // Returns 0 if correct, positive if too big, negative if too small
        public int guess(int g) {
            guesses++;
            return g - secret;
        }
    }
}
