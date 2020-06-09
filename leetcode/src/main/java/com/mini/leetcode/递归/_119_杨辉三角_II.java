package com.mini.leetcode.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 *
 * @author wanghongchao
 * @time 2020/6/9
 */
public class _119_杨辉三角_II {
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex == 0){
            return Collections.singletonList(1);
        }
        if(rowIndex == 1){
            return Arrays.asList(1,1);
        }

        List<Integer> pre = getRow(rowIndex - 1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < pre.size()-1; i++) {
            list.add(pre.get(i) + pre.get(i+1));
        }
        list.add(1);
        return list;
    }
}
