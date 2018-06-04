package ru.dglv.concurrency.messageloop;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;

    static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
        return k -> {
            try {
                throwingConsumer.accept(k);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
