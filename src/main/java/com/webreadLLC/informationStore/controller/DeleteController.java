package com.webreadLLC.informationStore.controller;

import gsonDefinitions.Project;
import gsonDefinitions.ProjectEnvironment;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.webreadLLC.informationStore.CRUD.CRUD;

/**
 * @author michael
 */
@RestController("/api/delete/")
public class DeleteController extends AbstractController {

    public DeleteController(CRUD crud) {
	super(crud);
    }

    //too dangerous
//    @DeleteMapping("api/delete/")
//    public String apiDeleteAll() throws SQLException {
//	String ret = "No Content";
//	if(crud.DeleteAll()) {
//	    ret = "OK";
//	}
//	return ret;
//    }
    
    @DeleteMapping("api/delete/{project}/")
    public String apiDeleteProject(@PathVariable String p) throws SQLException {
	String ret = "No Content";
	if(crud.DeleteProject(new Project(p))) {
	    ret = "OK";
	}
	return ret;
    }
    
    //yeah there is no way to delete a key from a project
    
    @DeleteMapping("api/delete/{project}/{environment}/")
    public String apiDeleteProjectEnvironment(@PathVariable String p, @PathVariable String e) throws SQLException {
	String ret = "No Content";
	if(crud.DeleteProjectEnvironment(new ProjectEnvironment(p, e))) {
	    ret = "OK";
	}
	return ret;
    }
}
