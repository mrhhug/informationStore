package com.webreadLLC.informationStore.controller;

import static com.webreadLLC.informationStore.controller.AbstractController.sqlCRUD;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */
@RestController("/api/delete/")
public class DeleteController {

    @DeleteMapping("api/delete/")
    public String apiDelete(@PathVariable String project) throws SQLException {
	//TODO
	////Why would yuo want to delete everything?
	return "";
    }
    
    @DeleteMapping("api/delete/{project}/")
    public String apiDeleteProject(@PathVariable String project) throws SQLException {
	sqlCRUD.deleteTable(project);
	return "DELETED project : " + project + "\n";
    }
    
    @DeleteMapping("api/delete/{project}/{environment}/")
    public String apiDeleteProjectEnvironment(@PathVariable String project) throws SQLException {
	//TODO
	return "";
    }
    
    @DeleteMapping("api/delete/{project}/{environment}/{key}/")
    public String apiDeleteProjectEnvironmentKey(@PathVariable String project) throws SQLException {
	//TODO
	return "";
    }
}
