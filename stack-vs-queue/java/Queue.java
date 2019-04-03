public class Queue
{
    private int front, rear, size;
    int capacity;
    int[] queue;

    public Queue()
    {
        this(1000);
    }

    public Queue(int capacity)
    {
        this.capacity = capacity;
        this.front = this.size = 0;
        this.rear = capacity -1;
        this.queue = new int[this.capacity];
    }

    public boolean isFull()
    {
        return this.size == this.capacity;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public void enqueue(int element)
    {
        if (isFull())
            return;

        this.rear = (this.rear++) % this.capacity;
        this.queue[this.rear] = element;
        this.size++;
    }

    public int dequeue()
    {
        if (isEmpty())
            return Integer.MIN_VALUE;

        int element = this.queue[this.front];
        this.front = (this.front++) % this.capacity;
        this.size--;
        return element;
    }

    public int front()
    {
        if (isEmpty())
            return Integer.MIN_VALUE;

        return this.queue[this.front];
    }

    public int rear()
    {
        if (isEmpty())
            return Integer.MIN_VALUE;

        return this.queue[this.rear];
    }
}
