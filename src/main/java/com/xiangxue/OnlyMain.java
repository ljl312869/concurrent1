package com.xiangxue;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author : ljl
 * 类说明：java多线程无处不在，Java天生就是多线程的
 * @Date :2019/1/13 14:55
 **/
public class OnlyMain {
    public static void main(String[] args) {
        //JVM线程管理接口 通过这个接口可以获取JVM中的所有线程
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo:threadInfos) {
            System.out.println("["+threadInfo.getThreadId()+"] ["+threadInfo.getThreadName()+"]");
        }
    }
}
