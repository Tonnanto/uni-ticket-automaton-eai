package de.leuphana.cosa.eai.messagingsystem.behaviour.service;

import de.leuphana.cosa.eai.messagingsystem.structure.DeliveryReport;
import de.leuphana.cosa.eai.messagingsystem.structure.Sendable;

public interface MessagingService {
	String MESSAGING_KEY = "message";

	DeliveryReport sendMessage(Sendable sendable);
}