package com.webreadLLC.informationStore.main;

import com.webreadLLC.informationStore.crud.CRUD;
import com.webreadLLC.informationStore.crud.MySqlCRUD;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InformationStoreApplication {
    
    public static CRUD crud ;
    
	public static void main(String[] args) throws SQLException{
	    SpringApplication.run(InformationStoreApplication.class, args);
            crud = new MySqlCRUD().initTest();
	}
}
