package com.github.berrouz.depot;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 07.11.12
 * Time: 07:19
 * To change this template use File | Settings | File Templates.
 */
public class InputQueue<E> {

    // input queue
    private final Queue<E> queue;

    private Logger logger = Logger.getLogger(InputQueue.class);

    public InputQueue(){
        this.queue = new LinkedList<E>();
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
            if(queue.isEmpty()){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    logger.error("Thread is waiting and was interrupted", e);
                }
            }
            return queue.poll();
        }
    }

    public Queue<E> getQueue() {
        return queue;
    }
}
