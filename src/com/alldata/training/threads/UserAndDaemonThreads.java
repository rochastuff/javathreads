package com.alldata.training.threads;

public class UserAndDaemonThreads {

    public static void main(String[] args) {

        MyThread normalThread = new MyThread("Normal Thread", 10);
        MyThread daemonThread = new MyThread("Daemon Thread", 20);

        /** Make a thread a daemon thread*/
        daemonThread.setDaemon(true);
        daemonThread.setPriority(10);
        normalThread.setPriority(1);

        normalThread.start();
        daemonThread.start();

        /** Cant' set a daemon property once it is started*/
        //daemonThread.setDaemon(true);
    }
}

class MyThread extends Thread {

    private int limit;

    public MyThread(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    public void run() {

        try {

            System.out.println(Thread.currentThread().getName() + " Start");
            System.out.println("This is a daemon thread? " + Thread.currentThread().isDaemon());
            int count = 0;
            do {
                System.out.println(Thread.currentThread().getName() + " " + ++count);
                Thread.sleep(500);
            } while (count < limit);
            System.out.println(Thread.currentThread().getName() + " Finish");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
