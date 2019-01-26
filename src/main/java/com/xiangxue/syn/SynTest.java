package com.xiangxue.syn;

/**
 * @Author: lijunlei
 * @Date: 2019/1/26 19:36
 * @Description:
 */
public class SynTest {
    private volatile int age=100000;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private static class TestThread extends Thread{
        private SynTest synTest;

        public TestThread( SynTest synTest,String name) {
            super(name);
            this.synTest = synTest;
        }

        @Override
        public void run(){
            for (int i = 0; i <100000 ; i++) {
                synTest.test();

            }
            System.out.println(Thread.currentThread().getName()+" age= "+synTest.getAge());
        }

    }
    public synchronized void test(){
        age++;
        test2();
    }
    public synchronized   void test2(){
        age--;
    }

    public static void main(String[] args) {
        SynTest synTest=new SynTest();
        Thread endThread=new TestThread(synTest,"endThread");
        endThread.start();
        System.out.println(Thread.currentThread().getName()+" age= "+synTest.getAge());
        for (int i = 0; i < 100000; i++) {
            synTest.test2();
        }
        System.out.println(Thread.currentThread().getName()+" age= "+synTest.getAge());
    }
}
