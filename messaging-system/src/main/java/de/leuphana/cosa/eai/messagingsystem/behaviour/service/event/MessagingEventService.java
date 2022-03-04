package de.leuphana.cosa.eai.messagingsystem.behaviour.service.event;

public interface MessagingEventService {
    void addMessagingEventListener(MessagingEventListener messagingEventListener);
    void removeMessagingEventListener(MessagingEventListener messagingEventListener);
}
