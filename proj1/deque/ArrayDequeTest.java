package deque;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void add(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 10; i++){
            a.addLast(i);
        }
        a.printDeque();

        for (int i = 0; i < 10; i++){
            System.out.print(a.removeFirst() + " ");
        }
        System.out.println();
    }
}
