import java.util.Arrays;

public class Stack
{
    private int capacity;
    private int top;
    private int[] stack;

    public Stack()
    {
        this(1000);
    }

    public Stack(int capacity)
    {
        this.capacity = capacity;
        this.top = -1;
        this.stack = new int[capacity];
    }

    public boolean isEmpty()
    {
        return top < 0;
    }

    public boolean isFull()
    {
        return this.top >= this.capacity - 1;
    }

    public boolean push(int element)
    {
        if (isFull())
        {
            System.out.println("Stack Overflow");
            return false;
        }

        stack[++top] = element;
        return true;
    }

    public int pop()
    {
        if (isEmpty())
        {
            System.out.println("Stack Underflow");
            return Integer.MIN_VALUE;
        }

        return this.stack[top--];
    }

    public int peak()
    {
        if (isEmpty())
        {
            System.out.println("Stack Underflow");
            return Integer.MIN_VALUE;
        }

        return this.stack[top];
    }

    @Override
    public String toString()
    {
        return Arrays.toString(this.stack);
    }
}
