package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        Node pre;
        T item;
        Node next;

        public Node(Node p, T i, Node n) {
            pre = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int i;

        public boolean hasNext(){
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


    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;

        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.pre = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        Node p = sentinel.next;
        p.pre = new Node(sentinel, item, p);
        sentinel.next = p.pre;
        p.pre.next = p;
        p.pre.pre = sentinel;
        size++;
    }

    public void addLast(T item) {
        Node p = sentinel.pre;
        p.next = new Node(p, item, sentinel);
        sentinel.pre = p.next;
        p.next.pre = p;
        p.next.next = sentinel;
        size++;
    }

    public int size(){
        return size;
    }

    public T removeFirst() {

        if (size == 0) {
            return null;
        }

        T first = sentinel.next.item;
        Node p = sentinel.next;

        sentinel.next = p.next;
        p.pre = null;
        p.next.pre = sentinel;
        p.next = null;

        size--;

        return first;
    }

    public T removeLast() {

        if (size == 0){
            return null;
        }

        T last = sentinel.pre.item;
        Node p = sentinel.pre;
        p.next = null;
        p.pre.next = sentinel;
        sentinel.pre = p.pre;
        p.pre = null;

        size--;

        return last;
    }

    public T get(int index) {
        Node obj = sentinel.next;
        for (int i = 0; i < index; i++){
            obj = obj.next;
        }

        return obj.item;
    }

    public T getRecursive(int index) {
        return nodeRecursive(index).item;
    }

    //help method of getRecursive
    public Node nodeRecursive(int index) {
        Node obj = sentinel.next;

        if (index == 0){
            return obj;
        }

        return nodeRecursive(index - 1).next;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

}