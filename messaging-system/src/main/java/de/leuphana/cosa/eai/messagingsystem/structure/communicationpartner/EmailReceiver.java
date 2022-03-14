package de.leuphana.cosa.eai.messagingsystem.structure.communicationpartner;

public class EmailReceiver implements Receiver {
    // TODO statt String sp�ter event. Address
    private final String address;
    // sp�ter Role-Object-Pattern
    private String name;

    public EmailReceiver(String receiverAddress) {
        this.address = receiverAddress;
    }
}
