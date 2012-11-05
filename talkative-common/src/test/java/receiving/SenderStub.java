package receiving;

import com.github.berrouz.Message;
import com.github.berrouz.sending.Sender;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class SenderStub extends Sender {

    public void send(Message message){
        super.sendMessage(message);
    }
}
