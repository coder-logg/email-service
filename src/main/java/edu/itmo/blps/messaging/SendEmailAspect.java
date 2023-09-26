package edu.itmo.blps.messaging;


import edu.itmo.blps.messaging.dto.EmailMessage;
import edu.itmo.blps.service.EmailService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@Aspect
public class SendEmailAspect {
	@Autowired
	private EmailService sender;

	@AfterReturning(
			pointcut = "execution(edu.itmo.blps.messaging.dto.EmailMessage edu.itmo.blps.messaging.Receiver.receiveMessage(*))",
			returning = "message")
	public void sendEmailAfterReceiveMessage(EmailMessage message) throws MessagingException {
		sender.sendMail(message);
	}
}
