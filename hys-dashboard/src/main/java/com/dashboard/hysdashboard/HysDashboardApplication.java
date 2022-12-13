package com.dashboard.hysdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.*
@SpringBootApplication
@EnableEurekaServer
@EnableHystrixDasboard
public class HysDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HysDashboardApplication.class, args);
	}

}
