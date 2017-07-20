package com.webreadLLC.informationStore.controller;

import com.google.gson.Gson;
import static com.webreadLLC.informationStore.controller.AbstractController.crud;
import gsonDefinitions.ProjectEnvironmentKeyValues;
import gsonDefinitions.ProjectKeys;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */

@RestController("api/post/")
public class PostController {

    @PostMapping("api/post/{project}/")
    public String apiPutProject(@PathVariable String p, @RequestBody String ks) throws SQLException {
	String ret = "No Content";
	if(crud.PostProject(new ProjectKeys(p, new Gson().fromJson(ks, Set.class)))) {
	    ret = "OK";
	}
	return ret;
    }
    
    @PostMapping("api/post/{project}/{environment}/")
    public String apiPutProjectEnvironmentKeyValues(@PathVariable String p, @PathVariable String e, @RequestBody String ks) throws SQLException {
	String ret = "No Content";
	if(crud.PostProjectEnvironmentKeyValues(new ProjectEnvironmentKeyValues(p, e, new Gson().fromJson(ks, Map.class)))) {
	    ret = "OK";
	}
	return ret;
    }
}
