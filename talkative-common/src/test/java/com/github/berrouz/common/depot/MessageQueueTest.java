package com.github.berrouz.common.depot;

import com.github.berrouz.common.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageQueueTest {

    private MessageQueue<Message> messageQueue;

    @Before
    public void setMessageQueue(){
        messageQueue = new MessageQueue();
    }

    @Test
    public void testAddAndPoll(){
        messageQueue.add(new Message());
        assertEquals(messageQueue.getQueue().size(), 1);
        messageQueue.poll();
        assertEquals(messageQueue.getQueue().size(), 0);
    }

    // variable is used by testPollWhenEmpty
    private boolean k;

    // test that addition action should be first, polling - second
    @Test
    public void testPollWhenEmpty() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Thread adding = new Thread(new Runnable() {
            @Override
            public void run() {
                messageQueue.add(new Message());
                k = false;
            }
        });
        Thread polling = new Thread(new Runnable() {
            @Override
            public void run() {
                messageQueue.poll();
                k = true;
            }
        });
        executorService.execute(polling);
        executorService.execute(adding);
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        assertTrue(k);
    }
}
