package Server;

import Shared.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 29.10.12
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public enum Global {
    SERVER_CONTACT("server", "server", "", "127.0.0.1", 7000);

    Contact myContact;
    Global(String firstName, String lastName, String url, String ipAddress, int port){
        myContact = new Contact(firstName,lastName,url,ipAddress,port);
    }
}
