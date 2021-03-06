package de.leuphana.cosa.eai.messagingsystem.structure.messagingfactory;

import de.leuphana.cosa.eai.messagingsystem.structure.MessageType;
import de.leuphana.cosa.eai.messagingsystem.structure.message.Message;
import de.leuphana.cosa.eai.messagingsystem.structure.messagingprotocol.MessagingProtocol;

public abstract class AbstractMessagingFactory {
    public static AbstractMessagingFactory getFactory(MessageType messageType) {
        AbstractMessagingFactory abstractMessagingFactory;

        switch (messageType) {
            case EMAIL: {
                abstractMessagingFactory = new EmailMessagingFactory();
                break;
            }
            case SMS: {
                abstractMessagingFactory = new SMSMessagingFactory();
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + messageType);
        }

        return abstractMessagingFactory;
    }

    public abstract MessagingProtocol createMessagingProtocol();

    public abstract Message createMessage(String sender, String receiver, String contentAsString);
}
