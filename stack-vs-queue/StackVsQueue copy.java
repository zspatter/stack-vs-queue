import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class StackVsQueue
{
    public static void main(String[] args)
    {
        runComparison(new File("1k_ints.csv"), 1000);
//        runComparison(new File("10k_ints.csv"), 10000);
//        runComparison(new File("100k_ints.csv"), 100000);
//        runComparison(new File("1000k_ints.csv"), 1000000);

    }

    private static void pushElements(Stack stack, File file)
    {
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                for(String token : tokens)
                {
                    //Print all tokens
                    stack.push(Integer.parseInt(token));
                }
            }
            // scanner.useDelimiter(",");
            // int counter = 0;
            // while (scanner.hasNext())
            // {
            //     if (counter % 20 != 0)
            //         stack.push(Integer.parseInt(scanner.next()));
            //      else
            //         scanner.next();

            //     counter++;
            // }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(stack.toString());
    }

    private static void popElements(Stack stack)
    {
        while (!stack.isEmpty())
            stack.pop();
        // System.out.println(stack.toString());
    }

    private static void enqueueElements(Queue queue, File file)
    {
        try (Scanner scanner = new Scanner(file))
        {
            scanner.useDelimiter(",");
            int counter = 0;

            while (scanner.hasNext())
            {
                if (counter % 20 != 0)
                    queue.enqueue(Integer.parseInt(scanner.next()));
                else
                    scanner.next();

                counter++;
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void dequeueElements(Queue queue)
    {
        while (!queue.isEmpty())
            queue.dequeue();
    }

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
