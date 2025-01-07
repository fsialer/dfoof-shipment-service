package com.fernando.ms.shipments.app.dfood_shipments_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DfoodShipmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfoodShipmentsServiceApplication.class, args);
	}

}
