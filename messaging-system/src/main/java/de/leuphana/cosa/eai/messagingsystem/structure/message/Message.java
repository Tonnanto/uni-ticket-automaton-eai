package de.leuphana.cosa.eai.messagingsystem.structure.message;

public abstract class Message {
	private final MessageHeader messageHeader;
	private final MessageBody messageBody;
	
	public Message(MessageHeader messageHeader, MessageBody messageBody) {
		this.messageBody = messageBody;
		this.messageHeader = messageHeader;
	}

}
