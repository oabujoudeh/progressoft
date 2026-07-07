package org.task3;

public class Main {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        // Build the tree
        tree.accept(10);
        tree.accept(6);
        tree.accept(18);
        tree.accept(4);
        tree.accept(8);
        tree.accept(15);
        tree.accept(21);

        // Test duplicate
        System.out.println(tree.accept(10));

        // Test depth
        System.out.println("Depth of 10: " + tree.depth(10));
        System.out.println("Depth of 6: " + tree.depth(6));
        System.out.println("Depth of 4: " + tree.depth(4));
        System.out.println("Depth of 21: " + tree.depth(21));
        System.out.println("Depth of 99: " + tree.depth(99));


        System.out.println("Tree depth: " + tree.treeDepth());
    }
}