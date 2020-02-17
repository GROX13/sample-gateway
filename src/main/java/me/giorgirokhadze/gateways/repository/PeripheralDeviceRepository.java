package me.giorgirokhadze.gateways.repository;

import me.giorgirokhadze.gateways.model.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeripheralDeviceRepository extends JpaRepository<PeripheralDevice, Long> {
	PeripheralDevice findByUid(Long uid);
}
