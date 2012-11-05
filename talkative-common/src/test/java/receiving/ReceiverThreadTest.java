package receiving;

import com.github.berrouz.MessageQueue;
import com.github.berrouz.receiver.MessageAnalyzer;
import com.github.berrouz.receiving.Analyzer;
import com.github.berrouz.receiving.ReceiverThread;
import com.github.berrouz.receiving.SocketReader;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;
/**
 * Created with IntelliJ IDEA.
 * User: shevchik
 * Date: 05.11.12
 * Time: 12:43
 * To change this template use File | Settings | File Templates.
 */
public class ReceiverThreadTest {
    private ReceiverThread serverReceiver;
    @Before
    public void ReceiverThread() throws InterruptedException {
        this.serverReceiver = ReceiverStub.SERVER_RECEIVER;
        new Thread(this.serverReceiver).start();
        Thread.sleep(2000);
        new SenderStub().send(MessageStub.HELLO_CLIENT1_SERVER);

    }

    @Test
    public void testRun(){
        assertNotNull(this.serverReceiver.getSocketReader().getSocket());
    }
}
