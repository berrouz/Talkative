package com.github.berrouz.client;

import com.github.berrouz.client.gui.Controller;
import com.github.berrouz.common.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Runner starts Server and 2 depot Clients
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("context.xml");
        Contact clientContact1 = (Contact) applicationContext1.getBean("clientContact");
        clientContact1.setFirstName("Sergey");
        clientContact1.setLastName("Shevchik");
        clientContact1.setPort(8001);

        Client client1 = (Client) applicationContext1.getBean("client");
        client1.start();
        Controller controller1 = (Controller) applicationContext1.getBean("controller");
        controller1.updateTitle();


        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("context.xml");
        Contact clientContact2 = (Contact) applicationContext2.getBean("clientContact");
        clientContact2.setFirstName("Dmitry");
        clientContact2.setLastName("Zelinskiy");
        clientContact2.setPort(8002);

        Client client2 = (Client) applicationContext2.getBean("client");
        client2.start();
        Controller controller2 = (Controller) applicationContext2.getBean("controller");
        controller2.updateTitle();
    }
}
