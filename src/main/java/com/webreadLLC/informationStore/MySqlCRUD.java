package com.webreadLLC.informationStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author michael
 */
public class MySqlCRUD {
    private String url;
    private String db;
    private String user;
    private String pass;

    public MySqlCRUD() {
	this.url = "jdbc:mysql://localhost:3306";
	this.db = "MicahelInformationStore";
	this.user = "root";
	this.pass = "support1";
    }
    
    public void init() throws SQLException {
	Connection c = DriverManager.getConnection(url, user, pass);
	String statement = "CREATE DATABASE IF NOT EXISTS " + db;
	PreparedStatement prepareStatement = c.prepareStatement(statement);
	prepareStatement.execute();
    }
    public Set<String> queryAllProjects() throws SQLException {
	Set<String> ret = new HashSet<>();
	Connection c = DriverManager.getConnection(url, user, pass);
	String statement = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='"+db+"'";
	PreparedStatement prepareStatement = c.prepareStatement(statement);
	prepareStatement.execute();
	ResultSet rs = prepareStatement.getResultSet();
	while(rs.next()) {
	    ret.add(rs.getString("TABLE_NAME"));
	}
	return ret;
    }
    
}
