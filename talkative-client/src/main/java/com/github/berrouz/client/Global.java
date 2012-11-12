package com.github.berrouz.client;

import com.github.berrouz.common.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 01.11.12
 * Time: 12:05
 * To change this template use File | Settings | File Templates.
 */
public enum Global {
    SERVER_CONTACT("server", "server", "", "127.0.0.1", 7000);

    public Contact myContact;
    Global(String firstName, String lastName, String url, String ipAddress, int port){
        myContact = new Contact(firstName,lastName, ipAddress,port);
    }
}
