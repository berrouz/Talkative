package com.github.berrouz.server;

import com.github.berrouz.common.Contact;
import com.github.berrouz.common.Transceiver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Properties;


/**
 * Encapsulates all classes for Server side of Chat application
 */
@Component
public class Server{

    @Inject
    private Transceiver transceiver;

    private static final Logger logger = Logger.getLogger(Server.class);

    public void start(){
        transceiver.start();
    }

    public void setTransceiver(Transceiver transceiver) {
        this.transceiver = transceiver;
    }
}
