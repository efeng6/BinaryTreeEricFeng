import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Eric Feng
 * @version: 4/17
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        //Runs helper starting at root
        return SearchHelper(val, root);
    }
    //Helper function
    public boolean SearchHelper(int val, BSTNode node) {
        //Returns true if the value is at this node
        if(node.getVal() == val)
        {
            return true;
        }
        //Returns false is reached end without finding value
        if(node.getLeft() == null && node.getRight() == null ){
            return false;
        }
        //If value is less than node value, it is known that the value has to be to the left
        if (val < node.getVal())
        {
            return SearchHelper(val, node.getLeft());
        }
        //Otherwise the value has to be to the right (not left or equal)
        return SearchHelper(val, node.getRight());
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        //Makes arraylist to return and passes root and list into helper
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        return getInorderHelper(root,list);
    }

    //Helper function, looks left, node, right
    public ArrayList<BSTNode> getInorderHelper(BSTNode n, ArrayList<BSTNode> list) {
        //Recursively looks at left if exists
        if (n.getLeft() != null)
        {
            getInorderHelper(n.getLeft(),list);
        }
        //Once no more left in this line, adds this to list (also means it adds at leaf)
        list.add(n);
        //Then goes through all the right sides and repeats
        if (n.getRight() != null)
        {
            getInorderHelper(n.getRight(),list);
        }
        //Returns edited list
        return list;
    }


    /**
     * @return ArrayList of BSTNodes in preorder
     */
    //Same thing as inorder, but it adds the node first, then looks left then right
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        return getPreorderHelper(root,list);
    }
    public ArrayList<BSTNode> getPreorderHelper(BSTNode n, ArrayList<BSTNode> list) {
        list.add(n);
        if (n.getLeft() != null)
        {
            getPreorderHelper(n.getLeft(),list);
        }
        if (n.getRight() != null)
        {
            getPreorderHelper(n.getRight(),list);
        }
        return list;
    }


    /**
     * @return ArrayList of BSTNodes in postorder
     */
    //Same thing as inorder and preorder, but it looks left then right then adds the original node
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        return getPostorderHelper(root,list);
    }
    public ArrayList<BSTNode> getPostorderHelper(BSTNode n, ArrayList<BSTNode> list) {
        if (n.getLeft() != null) {
            getPostorderHelper(n.getLeft(), list);
        }
        if (n.getRight() != null)
        {
            getPostorderHelper(n.getRight(),list);
        }
        list.add(n);
        return list;
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    //Just runs helper with root
    public void insert(int val) {
        insertHelper(val, root);
    }
    //Similar to the search function:
    //The tree is sorted, so if the val is greater than the current node,
    //Looks right if possible, if not possible, then adds right and exits
    //Same things for the left side
    public void insertHelper(int val, BSTNode node) {
        if (val > node.getVal())
        {
            if (node.getRight() == null)
            {
                node.setRight(new BSTNode(val));
                return;
            }
            insertHelper(val, node.getRight());
        }
        else if (val < node.getVal())
        {
            if (node.getLeft() == null)
            {
                node.setLeft(new BSTNode(val));
                return;
            }
            insertHelper(val, node.getLeft());
        }
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
