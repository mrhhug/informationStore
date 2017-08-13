package com.webreadLLC.informationStore.controller;

import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironment;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKeyValue;
import static com.webreadLLC.informationStore.main.InformationStoreApplication.crud;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */

@RestController("api/put")
public class PutController {
    
    @PutMapping("api/put/{project}/{environment}")
    public String apiPutProjectEnvironment(@PathVariable String project, @PathVariable String environment) throws SQLException {
	String ret = "No Content";
	if(crud.PutProjectEnvironment(new ProjectEnvironment(project, environment))) {
	    ret = "OK";
	}
	return ret;
    }
    
    @PutMapping("api/put/{project}/{environment}/{key}/{value}")
    public String apiPutProjectEnvironmentKeyValue(@PathVariable String project, @PathVariable String environment, @PathVariable String key, @PathVariable String value) throws SQLException {
	String ret = "No Content";
	if(crud.PutProjectEnvironmentKeyValue(new ProjectEnvironmentKeyValue(project, environment, key, value))) {
	    ret = "OK";
	}
	return ret;
    }
}
