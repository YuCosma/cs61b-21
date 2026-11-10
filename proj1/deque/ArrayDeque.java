package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {

    private T[] ts;
    private int size;
    private int INITI_CAPACITY = 8;

    private int capacity = INITI_CAPACITY;
    private int nextFirst = 7;
    private int nextLast = 0;

    public ArrayDeque() {
        ts = (T[]) new Object[INITI_CAPACITY];
        size = 0;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int i = 0;

        public boolean hasNext() {
            return i < size;
        }

        public T next() {
            T returnT = get(i);
            i++;
            return returnT;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean same = o instanceof Deque;
        if (!same) {
            return false;
        }
        if (this == o) {
            return true;
        }
        //Deque needs <>, from deepseek, or other is an object, actually.
        Deque<T> other = (Deque) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T th = this.get(i);
            //here was some trouble
            T ot = other.get(i);
            if (!th.equals(ot)){
                return false;
            }
        }
        return true;
    }

    //the expression of next when they are plused or subed
    //increment = 1 --> plus
    //increment = -1 --> sub
    private int cirOperate(int i, int increment) {
        switch (increment) {
            case 1:
                if (i == capacity - 1) {
                    i = 0;
                } else {
                    i++;
                }
                break;
            case -1:
                if (i == 0) {
                    i = capacity - 1;
                } else {
                    i--;
                }
                break;
            default:
        }
        return i;
    }

    public void addFirst(T i) {
        if (size == capacity) {
            resize(size * 2);
        }

        size++;
        ts[nextFirst] = i;
        nextFirst = cirOperate(nextFirst, -1);
    }

    public void addLast(T i) {
        if (size == capacity) {
            resize(size * 2);
        }

        size++;
        ts[nextLast] = i;
        nextLast = cirOperate(nextLast, 1);
    }

    private void resize(int c) {
        T[] temp = (T[]) new Object[size];
        for (int i = 0; i < size; i++){
            temp[i] = get(i);
        }

        ts = (T[]) new Object[c];
        System.arraycopy(temp, 0, ts, 0, size);

        this.capacity = c;

        nextFirst = c - 1;
        nextLast = size;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            size = 0;
            return null;
        }

        int i = cirOperate(nextFirst, 1);
        T f = ts[i];

        nextFirst = i;
        ts[i] = null;
        size--;

        //Need to cut array
        cutArray();

        return f;
    }

    public T removeLast() {
        if (isEmpty()) {
            size = 0;
            return null;
        }

        int i = cirOperate(nextLast, 1);
        T l = ts[i];

        nextLast = i;
        ts[i] = null;
        size--;

        //Need to cut array
        cutArray();

        return l;
    }

    //when capacity >= 16 and R > 4, cut the array
    private void cutArray() {
        double R = (double) capacity / (double) size;

        if (capacity > 15 && R > 4.0) {
            T[] temp = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                temp[i] = get(i);
            }
            capacity = capacity / 2;
            ts = (T[]) new Object[capacity];
            nextFirst = capacity - 1;
            nextLast = size;
            System.arraycopy(temp, 0, ts, 0, size);
        }
    }

    public T get(int index) {
        int i = nextFirst;
        int count = 0;

        while (count != index) {
            i = cirOperate(i, 1);
            count++;
        }

        i = cirOperate(i, 1);
        return ts[i];
    }

}