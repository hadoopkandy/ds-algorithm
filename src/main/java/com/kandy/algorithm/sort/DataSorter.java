package com.kandy.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序实现类
 */
public class DataSorter {
    /**
     * 直接插入排序
     * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，现在要把第n个数插到前面的有序数中，
     * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     */
    public  class InsertSort extends AbstractSort {
        @Override
        public  void sort(int[] a) {
            for (int i = 1; i < a.length; i++) {
                int j = i - 1;
                int temp = a[i];

                for (; j >= 0 && temp < a[j]; j--) {
                    a[j + 1] = a[j];  //将大于temp的值整体后移一个单位
                }
                a[j + 1] = temp;
            }
        }
    }

    /**
     * 希尔排序（缩小增量排序）
     * 基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.
     * 对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，
     * 在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     */
    public class ShellSort extends AbstractSort {
        @Override
        public  void sort(int[] a) {
            int d = a.length;
            while (true) {
               d=d/2;
                for (int x = 0; x < d; x++) {

                    for (int i = x + d; i < a.length; i += d) {
                        int j = i - d;
                        int temp = a[i];
                        for (; j >= 0 && temp < a[j]; j -= d) {
                            a[j + d] = a[j];
                        }
                        a[j + d] = temp;
                    }
                }

                if (d == 1) {
                    break;
                }
            }
        }
    }

    /**
     * 简单选择排序
     * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     */
    public class SelectSort extends AbstractSort {
        @Override
        public void sort(int[] a) {
            int  min, len = a.length;
            for (int i = 0; i < len - 1; i++) {  //i循环到倒数第二个
                min = i;
                for (int j = i + 1; j < len; j++) //j循环到最后一个
                    if (a[min] > a[j])
                        min = j; //获取最小数的下标
                swap(a,min,i);
            }
        }
    }

    /**
     * 堆排序
     * 基本思想：堆排序是一种树形选择排序，是对直接选择排序的有效改进。
     */
    public class HeapSort extends AbstractSort {
        @Override
        public  void sort(int[] a) {
            System.out.println("堆排序开始");
            int arrayLength = a.length;
            //循环建堆
            for (int i = 0; i < arrayLength - 1; i++) {
                //建堆
                buildMaxHeap(a, arrayLength - 1 - i);
                //交换堆顶和最后一个元素
                swap(a, 0, arrayLength - 1 - i);
                System.out.println(Arrays.toString(a));
            }
            System.out.println("堆排序结束");
        }

