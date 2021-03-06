
/**
 *
 * @author aditya
 */
import java.io.*;
import java.util.*;

public class BinaryTree {

    static class Node {

        int data;
        Node left;
        Node right;

        public Node(int item) {
            data = item;
            left = right = null;
        }

    }

    Node root;

    BinaryTree(int data) {
        root = new Node(data);
    }

    BinaryTree() {
        root = null;
    }

    public static int CountNonLeafNodes(Node root) {
        if (root == null) {
            return 0;
        } else {

            int rightcount = CountNonLeafNodes(root.right);
            int left_count = CountNonLeafNodes(root.left);

            if ((root.left != null) || (root.right != null)) {
                return 1 + left_count + rightcount;
            } else {
                return left_count + rightcount;
            }

        }

    }

    public static int TotalNodes(Node root) {
        if (root == null) {
            return 0;
        } else {

            int rightcount = TotalNodes(root.right);
            int left_count = TotalNodes(root.left);
            return 1 + left_count + rightcount;

        }

    }

    public static int CountLeafNodes(Node root) {
        return TotalNodes(root) - CountNonLeafNodes(root);

    }

    public int maxDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int left_depth = maxDepth(node.left);
            int right_depth = maxDepth(node.right);

            if (left_depth > right_depth) {
                return left_depth + 1;
            } else {
                return right_depth + 1;
            }
        }
    }
    
     public static void Mirror(Node root) {
        if (root == null) {
            return;
        } else {

            Node temp = root.left;
            root.left = root.right;
            root.right = temp;

            Mirror(root.right);
            Mirror(root.left);

        }

    }
    
    /* Prints the nodes having no siblings.  */
    private static ArrayList<Integer> list;

    public static void printSibling(Node node) {
        list = new ArrayList<>();
        printSiblingUtil(node);

        if (list.isEmpty()) {
            System.out.print("-1");
        } else {
            Collections.sort(list);
            for (Integer i : list) {
                System.out.print(i + " ");
            }

        }

    }

    private static void printSiblingUtil(Node node) {
        // Your code here
        if (node == null) {
            return;
        }
        if ((node.left != null) && (node.right == null)) {
            list.add(node.left.data);
        } else if ((node.left == null) && (node.right != null)) {
            list.add(node.right.data);
        }
        printSiblingUtil(node.left);
        printSiblingUtil(node.right);

    }
    
// function should return the sum of all the 
// leaf nodes of the binary tree 
    public static int sumLeaf(Node root) {
        if (root == null) {
            return 0;
        }
        int leftval = sumLeaf(root.left);
        int rightval = sumLeaf(root.right);

        if ((root.right == null) && (root.left == null)) {
            return root.data + leftval + rightval;
        } else {
            return leftval + rightval;
        }

    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        /*create root*/
        tree.root = new Node(1);

        /* following is the tree after above statement
 
              1
            /   \
          null  null     */
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);

        /* 2 and 3 become left and right children of 1
               1
             /   \
            2      3
          /    \    /  \
        null null null null  */
        
        tree.root.left.left = new Node(4);
        
        /* 4 becomes left child of 2
                    1
                /       \
               2          3
             /   \       /  \
            4    null  null  null
           /   \
          null null
         */
         
        System.out.println("Non leaf nodes: " + CountNonLeafNodes(tree.root));
        System.out.println("Total nodes: " + TotalNodes(tree.root));
        System.out.println("Leaf nodes: " + CountLeafNodes(tree.root));

        Mirror(tree.root);

        System.out.println(tree.root.data);
        System.out.println(tree.root.left.data);
        System.out.println(tree.root.right.data);
        System.out.println(tree.root.right.right.data);

        printSibling(tree.root);
    }

}
