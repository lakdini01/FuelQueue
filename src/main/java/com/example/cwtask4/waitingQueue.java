package com.example.cwtask4;

public class waitingQueue {
    int front = -1;
    int rear = -1;
    int size = 5;

    FuelQueue [] waiting = new FuelQueue[size];


    boolean isFull() {
        if (front == 0 && rear == size - 1) {
            return true;
        }
        return front == rear + 1;
    }

    // Checks whether the queue is empty
    boolean isEmpty() {
        return front == -1;
    }

    // Adding an element
    void enQueue(FuelQueue element) {
        if (isFull()) {
            System.out.println("The waiting queue is also full!");
        } else {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % size;
            waiting[rear] = element;
            System.out.println(element.getFirstName() + " is added to the waiting queue!");
        }
    }

    // Removes an element
    FuelQueue deQueue() {
        FuelQueue element;
        if (isEmpty()) {
            System.out.println("The waiting queue is empty ");
            return (null);
        } else {
            element = waiting[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } /* Q only got one element, so we reset the queue after deleting it. */
            else {
                front = (front + 1) % size;
            }
            return (element);
        }
    }
}

