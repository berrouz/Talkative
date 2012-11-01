package com.github.berrouz;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class HandleTest extends TestCase {
    Contact contactA = new Contact("ClientA", "test", "", "127.0.0.1", 9090);
    Contact contactB = new Contact("ClientB", "test", "", "127.0.0.1", 9091);
    Message addMessage = new Message("test", Message.MESSAGE_TYPES.ADD_CONTACT, contactA, contactB);
    Message removeMessage = new Message("test", Message.MESSAGE_TYPES.REMOVE_CONTACT, contactA, contactB);
    private Handler handler= new Handler();
    public void testHandleMessageAdd(){
        handler.handleMessage(addMessage);
        assertTrue(handler.getCurrentContactList().size() == 1);
    }
    public void testHandleMessageRemove(){
        testHandleMessageAdd();
        handler.handleMessage(removeMessage);
        assertTrue(handler.getCurrentContactList().size() == 0);
    }
}
