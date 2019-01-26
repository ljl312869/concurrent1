package com.xiangxue.safeend;

/**
 * @Author: lijunlei
 * @Date: 2019/1/26 18:17
 * @Description:
 */
public class EndThread {
    private static class UseThread extends Thread{


        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run(){
             String name = Thread.currentThread().getName();
             while(!isInterrupted()){
                 System.out.println(name+" is run!");
                 System.out.println(name+" interrupt flag is "+isInterrupted());

             }
            System.out.println(name+" interrupt flag is "+isInterrupted());
         }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread=new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
