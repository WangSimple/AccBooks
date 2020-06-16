package threadpool;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args){
        ThreadPoolExecutor executor=new ThreadPoolExecutor(5,10,200,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        //ThreadPoolExecutor executor= Executors.newCachedThreadPool();newCachedThreadPool将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。
        //ThreadPoolExecutor executor= Executors.newSingleThreadExecutor();newSingleThreadExecutor将corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue；
        //ThreadPoolExecutor executor= Executors.newFixedThreadPool(1);newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue；
        //ThreadPoolExecutor executor= Executors.newScheduledThreadPool(1);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());//设置拒绝策略
        //1、直接丢弃（DiscardPolicy）
        //2、丢弃队列中最老的任务(DiscardOldestPolicy)。
        //3、抛异常(AbortPolicy)
        //4、将任务分给调用线程来执行(CallerRunsPolicy)。
        //CountDownLatch
        for (int i=0;i<15;i++){
            MyTask task=new MyTask(i+1);
            executor.execute(task);
            System.out.println("线程池中线程数"+executor.getPoolSize()
                    +",队列中等待执行的任务数"+executor.getQueue().size()
                    +",已经执行完的任务数"+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
