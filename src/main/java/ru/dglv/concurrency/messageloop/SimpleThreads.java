package ru.dglv.concurrency.messageloop;

import java.util.stream.Stream;

import static ru.dglv.concurrency.messageloop.ThrowingConsumer.throwingConsumerWrapper;

public class SimpleThreads {
    private static final long MAIN_THREAD_TIMEOUT = 10000;

    // Display thread`s name and message
    private static void logger(String message) {
        String threadName = Thread.currentThread().getName();

        System.out.println(String.format("%s: %s", threadName, message));
    }

    // Message Loop thread
    private static class MessageLoop implements Runnable {
        private static final long MLOOP_SLEEP_TIMEOUT = 4000;

        @Override
        public void run() {
            String[] output = {"BMW", "LADA", "Mercedes", "OKA", "KAMAZ",
                    "Ford", "Opel", "Renault"};

            try {
                Stream.of(output).forEach(throwingConsumerWrapper(k -> {
                    Thread.sleep(MLOOP_SLEEP_TIMEOUT);
                    logger(k);
                }));
            } catch (RuntimeException e) {
                if (e.getCause() != null &&
                        e.getCause() instanceof InterruptedException) {
                    logger("Received an interruption!");
                } else {
                    logger(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        long currentTime;
        Thread t = new Thread(new MessageLoop());
        t.start();
        logger("MessageLoop thread started");

        // loop until the Message Loop thread is alive
        while (t.isAlive()) {
            logger("Still waiting...");
            t.join(1000);

            currentTime = System.currentTimeMillis();
            if (((currentTime - startTime) > MAIN_THREAD_TIMEOUT) &&
                    t.isAlive()) {
                logger("Too long! Lets stop the MessageLoop");
                t.interrupt();
                t.join();
            }
        }

        logger("Finished");
    }
}
