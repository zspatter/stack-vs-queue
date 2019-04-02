import csv, time
from Include.Stack import Stack
from Include.Queue import Queue


def push_elements(stack, filename):
    with open(filename + '.csv', 'r') as csv_file:
        reader = csv.reader(csv_file)
        for row in reader:
            for x in range(len(row)):
                try:
                    stack.push(int(row[x]))
                except ValueError:
                    pass


def pop_elements(stack):
    while not stack.is_empty():
        stack.pop()


def enqueue_elements(queue, filename):
    with open(filename + '.csv', 'r') as csv_file:
        reader = csv.reader(csv_file)
        for row in reader:
            for x in range(len(row)):
                try:
                    queue.enqueue(int(row[x]))
                except ValueError:
                    pass


def dequeue_elements(queue):
    while not queue.is_empty():
        queue.dequeue()


def time_stack(data_set):
    push_elapsed_time = []
    pop_elapsed_time = []

    for _ in range(10):
        stack = Stack()
        start_time = time.time_ns()
        push_elements(stack, data_set)
        end_time = time.time_ns()
        push_elapsed_time.append(end_time - start_time)

        start_time = time.time_ns()
        pop_elements(stack)
        end_time = time.time_ns()
        pop_elapsed_time.append(end_time - start_time)

    return push_elapsed_time, pop_elapsed_time


def time_queue(data_set, n):
    enqueue_elapsed_time = []
    dequeue_elapsed_time = []

    for _ in range(10):
        queue = Queue(n)
        start_time = time.time_ns()
        enqueue_elements(queue, data_set)
        end_time = time.time_ns()
        enqueue_elapsed_time.append(end_time - start_time)

        start_time = time.time_ns()
        dequeue_elements(queue)
        end_time = time.time_ns()
        dequeue_elapsed_time.append(end_time - start_time)

    return enqueue_elapsed_time, dequeue_elapsed_time


def average_time(times):
    total_time = 0
    for element in times:
        total_time += element

    return int(total_time / len(times))


def run_comparison(filename, n):
    print(filename + ' comparison:')
    push_times, pop_times = time_stack(filename)
    enqueue_times, dequeue_times = time_queue(filename, n)

    print('Stack:')
    print('\tPush times: ' + str(push_times))
    print(f'\tAverage: {average_time(push_times): ,d}\n')
    print('\tPop times: ' + str(pop_times))
    print(f'\tAverage: {average_time(pop_times): ,d}\n')

    print('Queue:')
    print('\tEnqueue times: ' + str(enqueue_times))
    print(f'\tAverage: {average_time(enqueue_times): ,d}\n')
    print('\tDequeue times: ' + str(dequeue_times))
    print(f'\tAverage: {average_time(dequeue_times): ,d}\n\n')


run_comparison('1k_ints', 1000)
run_comparison('10k_ints', 10000)
run_comparison('100k_ints', 100000)
run_comparison('1000k_ints', 1000000)
