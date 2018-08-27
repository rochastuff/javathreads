package com.alldata.training.threads;

public class WaitAndNotify {

    public static void main(String[] arg) {
        final Shared sharedObject = new Shared();

        Thread threadOne = new Thread("Thread One") {
            public void run() {
                sharedObject.firstMethod();
            }
        };

        Thread threadTwo = new Thread("Thread Two") {
            public void run() {
                sharedObject.secondMethod();
            }
        };

        threadOne.start();
        threadTwo.start();
    }
}

class Shared {

    synchronized void firstMethod()
    {
        try
        {
            System.out.println("\n" + Thread.currentThread().getName() +
                    " release the lock and wait");

            wait();

            System.out.println(Thread.currentThread().getName() +
            " get again the lock back and continue with it's execution");
    }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    synchronized void secondMethod()
    {
        try
        {
            System.out.println("\n" + Thread.currentThread().getName() + " started working");

            Thread.sleep(5000);

            /**Wakes up one thread randomly which is waiting
             * for lock of this object*/
            notify();

            System.out.println("A thread which is waiting for" +
                    " lock of this object is notified by "
                    + Thread.currentThread().getName());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
