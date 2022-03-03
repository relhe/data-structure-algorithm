import java.util.Iterator;
public class DoubleLinkedList<AnyType> implements Iterable<AnyType> {
    private int theSize;
    private int modeCount;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
    /**
     * Double linked list constructor
     */
    public DoubleLinkedList(){
        clear();
    }
    /**
     * Evaluate the number of data store in the DoubleLinkedList
     * @return number of data stored in the DoubleLinkedList
     */
    public int size(){
        return theSize;
    }
    /**
     * Evaluate if there is no more data stored in the DoubleLinkedList
     * @return boolean indicating if there is no more data stored in the DoubleLinkedList
     */
    public boolean isEmpty(){
        return size()== 0;
    }
    /**
     * Reset the DoubleLinkedList to its initial state
     */
    public void clear(){
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modeCount++;
    }
    /**
     * Look for the node encapsulating the data
     * @param index int value where to look for the Node
     * @return Node encapsulating data
     */
    private Node<AnyType> getNode(int index){
        Node<AnyType> nodeToFind;
        if(index < 0 || index >= theSize)
            throw new IndexOutOfBoundsException();
        if(index<size()/2){
            nodeToFind = beginMarker.next;
            for(int i=0; i< index; i++)
                nodeToFind = nodeToFind.next;
        } else {
            nodeToFind = endMarker;
            for(int i=size(); i>index; i--)
                nodeToFind = nodeToFind.previous;
        }
        return nodeToFind;
    }
    /**
     * Provide the data stored in the DoubleLinkedList
     * @param ind position where to look for
     * @return data stored in DoubleLinkedList
     */
    public AnyType get(int ind){
        return getNode(ind).data;
    }
    /**
     * Modify a stored data in the DoubleLinkedList
     * @param index index where to find the data
     * @param newData data to replace the old data
     * @return old data replaced in the DoubleLinkedList
     */
    public AnyType set(int index, AnyType newData){
        Node<AnyType> nodeWithOldData= getNode(index);
        AnyType oldData = nodeWithOldData.data;
        nodeWithOldData.data = newData;
        return oldData;
    }
    /**
     * Add a new data to the DoubleLinkedList at a specified index
     * @param index specified position to store the data
     * @param value data to be stored in the DoubleLinkedList
     */
    public void add(int index, AnyType value){
        addBefore(getNode(index), value);
    }
    /**
     * Store a data at the end of the DoubleLinkedList
     * @param value data to be stored in the DoubleLinkedList
     * @return boolean indicating the data is stored in the DoubleLinkedList
     */
    public boolean pushBack(AnyType value){
        add(size(), value);
        return true;
    }
    /**
     * Remove a specified node from the DoubleLinkedList
     * @param node node to be removed from the DoubleLinkedList
     * @return data that was stored
     */
    private AnyType remove(Node<AnyType> node){
        node.previous.next = node.next;
        node.next.previous = node.previous;
        theSize--;
        modeCount++;
        return node.data;
    }
    /**
     * Remove the data stored at a specified position in the DoubleLinkedList
     * @param index position where the data is stored in the DoubleLinkedList
     * @return data that was stored in the DoubleLinkedList
     */
    public AnyType remove(int index){
        return remove(getNode(index));
    }
    /**
     * Store data before a specified node in the DoubleLinkedList
     * @param node the reference node
     * @param value the data to be stored
     */
    public void addBefore(Node<AnyType> node, AnyType value){
        Node<AnyType> newNode = new Node<AnyType>(value, node.previous, node);
        newNode.previous.next = newNode;
        node.previous = newNode;
        theSize++;
        modeCount++;
    }
    /**
     * Look if the DoubleLinkedList contains a specified data
     * @param data the data to look for
     * @return true if the DoubleLinkedList contains the data or false otherwise
     */
    public boolean contains(AnyType data){
        boolean isIn = false;
        Node<AnyType> searchItem = beginMarker;
        while(searchItem.next != endMarker){
            if(searchItem.data.equals(data)){
                isIn = true;
                break;
            }
        }
        return isIn;
    }
    /**
     * implementation of a Node to encapsulation data to store in the DoubleLinkedList
     */
    private static class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> previous;
        private Node<AnyType> next;
        public Node(AnyType dataC, Node<AnyType> previousC, Node<AnyType> nextC) {
            data = dataC;
            previous = previousC;
            next = nextC;
        }
    }
    /**
     * implementation of the DoubleLinkedList iterator 
     */
    private class DoubleLinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker;
        private int expectedModCount = modeCount;
        private boolean okToRemove = false;
        public boolean hasNext() {
            return current != endMarker;
        }
        public AnyType next() {
            if(modeCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        public void remove(){
            if(modeCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            DoubleLinkedList.this.remove(current.previous);
            okToRemove = false;
        }        
    }
    public Iterator<AnyType> iterator() {
        return new DoubleLinkedListIterator();
    }
}
