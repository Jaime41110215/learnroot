package com.mini.datastructure.二叉树;

/**
 * @author wanghongchao
 * @time 2020/5/20
 */
public class RedBlackTree extends AbstractTree {

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public void remove(int element) {
        Node node = node(element);
        if(node == null) return;
        size--;

        //度为2
        if(node.left != null && node.right != null){
            Node s = successor(node);
            node.element = s.element;
            node = s;
        }
        //度为0
        Node replacement = node.left == null ? node.right : node.left;
        if(node.left == null && node.right == null){
            if(node.parent == null){
                root = null;
            }else{
                if(node == node.parent.left){
                    node.parent.left = null;
                }else{
                    node.parent.right = null;
                }
            }
        }else {//度为1
            if(node.parent == null){
                root = replacement;
                replacement.parent = null;
            }else{
                if(node == node.parent.left){
                    node.parent.left = replacement;
                }else {
                    node.parent.right = replacement;
                }
                replacement.parent = node.parent;
            }
        }
    }


    public void fixAfterDeletion(Node node,Node replacement){
        //
    }


    public Node node(int element){
        Node node = root;
        while (node != null){
            if(element > node.element){
                node = node.right;
            }else if(element < node.element){
                node = node.left;
            }else {
                return node;
            }
        }
        return null;
    }


    @Override
    public void add(int element) {
        if(root == null){
            root = new Node(element,null);
            setColor(root,BLACK);
            size++;
        }else{
            Node node = root;
            Node parent;
            int cmp;
            do{
                parent = node;
                if(element > node.element){
                    node = node.right;
                    cmp = 1;
                }else if(element < node.element){
                    node = node.left;
                    cmp = -1;
                }else {
                    node.element = element;
                    return;
                }
            }while (node != null);

            Node e = new Node(element,parent);

            if(cmp > 0){
                parent.right = e;
            }else{
                parent.left = e;
            }

            fixAfterInsertion(e);
            size++;
        }
    }

    public void fixAfterInsertion(Node node){

        if(node.parent == null){
            setColor(node,BLACK);
            return;
        }

        // rbr rb  br  b
        if(colorOf(node.parent) == BLACK){
            //do nothing
        }else {
            if(colorOf(node.uncle()) == BLACK){//旋转
                Node parent = node.parent;
               if (parent == parent.parent.left){//L
                   if(node == parent.left){//LL
                       setColor(parent,BLACK);
                       setColor(parent.parent,RED);
                       rotateRight(parent.parent);
                   }else {//LR
                       setColor(parent.parent,RED);
                       setColor(node,BLACK);
                       rotateLeft(parent);
                       rotateRight(node.parent);
                   }
               }else{//R
                   if(node == parent.left){//RL
                       setColor(parent.parent,RED);
                       setColor(node,BLACK);
                       rotateRight(parent);
                       rotateLeft(node.parent);
                   }else {//RR
                       setColor(parent,BLACK);
                       setColor(parent.parent,RED);
                       rotateLeft(parent.parent);
                   }
               }
            }else{//上溢
                setColor(node.parent,BLACK);
                setColor(node.uncle(),BLACK);
                setColor(node.parent.parent,RED);
                fixAfterInsertion(node.parent.parent);
            }
        }
    }

    public boolean colorOf(Node node){
        if(node == null) return BLACK;
        return node.color;
    }



    public void setColor(Node node,boolean color){
        node.color = color;
    }

    @Override
    public boolean contains(int element) {
        return false;
    }

    @Override
    public void rotateRight(Node node) {//LL
        Node g = node;
        Node p = g.left;

        p.parent = g.parent;
        if(p.parent == null){
            root = p;
        } else if(g == g.parent.left){
            p.parent.left = p;
        }else {
            p.parent.right = p;
        }
        g.left = p.right;
        if(g.left != null){
            g.left.parent = g;
        }
        p.right = g;
        g.parent = p;
    }

    @Override
    public void rotateLeft(Node node) {//RR
        Node g = node;
        Node p = node.right;

        p.parent = g.parent;
        if(p.parent == null){
            root = p;
        }else if(g == g.parent.left){
            p.parent.left = p;
        }else {
            p.parent.right = p;
        }

        g.right = p.left;
        if(g.right != null){
            g.right.parent = g;
        }
        p.left = g;
        g.parent = p;
    }

    public Node successor(Node node){
        if(node.right != null){
            Node pointer = node.right;
            while (pointer.left != null){
                pointer = pointer.left;
            }
            return pointer;
        }else {
            while (node.parent != null && node == node.parent.right){
                node = node.parent;
            }
            return node.parent;
        }

    }
}
