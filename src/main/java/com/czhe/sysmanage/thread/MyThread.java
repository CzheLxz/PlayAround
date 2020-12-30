package com.czhe.sysmanage.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/9/10 16:08
 * @description
 **/
@Slf4j
public class MyThread {

    private static int count = 0;

    private static Object lock = new Object();

    public static native void yield();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      /*  new T().start();
        new Thread(() -> log.info("我是Runnable的lambda简化后的任务")).start();
        new R().run();
        FutureTask<String> target = new FutureTask<>(new C());
        new Thread(target).start();
        log.info(target.get());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 5000; i > 0; i--) {
                synchronized (lock) {
                    count--;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count: " + count);*/

        /*Runnable r1 = () -> {
            int cou = 0;
            for (; ; ) {
                log.info("------------------1 " + cou++);
            }
        };
        Runnable r2 = () -> {
            int cou = 0;
            for (; ; ) {
                //Thread.yield();
                log.info("       ------------------2 " + cou++);
            }
        };

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");
        t1.setPriority(Thread.NORM_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();*/
       /* Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("------------------");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设为true表示守护线程，当主线程结束后，守护线程也结束。
        // 默认是false，当主线程结束后，thread继续运行，程序不停止
        //thread.setDaemon(true);
        thread.start();
        thread.sleep(3000);
        thread.currentThread();*/
        //log.info(".........................结束");
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        });
        t.start();
        t.join();
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");


    }


}

@Slf4j
class T extends Thread {
    @Override
    public void run() {
        log.info("我是继承Thread的任务");
    }
}

@Slf4j
class R implements Runnable {

    @Override
    public void run() {
        log.info("我是实现Runnable的任务");
    }
}

@Slf4j
class C implements Callable<String> {

    @Override
    public String call() {
        log.info("我是实现Callable的任务");
        return "success";
    }
}

