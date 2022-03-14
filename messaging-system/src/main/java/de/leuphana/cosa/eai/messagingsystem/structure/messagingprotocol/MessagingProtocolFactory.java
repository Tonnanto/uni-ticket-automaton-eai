package de.leuphana.cosa.eai.messagingsystem.structure.messagingprotocol;

import de.leuphana.cosa.eai.messagingsystem.structure.MessageType;

public class MessagingProtocolFactory {
    private MessagingProtocolFactory() {
    }

    public static MessagingProtocol createMessagingProtocol(MessageType messageType) {
        MessagingProtocol messagingProtocol;

        switch (messageType) {
            case EMAIL: {
                messagingProtocol = new EmailMessagingProtocol();
                break;
            }
            case SMS: {
                messagingProtocol = new SMSMessagingProtocol();
                break;
            }
            default:
                // besser mit MessageTypeNotAllowedException
                throw new IllegalArgumentException("Unexpected value: " + messageType);
        }

        return messagingProtocol;
    }

}
