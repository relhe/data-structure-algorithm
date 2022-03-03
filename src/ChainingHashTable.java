import java.util.LinkedList;
public class ChainingHashTable<AnyType>{
    /**
     *  Default constructor
    */
    public ChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }
    /**
     * Parametrized constructor
     * @param size size indicates the number of internal linkedLists to allocate
     */
    public ChainingHashTable(int size){
        theLists = new LinkedList[nextPrime(size)]; // size to be a prime number, for an adequate distribution
        for(int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<AnyType>();  // define all linkedList elements
    }
    /**
     * Appends the specified data at the end of the internal linkedList  if it's not already present in ChainingHashtable
     * @param data data to be appended to the ChainingHashTable
     */
    public void add(AnyType data){
        LinkedList<AnyType> embedList= theLists[hash(data)];
        if(!embedList.contains(data))
            embedList.add(data);
        if(++currentSize > theLists.length)
            rehash();
    }
    /**
     * Remove the unique occurences of data in the ChainingHashTable if data is present
     * @param data the data to be removed
     */
    public void remove(AnyType data){
        LinkedList<AnyType> embedList= theLists[hash(data)];
        if(embedList.remove(data)) //returns true if the contains the specified value
            currentSize--;
    }
    /**
     * Find if any data has been stored in chainingHashTable
     * @param data data to look for in the chainingHashTable
     * @return boolean indicating if the data has been stored in chainingHashTable
     */
    public boolean contains(AnyType data){
        LinkedList<AnyType> embedList= theLists[hash(data)];  // Find the specific list that could have stored the data
        return embedList.contains(data);  // verify if the data is in that list
    }
    /**
     * Reset the chainingHashTable to its initial state
     */
    public void clear(){
        for(int i = 0; i < theLists.length; i++)
            theLists[i].clear();  // clear each individual internal linkedList
        currentSize = 0;
    }
    private static final int DEFAULT_TABLE_SIZE = 101;
    private LinkedList<AnyType> [] theLists;  // Array of linkedLists
    private int currentSize;  // Number of data stored in the ChainingHashTable
    private void rehash(){
        LinkedList<AnyType> [] oldList = theLists;
        theLists = new LinkedList[nextPrime(2*theLists.length)];
        currentSize = 0;
        for(int i = 0; i < oldList.length; i++){
            if(oldList[i] != null && oldList[i].size() > 0)
                theLists[i] = oldList[i];
        }
    }
    public int getSize(){
        return currentSize;
    }
    /**
     * Calculate hashcode value corresponding to any Object for which its class implements a hashCode method
     * @param data Object to calculate hashcode for
     * @return integer corresponding to the hashcode value
     */
    private int hash(AnyType data){  // hashCode to be implemented inside AnyType API
        int hashValue = data.hashCode();
        hashValue %= theLists.length;
        if(hashValue < 0)
            hashValue += theLists.length;
        return hashValue;
    }
    /**
     * Calculate hashcode value corresponding to a string for a specific size of storage table
     * @param key string value for which the hash code value is to be calculated
     * @param storageSize size of the storage table
     * @return integer corresponding to the hashcode value
     */
    public static int hash(String key, int storageSize){
        int hashValue = 0;
        for(int i=0; i<key.length(); i++)
            hashValue = 37*hashValue + key.charAt(i);  // why 37? prime number sufficiently large enough to facilitate adequate distribution
        hashValue %= storageSize;
        if(hashValue < 0)
            hashValue+=storageSize;
        return hashValue;
    }
    /**
     * Find the next prime number from any provided integer
     * @param value an integer value
     * @return the next prime number
     */
    private static int nextPrime(int value){
        int primeNumber = ++value;
        while(!isPrime(primeNumber)){
            primeNumber++;
        }
        return primeNumber;
    }
    /**
     * Determine if a given value is prime
     * @param value an Integer value
     * @return boolean indicating the value is prime or not
     */
    private static boolean isPrime(int value){
        if(value<=1) return false;
        if(value>1 && value<=3) return true;
        else{
            for(int i=2; i<=value/2; i++){
                if(value % i == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        ChainingHashTable<Integer> myList = new ChainingHashTable<Integer>(11);
        for(int i=0; i<10; i++)
            myList.add(i);
        System.out.print(myList.getSize());
        System.out.println(myList.contains(10));
        for(int i=4; i<45; i++)
            myList.add(i);
        myList.add(10);
        System.out.print(myList.getSize());
        myList.add(10);
        System.out.print(myList.getSize());
        myList.add(20);
        myList.add(34);
        myList.add(90);
        System.out.print(myList.getSize());
        myList.add(90);
        System.out.print(myList.getSize());
        System.out.println(myList.contains(10));
        System.out.println();
    }
}