package com.xiangxue;

/**
 * @Author: lijunlei
 * @Date: 2019/1/27 11:09
 * @Description: 演示通过ThreadLocal能达到在每个线程中创建变量副本的
 */
public class ThreadLocalTest {
    ThreadLocal<Long> longLocat=new ThreadLocal<Long>(){
        protected Long initialValue(){
            return Thread.currentThread().getId();
        };

    };
    ThreadLocal<String> stringLocal=new ThreadLocal<String>(){
        protected String initialValue(){
            return Thread.currentThread().getName();
        };
    };

    public void set(){
        longLocat.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
    public long getLong(){
        return longLocat.get();
    }

    public String getString(){
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test=new ThreadLocalTest();
       // test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread1=new Thread(){
              @Override
              public void run(){
                  test.set();
                  System.out.println(test.getLong());
                  System.out.println(test.getString());
              }
        };
        thread1.start();
        thread1.join();

        Thread thread2=new Thread(){
            @Override
            public void run(){
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };
        thread2.start();
        thread2.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
