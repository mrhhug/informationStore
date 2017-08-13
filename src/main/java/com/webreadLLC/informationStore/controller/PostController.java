package com.webreadLLC.informationStore.controller;

import com.google.gson.Gson;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKeyValues;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectKeys;
import static com.webreadLLC.informationStore.main.InformationStoreApplication.crud;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */

@RestController("/api/post")
public class PostController {

    @PostMapping("/api/post/{project}")
    public String apiPostProject(@PathVariable String project, @RequestBody String ks) throws SQLException {
	String ret = "FAIL";
	if(0 == crud.PostProject(new ProjectKeys(project, new Gson().fromJson(ks, List.class)))) {
	    ret = "OK";
	}
	return ret;
    }
    
    @PostMapping("/api/post/{project}/{environment}")
    public String apiPostProjectEnvironmentKeyValues(@PathVariable String project, @PathVariable String environment, @RequestBody String ks) throws SQLException {
	String ret = "No Content";
	if(1 == crud.PostProjectEnvironmentKeyValues(new ProjectEnvironmentKeyValues(project, environment, new Gson().fromJson(ks, Map.class)))) {
	    ret = "OK";
	}
	return ret;
    }
}
