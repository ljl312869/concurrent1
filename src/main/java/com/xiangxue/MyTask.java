package com.xiangxue;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        System.out.println("任务执行了，时间为："+new Date());
    }

    public static void main(String[] args) {
        System.out.println("当前时间："+new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Date date=calendar.getTime();
        MyTask task=new MyTask();
        Timer timer=new Timer();
        //Timer timer=new Timer(true);//修改Timer为守护线程后 mian停止 timer也就停止了
        timer.schedule(task,date);
    }
}
