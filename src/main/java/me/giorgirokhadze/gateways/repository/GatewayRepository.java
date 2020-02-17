package me.giorgirokhadze.gateways.repository;

import me.giorgirokhadze.gateways.model.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
	Gateway findBySerialNumber(String serialNumber);
}
