package cn.faceland.springbootexecutorbetter.example;

import cn.faceland.springbootexecutorbetter.basic.DwThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author watermelon
 * @Date 2019-11-28
 * @Description
 */
public class ThreadPoolExecutorBetter {

    private static void doThreadPool(){
//        ThreadPoolExecutor threadPoolExecutor =
//                new ThreadPoolExecutor(5,20,1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(5),new ThreadPoolExecutor.DiscardPolicy());
        DwThreadPoolExecutor threadPoolExecutor =
                new DwThreadPoolExecutor(
                        5,
                        10,
                        100,
                        20,
                        0.75f,
                        new ThreadPoolExecutor.AbortPolicy());

        for(int i = 0 ; i < 100 ; i ++){
            final int temp = i;
             Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        if(temp >=5 && temp <20){
                            Thread.sleep(3000);
                            System.out.println("i am queue " + temp);
                        }else{
                            Thread.sleep(500);
                            System.out.println("i am" + temp);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

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
