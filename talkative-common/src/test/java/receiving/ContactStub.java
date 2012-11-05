package receiving;

import com.github.berrouz.Contact;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
public class ContactStub extends Contact {
    public final static Contact CLIENT1 = client1();
    public final static Contact CLIENT2 = client2();
    public final static Contact SERVER = server();
    public ContactStub(){
        super();
    }

    private static Contact client1(){
        return new Contact("Client1", "MADRID", "", "127.0.0.1", 5001);
    }

    private static Contact client2(){
        return new Contact("Client1", "MADRID", "", "127.0.0.1", 5001);
    }

    private static Contact server(){
        return new Contact("Server", "MUNICH", "", "127.0.0.1", 5555);
    }

}
