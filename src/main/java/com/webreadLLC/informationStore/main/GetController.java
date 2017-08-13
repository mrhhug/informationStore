package com.webreadLLC.informationStore.main;

import com.google.gson.Gson;
import com.webreadLLC.informationStore.gsonDefinitions.Project;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironment;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKey;
import static com.webreadLLC.informationStore.main.InformationStoreApplication.crud;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//import static com.webreadLLC.informationStore.main.InformationStoreApplication.crud;

/**
 * @author michael
 */
@RestController("/api/get")
public class GetController {
    
    @GetMapping("/api/get")
    public String apiGetProjects() throws SQLException {
	return new Gson().toJson(crud.GetProjects());
    }
    
    @GetMapping("/api/get/{project}")
    public String apiGetProjectEnvironments(@PathVariable String project) throws SQLException {
	return new Gson().toJson(crud.GetProjectEnvironments(new Project(project)));
    }
    
    @GetMapping("/api/get/{project}/keys")
    public String apiGetProjectKeys(@PathVariable String project) throws SQLException {
	return new Gson().toJson(crud.GetProjectKeys(new Project(project)));
    }
    
    @GetMapping("/api/get/{project}/{environment}")
    public String apiGetProjectEnvironmentKeyValues(@PathVariable String project, @PathVariable String environment) throws SQLException {
	return new Gson().toJson(crud.GetProjectEnvironmentKeyValues(new ProjectEnvironment(project,environment)));
    }
    
    @GetMapping("/api/get/{project}/{environment}/{key}")
    public String apiGetProjectEnvironmentKeyValue(@PathVariable String project, @PathVariable String environment, @PathVariable String key) throws SQLException {
	return new Gson().toJson(crud.GetProjectEnvironmentKeyValue(new ProjectEnvironmentKey(project,environment,key)));
    }
}
