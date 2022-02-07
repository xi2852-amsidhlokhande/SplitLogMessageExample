package com.amsidh.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitLogMessageExampleApplication implements CommandLineRunner {
	private static final Logger logger
			= LoggerFactory.getLogger(SplitLogMessageExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SplitLogMessageExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Example log from {}", SplitLogMessageExampleApplication.class.getSimpleName());
	}
}
