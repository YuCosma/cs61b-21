package deque;

import org.junit.Test;
import static org.junit.Assert.*;

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

    @Test
    public void remove(){
        ArrayDeque<Integer> a = new ArrayDeque<>();

        for (int i = 0; i < 8; i++) {
            a.addFirst(i);
        }
        assertTrue(a.removeLast() == 7);
    }
}
