import java.util.Random;

public class Exercise_19 {
    public static int[] localMinimum(int[][] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = lo + (hi - lo) / 2;
        int midc = mid;
        while (hi >= lo) {
            int[] min = localMinimum(nums, mid, 0, nums.length - 1);
            if (min[0] >= 0) return min;
            else {
                if (nums[mid - 1][midc] < nums[mid + 1][midc]) hi = mid - 1;
                else lo = mid + 1;
                mid = lo + (hi - lo) / 2;
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] localMinimum(int[][] nums, int row, int lo, int hi) {
        int[] fail = {-1, -1};
        int mid = lo + (hi - lo) / 2;
        if (lo > hi) return fail;
        if ((row == 0 || nums[row - 1][mid] > nums[row][mid]) &&
                (row == nums.length - 1 || nums[row + 1][mid] > nums[row][mid]) &&
                (mid == 0 || nums[row][mid - 1] > nums[row][mid]) &&
                (mid == nums.length - 1 || nums[row][mid + 1] > nums[row][mid])) return new int[]{row, mid};
        if (nums[row][mid - 1] < nums[row][mid + 1]) return localMinimum(nums, row, lo, mid - 1);
        else return localMinimum(nums, row, mid + 1, hi);
    }

    public static void main(String[] args) {
        int n = 100;
        int[][] nums = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = r.nextInt(n * 10);
            }
        }
        int[] min = localMinimum(nums);
        if (min[0] > 0) {
            System.out.format("nums[%d][%d] = %d\n", min[0], min[1], nums[min[0]][min[1]]);
            if (min[0] + 1 < nums.length - 1)
                System.out.format("nums[%d][%d] = %d\n", min[0] + 1, min[1], nums[min[0] + 1][min[1]]);
            if (min[1] + 1 < nums.length - 1)
                System.out.format("nums[%d][%d] = %d\n", min[0], min[1] + 1, nums[min[0]][min[1] + 1]);
            if (min[0] - 1 >= 0)
                System.out.format("nums[%d][%d] = %d\n", min[0] - 1, min[1], nums[min[0] - 1][min[1]]);
            if (min[1] - 1 >= 0)
                System.out.format("nums[%d][%d] = %d\n", min[0], min[1] - 1, nums[min[0]][min[1] - 1]);
        } else {
            System.out.format("Fail");
        }
    }
}
