package ru.dglv.concurrency.thread;

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("[Secondary Thread]: Run");
    }
}

public class SimpleThread {
    public static void main(String[] args) {
/*        System.out.println("[Main Thread]: Started");
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();

        System.out.println("[Main Thread]: Finished");*/
        //-------------------------------------------------------

/*        System.out.println("[Main Thread]: Started");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[Secondary Thread]: Run");
            }
        });
        thread.start();

        System.out.println("[Main Thread]: Finished");*/
        //-------------------------------------------------------

        System.out.println("[Main Thread]: Started");
        Thread thread = new Thread(() -> System.out.println("[Secondary Thread]: Run"));
        thread.start();

        System.out.println("[Main Thread]: Finished");
    }
}
