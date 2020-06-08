package com.mini.datastructure.二叉树;

/**
 * @author wanghongchao
 * @time 2020/5/19
 */
public class AVLTree extends AbstractTree {

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空
     */
    public void clear() {
        root = null;
    }

    /**
     * 删除节点
     */
    public void remove(int element) {
        Node node = node(element);
        if (node == null) {
            return;
        }
        //度为2 寻找后继节点替代
        if (node.left != null && node.right != null) {
            Node s = successor(node);
            node.element = s.element;
            node = s;
        }
        Node replace = node.left == null ? node.right : node.left;

        //删除节点为根节点
        if (node.parent == null) {
            root = replace;
            if(root != null){
                root.parent = null;
            }
            return;
        }
        Node parent = node.parent;
        //删除节点的度为1
        if (replace != null) {
            replace.parent = node.parent;
            if (node == node.parent.left) {
                node.parent.left = replace;
            } else {
                node.parent.right = replace;
            }
        } else {//度为0
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        afterRemove(parent);
        size--;
    }

    private Node successor(Node node) {
        if (node.right != null) {
            Node pointer = node.right;
            while (pointer.left != null) {
                pointer = pointer.left;
            }
            return pointer;
        } else {
            while (node.parent != null && node == node.parent.right) {
                node = node.parent;
            }
            return node.parent;
        }
    }

    /**
     * 添加节点
     */
    public void add(int element) {
        Node node = root;
        if (root == null) {
            root = new Node(element, null);
            size++;
            return;
        }
        Node parent;
        int cmp;
        do {
            parent = node;
            cmp = element - node.element;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return;
            }
        } while (node != null);

        Node e = new Node(element, parent);
        if (cmp > 0) {
            parent.right = e;
        } else {
            parent.left = e;
        }
        afterAdd(e);
        size++;
    }

    /**
     * 是否包含
     */
    public boolean contains(int element) {
        return node(element) != null;
    }

    private Node node(int element) {
        Node node = root;
        while (node != null) {
            if (node.element > element) {
                node = node.left;
            } else if (node.element < element) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private void afterAdd(Node node){
        //g p n
        node = node.parent;
        while (node != null){
            if(isBalance(node)){
                updateHeight(node);
            }else {
                reBalance(node);
                break;
            }
            node = node.parent;
        }
    }

    private void afterRemove(Node node){
        while (node != null){
            if(isBalance(node)){
               updateHeight(node);
            }else {
                reBalance(node);
            }
            node = node.parent;
        }
    }

    private void reBalance(Node node){
        //失衡节点
        Node g = node;
        //p
        int hl = leftHeight(node);
        int hr = rightHeight(node);
        Node p;
        if(hl > hr){//L
            p = g.left;
            int phl = leftHeight(p);
            int phr = leftHeight(p);
            if(phl >= phr){//LL
                rotateRight(g);
            }else {//LR
                rotateLeft(p);
                rotateRight(g);
            }
        }else {//R
            p = g.right;
            int phl = leftHeight(p);
            int phr = rightHeight(p);
            if(phl > phr){//RL
                rotateRight(p);
                rotateLeft(g);
            }else {//RR
                rotateLeft(g);
            }
        }

    }

    public void rotateRight(Node node){
        Node g = node;
        Node parent = g.parent;
        Node p = node.left;
        //父节点
        if(parent == null){
            root = p;
            p.parent =null;
        }else {
            if(node == parent.left){
                parent.left = p;
            }else {
                parent.right = p;
            }
            p.parent = parent;
        }
        //右旋转
        g.parent = p;
        g.left = p.right;
        if(g.left != null){
            g.left.parent = g;
        }
        p.right = g;

        //更新高度
        updateHeight(g);
        updateHeight(p);

    }

    public void rotateLeft(Node node){
        Node g = node;
        Node parent = g.parent;
        Node p = node.right;
        //父节点
        if(parent == null){
            root = p;
            p.parent =null;
        }else {
            if(node == parent.right){
                parent.right = p;
            }else {
                parent.left = p;
            }
            p.parent = parent;
        }
        //左旋转
        g.parent = p;
        g.right = p.left;
        if(g.right != null){
            g.right.parent = g;
        }
        p.left = g;

        //更新高度
        updateHeight(g);
        updateHeight(p);
    }

    private void updateHeight(Node node){
        if(node == null) return;
        int hl = leftHeight(node);
        int hr = rightHeight(node);
        node.height = Math.max(hl,hr) + 1;
    }

    private boolean isBalance(Node node){
        if(node == null) return true;
        int hl = leftHeight(node);
        int hr = rightHeight(node);
        return Math.abs(hl-hr) <= 1;
    }

    private int leftHeight(Node node){
        return node.left == null ? 0 : node.left.height;
    }
    private int rightHeight(Node node){
        return node.right == null ? 0 : node.right.height;
    }
}
