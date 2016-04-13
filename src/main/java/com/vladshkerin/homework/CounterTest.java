package com.vladshkerin.homework;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class for testing multithreading increment counter.
 */
public class CounterTest {

    private Integer counter;
    private final AtomicInteger atomCounter;

    private CounterTest(int counter) {
        this.counter = counter;
        this.atomCounter = new AtomicInteger(counter);
    }

    private synchronized int getCounter() {
        return counter;
    }

    private synchronized void incrementCounter() {
        atomCounter.set(++counter);
    }

    private void incrementAtomicCounter() {
        counter = atomCounter.incrementAndGet();
    }

    public static void main(String[] args) {
        final CounterTest counterTest = new CounterTest(0);

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer current = counterTest.getCounter();
                    if (current % 2 == 0) {
                        counterTest.incrementCounter();
                    } else {
                        counterTest.incrementAtomicCounter();
                    }

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            if (counterTest.getCounter() % 10 == 0) {
                System.out.println(counterTest.getCounter());
            }
        }
    }
}
