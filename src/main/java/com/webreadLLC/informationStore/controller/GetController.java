package com.webreadLLC.informationStore.controller;

import static com.webreadLLC.informationStore.controller.AbstractController.sqlCRUD;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */
@RestController("api/get/")
public class GetController extends AbstractController {

    public GetController() throws SQLException {
	super();
    }
    /**
     * Get projects
     * 
     * @return json string of all projects
     */
    @GetMapping("api/get/")
    public String api() {
	StringBuilder ret = new StringBuilder();
	try {
	    ret.append("{\"projects\":[");
	    for( String i : sqlCRUD.queryAllProjects()) {
		ret.append("\"").append(i).append("\",");
	    }
	    deleteLastComma(ret);
	    ret.append("]}\n");
	}
	catch (SQLException e) {
	    ret.append("ERROR : ").append(e.getLocalizedMessage());
	}
	return ret.toString();
    }
    
    /**
     * Get environments for project
     * 
     * @param project
     * @return json string of project environments
     */
    @GetMapping("api/get/{project}/")
    public String apiGetProject(@PathVariable String project) {
	StringBuilder ret = new StringBuilder();
	try {
	    Set<String> q = sqlCRUD.queryProjectEnvironments(project);
	    ret.append("{\"").append(project).append("\":[");
	    for( String i : q) {
		ret.append("\"").append(i).append("\",");
	    }
	    deleteLastComma(ret);
	    ret.append("]}\n");
	}
	catch (SQLException e) {
	    ret.append("ERROR : ").append(e.getLocalizedMessage());
	}
	return ret.toString();
    }
    
    /**
     * Get key/value pairs for project environment
     * 
     * @param project
     * @param environment
     * @return json string of project environment key/value pairs
     * @throws SQLException 
     */
    @GetMapping("api/get/{project}/{environment}/")
    public String apiGetProjectEnvironment(@PathVariable String project, @PathVariable String environment) throws SQLException {
	StringBuilder ret = new StringBuilder();
	try {
	    Map<String, String> KV = sqlCRUD.queryEnvironmentKeyValues(project,environment);
	    ret.append("{\"").append(project).append("\":{");
	    for( String i : KV.keySet()) {
		ret.append("\"").append(i).append("\":\"").append(KV.get(i)).append("\",");
	    }
	    deleteLastComma(ret);
	    ret.append("}}\n");
	}
	catch (SQLException e) {
	    ret.append("ERROR : ").append(e.getLocalizedMessage());
	}
	return ret.toString();
    }
    
    @GetMapping("api/get/{project}/{environment}/{key}/")
    public String apiGetProjectEnvironmentKey(@PathVariable String project, @PathVariable String environment, @PathVariable String key) throws SQLException {
	//TODO
	return "";
    }
}
