package com.xiangxue.vola;

/**
 * @Author: lijunlei
 * @Date: 2019/1/14 23:45
 * @Description: 演示violate无法提供操作的原子性
 */
public class VolatileUnsafe {

    private static class VolatileVar implements  Runnable{

        private volatile int a = 0;

        @Override
        public void run() {
            try {
                String name = Thread.currentThread().getName();
                a=a++;
                System.out.println(name+"========"+a);
                Thread.sleep(1000);
                a =a+1;
                System.out.println(name+"========"+a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        VolatileVar v=new VolatileVar();
        Thread t1=new Thread(v);
        Thread t2=new Thread(v);
        Thread t3=new Thread(v);
        Thread t4=new Thread(v);
        t1.start();t2.start();t3.start();t4.start();
    }
}
