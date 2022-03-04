package de.leuphana.cosa.eai.messagingsystem.structure.messagingprotocol;

import de.leuphana.cosa.eai.messagingsystem.structure.message.Message;

public interface MessagingProtocol {
	boolean open();
	boolean transfer(Message message);
	boolean close();
}
