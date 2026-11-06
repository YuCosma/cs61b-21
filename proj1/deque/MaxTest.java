package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxTest {

    public static class NumComparator<Fuck> implements Comparator<Fuck> {
        public int compare(Fuck a, Fuck b) {
            return (int) a - (int) b;
        }
    }



    public static void main(String[] args){
        MaxArrayDeque<Integer> m = new MaxArrayDeque<>(new NumComparator<>());
        m.addFirst(1);
        m.addFirst(100);

        System.out.println(m.max());
    }
}
