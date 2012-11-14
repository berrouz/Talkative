package com.github.berrouz.client;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Transceiver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Properties;

/**
 * Client encompasses all the classes required for Client side of Chat application
 */
@Component
@Scope("singleton")
public class Client {

    public Contact myContact;

    @Inject
    private Transceiver transceiver;

    public void start(){
        transceiver.start();
    }

    private void readConfig(){
        Properties properties = new Properties();

    }

    public void setTransceiver(Transceiver transceiver) {
        this.transceiver = transceiver;
    }

    public void setMyContact(Contact myContact) {
        this.myContact = myContact;
    }
}
