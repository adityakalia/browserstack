package org.hackathon.elite7.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan (value = {"org.hackathon.elite7"})
public class Application {
	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
}