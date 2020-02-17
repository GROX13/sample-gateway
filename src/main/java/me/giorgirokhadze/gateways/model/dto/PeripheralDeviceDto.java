package me.giorgirokhadze.gateways.model.dto;

import me.giorgirokhadze.gateways.model.PeripheralDevice;
import me.giorgirokhadze.gateways.model.PeripheralDeviceStatus;

import java.time.LocalDateTime;

public class PeripheralDeviceDto {

	private Long uid;

	private String vendor;

	private LocalDateTime creationDate;

	private PeripheralDeviceStatus status;

	public PeripheralDeviceDto() {
	}

	public PeripheralDeviceDto(PeripheralDevice peripheralDevice) {
		this(peripheralDevice.getUid(), peripheralDevice.getVendor(), peripheralDevice.getCreationDate(), peripheralDevice.getStatus());
	}

	public PeripheralDeviceDto(Long uid, String vendor, LocalDateTime creationDate, PeripheralDeviceStatus status) {
		this.uid = uid;
		this.vendor = vendor;
		this.creationDate = creationDate;
		this.status = status;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public PeripheralDeviceStatus getStatus() {
		return status;
	}

	public void setStatus(PeripheralDeviceStatus status) {
		this.status = status;
	}
}
