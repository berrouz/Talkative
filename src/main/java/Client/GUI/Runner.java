package Client.GUI;
import Client.ClientBase;
import Client.Contact;
import Client.Receiver;
import javax.swing.*;
import java.awt.*;
public class Runner {
    public Runner(final ClientBase clientBase){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View(clientBase);
                Model model = new Model(view, clientBase);
                new Controller(model, view, clientBase);
            }
        });
    }
}

class View extends JFrame{
    private static int defaultLocationX = 400;
    private static int defaultLocationY = 300;
    private static String windowName = "Simple Communicator";
    private static Dimension defaultDimension = new Dimension(300, 600);
    protected JTextArea textAreaToSend = new JTextArea("");
    protected final JTextArea receivedMessages = new JTextArea("");
    private final JPanel contactsPanel = new JPanel();
    protected final JComboBox names = new JComboBox();
    protected JScrollPane jScrollPaneReceivedMessages;
    protected final JButton sendButton = new JButton("Send");
    private ClientBase clientBase;

    // data from model which are loaded into GUI
    private java.util.Set<Contact> contactsData;

    View(ClientBase clientBase){
        super(clientBase.myContact.getFirstName()+" "+clientBase.myContact.getLastName());
       // to model
        this.contactsData = clientBase.receiver.getCurrentContactList();
        for(Contact c: contactsData){
            names.addItem(c.toString());
        }
        setDefault();
        setVisible(true);
        setBottom();
        setContacts();
        setTop();
        pack();
    }

    void setDefault(){
        setLocation(defaultLocationX, defaultLocationY);
        setPreferredSize(defaultDimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }
    void setTop(){
        JPanel topPanel = new JPanel();
        JLabel topLabel = new JLabel(windowName);
        topPanel.add(topLabel);
        add(topPanel, BorderLayout.NORTH);
    }
    void setContacts(){
        contactsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        textAreaToSend.setPreferredSize(new Dimension(240, 50));
        textAreaToSend.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        receivedMessages.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        receivedMessages.setEditable(false);
        jScrollPaneReceivedMessages = new JScrollPane(receivedMessages, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneReceivedMessages.setPreferredSize(new Dimension(240, 150));
        contactsPanel.add(names);
        contactsPanel.add(Box.createRigidArea(new Dimension(100,15)));
        contactsPanel.add(jScrollPaneReceivedMessages);
        contactsPanel.add(Box.createRigidArea(new Dimension(100,15)));
        contactsPanel.add(textAreaToSend);
        contactsPanel.add(Box.createRigidArea(new Dimension(100,15)));
        contactsPanel.add(sendButton);
        add(contactsPanel, BorderLayout.CENTER);
    }

    void setBottom(){
        JPanel status = new JPanel();
        JLabel statusLabel = new JLabel("default status");
        status.add(statusLabel);
        add(status, BorderLayout.SOUTH);
    }


}