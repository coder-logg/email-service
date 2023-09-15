package edu.itmo.blps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJms
public class MailSenderApplication {

	public static ApplicationContext CONTEXT;

	public static void main(String[] args) {
		CONTEXT = SpringApplication.run(MailSenderApplication.class, args);
	}
}
