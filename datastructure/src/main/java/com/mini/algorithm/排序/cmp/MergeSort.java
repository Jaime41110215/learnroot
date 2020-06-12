package com.mini.algorithm.排序.cmp;

import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/12
 */
public class MergeSort {

    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,5,9,8,2,10};
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    /**
     * 归并排序
     * @param arr
     */
    public void mergeSort(int[] arr){
        int[] mergeArr = new int[(arr.length>>1) + 1];
        mergeSort(arr,0,arr.length,mergeArr);
    }

    public void mergeSort(int[] arr,int start,int end,int[] mergeArr){
        if(end - start <= 1) return;
        int mid = (start + end) >> 1;
        mergeSort(arr,start,mid,mergeArr);
        mergeSort(arr,mid,end,mergeArr);
        merge(arr,start,mid,end,mergeArr);
    }



    public void merge(int[] arr,int start,int mid,int end,int[] mergeArr){
        for (int i = start; i < mid; i++) {
            mergeArr[i-start] = arr[i];
        }
        //合并
        int li = 0;
        int le = mid - start;
        int ri = mid;
        int re = end;
        int ai = start;
        while (li < le){
            if(ri < re && arr[ri]<mergeArr[li]){
                arr[ai++] = arr[ri++];
            }else {
                arr[ai++] = mergeArr[li++];
            }
        }
    }

}
