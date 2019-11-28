package cn.faceland.springbootexecutorbetter.basic;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author watermelon
 * @Date 2019-11-28
 * @Description  参考 简书：https://www.jianshu.com/p/896b8e18501b
 */
public class DwThreadBlockingQueue<T> extends LinkedBlockingDeque<T> {
    private int capacity;
    private int additionalCapacity;
    private int queueSize;

    //处理原则：capacity，additionalCapacity生产的时候分开生产、消费的时候统一消费
    public DwThreadBlockingQueue(int queueSize, float threshold) {
        super(queueSize);

        this.capacity = (int)(queueSize * threshold);
        this.additionalCapacity = queueSize - capacity;
        this.queueSize = queueSize;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    //普通生产者接口
    @Override
    public boolean add(T e) {
        if (super.size() >= capacity) {
            throw new IllegalStateException();
        }

        //并发下不足够精确
        return super.add(e);
    }

    //普通生产者接口
    @Override
    public boolean offer(T e) {
        //并发下不足够精确
        if (super.size() >= capacity) {
            return false;
        }

        return super.offer(e);
    }

    //普通生产者接口
    @Override
    public boolean offer(T e, long timeout, TimeUnit unit) throws InterruptedException {
        //并发下不足够精确
        if (super.size() >= capacity) {
            return false;
        }

        return super.offer(e, timeout, unit);
    }

    //普通生产者接口
    @Override
    public void put(T e) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    //符合LVS替换原则，弱化为BlockingQueue时为标准的单生产者、消费者队列
    @Override
    public int remainingCapacity() {
        int remain = super.remainingCapacity() - additionalCapacity;
        return remain >= 0 ? remain : 0;
    }

    //超限生产者接口
    public boolean additionalOffer(T e) {
        if (super.size() >= queueSize) {
            return false;
        }
        return super.offer(e);
    }
}
