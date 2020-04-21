package com.demo.upipayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.demo.upipayment.conf.BankingServiceConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "banking-client",configuration = BankingServiceConfiguration.class)

public class UpipaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpipaymentApplication.class, args);
	}

}
