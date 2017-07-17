package com.webreadLLC.informationStore;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InformationStoreApplication {
    
	public static void main(String[] args){
		SpringApplication.run(InformationStoreApplication.class, args);
	    try {
		new Controller();
	    } catch (SQLException e) {
		System.out.println("ERROR : " + e.getLocalizedMessage());
	    }
	}
}
