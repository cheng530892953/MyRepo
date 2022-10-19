import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        CompletableFuture<List<String>> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread1 start at :" + getCurrentTime());
            System.out.println("Thread1");
            List<String> res = getStrs();
            System.out.println("Thread1 end at :" + getCurrentTime());
            return res;
        },es);
        CompletableFuture<List<String>> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread2 start at :" + getCurrentTime());
            System.out.println("Thread2");
            List<String> res = getStrs();
            System.out.println("Thread2 end at :" + getCurrentTime());
            return res;
        },es);
        System.out.println(f1.join());
        System.out.println(f2.join());
        es.shutdown();
    }

    static List<String> getStrs(){
        return Arrays.asList("a", "b", "c");
    }

    static long getCurrentTime(){
        return new Date().getTime();
    }

}
