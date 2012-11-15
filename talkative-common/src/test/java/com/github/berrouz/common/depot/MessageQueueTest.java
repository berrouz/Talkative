package com.github.berrouz.common.depot;

import com.github.berrouz.common.Message;
import com.github.berrouz.common.depot.MessageQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageQueueTest {

    private MessageQueue<Message> messageQueue;

    @Before
    public void setMessageQueue(){
        messageQueue = new MessageQueue();
    }
    @Test
    public void TestAddAndPoll(){
        messageQueue.add(new Message());
        assertEquals(messageQueue.getQueue().size(), 1);
        messageQueue.poll();
        assertEquals(messageQueue.getQueue().size(), 0);
    }
}
