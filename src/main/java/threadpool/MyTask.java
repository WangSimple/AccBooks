package threadpool;

public class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num){
        this.taskNum=num;
    }

    @Override
    public void run() {
        System.out.println("当前正在执行第"+taskNum+"个任务。");
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task"+taskNum+"执行完毕");
    }
}
