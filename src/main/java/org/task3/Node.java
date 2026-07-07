package org.task3;

public class Node {

    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public boolean accept(int value) {

        if (value == this.data) {
            return false;
        }

        if (value < this.data) {

            if (left == null) {
                left = new Node(value);
                return true;
            }

            return left.accept(value);
        }

        if (value > this.data) {

            if (right == null) {
                right = new Node(value);
                return true;
            }

            return right.accept(value);
        }

        return false;
    }

    public int depth(int value, int level){
        if (value == this.data){
            return level;
        }

        if (value < this.data){
           if (left == null){
               return -1;
           }

           return left.depth(value, level+1);
        }

        if (value > this.data){
            if (right == null){
                return -1;
            }
            return right.depth(value, level+1);
        }

        return -1;
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