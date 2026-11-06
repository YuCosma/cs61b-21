package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item>, Deque<Item> {

    private Item[] items;
    private int size;
    final int INITI_CAPACITY = 8;

    private int capacity = INITI_CAPACITY;
    private int nextFirst = 7;
    private int nextLast = 0;

    public ArrayDeque(){
        items = (Item[]) new Object[INITI_CAPACITY];
        size = 0;
    }

    public Iterator<Item> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<Item>{
        private int i;

        public ArrayDequeIterator(){
            i = 0;
        }

        public boolean hasNext(){
            return i < size;
        }

        public Item next(){
            Item returnItem = get(i);
            i++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o){
        boolean same = o instanceof Deque;
        if (!same) {
            return false;
        }
        if (this == o){
            return true;
        }
        //Deque needs <>, from deepseek, or other is an object, actually.
        Deque<Item> other = (Deque)o;
        if (this.size() != other.size()){
            return false;
        }
        for (int i = 0; i < size; i ++){
            Item th = this.get(i);
            //here was some trouble
            Item ot = other.get(i);
            if (!th.equals(ot)){
                return false;
            }
        }
        return true;
    }

    //the expression of next when they are plused or subed
    //increment = 1 --> plus
    //increment = -1 --> sub
    private int cirOperate(int i, int increment){
        switch (increment){
            case 1:
                if (i == capacity - 1){
                    i = 0;
                }else {
                    i++;
                }
                break;
            case -1:
                if (i == 0){
                    i = capacity - 1;
                }else {
                    i--;
                }
                break;
            default:
        }
        return i;
    }

    public void addFirst(Item i){
        if (size == capacity){
            resize(size * 2);
        }

        size++;
        items[nextFirst] = i;
        nextFirst = cirOperate(nextFirst, -1);
    }

    public void addLast(Item i){
        if (size == capacity){
            resize(size * 2);
        }

        size++;
        items[nextLast] = i;
        nextLast = cirOperate(nextLast, 1);
    }

    private void resize(int capacity){
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < size; i++){
            temp[i] = get(i);
        }

        items = (Item[]) new Object[capacity];
        System.arraycopy(temp, 0, items, 0, size);

        this.capacity = capacity;

        nextFirst = capacity - 1;
        nextLast = size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public Item removeFirst(){
        if (isEmpty()){
            size = 0;
            return null;
        }

        int i = cirOperate(nextFirst, 1);
        Item f = items[i];

        nextFirst = i;
        items[i] = null;
        size--;

        //Need to cut array
        cutArray();

        return f;
    }

    public Item removeLast(){
        if (isEmpty()){
            size = 0;
            return null;
        }

        int i = cirOperate(nextLast, 1);
        Item l = items[i];

        nextLast = i;
        items[i] =null;
        size--;

        //Need to cut array
        cutArray();

        return l;
    }

    //when capacity >= 16 and R > 4, cut the array
    private void cutArray(){
        double R = (double)capacity / (double)size;

        if (capacity > 15 && R > 4.0){
            Item[] temp = (Item[]) new Object[size];
            for (int i = 0; i < size; i++){
                temp[i] = get(i);
            }
            capacity = capacity / 2;
            items = (Item[]) new Object[capacity];
            nextFirst = capacity - 1;
            nextLast = size;
            System.arraycopy(temp, 0, items, 0, size);
        }
    }

    public Item get(int index){
        int i = nextFirst;
        int count = 0;

        while (count != index){
            i = cirOperate(i, 1);
            count++;
        }

        i = cirOperate(i, 1);
        return items[i];
    }
}