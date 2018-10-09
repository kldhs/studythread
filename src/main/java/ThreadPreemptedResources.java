/**
 * ****************
 * 线程抢占资源问题*
 * ****************s
 * 加上synchronized后防止出现抢占资源情况
 */
public class ThreadPreemptedResources implements Runnable {
    int num = 10;
    @Override
    public synchronized void run() {
        while (true) {
            if (num > 0) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("剩余车票：" + num-- + "张");
            }else {
                break;
            }
        }
    }
    public static void main(String[] args) {
        ThreadPreemptedResources test = new ThreadPreemptedResources();
        Thread tA = new Thread(test);        //实例化4个线程
        Thread tB = new Thread(test);
        Thread tC = new Thread(test);
        Thread tD = new Thread(test);
        tA.start();        //启动线程
        tB.start();
        tC.start();
        tD.start();
    }

}
