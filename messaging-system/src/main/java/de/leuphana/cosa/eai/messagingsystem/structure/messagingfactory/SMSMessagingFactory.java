package de.leuphana.cosa.eai.messagingsystem.structure.messagingfactory;

import de.leuphana.cosa.eai.messagingsystem.structure.MessageType;
import de.leuphana.cosa.eai.messagingsystem.structure.message.Message;
import de.leuphana.cosa.eai.messagingsystem.structure.message.MessageBuilder;
import de.leuphana.cosa.eai.messagingsystem.structure.messagingprotocol.MessagingProtocol;
import de.leuphana.cosa.eai.messagingsystem.structure.messagingprotocol.MessagingProtocolFactory;

public class SMSMessagingFactory extends AbstractMessagingFactory {

    @Override
    public MessagingProtocol createMessagingProtocol() {
        return MessagingProtocolFactory.createMessagingProtocol(MessageType.SMS);
    }

    @Override
    public Message createMessage(String sender, String receiver, String contentAsString) {
        return MessageBuilder.createMessage(receiver, sender, contentAsString, MessageType.SMS);
    }
}
