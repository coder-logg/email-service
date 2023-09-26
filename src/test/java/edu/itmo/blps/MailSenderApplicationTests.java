//package edu.itmo.blps;
//
//import edu.itmo.blps.messaging.dto.TransactionDTO;
//import edu.itmo.blps.service.EmailService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.mail.MessagingException;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//class MailSenderApplicationTests {
//
//	@Autowired
//	private EmailService emailService;
//
//	@Test
//	public void testJsoupAttr() {
//		System.out.println(emailService.makeNiceReceipt(TransactionDTO.builder().customerUsername("Aidar").build()));
//		Assertions.assertTrue(true);
//	}
//
//}
