package ru.vasilev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LibrarysystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarysystemApplication.class, args);
	}
}