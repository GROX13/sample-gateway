package me.giorgirokhadze.gateways.service;

import me.giorgirokhadze.gateways.model.Gateway;
import me.giorgirokhadze.gateways.model.PeripheralDeviceStatus;
import me.giorgirokhadze.gateways.model.dto.CreateGatewayDto;
import me.giorgirokhadze.gateways.model.dto.CreatePeripheralDeviceDto;
import me.giorgirokhadze.gateways.model.dto.GatewayDto;
import me.giorgirokhadze.gateways.repository.GatewayRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GatewayServiceTest {

	@Autowired
	GatewayService gatewayService;

	@Autowired
	GatewayRepository gatewayRepository;

	@Test
	public void shouldGetCorrectGatewayBySerialNumber() {
		Gateway gateway = new Gateway("1", "test", "0.0.0.0");
		gatewayRepository.save(gateway);

		GatewayDto gatewayDto = gatewayService.getGateway(gateway.getSerialNumber());

		assertThat(gatewayDto.getSerialNumber(), equalTo(gateway.getSerialNumber()));
		assertThat(gatewayDto.getName(), equalTo(gateway.getName()));
		assertThat(gatewayDto.getIpAddress(), equalTo(gateway.getIpAddress()));
	}

	@Test
	public void shouldCreateNewGateway() {
		CreateGatewayDto gatewayDto = new CreateGatewayDto();
		gatewayDto.setSerialNumber("1");
		gatewayService.createGateway(gatewayDto);

		List<GatewayDto> list = gatewayService.listGateways();

		assertEquals(list.size(), 1);
	}

	@Test
	public void shouldCreateNewGatewayAndCreatePeripheralDevices() {
		CreateGatewayDto gatewayDto = new CreateGatewayDto();
		gatewayDto.setSerialNumber("1");
		List<CreatePeripheralDeviceDto> peripheralDeviceDtos = new ArrayList<>();

		CreatePeripheralDeviceDto dto = new CreatePeripheralDeviceDto();
		dto.setStatus(PeripheralDeviceStatus.OFFLINE);
		dto.setUid(1L);
		dto.setVendor("test");

		peripheralDeviceDtos.add(dto);
		gatewayDto.setPeripheralDevices(peripheralDeviceDtos);
		gatewayService.createGateway(gatewayDto);

		List<GatewayDto> list = gatewayService.listGateways();

		assertEquals(list.size(), 1);

		GatewayDto savedGatewayDto = list.get(0);

		assertEquals(savedGatewayDto.getPeripheralDevices().size(), 1);
	}

	@Test
	public void shouldAddPeripheralDevice() {
		CreateGatewayDto gatewayDto = new CreateGatewayDto();
		gatewayDto.setSerialNumber("1");
		List<CreatePeripheralDeviceDto> peripheralDeviceDtos = new ArrayList<>();

		CreatePeripheralDeviceDto dto = new CreatePeripheralDeviceDto();
		dto.setStatus(PeripheralDeviceStatus.OFFLINE);
		dto.setUid(1L);
		dto.setVendor("test");

		peripheralDeviceDtos.add(dto);
		gatewayDto.setPeripheralDevices(peripheralDeviceDtos);
		gatewayService.createGateway(gatewayDto);

		List<GatewayDto> list = gatewayService.listGateways();

		assertEquals(list.size(), 1);

		GatewayDto savedGatewayDto = list.get(0);

		assertEquals(savedGatewayDto.getPeripheralDevices().size(), 1);

		dto = new CreatePeripheralDeviceDto();
		dto.setStatus(PeripheralDeviceStatus.ONLINE);
		dto.setUid(2L);
		dto.setVendor("test");

		gatewayService.createPeripheralDevice(savedGatewayDto.getSerialNumber(), dto);

		savedGatewayDto = gatewayService.getGateway(savedGatewayDto.getSerialNumber());

		assertEquals(savedGatewayDto.getPeripheralDevices().size(), 2);
	}

	@Test
	public void shouldDeletePeripheralDevice() {
		CreateGatewayDto gatewayDto = new CreateGatewayDto();
		gatewayDto.setSerialNumber("1");
		List<CreatePeripheralDeviceDto> peripheralDeviceDtos = new ArrayList<>();

		CreatePeripheralDeviceDto dto = new CreatePeripheralDeviceDto();
		dto.setStatus(PeripheralDeviceStatus.OFFLINE);
		dto.setUid(1L);
		dto.setVendor("test");

		peripheralDeviceDtos.add(dto);
		gatewayDto.setPeripheralDevices(peripheralDeviceDtos);
		gatewayService.createGateway(gatewayDto);

		List<GatewayDto> list = gatewayService.listGateways();

		assertEquals(list.size(), 1);

		GatewayDto savedGatewayDto = list.get(0);

		assertEquals(savedGatewayDto.getPeripheralDevices().size(), 1);

		gatewayService.deletePeripheralDevice(savedGatewayDto.getSerialNumber(), dto.getUid());

		savedGatewayDto = gatewayService.getGateway(savedGatewayDto.getSerialNumber());

		assertEquals(savedGatewayDto.getPeripheralDevices().size(), 0);
	}
}
