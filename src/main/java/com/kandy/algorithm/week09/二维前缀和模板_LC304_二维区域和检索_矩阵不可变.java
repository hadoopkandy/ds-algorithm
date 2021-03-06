package com.kandy.algorithm.week09;

public class 二维前缀和模板_LC304_二维区域和检索_矩阵不可变 {
    class NumMatrix {

        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            sum = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                    //二维数组前缀和 sum[i - 1][j - 1]是加法多出来的部分要减掉，下标从1开始
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
        }

        //二维数组子矩阵和 （p,q）为左上角 (i,j)为右下角的子矩阵中数的和
        //sum(p, q, i, j)=s[i][j]- s[i][q-1] - s[p-1][j] + s[p-1][q-1]
        //本题(row1,col1) 为左上角 (row2,col2)为右下角
        public int sumRegion(int row1, int col1, int row2, int col2) {
            //下标变成从1开始
            row1++;
            col1++;
            row2++;
            col2++;
            //sum[row1 - 1][col1 - 1] 是减法减多出来的部分，要加回来
            return sum[row2][col2] - sum[row2][col1 - 1] - sum[row1 - 1][col2] + sum[row1 - 1][col1 - 1];
        }

        private int[][] sum;
    }
}
