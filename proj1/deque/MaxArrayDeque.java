package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Iterable<T> {
    private Comparator<T> cM;
    public MaxArrayDeque(Comparator<T> c) {
        cM = c;
    }

    public T max() {
        return max(cM);
    }

    public T max(Comparator<T> c) {
        T m = get(0);
        for (int i = 0; i < size(); i++) {
            T now = get(i);
            boolean b = c.compare(now, m) > 0;
            if (b) {
                m = now;
            }
        }
        return m;
    }

}
