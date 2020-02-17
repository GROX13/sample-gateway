package me.giorgirokhadze.gateways.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PeripheralDevice {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private Long uid;

	private String vendor;

	private LocalDateTime creationDate;

	@Enumerated(EnumType.STRING)
	private PeripheralDeviceStatus status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "gatewayId")
	private Gateway gateway;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}
}
