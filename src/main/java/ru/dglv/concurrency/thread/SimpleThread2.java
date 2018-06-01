package ru.dglv.concurrency.thread;

class SecondaryThread extends Thread {
    @Override
    public void run() {
        System.out.println("[Secondary Thread]: Run");
    }
}

public class SimpleThread2 {
    public static void main(String... args) {
        System.out.println("[Main Thread]: Started");
        Thread thread = new SecondaryThread();
        thread.start();

        System.out.println("[Main Thread]: Finished");
    }
}
