package me.giorgirokhadze.gateways.model.dto.validation;

import me.giorgirokhadze.gateways.repository.PeripheralDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext;

public class UniqueUIDValidator implements ConstraintValidator<UniqueUID, String> {

	@Autowired
	private PeripheralDeviceRepository peripheralDeviceRepository;

	public void initialize(UniqueUID constraint) {
		processInjectionBasedOnCurrentContext(this);
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return peripheralDeviceRepository.findByUid(Long.parseLong(value)) == null;
	}
}
