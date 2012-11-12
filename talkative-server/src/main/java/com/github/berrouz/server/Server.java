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
public class Server implements InitializingBean{

    @Inject
    private Transceiver transceiver;

    @Inject
    private Contact serverContact;

    private static final Logger logger = Logger.getLogger(Server.class);

    public void start(){
        transceiver.start();
    }

    /**
     * Reads config from server.properties
     */
    private void readConfig(){
        Properties properties = new Properties();
        try {
            properties.load(Class.class.getResourceAsStream("/server.properties"));
        } catch (IOException e) {
            logger.error("Cannot open resource while reading server properties", e);
        }
        this.serverContact = new Contact(properties.getProperty("server"),
                properties.getProperty("server"),
                properties.getProperty("ip.address"),
                Integer.parseInt(properties.getProperty("port")));
    }

    public void setTransceiver(Transceiver transceiver) {
        this.transceiver = transceiver;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //readConfig();
    }

    public void setServerContact(Contact serverContact) {
        this.serverContact = serverContact;
    }
}
