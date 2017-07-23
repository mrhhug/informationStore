package com.webreadLLC.informationStore.main;

import com.webreadLLC.informationStore.crud.CRUD;
import com.webreadLLC.informationStore.crud.MySqlCRUD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InformationStoreApplication {
    
    public static CRUD crud = new MySqlCRUD();
    
	public static void main(String[] args){
	    SpringApplication.run(InformationStoreApplication.class, args);
	}
}
