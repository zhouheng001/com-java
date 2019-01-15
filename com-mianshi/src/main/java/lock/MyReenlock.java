package lock;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2019-01-15 18:46
 */
public class MyReenlock {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean locked = reentrantLock.isLocked();
                System.out.println("获取锁");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("释放锁");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.unlock();

            }
        });

        t1.start();
        t2.start();
    }
}
