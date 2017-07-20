package com.webreadLLC.informationStore.controller;

import gsonDefinitions.ProjectEnvironment;
import gsonDefinitions.ProjectEnvironmentKeyValue;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webreadLLC.informationStore.CRUD.CRUD;

/**
 * @author michael
 */

@RestController("api/put/")
public class PutController extends AbstractController {

    public PutController(CRUD crud) {
	super(crud);
    }
    
    @PutMapping("api/put/{project}/{environment}/")
    public String apiPutProjectEnvironment(@PathVariable String p, @PathVariable String e) throws SQLException {
	String ret = "No Content";
	if(crud.PutProjectEnvironment(new ProjectEnvironment(p, e))) {
	    ret = "OK";
	}
	return ret;
    }
    
    @PutMapping("api/put/{project}/{environment}/{key}/{value}/")
    public String apiPutProjectEnvironmentKeyValue(@PathVariable String p, @PathVariable String e, @PathVariable String k, @PathVariable String v) throws SQLException {
	String ret = "No Content";
	if(crud.PutProjectEnvironmentKeyValue(new ProjectEnvironmentKeyValue(p, e, k, v))) {
	    ret = "OK";
	}
	return ret;
    }
}
