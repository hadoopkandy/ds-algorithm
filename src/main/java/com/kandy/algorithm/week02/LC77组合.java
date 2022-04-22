package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合 （选或不选，选K个）
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
 */
public class LC77组合 {
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<List<Integer>>();
        chosen = new ArrayList<Integer>();
        this.n = n;
        this.k = k;
        // 1..n 个数，从1开始遍历，到n+1终止
        findSubsets(1);
        return ans;
    }

    private void findSubsets(int i) {
        // 已经选了超过k个，
        // 或者把剩下的全选上也不够k个，退出
        if (chosen.size() > k || chosen.size() + n - i + 1 < k) return;
        if (i == n + 1) {
            ans.add(new ArrayList<Integer>(chosen));
            return;
        }
        findSubsets(i + 1);
        chosen.add(i);
        findSubsets(i + 1);
        chosen.remove(chosen.size() - 1);
    }

    private List<List<Integer>> ans;
    private List<Integer> chosen;
    private int n;
    private int k;
}
