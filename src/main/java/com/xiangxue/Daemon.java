package com.xiangxue;


import java.util.concurrent.TimeUnit;

/**
 * 如果用户线程全部退出了，只剩下守护线程存在了，虚拟机也就退出了
 */
public class Daemon implements Runnable {


    private Thread[] t=new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i <t.length ; i++) {
            t[i]=new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn "+i+" started.");
        }

        for (int i = 0; i <t.length ; i++) {
            System.out.println("t["+i+"].isDaemon() = "+t[i].isDaemon()+"." );
        }

        while(true){
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread d=new Thread(new Daemon());
        d.setDaemon(true);//设置为守护线程
        d.start();
        System.out.println("d.isDaemon() = "+d.isDaemon() +".");
        TimeUnit.SECONDS.sleep(1);


    }
}


class DaemonSpawn implements Runnable{

    @Override
    public void run() {
        while(true){
            Thread.yield();
        }
    }
}
