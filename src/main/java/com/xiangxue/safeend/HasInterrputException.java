package com.xiangxue.safeend;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: lijunlei
 * @Date: 2019/1/26 19:06
 * @Description: 抛出InterruptedException异常的时候，要注意中断标志位
 */
public class HasInterrputException {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");
    private static class UseThread extends Thread{
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run(){
            String name = Thread.currentThread().getName();
            while(!isInterrupted()){
                try {
                    System.out.println("UseThread:"+sdf.format(new Date()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(name+" interrupt flag is "+isInterrupted()+" at "+sdf.format(new Date()));
                    interrupt();
                    e.printStackTrace();
                } finally {
                }
            }
            System.out.println(name+" interrupt flag is "+isInterrupted()+" at "+sdf.format(new Date()));
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread endThread=new UseThread("endThread");
        endThread.start();
        System.out.println("Main:"+sdf.format(new Date()));
        Thread.sleep(800);
        System.out.println("Main begin interrupt thread "+sdf.format(new Date()));
        endThread.interrupt();
    }

}
