package com.czhe.sysmanage.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/9/10 16:08
 * @description
 **/
@Slf4j
public class MyThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new T().start();
        new Thread(() -> log.info("我是Runnable的lambda简化后的任务")).start();
        new R().run();
        FutureTask<String> target = new FutureTask<>(new C());
        new Thread(target).start();
        log.info(target.get());

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

