package com.webreadLLC.informationStore.crud;

import com.webreadLLC.informationStore.gsonDefinitions.Project;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironment;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKey;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKeyValue;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKeyValues;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironments;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectKeys;
import com.webreadLLC.informationStore.gsonDefinitions.Projects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author michael
 */
public class MySqlCRUD implements CRUD{
    private final String url;
    private final String db;
    private final String user;
    private final String pass;
    private final String id;
    private final String environment;

    //I know you are going to tell me to use hibernate, but i am modding columns too much
    public MySqlCRUD() {
	this.url = "jdbc:mysql://localhost:3306";
	this.db = "MicahelInformationStore";
	this.user = "root";
	this.pass = "support1";
	this.id = "ID";
	//this should be the primary key, we know it will be unique
	this.environment = "environment";
    }

    @Override
    public Projects GetProjects() throws SQLException {
	Set<String> ret = new HashSet<>();
	ResultSet rs = executeMySQL("SELECT table_name FROM information_schema.tables where table_schema='" + db + "';");
	while (rs.next()) {
	    ret.add(rs.getString("table_name"));
	}
	return new Projects(ret);
    }

    @Override
    public ProjectEnvironments GetProjectEnvironments(Project p) throws SQLException {
//	Set<String> ret = new HashSet<>();
//	ResultSet rs = executeMySQL("DESC " + p.project);
//	while (rs.next()) {
//	    ret.add(rs.getString("table_name"));
//	}
//	return new ProjectEnvironments(p.project, ret);
	//not sure if the above works
	return null;
    }

    @Override
    public ProjectKeys GetProjectKeys(Project p) throws SQLException {
	List<String> ret = new ArrayList<>();
	ResultSet rs = executeMySQL("DESC " + p.project);
	while (rs.next()) {
	    ret.add(rs.getString("Field"));
	}
	ret.remove(id);
	ret.remove(environment);
	return new ProjectKeys(p.project, ret);
    }

    @Override
    public ProjectEnvironmentKeyValues GetProjectEnvironmentKeyValues(ProjectEnvironment pe) throws SQLException {
	Map<String, String> ret = new HashMap<>();
	List<String> keys = GetProjectKeys(new Project(pe.project)).keys;
	StringBuilder columns = new StringBuilder();
	for( String i : keys) {
	    columns.append(i).append(", ");
	}
	columns.delete(columns.lastIndexOf(","), columns.length());
	String statment = "SELECT " + columns + " FROM " + pe.project + " where " + environment + "='" + pe.environment + "'";
	ResultSet rs = executeMySQL(statment);
	rs.next();
	for (String i : keys) {
	    ret.put(i, rs.getString(i));
	}
	return new ProjectEnvironmentKeyValues(pe.project, pe.environment, ret);
    }

    @Override
    public ProjectEnvironmentKeyValue GetProjectEnvironmentKeyValue(ProjectEnvironmentKey pek) throws SQLException {
	StringBuilder statement = new StringBuilder();
	statement.append("SELECT ");
	statement.append(pek.key);
	statement.append(" FROM ");
	statement.append(pek.project);
	statement.append(" WHERE ");
	statement.append(environment);
	statement.append("='");
	statement.append(pek.environment);
	statement.append("'");
	ResultSet rs = executeMySQL(statement.toString());
	rs.next();
	return new ProjectEnvironmentKeyValue(pek.project, pek.environment, pek.key, rs.getString(pek.key));
    }

    @Override
    public boolean PutProjectEnvironment(ProjectEnvironment pe) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean PutProjectEnvironmentKeyValue(ProjectEnvironmentKeyValue pekv) throws SQLException {
	StringBuilder statement = new StringBuilder();
	statement.append("UPDATE ");
	statement.append(pekv.project);
	statement.append(" SET ");
	statement.append(pekv.key);
	statement.append("='");
	statement.append(pekv.value);
	statement.append("' WHERE ");
	statement.append(environment);
	statement.append("='");
	statement.append(pekv.environment);
	statement.append("'");
	int result = updateMySQL(statement.toString());
	return 1 == result;
    }

    @Override
    public int PostProject(ProjectKeys pks) throws SQLException {
	StringBuilder statement = new StringBuilder();
	statement.append("CREATE TABLE ");
	statement.append(pks.project);
	statement.append(" (");
	statement.append(id);
	statement.append(" int NOT NULL AUTO_INCREMENT, PRIMARY KEY (");
	statement.append(id);
	statement.append("), ");
	statement.append("environment TEXT, ");
	for( String i : pks.keys) {
	    statement.append(i);
	    statement.append(" TEXT, ");   
	}
	String finalStatment = statement.substring(0,statement.lastIndexOf(", ")) + ")";
	int result = updateMySQL(finalStatment);
	return result;
    }

    @Override
    public int PostProjectEnvironmentKeyValues(ProjectEnvironmentKeyValues pekvs) throws SQLException {
	StringBuilder statement = new StringBuilder();
	statement.append("INSERT INTO ");
	statement.append(pekvs.project);
	statement.append(" (");
	
	StringBuilder columns = new StringBuilder();
	columns.append(environment).append(", ");
	StringBuilder values = new StringBuilder();
	values.append("'");
	values.append(pekvs.environment).append("', ");
	for( String i : pekvs.keyValuePairs.keySet()) {
	    columns.append(i).append(", ");
	    values.append("'");
	    values.append(pekvs.keyValuePairs.get(i)).append("', ");
	}
	statement.append(columns.substring(0, columns.lastIndexOf(", "))).append(") ");
	statement.append("VALUES (").append(values.substring(0, values.lastIndexOf(", "))).append(") ");
	int result = updateMySQL(statement.toString());
	return result;
    }

    @Override
    public int DeleteProject(Project p) throws SQLException {
	StringBuilder statement = new StringBuilder();
	statement.append("DROP TABLE ");
	statement.append(p.project);
	int result = updateMySQL(statement.toString());
	return result;
    }

    @Override
    public int DeleteProjectEnvironment(ProjectEnvironment pe) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ResultSet executeMySQL(String s) throws SQLException {
	Connection conn = DriverManager.getConnection(url+"/"+db,user, pass);
	PreparedStatement prepareStatement = conn.prepareStatement(s);
	ResultSet executeQuery = prepareStatement.executeQuery();
	return executeQuery;
    }
    
    private int updateMySQL(String s) throws SQLException {
	Connection conn = DriverManager.getConnection(url+"/"+db,user, pass);
	PreparedStatement prepareStatement = conn.prepareStatement(s);
	int executeQuery = prepareStatement.executeUpdate();
	return executeQuery;
    }
}
