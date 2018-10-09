/**
 * @author xs
 * @date 2018/10/9 14:01
 */
public class CreateThread {

    public static void main(String[] args) {


        //Thread thread1 = new CreateThreadByThread();
        //thread1.start();
        //Thread thread2 = new CreateThreadByThread();
        //thread2.start();
        //CreateThreadByRunnable thread3 = new CreateThreadByRunnable();
        //CreateThreadByRunnable thread4 = new CreateThreadByRunnable();
        //new Thread(thread3).start();
        //new Thread(thread4).start();

        //new Thread(() -> {
        //    System.out.print("我也是通过实现Runnable+lambad表达式创建的线程");
        //    System.out.println(Thread.currentThread().getName());
        //}).start();

        ThreadData threadData = new ThreadData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadData.Minus();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadData.Add();
            }
        }).start();

    }

}

class ThreadData implements Runnable {
    private int count = 100;

    @Override
    public void run() {
        while (true) {
            count=count-1;
            if(count<=0){
                break;
            }
            System.out.println(count);
        }
    }

    private int j = 0;

    public synchronized void Add() {
        j++;
        
    }

    public synchronized void Minus() {
        j--;
    }
}


class CreateThreadByThread extends Thread {
    @Override
    public void run() {
        System.out.print("我是通过继承Thread创建的线程");
        System.out.println(Thread.currentThread().getName());
    }
}


class CreateThreadByRunnable implements Runnable {
    @Override
    public void run() {
        System.out.print("我是通过实现Runnable创建的线程");
        System.out.println(Thread.currentThread().getName());
    }


}
