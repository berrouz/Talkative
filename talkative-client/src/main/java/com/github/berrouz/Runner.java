package com.github.berrouz;

/**
 * Runner starts Server and 2 depot Clients
 */
public class Runner {
    public static void main(String[] args) {
        //new Server(new Contact("server", "server", "127.0.0.1", 7000)).start();
        new Client("Sergey", "Shevchik", 9090);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Client("Dima", "Zelinskiy", 9091);
    }
}
