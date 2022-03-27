import java.util.*;
import java.util.ArrayList;

public class Heap<AnyType> extends AbstractCollection<AnyType> implements Queue<AnyType> {
    public Heap(){
        currentSize = 0;
        cmp = null;
        data = new ArrayList<AnyType>(DEFAULT_CAPACITY + 1);
    }
    public Heap(Comparator<? super AnyType> comparator){
        currentSize = 0;
        cmp = comparator;
        data = new ArrayList<AnyType>(DEFAULT_CAPACITY + 1);
    }
    public Heap(Collection<? extends AnyType> coll){
        currentSize = coll.size();
        cmp = null;
        data = new ArrayList<AnyType>((currentSize + 2)*11/10);
        int i = 1;
        for(AnyType item:coll)
            data.add(i++, item);
        buildHeap();
    }
    public int size(){ return currentSize; }
    public void clear(){ currentSize = 0; }
    public Iterator<AnyType> iterator(){ return data.iterator();}
    public AnyType element(){
        if(isEmpty())
            throw new NoSuchElementException();
        return data.get(1);
    }
    public boolean add(AnyType newData){
        if(newData != null){
            data.add(currentSize, newData);
            currentSize++;
            percolateUp(currentSize - 1);
            return true;
        }
        return false;
    }
    public AnyType remove(){
        if(isEmpty())
            return null;
        AnyType output = data.get(1);
        data.remove(1);
        return output;
    }
    private void percolateDown(int hole){

    }
    private void buildHeap(){

    }
    private void percolateUp(int hole){

    }

    private int currentSize;
    private ArrayList<AnyType> data;
    private Comparator<?super AnyType> cmp;
    private static final int DEFAULT_CAPACITY = 100;

    private void doubleArray(){

    }
    private int compare(AnyType lhs, AnyType rhs){
        return 1;
    }
    public AnyType peek(){
        if(isEmpty())
            return null;
        return data.get(1);}
    public AnyType poll(){
        if(isEmpty())
            return null;
        return this.remove();
    }
    public boolean offer(AnyType data){
        return true;

    }

}

