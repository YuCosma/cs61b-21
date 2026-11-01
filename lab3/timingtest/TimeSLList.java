package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int count = 8;
        int op = 10000;
        for (int i = 0; i < count; i++){
            int N = (int)Math.pow(2.0, i) * 1000;
            double time =getLastLoop(N, op);

            Ns.addLast(N);
            times.addLast(time);
            opCounts.addLast(op);
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static double getLastLoop(int N, int op){
        SLList<Integer> SL = getList(N);
        Stopwatch sw = new Stopwatch();

        for (int i = 0; i < op; i++){
            SL.getLast();
        }
        return sw.elapsedTime();
    }


    private static SLList<Integer> getList(int N){
        SLList<Integer> list = new SLList<>();
        int nobody = 1;

        for (int i = 0; i < N; i++){
            list.addLast(nobody);
        }

        return list;
    }

}
