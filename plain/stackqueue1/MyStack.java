/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackqueue1;

/**
 *
 * @author user
 */
public class MyStack {

    private int capacity;
    private Object[] stackArray;
    private int top = -1;

    public MyStack() {
        this(100);
    }

    public MyStack(int cap) {
        capacity = cap;
        stackArray = new Object[capacity];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }
        return stackArray[top];
    }

    public void push(Object item) throws StackFullException {
        if (size() == capacity - 1) {
            throw new StackFullException("Stack overflow");
        }
        top = top++;
        stackArray[top] = item;
    }

    public Object pop() throws StackEmptyException {
        Object element;
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty");
        }
        element = stackArray[top];
        top = top--;
        stackArray[top] = null;
        return element;
    }

    public String toString() {
        String s = "";
        if (isEmpty()) {
            return "";
        }
        for (int i = 0; i < stackArray.length; i++) {
            s = s + stackArray[i] + " ";
        }
        return s;
    }
}
