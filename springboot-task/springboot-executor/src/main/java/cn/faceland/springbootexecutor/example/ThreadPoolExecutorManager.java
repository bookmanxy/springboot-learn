package cn.faceland.springbootexecutor.example;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author watermelon
 * @Date 2019-11-28
 * @Description
 */
public class ThreadPoolExecutorManager {

    private static void doThreadPool(){
//        ThreadPoolExecutor threadPoolExecutor =
//                new ThreadPoolExecutor(5,20,1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(5),new ThreadPoolExecutor.DiscardPolicy());
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5,20,1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(5));

        for(int i = 0 ; i < 100 ; i ++){
            final int temp = i;
             Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("i am" + temp);
//                    System.out.println(String.format("i am :%d,ActiveCount%d,队列剩余%d"
//                            ,temp
//                            ,threadPoolExecutor.getActiveCount()
//                            ,threadPoolExecutor.getQueue().remainingCapacity()
//                    ));;
                }
            };

             try{
                 threadPoolExecutor.execute(runnable);
                 System.out.println(String.format("i am :%d,ActiveCount:%d,队列剩余%d"
                         ,temp
                         ,threadPoolExecutor.getActiveCount()
                         ,threadPoolExecutor.getQueue().remainingCapacity()
                 ));;
             }catch (RejectedExecutionException e){
                 System.out.println(String.format("我被阻塞了:%d",temp));
             }
        }

        threadPoolExecutor.shutdown();
    }

    public static void main(String[] args) {
        doThreadPool();
    }
}
