package com.mini.algorithm.排序.cmp;

import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/12
 */
public class QuickSort {

    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,9,8,2,10};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
    /**
     * 快排
     * @param arr
     */
    public void quickSort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    /**
     *         // > pivot right    < pivot left
     *         // 3    6 5 4 2 1
     *         //     1 6 5 4 2 1
     *         //     1 6 5 4 2 6
     *         //     1 2 5 4 5 6
     *
     * @param arr
     * @param begin
     * @param end
     */
    public void sort(int[] arr,int begin,int end){
        if(begin >= end) return;

        int rand = begin + (int)(Math.random()*(end-begin));
        int pivot = arr[rand];
        arr[rand] = arr[begin];
        arr[begin] = pivot;

        int left = begin;
        int right = end;
        while (left < right){
            while (left < right){
                if(arr[right] < pivot){
                    arr[left++] = arr[right];
                    break;
                }else {
                    right--;
                }

            }

            while (left < right){
                if(arr[left] > pivot){
                    arr[right--] = arr[left];
                    break;
                }else {
                    left++;
                }
            }
        }
        arr[left] = pivot;
        sort(arr,begin,left-1);
        sort(arr,left+1,end);
    }

}