        //对data数组从0到lastIndex建大顶堆
        private void buildMaxHeap(int[] data, int lastIndex) {
            //从lastIndex处节点（最后一个节点）的父节点开始（最后一个非终端节点）

            for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
                //k保存正在判断的节点
                int k = i;
                /**
                 * 用while不用if的理由：
                 * 无法保证，biggerIndex 所在的节点就是比所有子节点都大，所以需要while循环，继续筛选,if只能保证当前
                 */
                //如果当前k节点的左子节点存在
                while (k * 2 + 1 <= lastIndex) {
                    //k节点的左子节点的索引
                    int biggerIndex = 2 * k + 1;
                    //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                    if (biggerIndex < lastIndex) {
                        //左右子节点比较，若果右子节点的值较大
                        if (data[biggerIndex] < data[biggerIndex + 1]) {
                            //biggerIndex总是记录较大子节点的索引
                            biggerIndex++;
                        }
                    }

                    //如果k节点的值小于其较大的子节点的值
                    if (data[k] < data[biggerIndex]) {
                        //交换他们
                        swap(data, k, biggerIndex);
                        //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                        k = biggerIndex;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 冒泡排序
     * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     */
    public class BubbleSort extends AbstractSort {
        @Override
        public  void sort(int[] a) {
            int temp = 0;
            for (int i = 0; i < a.length - 1; i++) {
                for (int j = 0; j < a.length - 1 - i; j++) {
                    if (a[j] > a[j + 1]) { //把大的值交换到后面
                       swap(a,j,j+1);
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
     */
    public class QuickSort extends AbstractSort {
        @Override
        public  void sort(int[] a) {
            if (a.length > 0) {    //查看数组是否为空
                _quickSort(a, 0, a.length - 1);
            }
        }

        public int getMiddle(int[] list, int low, int high) {
            int tmp = list[low];    //数组的第一个作为中轴
            while (low < high) {
                while (low < high && list[high] >= tmp) {
                    high--;
                }


                list[low] = list[high];   //比中轴小的记录移到低端
                while (low < high && list[low] <= tmp) {
                    low++;
                }

                list[high] = list[low];   //比中轴大的记录移到高端
            }
            list[low] = tmp;              //中轴记录到尾
            return low;                   //返回中轴的位置
        }

        public void _quickSort(int[] list, int low, int high) {
            if (low < high) {
                int middle = getMiddle(list, low, high);  //将list数组进行一分为二
                _quickSort(list, low, middle - 1);       //对低字表进行递归排序
                _quickSort(list, middle + 1, high);       //对高字表进行递归排序
            }
        }
    }

    /**
     * 归并排序
     * 基本思想：归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
     */
    public class MergeSort extends AbstractSort {
        @Override
        public void sort(int[] a) {
            sort(a, 0, a.length - 1);
        }

        public void sort(int[] data, int left, int right) {
            if (left < right) {
                //找出中间索引
                int center = (left + right) / 2;
                //对左边数组进行递归
                sort(data, left, center);
                //对右边数组进行递归
                sort(data, center + 1, right);
                //合并
                merge(data, left, center, right);
            }

        }

        /**
         * left～center，center+1～right
         */
        public void merge(int[] data, int left, int center, int right) {
            int[] tmpArr = new int[data.length];
            int mid = center + 1;
            //third记录中间数组的索引
            int third = left;
            int tmp = left;
            while (left <= center && mid <= right) {
                //从两个数组中取出最小的放入中间数组
                if (data[left] <= data[mid]) {
                    tmpArr[third++] = data[left++];
                } else {
                    tmpArr[third++] = data[mid++];
                }
            }

            //剩余部分依次放入中间数组
            while (mid <= right) {
                tmpArr[third++] = data[mid++];
            }

            while (left <= center) {
                tmpArr[third++] = data[left++];
            }

            //将中间数组中的内容复制回原数组
            while (tmp <= right) {
                data[tmp] = tmpArr[tmp++];
            }
            System.out.println(Arrays.toString(data));
        }
    }

    /**
     * 基数排序
     * 基本思想：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
     */
    public class RadixSort extends AbstractSort {
        @Override
        public  void sort(int[] a) {
            //首先确定排序的趟数;
            int max = a[0];
            for (int i = 1; i < a.length; i++) {
                if (a[i] > max) {
                    max = a[i];
                }
            }
            int time = 0;
            //判断位数;
            while (max > 0) {
                max /= 10;
                time++;
            }

            //建立10个队列;
            List<ArrayList> queue = new ArrayList<ArrayList>();
            for (int i = 0; i < 10; i++) {
                ArrayList<Integer> queue1 = new ArrayList<Integer>();
                queue.add(queue1);
            }

            //进行time次分配和收集;
            for (int i = 0; i < time; i++) {
                //分配数组元素;
                for (int j = 0; j < a.length; j++) {
                    //得到数字的第time+1位数;
                    int x = a[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                    ArrayList<Integer> queue2 = queue.get(x);
                    queue2.add(a[j]);
                    queue.set(x, queue2);
                }
                int count = 0;//元素计数器;
                //收集队列元素;
                for (int k = 0; k < 10; k++) {
                    while (queue.get(k).size() > 0) {
                        ArrayList<Integer> queue3 = queue.get(k);
                        a[count] = queue3.get(0);
                        queue3.remove(0);
                        count++;
                    }
                }

            }
        }
    }


}
