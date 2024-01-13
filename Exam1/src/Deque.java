import java.util.Objects;

public class Deque {
    static final Integer MAX = 1000;
    Character arr[];
    Integer front;
    Integer rear;
    Integer size;

    public Deque(Integer size) {
        arr = new Character[MAX];
        front = -1;
        rear = 0;
        this.size = size;
    }

    // Checks whether Deque is full or not.
    Character get(Integer index) {
        Objects.checkIndex(index, size);
        return arr[index];
    }

    public Integer size() {
        return size;
    }

    boolean isFull() {
        return ((front == 0 && rear == size - 1) || front == rear + 1);
    }

    // Checks whether Deque is empty or not.
    boolean isEmpty() {
        return (front == -1);
    }

    // Inserts an element at front
    void insertFront(Character key) {
        // check whether Deque if full or not
        if (isFull()) {
            System.out.println("Sorry, that row is full.");
            return;
        }

        else {
            // If queue is initially empty
            if (front == -1) {
                front = size-1;
                rear = size-1;
            }
            else if (front == 0) // front is at first position of queue
                front = size - 1;
            else
                front = front - 1; // decrement front end by '1'
        }
        // insert current element into Deque
        arr[front] = key;
    }

    // function to inset element at rear end of Deque.
    void insertRear(Character key) {
        if (isFull()) {
            System.out.print("Sorry, that row is full.");
            return ;
        }

        else {
            // If queue is initially empty
            if (front == -1) {
                front = 0;
                rear = 0;
            }
            else if (rear == size - 1) // rear is at last position of queue
                rear = 0;
            else
                rear = rear + 1; // increment rear end by '1'
        }
        // insert current element into Deque
        arr[rear] = key;
    }

    // Deletes element at front end of Deque
    void deleteFront() {
        // check whether Deque is empty or not
        if (isEmpty()) {
            System.out.println("Underflow\n");
            System.out.println();
            return;
        }

        else {
            // Deque has only one element
            if (front == rear) {
                front = -1;
                rear = -1;
            }
            else
                // back to initial position
                if (front == size - 1)
                    front = 0;

                else // increment front by '1' to remove current
                    // front value from Deque
                    front = front + 1;
        }
    }

    // Delete element at rear end of Deque
    void deleteRear() {
        if (isEmpty()) {
            System.out.println("Underflow");
            System.out.println();
            return;
        }
        else {
            // Deque has only one element
            if (front == rear) {
                front = -1;
                rear = -1;
            }
            else if (rear == 0)
                rear = size - 1;
            else
                rear = rear - 1;
        }
    }

    // Returns front element of Deque
    int getFront() {
        // check whether Deque is empty or not
        if (isEmpty()) {
            System.out.println("Underflow");
            return -1;
        }
        return arr[front];
    }

    // function return rear element of Deque
    int getRear() {
        // check whether Deque is empty or not
        if (isEmpty() || rear < 0) {
            System.out.println("Underflow\n");
            return -1;
        }
        return arr[rear];
    }

    public boolean contains(Character customer) {
        for (int i = 0; i < arr.length; i++) {
            if (customer == arr[i]) {
                return true;
            }
        }
        return false;
    }
}