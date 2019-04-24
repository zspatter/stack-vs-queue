/**
 * Zachary Patterson
 * Lab 8 - Data Structures
 * 2019-04-24
 * INFO 211
 */

import java.util.Arrays;
import java.util.LinkedList;

public class MinMaxStack
{
    private int capacity;
    private int top;
    private int[] stack;
    private LinkedList<Integer> minimum;
    private LinkedList<Integer> maximum;

    public MinMaxStack()
    {
        this(1000);
    }

    public MinMaxStack(int capacity)
    {
        this.capacity = capacity;
        this.top = -1;
        this.stack = new int[capacity];
        this.minimum = new LinkedList<>();
        this.maximum = new LinkedList<>();
    }

    public boolean isEmpty()
    {
        boolean isEmpty = top < 0;

        if (isEmpty)
        {
            this.minimum.clear();
            this.maximum.clear();
        }

        return isEmpty;
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
        } else if (isEmpty())
        {
            this.maximum.addFirst(element);
            this.minimum.addFirst(element);
        }

        if (this.minimum.getFirst() >= element)
            this.minimum.addFirst(element);
        else if (this.maximum.getFirst() <= element)
            this.maximum.addFirst(element);


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

        if(this.minimum.getFirst() == this.stack[top])
            this.minimum.removeFirst();
        if (this.maximum.getFirst() == this.stack[top])
            this.maximum.removeFirst();

        return this.stack[top--];
    }

    public int top()
    {
        if (isEmpty())
        {
            System.out.println("Stack Underflow");
            return Integer.MIN_VALUE;
        }

        return this.stack[top];
    }

    public int getMin()
    {
        if (!isEmpty())
            return this.minimum.getFirst();

        System.out.println("Stack is empty!");
        return Integer.MAX_VALUE;
    }

    public int getMax()
    {
        if (!isEmpty())
            return this.maximum.getFirst();

        System.out.println("Stack is empty!");
        return Integer.MIN_VALUE;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(this.stack);
    }
}
