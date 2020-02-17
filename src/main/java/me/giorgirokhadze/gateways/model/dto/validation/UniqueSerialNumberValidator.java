package me.giorgirokhadze.gateways.model.dto.validation;

import me.giorgirokhadze.gateways.repository.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext;

public class UniqueSerialNumberValidator implements ConstraintValidator<UniqueSerialNumber, String> {

	@Autowired
	private GatewayRepository gatewayRepository;

	public void initialize(UniqueSerialNumber constraint) {
		processInjectionBasedOnCurrentContext(this);
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return gatewayRepository.findBySerialNumber(value) == null;
	}
}
