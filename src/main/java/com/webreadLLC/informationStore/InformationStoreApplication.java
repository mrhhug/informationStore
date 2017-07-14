package com.webreadLLC.informationStore;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InformationStoreApplication {
    
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(InformationStoreApplication.class, args);
		new MyController();
	}
}
