package com.github.berrouz.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Server Runner
 */
public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Server server = (Server) applicationContext.getBean("server");
        server.start();
    }
}
