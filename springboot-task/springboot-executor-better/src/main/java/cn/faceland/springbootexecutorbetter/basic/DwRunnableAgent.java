package cn.faceland.springbootexecutorbetter.basic;

/**
 * @author watermelon
 * @Date 2019-11-28
 * @Description
 */
import java.util.concurrent.BlockingQueue;

public class DwRunnableAgent implements Runnable{
    //保序任务队列
    private BlockingQueue<Runnable> realTasks = null;

    public DwRunnableAgent(BlockingQueue<Runnable> realTasks) {
        this.realTasks = realTasks;
    }

    @Override
    public void run() {
        //运行时绑定，头部获取任务，保证FIFO
        Runnable runnable = realTasks.remove();
        runnable.run();
    }
}