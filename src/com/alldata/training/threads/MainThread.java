package com.alldata.training.threads;

public class MainThread {

    public static void main (String[] args) {

        /**
         * Get reference to Main thread
         */
        Thread mainThread = Thread.currentThread();
        mainThread.setName("Main Thread");
        System.out.println("Main thread name: " + mainThread.getName());

        /**
         * Priority goes from 1 to 10
         * Default priority is 5
         */
        System.out.println("Thread priority: " + mainThread.getPriority());
        mainThread.setPriority(Thread.MIN_PRIORITY);  //1
        System.out.println("Thread priority: " + mainThread.getPriority());
        mainThread.setPriority(Thread.MAX_PRIORITY);  //10
        System.out.println("Thread priority: " + mainThread.getPriority());
        mainThread.setPriority(3);
        System.out.println("New thread priority: " + mainThread.getPriority());
    }

}
/*
class TempClass implements Runnable {

    public void run() {
        for(int i = 0; i < )
    }

}

*/