package deque;

public class LinkedListDeque<Bob>{
        public class Node {
            Node pre;
            Bob item;
            Node next;

            public Node(Node p, Bob i, Node n){
                pre = p;
                item = i;
                next = n;
            }
        }

        private Node sentinel;
        private int size;

        public LinkedListDeque(){
            sentinel = new Node(null, null, null);
            sentinel.pre = sentinel;
            sentinel.next = sentinel;
            size = 0;
        }

        public LinkedListDeque(Bob item){
            sentinel = new Node(null, null, null);
            sentinel.pre = sentinel;
            sentinel.next = sentinel;

            sentinel.next = new Node(sentinel, item, sentinel);
            sentinel.pre = sentinel.next;
            size = 1;
        }

        public void addFirst(Bob item){
            Node p = sentinel.next;
            p.pre = new Node(sentinel, item, p);
            sentinel.next = p.pre;
            p.pre.next = p;
            p.pre.pre =sentinel;
            size++;
        }

        public void addLast(Bob item){
            Node p = sentinel.pre;
            p.next = new Node(p, item, sentinel);
            sentinel.pre = p.next;
            p.next.pre = p;
            p.next.next = sentinel;
            size++;
        }

        public boolean isEmpty(){
            if (size == 0){
                return true;
            }else{
                return false;
            }
        }

        public int size(){
            return size;
        }

        public Bob removeFirst(){

            if (size == 0){
                return null;
            }

            Bob first = sentinel.next.item;
            Node p = sentinel.next;

            sentinel.next = p.next;
            p.pre = null;
            p.next.pre = sentinel;
            p.next = null;

            size--;

            return first;
        }

        public Bob removeLast(){

            if (size == 0){
                return null;
            }

            Bob last = sentinel.pre.item;
            Node p = sentinel.pre;
            p.next = null;
            p.pre.next = sentinel;
            sentinel.pre = p.pre;
            p.pre = null;

            size--;

            return last;
        }

        public Bob get(int index){
            Node obj = sentinel.next;
            for (int i = 0; i < index; i++){
                obj = obj.next;
            }

            return obj.item;
        }

        public Bob getRecursive(int index){
            return nodeRecursive(index).item;
        }

        //help method of getRecursive
        public Node nodeRecursive(int index){
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