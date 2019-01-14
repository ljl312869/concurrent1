package com.xiangxue;

import java.util.concurrent.*;

/**
 * @Author: lijunlei
 * @Date: 2019/1/14 21:04
 * @Description: 实现Callable接口 重写call()方法启动一个有返回值的线程
 */
public class NewThread1 {
    /**
     * 方式3 实现Callable接口
     */
    private static class UseCallable implements Callable<String> {


        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            System.out.println("I am use Callable");
            return "computed result";
        }


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable useCallable = new UseCallable();
        FutureTask<String> futureTask=new FutureTask<>(useCallable);
        Thread t=new Thread(futureTask);
        t.start();
        System.out.println(futureTask.get());


    }
}
