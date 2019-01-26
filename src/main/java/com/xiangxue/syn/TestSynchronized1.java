package com.xiangxue.syn;

/**
 * 类锁的修饰（静态）方法和代码块
 */
public class TestSynchronized1 {

    public synchronized void test1(){
        int i=5;
        while(i-->0){
            System.out.println(Thread.currentThread().getName()+" : "+i);
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){

            }
        }
    }

    public static synchronized void test2(){
        int i=5;
        while(i-->0){
            System.out.println(Thread.currentThread().getName()+" : "+i);
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){

            }
        }
    }

    public static void main(String[] args) {
        final TestSynchronized1 myt2 = new TestSynchronized1();
        Thread test1 = new Thread(new Runnable(){public void run(){myt2.test1();}},"test1");
        Thread test2 = new Thread(new Runnable(){public void run(){myt2.test2();}},"test2");
        test1.start();
        test2.start();
    }
}
