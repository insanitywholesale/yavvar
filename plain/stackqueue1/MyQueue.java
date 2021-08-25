package stackqueue1;

public class MyQueue {

    private int capacity;
    private Object[] queueArray;
    private int first = 0;
    private int last = 0;

    public MyQueue() {
        this(100);
    }

    public MyQueue(int cap) {
        capacity = cap;
        queueArray = new Object[capacity];
    }

    public int size() {
        return last - first;
    }

    public boolean isEmpty() {
        return first == last;
    }

    public Object front() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty");
        }
        return queueArray[first];
    }

    public void enqueue(Object item) throws QueueFullException {
        if (last == capacity) {
            throw new QueueFullException("Queue overflow");
        }
        last = last++;
        queueArray[last] = item;
    }

    public Object dequeue() throws QueueEmptyException {
        Object item;
        if (isEmpty()) {
            throw new QueueEmptyException("Queue is empty");
        }
        item = queueArray[first];
        first = first++;
        queueArray[first] = null;
        return item;
    }
}
