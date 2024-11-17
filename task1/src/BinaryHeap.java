import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap {
    private ArrayList<Element> heap;

    public BinaryHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int value, int priority) {
        Element element = new Element(value, priority);
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public Element extractMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        Element max = heap.get(0);
        Element last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return max;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index).getPriority() > heap.get(parentIndex).getPriority()) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).getPriority() > heap.get(largestIndex).getPriority()) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).getPriority() > heap.get(largestIndex).getPriority()) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != index) {
            Collections.swap(heap, index, largestIndex);
            heapifyDown(largestIndex);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}
