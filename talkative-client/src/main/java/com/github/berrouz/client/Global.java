package com.github.berrouz.client;

import com.github.berrouz.common.Contact;

public enum Global {
    SERVER_CONTACT("server", "server", "127.0.0.1", 7000);

    public Contact myContact;
    Global(String firstName, String lastName, String ipAddress, int port){
        myContact = new Contact(firstName,lastName, ipAddress,port);
    }
}
