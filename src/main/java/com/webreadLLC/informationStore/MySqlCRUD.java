package com.webreadLLC.informationStore;

import gsonDefinitions.ProjectKeysGSONDefinition;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	c.close();
	return ret;
    }
    
    public Set<String> queryProjectEnvironments(String myProject) throws SQLException {
	Set<String> ret = new HashSet<>();
	Connection c = DriverManager.getConnection(url+"/"+db, user, pass);
	String statement = "SELECT " + environment + " FROM " + myProject;
	PreparedStatement prepareStatement = c.prepareStatement(statement);
	prepareStatement.execute();
	ResultSet rs = prepareStatement.getResultSet();
	while(rs.next()) {
	    ret.add(rs.getString(environment));
	}
	c.close();
	return ret;
	
    }
    
    public Map<String,String> queryEnvironmentKeyValues(String myProject, String myEnvironment) throws SQLException {
	Map<String,String> ret = new HashMap<>();
	
	//grab columnsReadyToBeQueried
	Set<String> keys = queryProjectKeys(myProject);
	StringBuilder columnsReadyToBeQueried = new StringBuilder();
	for( String i : keys) {
	    columnsReadyToBeQueried.append(i).append(", ");
	}
	//this will crash if there are no keays
	columnsReadyToBeQueried.deleteCharAt(columnsReadyToBeQueried.lastIndexOf(", "));
	
	Connection c = DriverManager.getConnection(url+"/"+db, user, pass);
	String statement = "SELECT " + columnsReadyToBeQueried.toString() + "FROM " + myProject + " where " + environment + "='" + myEnvironment + "'";
	PreparedStatement prepareStatement = c.prepareStatement(statement);
	prepareStatement.execute();
	ResultSet rs = prepareStatement.getResultSet();
	rs.next();
	for( String i :keys) {
	    ret.put(i, rs.getString(i));
	}
	c.close();
	return ret;
    }
    
    public Set<String> insertNewProject(ProjectKeysGSONDefinition par) throws SQLException {
	Connection c = DriverManager.getConnection(url+"/"+db, user, pass);
	StringBuilder statement = new StringBuilder();
	statement.append("CREATE TABLE ").append(par.project).append(" ");
	statement.append("(").append(id).append(" int NOT NULL, PRIMARY KEY (ID), ");
	statement.append("environmentVAR TEXT NOT NULL, ");
	for( String i : par.keys) {
	    statement.append(i).append(" TEXT,");
	}
	statement.deleteCharAt(statement.lastIndexOf(","));
	statement.append(")");
	PreparedStatement prepareStatement = c.prepareStatement(statement.toString());
	prepareStatement.execute();
	c.close();
	Set<String> queryProjectKeys = queryProjectKeys(par.project);
	return queryProjectKeys;
    }
    
    public void deleteTable(String par) throws SQLException {
	Connection c = DriverManager.getConnection(url+"/"+db, user, pass);
	String statement = "DROP TABLE " + par;
	PreparedStatement ps = c.prepareStatement(statement);
	try {
	    ps.execute();
	} catch(SQLException e) {	    
	    if(!e.getMessage().contains("Unknown table")) {
		throw e; 
	    }
	}
    }
    
    private Set<String> queryProjectKeys(String myProject) throws SQLException {
	Set<String> ret = new HashSet<>();
	Connection c = DriverManager.getConnection(url+"/"+db, user, pass);
	String statement = "DESC " + myProject;
	PreparedStatement prepareStatement = c.prepareStatement(statement);
	prepareStatement.execute();
	ResultSet rs = prepareStatement.getResultSet();
	while(rs.next()) {
	    ret.add(rs.getString("Field"));
	}
	c.close();
	//like showing someone your underwear, no one wants to see this... unless they know you ;)
	ret.remove(id);
	ret.remove(environment);
	return ret;
    }
}
