package de.leuphana.cosa.eai.messagingsystem.structure.communicationpartner;

public class EmailSender implements Sender {
    // TODO statt String sp�ter event. Address
    private final String address;
    // sp�ter Role-Object-Pattern
    private String name;

    public EmailSender(String senderAddress) {
        this.address = senderAddress;
    }

}
