package edu.itmo.blps.messaging.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	property = "type")
public class DeviceDTO {
	private Integer id;
	private String name;
	private Integer price;
}
