package edu.itmo.blps.messaging.dto;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Data
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
public class EmailMessage {
	@Size(max = 50, message = "subject wrong size")
	private String subject;

	@NotNull(message = "emailTo is null")
	@Email(message = "emailTo not valid email")
	private String emailTo;

	@NotNull(message = "message content is null")
	private Object payload;
}
