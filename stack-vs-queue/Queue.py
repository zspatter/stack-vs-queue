class Queue:

    def __init__(self, capacity):
        self.front = self.size = 0
        self.rear = capacity - 1
        self.queue = [None] * capacity
        self.capacity = capacity

    def is_full(self):
        return self.size == self.capacity

    def is_empty(self):
        return self.size == 0

    def enqueue(self, element):
        if self.is_full():
            return False

        self.rear = (self.rear + 1) % (self.capacity)
        self.queue[self.rear] = element
        self.size = self.size + 1

    def dequeue(self):
        if self.is_empty():
            return False

        element = self.queue[self.front]
        self.front = (self.front + 1) % (self.capacity)
        self.size = self.size - 1
        return element

    def queue_front(self):
        if self.is_empty():
            return False

        return self.queue[self.front]

    def queue_rear(self):
        if self.is_empty():
            return False

        return self.queue[self.rear]
