package com.webreadLLC.informationStore.controller;

import java.sql.SQLException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */

@RestController("api/put/")
public class PutController {
    
    @PutMapping("api/put/{project}/")
    public String apiPutProject(@PathVariable String project, @RequestBody String keys) throws SQLException {
	//TODO
	return "";
    }
    
    @PutMapping("api/put/{project}/{environment}/")
    public String apiPutProjectEnvironment(@PathVariable String project, @PathVariable String environment) throws SQLException {
	//TODO
	return "";
    }
    
    @PutMapping("api/put/{project}/{environment}/{key}/{value}/")
    public String apiDeleteProjectKeyValue(@PathVariable String project, @PathVariable String environment, @PathVariable String key) throws SQLException {
	//TODO
	return "";
    }
}
