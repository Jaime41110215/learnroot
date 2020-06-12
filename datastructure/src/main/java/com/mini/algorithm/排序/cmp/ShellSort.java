package com.mini.algorithm.排序.cmp;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * 希尔排序
 * @author wanghongchao
 * @time 2020/6/12
 */
public class ShellSort {
    @Test
    public void test2(){
        int[] arr = {3,4,1,6,7,9,8,2,10};
        shellSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
    public void shellSort(int[] arr){
        List<Integer> list = stepLengthList(arr.length);
        for (int i = list.size()-1; i > 0 ; i--) {
            stepSort(arr,list.get(i));
        }
    }


    public void stepSort(int[] arr,int step){
        for (int col = 0; col < step; col++) {
            for (int j = step + col; j < arr.length; j+=step) {
                int index = j;
                while (index > 0 && arr[j] < arr[j-step]){
                    swap(arr,j,j-step);
                    index-=step;
                }
            }
        }
    }

    public void insertionSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index > 0 &&arr[index] < arr[index-1]){
                swap(arr,index,index-1);
                index--;
            }
        }
    }

    public void swap(int[] arr,int first,int second){
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }


    public List<Integer> stepLengthList(int size){
        List<Integer> stepLengthList = new ArrayList<>();
        int step = 1;
        while (step < size){
            stepLengthList.add(step);
            step <<= 1;
        }
        return stepLengthList;
    }

    @Test
    public void test(){
        List<Integer> list = stepLengthList(16);
        System.out.println(list);
    }

}
