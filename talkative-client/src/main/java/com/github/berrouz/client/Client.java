package com.github.berrouz.client;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Transceiver;

import javax.inject.Inject;
import java.util.Properties;

/**
 * Client encompasses all the classes required for Client side of Chat application
 */
public class Client {

    private Contact myContact;

    @Inject
    private Transceiver transceiver;

    public Client(String firstName, String lastName, int port){
        this.myContact = new Contact(firstName,lastName, "127.0.0.1", port);
        this.transceiver.start();
    }

    private void readConfig(){
        Properties properties = new Properties();

    }

    public void setTransceiver(Transceiver transceiver) {
        this.transceiver = transceiver;
    }
}
