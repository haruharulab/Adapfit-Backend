package com.harulab.adapfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AdapfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdapfitApplication.class, args);
	}

}
