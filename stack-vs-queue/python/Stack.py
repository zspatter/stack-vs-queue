"""
Python implementation of stack
"""


class Stack:

    def __init__(self):
        self.stack = []

    def is_empty(self):
        return len(self.stack) == 0

    def push(self, element):
        self.stack.append(element)

    def peak(self):
        if self.is_empty():
            return False

        return self.stack[len(self.stack)]

    def pop(self):
        if self.is_empty():
            return False

        return self.stack.pop()
