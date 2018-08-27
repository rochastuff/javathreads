package com.alldata.training.threads;

public class JoinMethod {

    public static void main (String[] args) {
        Thread t1 = new Thread(new ThreadProcess(), "Thread 1");
        Thread t2 = new Thread(new ThreadProcess(), "Thread 2");
        Thread t3 = new Thread(new ThreadProcess(), "Thread 3");

        /**Start Thread 1, and after 2 seconds Thread 2 will start
         *  or if Thread 1 it's dead, doesn't have to wait
         *  */
        try {
            t1.start();
            t1.join(2000);
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**Thread 3 will start when Thread 1 is dead
         *  */
        try {
            t1.join();
            t3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**All threads need to finish before Main thread finish
         */
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have finished");
    }
}

class ThreadProcess implements Runnable {

    @Override
    public void run() {

        try {
            System.out.println("Start " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("Finish " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

