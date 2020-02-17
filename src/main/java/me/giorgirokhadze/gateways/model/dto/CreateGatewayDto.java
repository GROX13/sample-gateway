package me.giorgirokhadze.gateways.model.dto;

import me.giorgirokhadze.gateways.model.dto.validation.UniqueSerialNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CreateGatewayDto {

	private static final String IP_ADDRESS_PATTERN =
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	@UniqueSerialNumber
	@NotBlank(message = "serialNumber is mandatory")
	private String serialNumber;

	private String name;

	@Pattern(regexp = IP_ADDRESS_PATTERN, message = "ipAddress has invalid value")
	private String ipAddress;

	@Size(max = 10, message = " no more that 10 peripheral devices are allowed for a gateway")
	private List<CreatePeripheralDeviceDto> peripheralDevices = new ArrayList<>();

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public List<CreatePeripheralDeviceDto> getPeripheralDevices() {
		return peripheralDevices;
	}

	public void setPeripheralDevices(List<CreatePeripheralDeviceDto> peripheralDevices) {
		this.peripheralDevices = peripheralDevices;
	}
}
