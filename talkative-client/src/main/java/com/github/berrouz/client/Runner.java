package com.github.berrouz.client;

import com.github.berrouz.common.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Runner starts Server and 2 depot Clients
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Contact clientContact = (Contact) applicationContext.getBean("clientContact");
        clientContact.setFirstName("Sergey");
        clientContact.setLastName("Shevchik");
        clientContact.setPort(8001);

        Client client = (Client) applicationContext.getBean("client");
        client.start();

        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("context.xml");
        Contact clientContact1 = (Contact) applicationContext1.getBean("clientContact");
        clientContact1.setFirstName("Dmitry");
        clientContact1.setLastName("Zelinskiy");
        clientContact1.setPort(8002);

        Client client1 = (Client) applicationContext1.getBean("client");
        client1.start();

    }
}
