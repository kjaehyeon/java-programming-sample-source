package jungsuk_practice;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class forkJoin {
    static final ForkJoinPool pool = new ForkJoinPool();
    public static void main(String[] args) {
        long from = 1L, to = 100_000_000L;

        SumTask task = new SumTask(from,to);
        long start = System.currentTimeMillis();

        Long result = pool.invoke(task);
        System.out.println("Elapsed Time(multi core) : " +
                (System.currentTimeMillis() - start));
        System.out.printf("%d ~ %d = %d",from,to,result);
        System.out.println();

        start = System.currentTimeMillis();
        result = 0L;
        for(long i = from; i <= to ; i++){
            result += i;
        }
        System.out.println("Elapsed Time(single core) : " +
                (System.currentTimeMillis() - start));
        System.out.printf("%d ~ %d = %d",from,to,result);

    }
}
class SumTask extends RecursiveTask<Long>{
    long from,to;

    SumTask(long from,long to){
        this.from = from;
        this.to = to;
    }
    @Override
    protected Long compute() {
        long size = to - from +1;

        if(size <= 5)
            return sum();

        long half = (from + to)/2;

        SumTask leftSum = new SumTask(from, half);
        SumTask rightSum = new SumTask(half+1, to);

        leftSum.fork();//leftSum을 작업큐에 넣는다.

        return rightSum.compute() + leftSum.join();
    }
    long sum(){
        long result = 0L;
        for(long i = from; i <= to; i++)
            result += i;
        return result;
    }
}