package com.sample.file.dwnld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootFileDownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFileDownloadApplication.class, args);
	}

}
