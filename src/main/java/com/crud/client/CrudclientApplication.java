package com.crud.client;

import com.crud.client.entities.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudclientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudclientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("---- TESTE ----");

		Client client = new Client();

		System.out.println(client.toString());
	}
}
