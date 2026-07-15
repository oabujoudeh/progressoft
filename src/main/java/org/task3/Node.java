package org.task3;

public class Node {

    // was package-private; nothing outside Node should be able to mutate the tree structure directly
    private int data;
    private Node left;
    private Node right;

    public Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public boolean accept(int value) {
        // value/this.data are ints, so exactly one of ==, <, > is true;
        // rewritten as if/else-if/else so there's no unreachable trailing branch
        if (value == this.data) {
            return false;
        } else if (value < this.data) {
            if (left == null) {
                left = new Node(value);
                return true;
            }

            return left.accept(value);
        } else {
            if (right == null) {
                right = new Node(value);
                return true;
            }

            return right.accept(value);
        }
    }

    public int depth(int value, int level){
        if (value == this.data){
            return level;
        } else if (value < this.data){
            if (left == null){
                return -1;
            }

            return left.depth(value, level+1);
        } else {
            if (right == null){
                return -1;
            }
            return right.depth(value, level+1);
        }
    }

    public int treeDepth(){
        int leftDepth = -1;
        int rightDepth = -1;

        if (left != null) {
            leftDepth = left.treeDepth();
        }
        if (right!= null) {
            rightDepth = right.treeDepth();
        }
        if (leftDepth > rightDepth){
            return leftDepth + 1;
        } else {
            return rightDepth + 1;
        }
    }
}
