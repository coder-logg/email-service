package edu.itmo.blps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.blps.messaging.dto.EmailMessage;
import edu.itmo.blps.messaging.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
@Slf4j
public class EmailService {
	@Autowired
	private JavaMailSender senderMails;

	private final static String OUR_COMPANY_NAME = "АО «ЧИП и ДИП»";

	@Value("classpath:/templates/receipt/receipt.html")
	private Resource receiptTemplate;

	@Autowired
	private ObjectMapper mapper;

	public void sendMail(EmailMessage emailMessage) throws MessagingException {
		MimeMessage mimeMessage = senderMails.createMimeMessage();
		MimeMailMessage mimeMailMessage = new MimeMailMessage(mimeMessage);
		mimeMailMessage.setTo(emailMessage.getEmailTo());
		mimeMailMessage.setSubject(emailMessage.getSubject());
		if (emailMessage.getPayload() instanceof TransactionDTO)
			mimeMailMessage.getMimeMessage().setContent(makeNiceReceipt((TransactionDTO) emailMessage.getPayload()), "text/html");
		else {
			try {
				mimeMailMessage.setText(mapper.writeValueAsString(emailMessage.getPayload()));
			} catch (JsonProcessingException e) {
				log.warn("Can't process EmailMessage.payload to json");
				throw new RuntimeException(e);
			}
		};
		Transport.send(mimeMailMessage.getMimeMessage());
	}


	public String makeNiceReceipt(TransactionDTO transaction){
		try {
			Document document = Jsoup.parse(receiptTemplate.getFile(), "utf-8");
//			document.getElementById("greeting").text(transaction.getCustomerUsername());
//			document.getElementById("customer-username").text(transaction.getCustomerUsername());
			document.getElementById("transaction-id").text(String.valueOf(transaction.getId()));
			document.getElementById("date").text(transaction.getDateTime().toString());
			document.getElementById("discount").text(transaction.getDiscount().toString());
			document.getElementById("device-name").text(transaction.getDevice().getName());
			document.getElementById("device-price").text(transaction.getDevice().getPrice().toString());
			document.getElementById("quantity").text(transaction.getAmount().toString());
			document.getElementsByClass("seller-name").forEach(x ->x.text(transaction.getSellerCompanyName()));
			document.getElementsByClass("customer-name").forEach(x ->x.text(transaction.getCustomerName()));
			document.getElementsByClass("seller-username").forEach(x ->x.text(transaction.getSellerUsername()));
			document.getElementsByClass("customer-username").forEach(x ->x.text(transaction.getCustomerUsername()));
			document.getElementsByClass("money-summary").forEach(x ->x.text(String.valueOf(transaction.getSummary())));
			return document.html();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

//	private String makeNiceReceipt(TransactionDTO transaction){
//		return "Чек №" + transaction.getId() + "\n" +
//				transaction.getDateTime() + "\n" +
//				OUR_COMPANY_NAME + "\n" +
//				"--------------------\n" +
//				transaction.getSellerCompanyName() + "\n" +
//				String.format("%60s  %-10s" , transaction.getDevice().getName(),
//						transaction.getDevice().getPrice() + "x" + transaction.getAmount() + "-" + transaction.getDiscount() + "% = " + transaction.getSummary()) ;
//	}
}
