package com.webreadLLC.informationStore.CRUD;

/**
 * @author michael
 */
public class MySqlCRUD {
    private final String url;
    private final String db;
    private final String user;
    private final String pass;
    private final String id;
    private final String environment;

    public MySqlCRUD() {
	this.url = "jdbc:mysql://localhost:3306";
	this.db = "MicahelInformationStore";
	this.user = "root";
	this.pass = "support1";
	this.id = "ID";
	//this should be the primary key, we know it will be unique
	this.environment = "environment";
    }
    
}
