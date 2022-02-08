package lock;

import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2019-01-15 18:13
 */
public class MyCountLanch {
    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10000);
                    System.out.println("睡3秒钟");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                    System.out.println("睡2秒钟");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });


        System.out.println();
        t1.start();
        t2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("任务都执行完了");

    }
}
