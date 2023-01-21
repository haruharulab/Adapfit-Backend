package com.harulab.adapfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@EnableCaching
@ConfigurationPropertiesScan
@SpringBootApplication
public class AdapfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdapfitApplication.class, args);
	}

	@PostConstruct
	public void startedAt(){
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
