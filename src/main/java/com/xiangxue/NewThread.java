package com.xiangxue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {
    /**
     * 方式一：继承Thread
     */
    private static class UseThread extends  Thread{
        @Override
        public void run(){
            System.out.println("I am extend Thread");
        }
    }

    /**
     * 实现Runnable
     */
    private static class UseRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }
    }

    private static class UseCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("I am implements Callable<T>");
            return "Result for Callable<T>";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseThread useThread=new UseThread();
        Thread useRunnable=new Thread(new UseRunnable());
        FutureTask<String> futureTask = new FutureTask<>(new UseCallable());
        Thread useCallable=new Thread(futureTask);
        useThread.start();
        useRunnable.start();
        useCallable.start();
        System.out.println(futureTask.get());

    }

}
