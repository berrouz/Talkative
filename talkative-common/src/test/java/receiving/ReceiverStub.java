package receiving;

import com.github.berrouz.MessageQueue;
import com.github.berrouz.receiver.MessageAnalyzer;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;

/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
public class ReceiverStub {
    public final static ReceiverThread SERVER_RECEIVER = getReceiver();

    public static ReceiverThread getReceiver(){
        MessageQueue messageQueue = new MessageQueue();
        Analyzer analyzer = new MessageAnalyzer(messageQueue);
        SocketReader socketReader = new SocketReader(messageQueue);
        return new ReceiverThread(ContactStub.SERVER, analyzer, socketReader);
    }
}
