package me.giorgirokhadze.gateways.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.giorgirokhadze.gateways.service.GatewayService;
import me.giorgirokhadze.gateways.service.PeripheralDeviceService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	protected ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected GatewayService gatewayService;

	@MockBean
	protected PeripheralDeviceService peripheralDeviceService;

	@Before
	public void setUp() {
		Mockito.reset(gatewayService, peripheralDeviceService);
	}
}
