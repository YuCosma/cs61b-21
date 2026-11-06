package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Iterable<T> {
    Comparator<T> cM;
    public MaxArrayDeque(Comparator<T> c){
        cM = c;
    }

    public T max(){
        return max(cM);
    }

    public T max(Comparator<T> c){
        T m = get(0);
        for (int i = 0; i < size(); i++){
            T now = get(i);
            boolean b = c.compare(now, m) > 0;
            if (b){
                m = now;
            }
        }
        return m;
    }


    /**It's no need. Just for exercise.
     *
     */
    public Iterator<T> iterator(){
        return new MaxArrayDequeIerator();
    }

    private class MaxArrayDequeIerator implements Iterator<T>{
        private int i;

        public MaxArrayDequeIerator(){
            i = 0;
        }

        public boolean hasNext(){
            return i < size();
        }

        public T next(){
            T returnItem = get(i);
            i++;
            return returnItem;
        }
    }

}
