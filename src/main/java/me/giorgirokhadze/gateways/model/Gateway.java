package me.giorgirokhadze.gateways.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gateway {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String serialNumber;

	private String name;

	private String ipAddress;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("creationDate DESC")
	private List<PeripheralDevice> peripheralDevices = new ArrayList<>();

	public Gateway() {
	}

	public Gateway(String serialNumber, String name, String ipAddress) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.ipAddress = ipAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<PeripheralDevice> getPeripheralDevices() {
		return peripheralDevices;
	}

	public void setPeripheralDevices(List<PeripheralDevice> peripheralDevices) {
		this.peripheralDevices = peripheralDevices;
	}

	public void addDevice(PeripheralDevice device) {
		peripheralDevices.add(device);
	}
}
