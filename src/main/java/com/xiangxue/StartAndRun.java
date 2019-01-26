package com.xiangxue;

/**
 * @Author: lijunlei
 * @Date: 2019/1/26 22:52
 * @Description: run()和普通方法是一样的 有谁调用就归属于哪一个线程 调用start()方法才会新启一个线程
 */
public class StartAndRun {
    public static class ThreadRun extends Thread{
        @Override
        public void run(){
            int i=90;
            while(i>0){
                try {
                    Thread.sleep(1000);
                    System.out.println("I am "+Thread.currentThread().getName()+" and now the i="+i--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun threadRun=new ThreadRun();
        threadRun.setName("beCalled");
        threadRun.run();
        //threadRun.start();

    }
}
