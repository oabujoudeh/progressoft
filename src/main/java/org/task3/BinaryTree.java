package org.task3;

public class BinaryTree {
    // was package-private; only this class should ever touch the root reference
    private Node root;


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
        // Node#treeDepth() returns height in edges (0 for a single node), but the lab defines
        // "tree depth" as a level count (the sample 3-level tree should report 3), so +1 here.
        return root.treeDepth() + 1;
    }
}
