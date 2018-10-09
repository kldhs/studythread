/**
 * @author xs
 * @date 2018/10/9 14:01
 * *******************************
 * 多个线程访问共享对象和数据的方式*
 * *******************************
 * 1、如果每个线程执行的代码相同，可以使用同一种Rinnable对象，这个
 * Runnable对象中有那个共享数据，例如卖票系统。
 * 2、如果每个线程执行的代码不同，这时候需要用不同的Runnable对象，
 * 有如下两种方式来实现这些Runnable对象之间的数据共享：
 *  ①、将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个Runnable对
 *      象。每个线程对共享数据的操作方法也分配到那个对象身上去完成，这样容易实现针对该
 *      数据进行的各个操作的互斥和通信。
 *  ②、将这些Runnable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，
 *      每个线程对共享数据的操作方法也分配给外部类，以便实现对共享数据进行的各个操作的互斥和通信，
 *      作为内部类的各个Runnable对象调用外部类的这些方法。
 * 3、上面两种方式的组合：将共享数据封装在另外一个对象中，每个线程对共享数据的操作方法也分配到那
 * 个对象身上去完成，对象作为这个外部类中的成员变量或方法中的局部变量，每个线程的Runnable对象
 * 作为外部类中的成员内部类或局部内部类。
 * 4、总之，要同步互斥的几段代码最好是分别放在几个独立的方法中，这些方法再放在同一个类中，这样比较容易实现它们之间的同步互斥和通信。
 * 极端且简单的方式，即在任意一个类中定义一个static的变量，这将被所有线程共享。
 */
public class CreateThread {
    public static void main(String[] args) {

        ThreadData threadData = new ThreadData();
        //第一种方式
        for (int i = 0; i <= 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        threadData.Add();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        threadData.Add();
                    }
                }
            }).start();
        }

        //第二种方式
        for (int i = 0; i <= 1; i++) {
            new Thread(new MyRunnableA(threadData)).start();
            new Thread(new MyRunnableB(threadData)).start();
        }

    }
}

class MyRunnableA implements Runnable {
    private ThreadData threadData;
    public MyRunnableA(ThreadData threadData) {
        this.threadData = threadData;
    }
    @Override
    public void run() {
        while (true) {
            threadData.Add();
        }
    }
}

class MyRunnableB implements Runnable {
    private ThreadData threadData;
    public MyRunnableB(ThreadData threadData) {
        this.threadData = threadData;
    }
    @Override
    public void run() {
        while (true) {
            threadData.Minus();
        }
    }
}

class ThreadData {
    private int j = 0;
    public synchronized void Add() {
        if (j <= 10) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
            System.out.println("j++：j=" + j);
        }
    }
    public synchronized void Minus() {
        if (j >= -10) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j--;
            System.out.println("j--：j=" + j);
        }
    }
}
