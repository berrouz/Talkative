package com.github.berrouz;

/**
 * Contact class presents the information about a client for
 * communication via network, including port and ip address of client
 */
public class Contact{

    private String firstName;

    private String lastName;

    private String ipAddress;

    private volatile int hashCode;

    private int port;

    public Contact(){}

    public Contact(String f, String l, String ipAddress, int port){
        this.firstName = f;
        this.lastName  = l;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String toString(){
        return firstName+" "+lastName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    @Override
    public int hashCode(){
        int result = hashCode;
        if(result==0){
            result = 17;
            result = 31*result + firstName.hashCode();
            result = 31*result + lastName.hashCode();
            hashCode = result;
        }
        return hashCode;
    }
    @Override
    public boolean equals(Object o){
        if( o == null || !(o instanceof Contact)){
            return false;
        }
        Contact c = (Contact) o;
        return  c.firstName.equals(firstName) && c.lastName.equals(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}
