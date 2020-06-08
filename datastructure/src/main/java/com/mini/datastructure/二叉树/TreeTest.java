package com.mini.datastructure.二叉树;

import org.junit.Test;
import 二叉树.printer.BinaryTrees;

/**
 * @author wanghongchao
 * @time 2020/5/19
 */
public class TreeTest {

  @Test
  public void testAVL(){
    Integer data[] = new Integer[] {
        1,2,3,4,5,6,7,8,9,10,20,30,99,33,11
    };

    AVLTree bst = new AVLTree();
    for (int i = 0; i < data.length; i++) {
      bst.add(data[i]);
    }
    BinaryTrees.println(bst);
    System.out.println("========================================================================");

    for (int i = 0; i < data.length; i++) {
      System.out.println("========================================================================");
      System.out.println("delete " + data[i]);
      bst.remove(data[i]);
      BinaryTrees.println(bst);
      System.out.println("========================================================================");
    }
  }

  @Test
  public void testRedBlackTree(){
//    Integer data[] = new Integer[] {
//            1,2,3,4,5,6,7,8,9,10,20,30,99,33,11,23,44,55,666,999,202,101,202
//    };

    RedBlackTree bst = new RedBlackTree();
    for (int i = 0; i < 20; i++) {
      bst.add(i);

    }
    BinaryTrees.println(bst);
    for (int i = 10; i > 1; i--) {
      bst.remove(i);
    }
    bst.remove(11);

    BinaryTrees.println(bst);

  }

}
