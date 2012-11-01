package com.github.berrouz;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
public class SpammerTest extends TestCase {
    private Spammer spammer = new Spammer();
    private Contact contactA = new Contact("ClientA", "test", "", "127.0.0.1", 9090);
    private Contact contactB = new Contact("ClientB", "test", "", "127.0.0.1", 9091);
    private List<Contact> contactList = new LinkedList<Contact>();
    public void testSendToAll(){
        contactList.add(contactA);
        contactList.add(contactB);
        assertTrue(contactList.size()==2);
        spammer.sendToAll(contactList);
    }


}
