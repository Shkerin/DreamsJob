package com.vladshkerin.homework;

/**
 * The class HandBlockingQueue implements a blocking queue.
 *
 * @author Vladimir Shkerin
 * @since 22.03.2016
 */

public class HandBlockingQueue {

    private final Integer[] items; // the queued items
    private int takeIndex; // items index for next take
    private int putIndex; // items index for next put
    private int count; // number of elements in the queue

    public HandBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Integer[capacity];
        this.takeIndex = 0;
        this.putIndex = 0;
        this.count = 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        synchronized (this) {
            return count;
        }
    }

    /**
     * Inserts the specified element at the tail of this queue, waiting
     * for space to become available if the queue is full.
     *
     * @throws InterruptedException if any thread interrupted the current thread.
     * @throws NullPointerException if the passed parameter is null
     */
    public void put(Integer value) throws InterruptedException {
        checkNotNull(value);
        synchronized (this) {
            while (count == items.length) {
                notifyAll();
                wait();
            }
            insert(value);
        }
    }

    /**
     * Retrieves and removes the head of this queue,
     * waiting if necessary until an element becomes available.
     *
     * @return the element in the queue
     * @throws InterruptedException
     */
    public Integer take() throws InterruptedException {
        synchronized (this) {
            while (count == 0) {
                notifyAll();
                wait();
            }
            return extract();
        }
    }

    /**
     * Throws NullPointerException if argument is null.
     *
     * @param value the element
     */
    private void checkNotNull(Integer value) {
        if (value == null)
            throw new NullPointerException("The value of the field \"value\" in method checkNotNull is null.");
    }

    /**
     * Inserts element at current put position.
     */
    private void insert(Integer value) {
        items[putIndex] = value;
        putIndex = inc(putIndex);
        ++count;
    }

    /**
     * Extracts element at current take position.
     */
    private Integer extract() {
        Integer value = items[takeIndex];
        items[takeIndex] = null;
        takeIndex = inc(takeIndex);
        --count;
        return value;
    }

    /**
     * Circularly increment i.
     */
    private final int inc(int i) {
        return (++i == items.length) ? 0 : i;
    }
}
