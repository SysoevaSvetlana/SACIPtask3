import java.util.Random;

public class PriorityQueueLinkedList {
    private static Node head;

    public void insert(int x, int p) {
        Node newNode = new Node(x, p);
        if (head == null || head.priority < p) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.priority >= p) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public Node extractMax() {
        if (head == null) {
            return null;
        }
        Node maxNode = head;
        head = head.next;
        return maxNode;
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
