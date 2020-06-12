package com.mini.algorithm.排序.cmp;

import org.junit.Test;

/**
 * 冒泡排序
 * @author wanghongchao
 * @time 2020/6/11
 */
public class BubbleSort {

    //basic
    public void bubbleSort0(int[] arr){
        int end = arr.length-1;
        for (int i = end; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
    //优化
    public void bubbleSort1(int[] arr){
        int end = arr.length-1;
        for (int i = end; i > 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    swap = true;
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
            if(!swap){
                System.out.println(i);
                System.out.println("停止交换");
                break;
            }
        }
    }

    //优化2
    public void bubbleSort2(int[] arr){
        int end = arr.length-1;
        for (int i = end; i > 0; i--) {
            int swapIndex = -1;
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    swapIndex = j+1;
                }
            }
            if(swapIndex > 0){
                i = swapIndex;
            }
        }
    }


    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,9,8,2,10};
        bubbleSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

}
