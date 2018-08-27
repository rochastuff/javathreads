package com.alldata.training.threads;

public class Synchronized {

    public static void main (String[] args) {

        final SharedClass shared = new SharedClass();

        Thread threadOne = new Thread("Thread One") {
            public void run(){
                shared.SharedMethod();
            }
        };

        Thread threadTwo = new Thread("Thread Two") {
            public void run(){
                shared.SharedMethod();
            }
        };

        threadOne.start();
        threadTwo.start();
    }
}

class SharedClass {

    /**Even if constructors, static initializer and instance initializer
     * can't be declared as synchronized, the can have synchronized blocks
     * */
    public SharedClass()
    {
        //not synchronized code
        synchronized (this)
        {
            //synchronized block inside a constructor
        }
    }

    static
    {
        //not synchronized code
        synchronized (SharedClass.class)
        {
            //synchronized block inside a static initializer
        }
    }

    {
        //not synchronized code
        synchronized (this)
        {
            //synchronized block inside a instance initializer
        }
    }

    /** For non-static methods the lock is at object level
     * */
    synchronized void SharedMethod () {
    //void SharedMethod () {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("In method body " + Thread.currentThread().getName() + " - " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        synchronized (this) {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("In block body " + Thread.currentThread().getName() + " - " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            /**Nested synchronized blocks are allowed
             * but for the second block, the lock is already done by the first block
             * */
            synchronized (this) {
                //code here
            }

        }
    }

    /** Also static methods can be synchronized.
     * For static methods, thread needs lock at class level
     * */
    public static void StaticSharedMethod () {

    }
}
