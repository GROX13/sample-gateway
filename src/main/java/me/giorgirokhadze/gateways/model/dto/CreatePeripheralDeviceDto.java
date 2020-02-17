package me.giorgirokhadze.gateways.model.dto;

import me.giorgirokhadze.gateways.model.PeripheralDeviceStatus;
import me.giorgirokhadze.gateways.model.dto.validation.UniqueUID;

import javax.validation.constraints.NotBlank;

public class CreatePeripheralDeviceDto {

	@UniqueUID
	@NotBlank(message = "uid is mandatory")
	private Long uid;

	private String vendor;

	private PeripheralDeviceStatus status;

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

	public PeripheralDeviceStatus getStatus() {
		return status;
	}

	public void setStatus(PeripheralDeviceStatus status) {
		this.status = status;
	}
}
