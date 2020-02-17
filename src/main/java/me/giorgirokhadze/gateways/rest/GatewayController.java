package me.giorgirokhadze.gateways.rest;

import me.giorgirokhadze.gateways.model.dto.CreateGatewayDto;
import me.giorgirokhadze.gateways.model.dto.CreatePeripheralDeviceDto;
import me.giorgirokhadze.gateways.model.dto.GatewayDto;
import me.giorgirokhadze.gateways.service.GatewayService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/gateway")
public class GatewayController {

	private final GatewayService gatewayService;

	public GatewayController(GatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}

	@GetMapping(value = "/list")
	@ResponseStatus(HttpStatus.OK)
	public List<GatewayDto> listGateways() {
		return gatewayService.listGateways();
	}

	@GetMapping(value = "/{serialNumber}")
	@ResponseStatus(HttpStatus.OK)
	public GatewayDto getGateway(@PathVariable String serialNumber) {
		return gatewayService.getGateway(serialNumber);
	}

	@PostMapping(value = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createGateway(@Valid @RequestBody CreateGatewayDto gatewayDto) {
		gatewayService.createGateway(gatewayDto);
	}

	@PostMapping(value = "/{serialNumber}/device/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPeripheralDevice(@PathVariable String serialNumber, @Valid @RequestBody CreatePeripheralDeviceDto gatewayDto) {
		gatewayService.createPeripheralDevice(serialNumber, gatewayDto);
	}

	@PostMapping(value = "/{serialNumber}/device/{uid}/delete")
	@ResponseStatus(HttpStatus.OK)
	public void deletePeripheralDevice(@PathVariable String serialNumber, @PathVariable Long uid) {
		gatewayService.deletePeripheralDevice(serialNumber, uid);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, String> handleIllegalArgumentExceptions(IllegalArgumentException exception) {
		final Map<String, String> errors = new HashMap<>();
		errors.put("illegalArgument", exception.getLocalizedMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalStateException.class)
	public Map<String, String> handleIllegalStateExceptions(IllegalStateException exception) {
		final Map<String, String> errors = new HashMap<>();
		errors.put("illegalState", exception.getLocalizedMessage());
		return errors;
	}
}
