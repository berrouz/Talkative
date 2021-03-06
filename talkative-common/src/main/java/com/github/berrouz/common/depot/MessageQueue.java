package com.github.berrouz.common.depot;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  Collection for holding messages
 * @param <E>
 */
public class MessageQueue<E> {

    // input queue
    private final Queue<E> queue;

    private static final Logger logger = Logger.getLogger(MessageQueue.class);

    public MessageQueue(){
        this.queue = new LinkedList<E>();
    }

    public MessageQueue(LinkedList queue){
        this.queue = queue;
    }

    /**
     * Adding an element to a queue and awakes polling Thread,
     * allowing to poll message
     * @param e
     * @return
     */
    public boolean add(E e){
        synchronized (queue){
            queue.notify();
            return queue.add(e);
        }
    }

    /**
     * polling input queue
     * If queue if empty - thread starts to sleep in order to allow
     * other thread to add an element to a queue. When element is added to a queue,
     * waited Thread is notified and proceeds to work.
     * @return
     */
    public E poll(){
        synchronized (queue){
            if (queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    logger.error("Thread is waiting and was interrupted", e);
                }
            }
            return queue.poll();
        }
    }

    // Getters and setters
    public Queue<E> getQueue() {
        return queue;
    }

    /**
     * Size of the queue
     * @return
     */
    public int size(){
        return queue.size();
    }
}
