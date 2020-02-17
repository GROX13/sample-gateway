package me.giorgirokhadze.gateways.rest;

import me.giorgirokhadze.gateways.model.dto.CreateGatewayDto;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GatewayControllerTest extends AbstractControllerTest {

	@Test
	public void shouldCreateNewGateway() throws Exception {
		// given
		CreateGatewayDto gatewayDto = new CreateGatewayDto();
		gatewayDto.setIpAddress("1.1.1.1");
		gatewayDto.setName("test");
		gatewayDto.setSerialNumber("1");

		// when
		String value = objectMapper.writeValueAsString(gatewayDto);

		// then
		mockMvc.perform(post("/gateway/create")
				.content(value)
				.contentType(APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void shouldFailValidationOnCreateNewGateway() throws Exception {
		// given
		CreateGatewayDto gatewayDto = new CreateGatewayDto();
		gatewayDto.setIpAddress("1.1.1");
		gatewayDto.setName("test");
		gatewayDto.setSerialNumber("1");

		// when
		String value = objectMapper.writeValueAsString(gatewayDto);

		// then
		mockMvc.perform(post("/gateway/create")
				.content(value)
				.contentType(APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
}
