package com.github.berrouz.client;

import com.github.berrouz.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Runner starts Server and 2 depot Clients
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Server server = (Server) applicationContext.getBean("server");
        server.start();
        //new Client("Sergey", "Shevchik", 9090);
        //new Client("Dima", "Zelinskiy", 9091);
    }
}
