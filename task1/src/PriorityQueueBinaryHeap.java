import java.util.Random;

public class PriorityQueueBinaryHeap {
    private BinaryHeap heap;

    public PriorityQueueBinaryHeap() {
        heap = new BinaryHeap();
    }

    public void insert(int value, int priority) {
        heap.insert(value, priority);
    }

    public Element extractMax() {
        return heap.extractMax();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void fillRandom(int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int value = random.nextInt(100);
            int priority = random.nextInt(100);
            insert(value, priority);
        }
    }
}