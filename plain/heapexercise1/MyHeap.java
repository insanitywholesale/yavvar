package heapexercise1;

public class MyHeap {

    private Object[] btree;
    private int index, capacity;

    public MyHeap() {
        this(100);
    }

    public MyHeap(int cap) {
        capacity = cap;
        btree = new Object[capacity + 1];
        index = 0;
    }

    public int size() {
        return index;
    }

    public boolean isEmpty() {
        return index == 00;
    }

    public boolean isFull() {
        return index == capacity;
    }

    public void insert(Object item) throws HeapFullException {
        if (isFull()) {
            throw new HeapFullException("Heap is now Full");
        }
        int father, son;
        son = ++index;
        btree[son] = item;  // αρχικά το item στο τέλος της heap
        father = son / 2;
        while (son > 1 && ((Comparable) btree[son]).compareTo((Comparable) btree[father]) > 0) {
            Object b = btree[father];
            btree[father] = btree[son];
            btree[son] = b;
            son = father;
            father = son / 2;
        }
    }

    public Object remove() throws HeapEmptyException {
        if (isEmpty()) {
            throw new HeapEmptyException("No  items in Heap");
        }
        int father, son;
        Object lastItem = btree[index];
        btree[index] = btree[1];
        Object removeItem = btree[1];
        index--;
        father = 1;
        if ((index > 2) && ((Comparable) btree[2]).compareTo((Comparable) btree[3]) > 0) {
            son = 2;
        } else {
            son = 3;
        }
        while (son <= index && ((Comparable) btree[son]).compareTo((Comparable) lastItem) > 0) {
            btree[father] = btree[son];
            father = son;
            son = father * 2;
            if (son + 1 <= index && ((Comparable) btree[son + 1]).compareTo((Comparable) btree[son]) > 0) {
                son = son + 1;
            }
        }
        btree[father] = lastItem;
        return removeItem;
    }
}
