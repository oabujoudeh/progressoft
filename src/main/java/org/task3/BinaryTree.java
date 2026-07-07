package org.task3;

public class BinaryTree {
    Node root;


    public BinaryTree(){
        this.root = null;
    }

    public boolean accept(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }

        return root.accept(value);
    }

    public int depth(int value){

        if (root == null){
            return -1;
        }
        return root.depth(value, 0);
    }

    public int treeDepth(){
        if (root == null){
            return -1;
        }
        return root.treeDepth();
    }
}
