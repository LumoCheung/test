package cn.paypalm.concurrent;

import java.util.concurrent.CountDownLatch;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年4月12日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年4月12日	新建文件.
 * 
 * </pre>
 */
public class PerformanceTestTool {
	public long timecost(final int times, final Runnable task) throws InterruptedException {
        if (times <= 0) throw new IllegalArgumentException();
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch overLatch = new CountDownLatch(times);
        for (int i = 0; i < times; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        startLatch.await();
                        //
                        task.run();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    } finally {
                        overLatch.countDown();
                    }
                }
            }).start();
        }
        //
        long start = System.nanoTime();
        startLatch.countDown();
        overLatch.await();
        return System.nanoTime() - start;
    }
}
