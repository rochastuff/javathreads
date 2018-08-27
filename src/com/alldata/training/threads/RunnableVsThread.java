package com.alldata.training.threads;

public class RunnableVsThread {

    public static void main(String[] args) {

        /**Creating threads from a class that extends Thread class
         */
        for(int i = 1; i < 5; i++) {
            new ProcessThread("ProcessThread " + i).start();
        }
        System.out.println("Active threads: " + Thread.activeCount());

        /**Creating an implementation of Runnable and passing it to the Thread class
         * utilizes composition and not inheritance – which is more flexible
         */
        for(int i = 1; i < 5; i++) {
            new Thread(new ProcessRunnable("ProcessRunnable " + i)).start();
        }
        System.out.println("Active threads: " + Thread.activeCount());

        /**Using the interface Runnable, threads can share information
         */
        ProcessRunnable runnableObject = new ProcessRunnable("ProcessRunnable Sharing Info ");
        for(int i = 1; i < 5; i++) {
            new Thread(runnableObject).start();
        }
        System.out.println("Active threads: " + Thread.activeCount());

    }

}

/**
 * Extends to Thread class to make you object able to running as a thread
 * */
class ProcessRunnable implements Runnable {

    private String name;
    private int count = 0;

    public ProcessRunnable(String name) {
        //super(name + " thread");
        this.name = name;
    }

    /**Implements the run method*/
    public void run () {

        try {
            count++;
            for (int i = 1; i <= 5; i++) {
                System.out.println("Name: " + name +
                        "\tId: " + Thread.currentThread().getId() +
                        "\tStep: " + i + "\tCount: " + count);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * Extends to Thread class to make you object able to running as a thread
 * */
class ProcessThread extends Thread {

    private String name;
    private int count = 0;

    public ProcessThread(String name) {
        super(name + " thread");
        this.name = name;
    }

    /**Overrides the run method of the Thread class*/
    @Override
    public void run () {

        try {
            count++;
            for (int i = 1; i <= 5; i++) {
                System.out.println("Name: " + name +
                        "\tId: " + Thread.currentThread().getId()
                        + "\tStep: " + i + "\tCount: " + count);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        yield();


    }

}


