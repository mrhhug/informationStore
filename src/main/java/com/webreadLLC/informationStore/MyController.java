package com.webreadLLC.informationStore;

import java.sql.SQLException;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */
@RestController("/")
public class MyController {

    private final MySqlCRUD sql;
    
    MyController() throws SQLException {
	this.sql = new MySqlCRUD();
	sql.init();
    }
    
    @GetMapping("")
    public String urlParser() throws SQLException {
	String ret = "";
	ret += "Please Select a project : <br>";
	for ( String i : sql.queryAllProjects()) {
	    ret += "<form action='./api/"+ i +"/'><input type='submit' value='" + i + "'/></form>";
	}
	ret += "<form action='./newProject'><input type='submit' value='Create new project'/></form>";
	return ret;
    }
    
    @GetMapping("newProject")
    public String newProject() throws SQLException {
	String ret = "";
	ret += "<form action='./'>";
	ret += "<input type='text' name='project' placeholder='Awesome project name'><br>";
	ret += "<input type='submit' value='Create Project'/></form>";
	return ret;
    }
    
    @GetMapping("api/{project}")
    public String apiProject(@PathVariable String project) throws SQLException {
	String ret = "";
	ret += "project = " + project + "<br>";
	return ret;
    }
    
    @GetMapping("api/{project}/{environment}")
    public String apiProjectEnvironment(@PathVariable String project, @PathVariable String environment) throws SQLException {
	String ret = "";
	ret += "project = " + project + "<br>";
	ret += "environment = " + environment +"<br>";
	return ret;
    }
}
