package heapexercise1;

public class HeapExercise1 {

    public static void main(String[] args) {
        MyHeap heap = new MyHeap(50);
        try {
            heap.insert(3);
            heap.insert(81);
            heap.insert(5);
            heap.insert(54);
            heap.insert(9);
            heap.insert(28);
            heap.insert(78);
        } catch (HeapFullException ex) {
            System.out.println(ex);
        }
        System.out.println(heap);
    }
}
