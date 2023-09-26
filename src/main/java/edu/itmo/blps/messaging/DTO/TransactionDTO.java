package edu.itmo.blps.messaging.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	private Integer id;
	private DeviceDTO device;
	private String sellerUsername;
	private String customerUsername;
	private String customerName;
	private Integer amount;
	private Integer discount;
	private LocalDateTime dateTime;
	private String sellerCompanyName;

	public int getSummary(){
		int summary = device.getPrice() * amount;
		summary -= summary * (discount / 100);
		return summary;
	}
}
