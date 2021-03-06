package com.freetymekiyan.algorithms.level.medium;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the
 * sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * click to show more practice.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 * <p>
 * Company Tags: Facebook
 * Tags: Array, Two Pointers, Binary Search
 * Similar Problems: (H) Minimum Window Substring, (M) Maximum Size Subarray Sum Equals k
 */
public class MinSizeSubarraySum {

    /**
     * Two Pointers. O(n) Time.
     * Use start and end index to represent a window.
     * Store the window sum and min length.
     * Move the end index and update window sum.
     * If window sum >= s, means we are able to remove some value from ahead and get smaller window.
     * Move the start index and update sum.
     * Update min length.
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int start = 0, end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= s) {
                min = Math.min(min, end - start + 1);
                sum -= nums[start++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min; // Min length can be MAX, then no window is found.
    }

    /**
     * Binary Search. O(nlogn) Time.
     * Binary search for a window size.
     * Use a helper function to check whether this window exists.
     * If it exists, then we can have smaller window. Record minimum size.
     * If it doesn't, then we should make the window larger.
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int lo = 1;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isWindowExist(mid, nums, s)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return isWindowExist(lo, nums, s) ? lo : 0;
    }

    /**
     * Array, O(n) Time.
     * Iterate the array and check whether the sum is larger than s.
     * If larger, return true. Otherwise false.
     */
    private boolean isWindowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size) {
                sum -= nums[i - size];
            }
            sum += nums[i];
            if (sum >= s) {
                return true;
            }
        }
        return false;
    }
}