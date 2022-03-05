package de.leuphana.cosa.eai.messagingsystem.behaviour;

import de.leuphana.cosa.eai.messagingsystem.behaviour.service.MessagingService;
import de.leuphana.cosa.eai.messagingsystem.structure.DeliveryReport;
import de.leuphana.cosa.eai.messagingsystem.structure.MessageType;
import de.leuphana.cosa.eai.messagingsystem.structure.Sendable;
import de.leuphana.cosa.eai.messagingsystem.structure.message.Message;
import de.leuphana.cosa.eai.messagingsystem.structure.messagingfactory.AbstractMessagingFactory;
import de.leuphana.cosa.eai.messagingsystem.structure.messagingprotocol.MessagingProtocol;
import de.leuphana.cosa.eai.uisystem.structure.SelectionView;
import de.leuphana.cosa.eai.uisystem.structure.StringInputView;
import de.leuphana.cosa.eai.uisystem.structure.View;

import java.util.ArrayList;
import java.util.List;

public class MessagingServiceImpl implements MessagingService {

    @Override
    public DeliveryReport sendMessage(Sendable sendable) {

        MessageType messageType = selectMessageType();
        String receiver = enterReceiver(messageType);
        String sender = messageType == MessageType.SMS ? "0152242069" : "ticket@automat.de";

        if (messageType != null) {
            AbstractMessagingFactory abstractMessagingFactory = AbstractMessagingFactory.getFactory(messageType);
            Message message = abstractMessagingFactory.createMessage(sender, receiver, sendable.getContent());
            MessagingProtocol messageProtocol = abstractMessagingFactory.createMessagingProtocol();
            messageProtocol.open();
            messageProtocol.transfer(message);
            View view = new View() {
                @Override
                protected String getMessage() {
                    return "Sending ...";
                }
            };
            view.display();
            messageProtocol.close();

            DeliveryReport deliveryReport = new DeliveryReport();
            deliveryReport.setSender(sender);
            deliveryReport.setReceiver(receiver);
            deliveryReport.setContent(sendable.getContent());
            deliveryReport.setMessageType(messageType.toString());

            return deliveryReport;
        } else {
            return null;
        }
    }

    private MessageType selectMessageType() {
        SelectionView selectionView = new SelectionView() {
            @Override
            protected List<String> getOptions() {
                List<String> option = new ArrayList<>();
                option.add("Keine Quittung");
                option.add("via E-Mail");
                option.add("via SMS");
                return option;
            }

            @Override
            protected String getMessage() {
                return "Möchten Sie eine Quittung zugestellt bekommen?";
            }
        };

        int selectedIndex = selectionView.displaySelection();

        if (selectedIndex == 1)
            return MessageType.EMAIL;
        if (selectedIndex == 2)
            return MessageType.SMS;
        return null;
    }

    private String enterReceiver(MessageType messageType) {
        if (messageType == null) return null;

        StringInputView view = null;
        if (messageType == MessageType.EMAIL) {
            view = new StringInputView() {
                @Override
                protected String getMessage() {
                    return "Geben Sie ihre E-Mail Adresse ein: ";
                }

                @Override
                protected boolean isValidString(String s) {
                    return s.contains("@") && s.contains(".");
                }

                @Override
                protected String getValidationMessage() {
                    return "Bitte geben Sie eine gültige Email Adresse ein";
                }
            };

        } else if (messageType == MessageType.SMS) {
            view = new StringInputView() {
                @Override
                protected String getMessage() {
                    return "Geben Sie ihre Handynummer ein: ";
                }

                @Override
                protected boolean isValidString(String s) {
                    return s.matches("[0-9]+");
                }

                @Override
                protected String getValidationMessage() {
                    return "Bitte geben Sie eine gültige Handynummer ein";
                }
            };
        }

        if (view == null) return null;
        return view.displayStringInput();
    }
}
