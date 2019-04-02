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

def time_stack(data_set):
    elapsed_time = []
    for _ in range(10):
        start_time = time.time_ns()
        stack = Stack()
        push_elements(stack, data_set)
        pop_elements(stack)
        end_time = time.time_ns()
        elapsed_time.append(end_time - start_time)

    return elapsed_time

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
    stack_times = time_stack(filename)
    queue_times = time_queue(filename, n)

    stack_avg = average_time(stack_times)
    queue_avg = average_time(queue_times)

    print('\tStack times: \n\t' + str(stack_times))
    print(f'\t\tAverage time: {stack_avg: ,d} ns\n')
    print('\tQueue times: \n\t' + str(queue_times))
    print(f'\t\tAverage time: {queue_avg: ,d} ns\n')


run_comparison('1k_ints', 1000)
run_comparison('10k_ints', 10000)
run_comparison('100k_ints', 100000)
run_comparison('1000k_ints', 1000000)
