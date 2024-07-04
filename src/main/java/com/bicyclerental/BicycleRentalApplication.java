package com.bicyclerental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class BicycleRentalApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BicycleRentalApplication.class, args);

		Arrays.stream(ctx.getBeanDefinitionNames()).toList().forEach(System.out::println);
	}
}
