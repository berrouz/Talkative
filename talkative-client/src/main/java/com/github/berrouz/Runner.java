package com.github.berrouz;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class Runner {
    public static void main(String[] args) {
        new Server(new Contact("server", "server", "", "127.0.0.1", 7000)).start();
        new Client("Sergey", "Shevchik", 9090);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Client("Dima", "Zelinskiy", 9091);
    }
}
