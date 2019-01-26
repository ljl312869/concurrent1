package com.xiangxue.safeend;

/**
 * @Author: lijunlei
 * @Date: 2019/1/26 18:01
 * @Description: 中断Runnable线程
 */
public class EndRunnable {

    private static class UseRunnable implements  Runnable{
        @Override
        public void run() {
            String threadName=Thread.currentThread().getName();
            while(!Thread.currentThread().isInterrupted()){
                System.out.println(threadName+"is run!");
            }
            System.out.println(threadName+" interrput flag is "+Thread.currentThread().isInterrupted());

        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable=new UseRunnable();
        Thread endThread=new Thread(useRunnable,"endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
