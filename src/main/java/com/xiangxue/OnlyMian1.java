package com.xiangxue;

import java.lang.management.*;

/**
 * @Author: lijunlei
 * @Date: 2019/1/14 21:01
 * @Description:
 */
public class OnlyMian1 {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo:
        threadInfos) {
            System.out.println("["+threadInfo.getThreadId()+"]"+" "+threadInfo.getThreadName());
        }
    }
}
