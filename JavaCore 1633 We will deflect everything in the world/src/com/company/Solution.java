package com.company;

/*

1633 We will deflect everything in the world

Understand what the program does.
Reading about UncaughtExceptionHandler is important.
Look carefully at the program again.
To understand - to bargain - why our OurUncaughtExceptionHandler does not work.
Correct the error, i.e. everything should work. :)
Expected result in random order:
Thread 1: My exception message
Thread 2: My exception message

Requirements:
1. The main method should create a thread with the parameters: commonThread and "Thread 1".
2. The main method should create a thread with the parameters: commonThread and "Thread 2".
3. The main method should start two created threads of type Thread.
4. The main method should interrupt two created threads of type Thread.
5. The program using the uncaughtException method of the OurUncaughtExceptionHandler class should output 2 messages.
6. The uncaughtException method of the OurUncaughtExceptionHandler class is not explicitly called.
7. The output of the program should contain the lines: "Thread 1: My exception message" and "Thread 2: My exception message".


 */

public class Solution {
    public static Thread.UncaughtExceptionHandler handler = new OurUncaughtExceptionHandler();

    public static void main(String[] args) {
        TestedThread commonThread = new TestedThread(handler);

        Thread threadA = new Thread(commonThread, "Нить 1");
        Thread threadB = new Thread(commonThread, "Нить 2");

        threadA.start();
        threadB.start();

        threadA.interrupt();
        threadB.interrupt();
    }

    public static class TestedThread extends Thread {
        public TestedThread(Thread.UncaughtExceptionHandler handler) {
            setDefaultUncaughtExceptionHandler(handler);
            start();
        }

        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException x) {
                throw new RuntimeException("My exception message");
            }
        }
    }

    public static class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + ": " + e.getMessage());
        }
    }
}





