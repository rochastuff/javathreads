package com.alldata.training.threads;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.Format;

public class Deadlock {

  public static void main(String[] args) {

      final MyClass myClass = new MyClass();

      Thread threadOne = new Thread("<First Thread>") {
          public void run() {
              myClass.firstMethod();
          }
      };

      Thread threadTwo = new Thread("<Second Thread>") {
          public void run() {
              myClass.secondMethod();
          }
      };

      threadOne.start();
      threadTwo.start();

      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          System.out.println(e.getMessage());
      }

      ThreadMXBean bean = ManagementFactory.getThreadMXBean();
      long ids[] = bean.findMonitorDeadlockedThreads();


      System.out.println("Active threads " + Thread.activeCount());

      if (ids != null) {
          ThreadInfo threadInfo[] = bean.getThreadInfo(ids);
          for (ThreadInfo threadInfo1 : threadInfo) {
              System.out.println(String.format(
                      "\n\nThe thread %s with ID %s, is deadlocked." +
                              "\nThe object that caused the deadlock is %s" +
                              "\nThe object is being used by the thread %s with ID %s.",
                      threadInfo1.getThreadName(),
                      threadInfo1.getThreadId(),
                      threadInfo1.getLockName(),
                      threadInfo1.getLockOwnerName(),
                      threadInfo1.getLockOwnerId()
                      )
              );
          }
      } else {
          System.out.println("There are not Deadlocked threads");
      }

      System.out.println("Active threads " + Thread.activeCount());
  }
}

class MyClass {

    public static Object cacheLock = new Object();
    public static Object tableLock = new Object();

    public void firstMethod() {
        synchronized (cacheLock) {
            try {
                System.out.println("Using cacheLock object in firstMethod");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Let's use tableLock object in firstMethod ");
            synchronized (tableLock) {
                try {
                    System.out.println("Using tableLock object in firstMethod");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void secondMethod() {

        /**For example if the first thread had enough time to use the objects
         * The second thread will use them without problem */

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        synchronized (tableLock) {
            try {
                System.out.println("Using tableLock object in secondMethod");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Let's use cacheLock object in secondMethod ");
            synchronized (cacheLock) {
                try {
                    System.out.println("Using cacheLock object in secondMethod");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}





