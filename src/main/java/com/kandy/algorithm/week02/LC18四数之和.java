package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC18四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //去重
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {

                //正确去重方法
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        //去重
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        while (left < right && nums[left] == nums[left + 1]) left++;

                        // 找到答案 双指针同时收缩
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

    //递归解法 n数之和
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 0, 4, target);
    }

    public List<List<Integer>> nSum(int[] nums, int start, int n, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int j = nums.length - 1;
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            if (n == 2) {
                while (i < j && nums[i] + nums[j] > target) j--;
                if (i < j && nums[i] + nums[j] == target) {
                    ans.add(Arrays.asList(new Integer[]{nums[i], nums[j]}));
                }
            } else {
                List<List<Integer>> others = nSum(nums, i + 1, n - 1, target - nums[i]);
                for (List<Integer> other : others) {
                    Integer[] temp = new Integer[n];
                    temp[0] = nums[i];
                    for (int k = 0; k < n - 1; k++) {
                        temp[k + 1] = other.get(k);
                    }
                    ans.add(Arrays.asList(temp));
                }
            }
        }
        return ans;
    }


}
