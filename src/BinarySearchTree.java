public class BinarySearchTree <AnyType extends Comparable<? super AnyType>>{
    /**
     * Binary search tree Constructor
     */
    public BinarySearchTree(){
        root = null;
    }
    /**
     * Remove all items from the binary search tree
     */
    public void clear(){ root = null; }
    /**
     * Verify if the binary search tree is empty
     * @return true if the binary search tree is empty; else false
     */
    public boolean isEmpty(){ return root == null; }
    /**
     * Getter of the root of the binary search tree
     * @return the root of the binary search tree
     */
    public BinaryNode<AnyType> getRoot(){ return root; }
    /**
     * Find any item in the binary search tree that matches data
     * @param data the data to search for in the binary search tree
     * @return node item that matches the data or null if not found
     */
    public BinaryNode<AnyType> find(AnyType data){
        return find(data, root);
    }
    /**
     * Find any item in the binary search tree that matches data starting the search at a certain node
     * @param data the data to search for in the binary search tree
     * @param node the node from which starting the search
     * @return node item that matches the data or null if not found
     */
    protected BinaryNode<AnyType> find(AnyType data, BinaryNode<AnyType> node){
        while(node != null){
            if(data.compareTo(node.data) < 0)
                node = node.leftNode;
            else if(data.compareTo(node.data)>0)
                node = node.rightNode;
            else
                return node; // Match
        }
        return null; // Not found
    }
    /**
     * Find the smallest item in the binary search tree
     * @param node the node from which starting the search
     * @return the smallest item in the binary search tree
     */
    protected BinaryNode<AnyType> findMin(BinaryNode<AnyType> node){
        if(node != null){
            while(node.leftNode != null)
                node = node.leftNode;  // the smallest item is at the leftmost node in the binary search tree
        }
        return node;
    }
    /**
     * Find the largest item the binary search tree
     * @param node the node from which starting the search
     * @return the largest item in the binary search tree
     */
    protected BinaryNode<AnyType> findMax(BinaryNode<AnyType> node){
        if(node != null){
            while(node.leftNode != null)
            node = node.rightNode; // the largest item is at the rightmost node in the binary search tree
        }
        return node;
    }
    /**
     * Add a data to the binary search tree
     * @param data the data to be inserted in the binary search tree
     */
    public void insert(AnyType data){
        root = insert(data, root);
    }
    /**
     * Add a data to the binary search tree starting at the at a certain node
     * @param data the data to be inserted in the binary search tree
     * @param node the node from which starting
     * @return the new node 
     */
    private BinaryNode<AnyType> insert(AnyType data, BinaryNode<AnyType> node){
        if(node == null)
            return new BinaryNode<AnyType> (data, null, null);
        if(data.compareTo(node.data) < 0)
            node.leftNode = insert(data, node.leftNode);
        else if(data.compareTo(node.data) > 0)
            node.rightNode = insert(data, node.rightNode);
        else; // when 0 => data == node.data, no double insertion of data
        return node;
    }
    /**
     * Remove a data from the binary search tree
     * @param data data to be removed to the the binary search tree if found
     */
    public void remove(AnyType data){
        root = remove(data, root);
    }
    /**
     * Remove the smallest item from the binary search tree
     */
    public void removeMin(){root = removeMin(root);}
    /**
     * Remove a data from the binary search tree starting at a certain node
     * @param data data to be removed to the the binary search tree
     * @param node node form which starting
     * @return the new node at the old position where the data has been removed
     */
    private BinaryNode<AnyType> remove(AnyType data, BinaryNode<AnyType> node){
        if(node == null)
            return node;
        if (data.compareTo(node.data) < 0)
            node.leftNode = remove(data, node.leftNode);
        else if (data.compareTo(node.data) > 0)
            node.rightNode = remove(data, node.rightNode);
        else if(node.leftNode != null && node.rightNode != null){
            node.data = findMin(node.rightNode).data;
            node.rightNode = removeMin(node.rightNode);
        }
        else
            node = (node.leftNode != null) ? node.leftNode : node.rightNode;
        return node;
    }
    /**
     * Remove the smallest item the binary search tree under any node
     * @param node the node from which to remove the smallest item under
     * @return the smallest node item being removed
     */
    private BinaryNode<AnyType> removeMin(BinaryNode<AnyType> node){
        //if(node == null)
           // throw new ItemNotFoundException();
        if(node.leftNode != null){
            node.leftNode = removeMin(node.leftNode);
            return node;
        }
        else
            return node.rightNode;
    }
    /**
     * Determine if any data is in the binary search tree
     * @param data data to be looking for
     * @param node node from which to start looking for
     * @return true if the data is in the binary search tree, else false
     */
    private boolean contains(AnyType data, BinaryNode<AnyType> node){
        if(find(data, node) != null)
            return true;
        return false;
    }
    /**
     * Determine if any data is in the binary search tree
     * @param data data to be looking for
     * @return true if the data is in the binary search tree, else false
     */
    public boolean contains(AnyType data){
        return contains(data, root);
    }
    /**
     * Compute the height of the binary search tree
     * @return the height of the binary search tree or -1 if the node is null or inexistent
     */
    public int height(){ return height(root);}
    /**
     * Compute the height of a certain node in the binary search tree
     * @param node node for which determining the height
     * @return the height of the binary search tree or -1 if the node is null or inexistent
     */
    private int height(BinaryNode<AnyType> node){
        if(node == null)
            return -1;
        else{
            return 1 + Math.max(height(node.leftNode), height(node.rightNode));
        }
    }
    /**
     * Traverse the binary search tree -- pre-order -- 
     */
    public void printTree(){ 
        if(isEmpty()){ System.out.print("Binary search tree is empty");}
        else{
            printPreOrder(root);
        }
    }
    /**
     * Post-order traversal printing of binary search tree
     * @param node node from which start printing
     */
    protected void printPostOrder(BinaryNode<AnyType> node){
        if(node != null){
            printPostOrder(node.leftNode);
            printPostOrder(node.rightNode);
            System.out.print(node.data);
        }
    }
    /**
     * Pre-order traversal printing of binary search tree
     * @param node node from which start printing
     */
    protected void printPreOrder(BinaryNode<AnyType> node){
        if(node != null){
            System.out.print(node.data + " ");
            printPreOrder(node.leftNode);
            printPreOrder(node.rightNode);
        }
    }
    /**
     * Post-order printing of binary search tree
     * @param node node from which start printing
     */
    protected void printOrder(BinaryNode<AnyType> node){
        if(node != null){
            printOrder(node.leftNode);
            System.out.print(node.data + " ");
            printOrder(node.rightNode);
        }
    }

    private BinaryNode<AnyType> root;
    public static class BinaryNode<AnyType>{
        BinaryNode(AnyType data){
            this(data, null, null);
        }
        BinaryNode(AnyType theData, BinaryNode<AnyType> leftNd, BinaryNode<AnyType> rightNd){
            data = theData;
            leftNode = leftNd;
            rightNode = rightNd;
        }
        AnyType data;
        BinaryNode<AnyType> leftNode;
        BinaryNode<AnyType> rightNode;
    }
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(24);
        tree.insert(20);
        tree.insert(30);
        tree.insert(25);
        tree.insert(19);
        tree.insert(23);
        tree.insert(34);
        tree.insert(29);
        tree.insert(36);
        tree.insert(32);
        System.out.println("\n");
        tree.printTree();
        System.out.println("\nThis binary search tree height is: " + tree.height()); 
        System.out.println("\n");
        tree.removeMin();
        tree.printTree();
        tree.remove(19);
        System.out.println("\n");
        tree.printTree();
        System.out.println("\n");
        System.out.println("\nDoes the binary search tree contain 19: " + tree.contains(19));
        System.out.println("\nDoes the binary search tree contain 34: " + tree.contains(34));
        System.out.println("\n");
        tree.remove(30);
        tree.printTree();
    }
}