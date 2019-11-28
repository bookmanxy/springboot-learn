package cn.faceland.springbootexecutorbetter.basic;

import cn.faceland.springbootexecutorbetter.basic.DwRunnableAgent;
import cn.faceland.springbootexecutorbetter.basic.DwThreadBlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author watermelon
 * @Date 2019-11-28
 * @Description
 */
public class DwThreadPoolExecutor extends ThreadPoolExecutor {
    //真正任务保序队列
    private LinkedBlockingQueue<Runnable> realRunnables = new LinkedBlockingQueue<>();

    public DwThreadPoolExecutor(
            int poolSize
            , int maxSize
            , int keepAliveTime
            , int queueSize
            , float threshold
            , RejectedExecutionHandler handler) {

//        DwThreadBlockingQueue queue = new DwThreadBlockingQueue<Runnable>(queueSize, threshold);
        //调用基类，传入定制的双生产者队列
        super(poolSize, maxSize, keepAliveTime, TimeUnit.SECONDS,
                new DwThreadBlockingQueue<Runnable>(queueSize, threshold));

        super.setRejectedExecutionHandler((runnable, executor) -> {
            //做为超限生产者，添加超限任务
            if (!((DwThreadBlockingQueue)executor.getQueue()).additionalOffer(runnable)) {

                //超限任务添加失败，转由外部处理
                handler.rejectedExecution(runnable, executor);
            }
        });
    }

    //保证同序的思路就是：execute的Runnable 都统一放到新的队列中，然后从头开始消费
    @Override
    public void execute(Runnable command) {
        // 任务记录
        realRunnables.add(command);

        // 封装代理对象
        super.execute(new DwRunnableAgent(realRunnables));
    }
}
