import java.lang.Runtime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.lang.InterruptedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class ConcurrentExecutor {

    public static void main(String[] argv) throws NoSuchAlgorithmException, InterruptedException, ExecutionException {
        Integer runs = Integer.valueOf(argv[1]);
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Random sRandom = new SecureRandom();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update("Hello, world!".getBytes());
        byte[] target = md.digest();
        List<HashWorker> workers = new ArrayList(runs.intValue());
        for(int i = 0; i<runs; i++) {
            workers.add(new HashWorker(sRandom,
                        "MD5",
                        16,
                        target,
                        10000));
        }

        List<Future<HashResult>> res = pool.invokeAll(workers);
        HashResult maxima = null;
        for (Future<HashResult> result : res) {
            HashResult dist = result.get();
            if (maxima == null || maxima.getDistance() < dist.getDistance()) {
                maxima = dist;
            }
        }

        System.out.println(maxima.toString());
    }
}


