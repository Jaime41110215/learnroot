package com.mini.algorithm.排序.cmp;

import org.junit.Test;

/**
 * 选择排序
 * @author wanghongchao
 * @time 2020/6/11
 */
public class SelectionSort {

    public void selectionSort(int[] arr){
        int end = arr.length - 1;

        for (int i = end; i > 0 ; i--) {
            int maxIndex = 0;
            for (int j = 0; j <= i; j++) {
                if(arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = tmp;
        }
    }

    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,9,8,2,10};
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

}
