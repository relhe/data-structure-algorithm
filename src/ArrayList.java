import java.lang.Object;
public class ArrayList<AnyType> implements Iterable<AnyType>{
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private AnyType [] theItems;
    public ArrayList(){
        clear();
    }
    /**
     * Reset the array list to its initial predefined capacity
     */
    public void clear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);

    }
    /**
     * Retrieve the size of the array list specified the number of data stored in the array list
     * @return value corresponding to the size of the array list
     */
    public int size(){
        return theSize;
    }
    /**
     * verify if the array list is empty -- complexity O(1)
     * @return boolean true if the array list is empty
     */
    public boolean isEmpty(){
        return size() == 0;
    }
    public void trimToSize(){
        ensureCapacity(size());
    }
    /**
     * Get a value from the array list at any valid specific index  -- complexity O(1)
     * throws an ArrayIndexOutOfBoundsException if index is not valid
     * @param index valid index to get the value
     * @return value from the from the array at the specified index
     */
    public AnyType get(int index){
        if(index < 0 || index >= theSize)
            throw new ArrayIndexOutOfBoundsException();
        else
            return theItems[index];
    }
    /**
     * Modify the value in the ArrayList at the index index  -- complexity O(1)
     * throws an ArrayIndexOutOfBoundsException if index is negative and more than the size
     * @param index the index where to modify the value
     * @param value the new value to be assigned
     * @return the old value that was modified
     */
    public AnyType set(int index, AnyType value){
        AnyType oldValue = null;
        if(index < 0 || index >= theSize) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            oldValue = theItems[index];
            theItems[index] = value;
        }
        return oldValue;
    }
    /**
     * Ensure that list is not at capacity
     * @param newCapacity an int representing new capacity of theItems array
     */
    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize) return;
        AnyType [] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for(int i=0; i<theSize; i++){
            theItems[i] = old[i];
        }
    }
    /**
     * Add a value at a valid index in the list -- complexity O(N)  -- worst case
     * Non-valid index throws an ArrayIndexOutOfBoundsException
     * @param index valid index to add the new value
     * @param value value to be added to the Array List
     */
    public void add(int index, AnyType value){
        if(index < 0 || index >= theSize)
            throw new ArrayIndexOutOfBoundsException();
        if(theItems.length == size())
            ensureCapacity(2 * size() + 1);
        for(int i = theSize; i > index; i--)
            theItems[i] = theItems[i-1];
        theItems[index] = value;
        theSize++;
    }
    /**
     * Add a value at the end of an array List -- complexity O(1)
     * @param value value to be added to the array list
     * @return boolean confirming the value has been added at the end of the array list
     */
    public boolean pushBack(AnyType value){
        add(size(), value);
        return true;
    }
    /**
     * Remove a value at a valid index of an array List  -- complexity O(N) -- worst case
     * @param index index where to remove the value
     * @return the value removed from the array list
     */
    public AnyType remove(int index){
        if(index < 0 || index >= theSize)
            throw new ArrayIndexOutOfBoundsException();
        AnyType removedValue = theItems[index];
        for(int i = index; i<theSize - 1; i++ )
            theItems[i] = theItems[i + 1];
        theSize--;
        return removedValue;
    }
    /**
     * Remove the las value of the array list  -- complexity O(1)
     */
    public void pop(){
        remove(theSize);
    }
    private class ArrayListIterator implements java.util.Iterator<AnyType>{
        private int current = 0;
        public boolean hasNext(){
            return current < size();
        }
        public AnyType next(){
            if(!hasNext())
                throw new java.util.NoSuchElementException();
            return theItems[current++];
        }
        public void remove(){
            ArrayList.this.remove(--current);
        }
    }
    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }
}