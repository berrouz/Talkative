package com.github.berrouz;

/**
 * Runner starts Server and 2 depot Clients
 */
public class Runner {
    public static void main(String[] args) {
        new Client("Sergey", "Shevchik", 9090);
        new Client("Dima", "Zelinskiy", 9091);
    }
}
