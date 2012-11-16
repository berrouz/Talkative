package com.github.berrouz.server;

import com.github.berrouz.common.Contact;

public class ContactStubber {

    public static final Contact KIEV = getContactKiev();
    public static final Contact LVIV = getContactLviv();

    private static Contact getContactKiev(){
        return new Contact("Kiev", "City", "127.0.0.1", 9000);
    }

    private static Contact getContactLviv(){
        return new Contact("Lviv", "City", "127.0.0.1", 10000);
    }
}
