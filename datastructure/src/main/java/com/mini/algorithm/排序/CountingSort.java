package com.mini.algorithm.排序;

import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/12
 */
public class CountingSort {

    public void countingSort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }

        int[] carr = new int[max+1];

        for (int i = 0; i < arr.length; i++) {
            carr[arr[i]]++;
        }

        for (int i = 1; i < carr.length; i++) {
            carr[i] += carr[i-1];
        }

        int[] output = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            output[--carr[arr[i]]] = arr[i];
        }

        for (int i = 0; i < output.length; i++) {
            arr[i] = output[i];
        }
    }


    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,9,8,2,10,99};
        countingSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

}
