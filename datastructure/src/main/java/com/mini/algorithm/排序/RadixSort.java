package com.mini.algorithm.排序;

/**
 * @author wanghongchao
 * @time 2020/6/12
 */
public class RadixSort {


    public void radixSort(int[] arr){

    }

    public void privateCountingSort(int[] arr,int nu){
        int[] counts = new int[10];
        for (int i = 0; i < arr.length; i++) {
            counts[(arr[i]/nu)%10]++;
        }


    }

}
