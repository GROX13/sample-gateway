package me.giorgirokhadze.gateways.service;

import me.giorgirokhadze.gateways.model.Gateway;
import me.giorgirokhadze.gateways.model.PeripheralDevice;
import me.giorgirokhadze.gateways.model.dto.CreateGatewayDto;
import me.giorgirokhadze.gateways.model.dto.CreatePeripheralDeviceDto;
import me.giorgirokhadze.gateways.model.dto.GatewayDto;
import me.giorgirokhadze.gateways.repository.GatewayRepository;
import me.giorgirokhadze.gateways.repository.PeripheralDeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GatewayService {

	private final GatewayRepository gatewayRepository;

	private final PeripheralDeviceRepository deviceRepository;

	public GatewayService(GatewayRepository gatewayRepository, PeripheralDeviceRepository deviceRepository) {
		this.gatewayRepository = gatewayRepository;
		this.deviceRepository = deviceRepository;
	}

	public GatewayDto getGateway(final String serialNumber) {
		Gateway gateway = gatewayRepository.findBySerialNumber(serialNumber);
		return new GatewayDto(gateway);
	}

	public List<GatewayDto> listGateways() {
		return gatewayRepository.findAll().stream().map(GatewayDto::new).collect(Collectors.toList());
	}

	public void createGateway(CreateGatewayDto gatewayDto) {
		Gateway gateway = new Gateway();

		gateway.setName(gatewayDto.getName());
		gateway.setIpAddress(gatewayDto.getIpAddress());
		gateway.setSerialNumber(gatewayDto.getSerialNumber());

		final Gateway dbGateway = gatewayRepository.save(gateway);

		gatewayDto
				.getPeripheralDevices()
				.forEach(deviceDto -> {
					PeripheralDevice device = new PeripheralDevice();
					device.setGateway(dbGateway);
					device.setCreationDate(LocalDateTime.now());
					device.setStatus(deviceDto.getStatus());
					device.setUid(deviceDto.getUid());
					device.setVendor(deviceDto.getVendor());
					
					dbGateway.addDevice(device);
					deviceRepository.save(device);
				});
	}

	public void createPeripheralDevice(String serialNumber, CreatePeripheralDeviceDto gatewayDto) {
		
	}

	public void deletePeripheralDevice(String serialNumber, Long uid) {
		
	}
}
