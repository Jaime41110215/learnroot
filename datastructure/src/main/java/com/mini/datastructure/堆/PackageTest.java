package com.mini.datastructure.堆;

import com.mini.datastructure.堆.impl.BinaryHeap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/5/28
 */
public class PackageTest {

    @Test
    public void test(){
        Heap heap = new BinaryHeap();
        for (int i = 0; i < 10; i++) {
            heap.add(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(heap.get() + " ");
            heap.remove();
        }

    }


    @Test
    public void topK(){
        int[] arr = {992,1,2,3334,55,66,888,222,3434,55,23,4343,43,34345,555,666};

//        Heap heap = new BinaryHeap(5);
        PriorityQueue<Integer> queue = new PriorityQueue<>(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return  o1 - o2;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            if(queue.size() < 5){
                queue.offer(arr[i]);
            }else if(arr[i] > queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
//        heap.print();
        int length = queue.size();
        for (int i = 0; i < length; i++) {

            System.out.print(queue.poll() + " ");
            //queue.remove();
        }
        System.out.println();
        int[] arr2 = {992,1,2,3334,55,66,888,222,3434,55,23,4343,43,34345,555,666};

        Arrays.sort(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }

    @Test
    public void test2(){
        int[] arr = {992,1,2,3334,55,66,888,222,3434,55,23,4343,43,34345,555,666};
        int k = 5;

        int[] kMax = findKMax(arr, k);
        for (int i = 0; i < kMax.length; i++) {
            System.out.print(kMax[i] + " ");
        }
    }

    public static int[] findKMax(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return  o2 - o1;
            }
        });//队列默认自然顺序排列，小顶堆，不必重写compare

        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (pq.peek() > num) {//如果堆顶元素 < 新数，则删除堆顶，加入新数入堆
                pq.poll();
                pq.offer(num);
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k&&!pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }
        return result;
    }


    @Test
    public void testHeapify(){
        int[] arr = {992,1,2,3334,55,66,888,222,3434,55,23,4343,43,34345,555,666};
        Heap heap = new BinaryHeap(arr);
        int size = heap.size();
        for (int i = 0; i < size; i++) {
            System.out.print(heap.get() + " ");
            heap.remove();
        }
    }

}
