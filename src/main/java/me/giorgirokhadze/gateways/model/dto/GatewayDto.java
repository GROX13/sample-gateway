package me.giorgirokhadze.gateways.model.dto;

import me.giorgirokhadze.gateways.model.Gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GatewayDto {

	private String serialNumber;

	private String name;

	private String ipAddress;

	private List<PeripheralDeviceDto> peripheralDevices = new ArrayList<>();

	public GatewayDto() {
	}

	public GatewayDto(Gateway gateway) {
		this(
				gateway.getSerialNumber(),
				gateway.getName(),
				gateway.getIpAddress(),
				gateway.getPeripheralDevices().stream().map(PeripheralDeviceDto::new).collect(Collectors.toList())
		);
	}

	public GatewayDto(String serialNumber, String name, String ipAddress, List<PeripheralDeviceDto> peripheralDevices) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.ipAddress = ipAddress;
		this.peripheralDevices = peripheralDevices;
	}

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

	public List<PeripheralDeviceDto> getPeripheralDevices() {
		return peripheralDevices;
	}

	public void setPeripheralDevices(List<PeripheralDeviceDto> peripheralDevices) {
		this.peripheralDevices = peripheralDevices;
	}
}
