import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class StackVsQueue
{
    public static void main(String[] args)
    {
        runComparison(new File("1k_ints.csv"), 1000);
        runComparison(new File("10k_ints.csv"), 10000);
        runComparison(new File("100k_ints.csv"), 100000);
        runComparison(new File("1000k_ints.csv"), 1000000);
    }

    /**
     * pushes all of the elements in the given file to the stack
     *
     * @param stack stack structure which will hold elements
     * @param file  data set of elements (must be CSV)
     */
    private static void pushElements(Stack stack, File file)
    {
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                for(String token : tokens)
                    stack.push(Integer.parseInt(token));
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * pops all of the elements currently in the passed stack
     *
     * @param stack stack containing elements
     */
    private static void popElements(Stack stack)
    {
        while (!stack.isEmpty())
            stack.pop();
    }

    /**
     * enqueues all of the elements in the given file to the queue
     *
     * @param queue queue passed to function to hold elements
     * @param file data set of elements (must be CSV)
     */
    private static void enqueueElements(Queue queue, File file)
    {
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                for(String token : tokens)
                    queue.enqueue(Integer.parseInt(token));
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * dequeues all of the elements currently in the passed queue
     *
     * @param queue queue containing elements
     */
    private static void dequeueElements(Queue queue)
    {
        while (!queue.isEmpty())
            queue.dequeue();

    }

    /**
     * times the insertion and deletion operations for the given data set
     * each time is measured in nanoseconds and the data set is tested 10 times
     *
     * @param file data set of elements (must be CSV)
     * @param capacity number of elements (sets capacity for stack)
     * @return long[][] array containing 2 elements:
     *              the first represents the push times for each iteration
     *              the second represents the pop times for each iteration
     */
    private static long[][] timeStack(File file, int capacity)
    {
        long[] pushElapsedTime = new long[10];
        long[] popElapsedTime = new long[10];

        for (int i = 0; i < 10; i++)
        {
            Stack stack = new Stack(capacity);
            long startTime = System.currentTimeMillis();
            pushElements(stack, file);
            pushElapsedTime[i] = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            popElements(stack);
            popElapsedTime[i] = System.currentTimeMillis() - startTime;
        }

        return new long[][] {pushElapsedTime, popElapsedTime};
    }

    /**
     * times the insertion and deletion operations for the given data set
     * each time is measured in nanoseconds, and the data set is tested 10 times
     *
     * @param file data set of elements (must be CSV)
     * @param capacity number of elements (sets capacity for queue)
     * @return long[][] array containing 2 elements:
     *              the first represents the enqueue times for each iteration
     *              the second represents the dequeue times for each iteration
     */
    private static long[][] timeQueue(File file, int capacity)
    {
        long[] enqueueElapsedTime = new long[10];
        long[] dequeueElapsedTime = new long[10];

        for (int i = 0; i < 10; i++)
        {
            Queue queue = new Queue(capacity);
            long startTime = System.currentTimeMillis();
            enqueueElements(queue, file);
            enqueueElapsedTime[i] = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            dequeueElements(queue);
            dequeueElapsedTime[i] = System.currentTimeMillis() - startTime;
        }

        return new long[][] {enqueueElapsedTime, dequeueElapsedTime};
    }

    /**
     * takes a collection of times and finds the average
     *
     * @param times 2D array containing a collection of insertion and deletion times
     * @return an array containing the average of all the insertion and deletion times
     */
    private static long[] averageTimes(long[][] times)
    {
        long insertionTime = 0;
        long deletionTime = 0;

        for (int i = 0; i < times[0].length; i++)
        {
            insertionTime += times[0][i];
            deletionTime += times[1][i];
        }

        insertionTime /= times[0].length;
        deletionTime /= times[1].length;
        return new long[] {insertionTime, deletionTime};
    }

    /**
     * runs a comparison between stacks and queues considering both insertion
     * and deletion operations using the given data set
     *
     * A summary is printed to the console indicating the file's name,
     * individual times for each run and the overall average time
     *
     * @param file data set to be tested (must be CSV)
     * @param capacity number of elements in the data set (sets stack/queue capacity)
     */
    private static void runComparison(File file, int capacity)
    {
        System.out.println(file.toString());
        long[][] stackTimes = timeStack(file, capacity);
        long[][] queueTimes = timeQueue(file, capacity);

        System.out.printf("Stack:\n\tPush times: %s\n", Arrays.toString(stackTimes[0]));
        System.out.printf("\tAverage: %,d\n\n", averageTimes(stackTimes)[0]);
        System.out.printf("\tPop times: %s\n", Arrays.toString(stackTimes[1]));
        System.out.printf("\tAverage: %,d\n\n", averageTimes(stackTimes)[1]);

        System.out.printf("Queue:\n\tEnqueue times: %s\n", Arrays.toString(queueTimes[0]));
        System.out.printf("\tAverage: %,d\n\n", averageTimes(queueTimes)[0]);
        System.out.printf("\tDequeue times: %s\n", Arrays.toString(queueTimes[1]));
        System.out.printf("\tAverage: %,d\n\n\n", averageTimes(queueTimes)[1]);
    }
}