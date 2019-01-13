package com.xiangxue.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : ljl
 * @Date :2019/1/13 15:34
 * 类说明：
 **/
public class DBPoolTest {
    static DBPool pool=new DBPool(10);

    //控制器：控制main线程将会等待所有的Woker结束后才能继续执行
    static CountDownLatch end;

    static class Woker implements  Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Woker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while(count>0){
                try{
                    //从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    //分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConn(1000);
                    if(connection!=null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }

                    }else{
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()+"等待超时！");
                    }
                }catch(Exception e){

                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        //线程数量
        int threadCount=50;
        end =new CountDownLatch(threadCount);
        int count=20;//每个线程的操作次数
        AtomicInteger got=new AtomicInteger();
        AtomicInteger notGot=new AtomicInteger();
        for (int i = 0; i <threadCount ; i++) {
            Thread thread=new Thread(new Woker(count,got,notGot),"worker_"+i);
            thread.start();
        }
        end.await();
        System.out.println("总共尝试了： "+(threadCount*count));
        System.out.println("拿到连接的次数: "+got);
        System.out.println("没能拿到连接的次数： "+notGot);

    }
}
