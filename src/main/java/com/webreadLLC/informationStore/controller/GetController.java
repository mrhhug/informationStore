package com.webreadLLC.informationStore.controller;

import com.google.gson.Gson;
import gsonDefinitions.Project;
import gsonDefinitions.ProjectEnvironment;
import gsonDefinitions.ProjectEnvironmentKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.webreadLLC.informationStore.CRUD.CRUD;

/**
 * @author michael
 */
@RestController("api/get/")
public class GetController extends AbstractController {

    public GetController(CRUD crud) {
	super(crud);
    }
    
    @GetMapping("api/get/")
    public String apiGetProjects() {
	return new Gson().toJson(crud.GetProjects());
    }
    
    @GetMapping("api/get/{project}/")
    public String apiGetProjectEnvironments(@PathVariable String p) {
	return new Gson().toJson(crud.GetProjectEnvironments(new Project(p)));
    }
    
    @GetMapping("api/get/{project}/keys")
    public String apiGetProjectKeys(@PathVariable String p) {
	return new Gson().toJson(crud.GetProjectKeys(new Project(p)));
    }
    
    @GetMapping("api/get/{project}/{environment}/")
    public String apiGetProjectEnvironmentKeyValues(@PathVariable String p, @PathVariable String e) {
	return new Gson().toJson(crud.GetProjectEnvironmentKeyValues(new ProjectEnvironment(p,e)));
    }
    
    @GetMapping("api/get/{project}/{environment}/{key}/")
    public String apiGetProjectEnvironmentKeyValue(@PathVariable String p, @PathVariable String e, @PathVariable String k) {
	return new Gson().toJson(crud.GetProjectEnvironmentKeyValue(new ProjectEnvironmentKey(p,e,k)));
    }
}
