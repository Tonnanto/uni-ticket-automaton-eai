package de.leuphana.cosa.eai.messagingsystem.structure.message;

import de.leuphana.cosa.eai.messagingsystem.structure.communicationpartner.Receiver;
import de.leuphana.cosa.eai.messagingsystem.structure.communicationpartner.Sender;

public class MessageHeader {
	private Sender sender;
	private Receiver receiver;
	
	public MessageHeader(Sender sender, Receiver receiver) {
		this.receiver = receiver;
		this.sender = sender;
	}
}
