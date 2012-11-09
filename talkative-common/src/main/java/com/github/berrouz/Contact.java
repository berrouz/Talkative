package com.github.berrouz;

public class Contact {

    private String firstName;
    private String lastName;
    private String ipAddress;
    private volatile int hashCode;
    private int port;

    public Contact(String firstName, String lastName, String ipAddress, int port) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Setters and getters
     */
    public String getIpAddress() {
        return ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = 31 * result + firstName.hashCode();
            result = 31 * result + lastName.hashCode();
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Contact)) {
            return false;
        }
        Contact c = (Contact) o;
        return c.firstName.equals(firstName) && c.lastName.equals(lastName);
    }
}
