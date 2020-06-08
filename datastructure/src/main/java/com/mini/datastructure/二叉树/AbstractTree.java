package com.mini.datastructure.二叉树;

import 二叉树.printer.BinaryTreeInfo;

/**
 * @author wanghongchao
 * @time 2020/5/19
 */
public abstract class AbstractTree implements BinaryTreeInfo {
  /*容量*/
  protected int size;

  /*根节点*/
  protected Node root;

  protected static final boolean RED = true;
  protected static final boolean BLACK = false;


  public void preorder(){
    preorder(root);
    System.out.println();
  }

  private void preorder(Node root){
    if(root == null) return;
    System.out.print(root.element);
    preorder(root.left);
    preorder(root.right);
  }

  public void inorder(){
    inorder(root);
    System.out.println();
  }
  private void inorder(Node root){
    if(root == null) return;
    inorder(root.left);
    System.out.print(root.element);
    inorder(root.right);
  }

  public void postorder(){
    postorder(root);
    System.out.println();
  }

  private void postorder(Node root){
    if(root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element);
  }


  @Override
  public Object root() {
    return root;
  }

  @Override
  public Object left(Object node) {
    return ((Node)node).left;
  }

  @Override
  public Object right(Object node) {
    return ((Node)node).right;
  }

  @Override
  public Object string(Object node) {
    Node n = (Node)node;
    Node parent = n.parent;
    String pval;
    if(parent == null){
      pval = "null";
    }else {
      pval = parent.element + "";
    }
    String color;
    if(n.color == RED){
      color = "RED";
    }else{
      color = "BLACK";
    }
    return n.element + "#" + pval + "#" +  color;
  }

  protected static class Node {
    int element;
    Node parent;
    Node left;
    Node right;
    int height = 1;
    boolean color = RED;

    Node(int element,Node parent){
      this.element = element;
      this.parent = parent;
    }

    public Node uncle(){
      Node parent = this.parent;
      if(parent == parent.parent.left){
        return parent.parent.right;
      }else {
        return parent.parent.left;
      }
    }
  }


  public abstract int size();

  public abstract boolean isEmpty();

  /**
   * 清空
   */
  public abstract void clear();

  /**
   * 删除节点
   */
  public abstract void remove(int element);

  /**
   * 添加节点
   */
  public abstract void add(int element);

  /**
   * 是否包含
   */
  public abstract boolean contains(int element);

  public abstract void rotateRight(Node node);

  public abstract void rotateLeft(Node node);

}
