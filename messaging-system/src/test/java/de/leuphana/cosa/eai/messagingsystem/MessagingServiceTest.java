//package de.leuphana.cosa.eai.messagingsystem;
//
//import de.leuphana.cosa.eai.messagingsystem.behaviour.MessagingServiceImpl;
//import de.leuphana.cosa.eai.messagingsystem.structure.MessageType;
//import de.leuphana.cosa.eai.messagingsystem.structure.Sendable;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//class MessagingServiceTest {
//
//	private static Sendable sendable;
//	private static MessagingServiceImpl messagingService;
//
//	@BeforeAll
//	static void setUpBeforeClass() {
//		messagingService = new MessagingServiceImpl();
//
//		sendable = new Sendable();
//		sendable.setSender("rainer.zufall@web.de");
//		sendable.setReceiver("slotos@leuphana.de");
//		sendable.setContent("This is a message");
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() {
//		messagingService = null;
//	}
//
//	@Test
//	void canMessageBeSentViaEmail() {
//		sendable.setMessageType(MessageType.EMAIL);
//		Assertions.assertNotNull(messagingService.sendMessage(sendable));
//	}
//
//	@Test
//	void canMessageBeSentViaSMS() {
//		sendable.setMessageType(MessageType.SMS);
//		Assertions.assertNotNull(messagingService.sendMessage(sendable));
//	}
//
//}
