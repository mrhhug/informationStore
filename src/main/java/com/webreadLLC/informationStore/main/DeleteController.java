package com.webreadLLC.informationStore.main;

import com.webreadLLC.informationStore.gsonDefinitions.Project;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironment;
import static com.webreadLLC.informationStore.main.InformationStoreApplication.crud;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */
@RestController("/api/delete/")
public class DeleteController {

    //too dangerous
//    @DeleteMapping("api/delete/")
//    public String apiDeleteAll() throws SQLException {
//	String ret = "No Content";
//	if(crud.DeleteAll()) {
//	    ret = "OK";
//	}
//	return ret;
//    }
    
    @DeleteMapping("/api/delete/{project}/")
    public String apiDeleteProject(@PathVariable String project) throws SQLException {
	String ret = "No Content";
	if(0 == crud.DeleteProject(new Project(project))) {
	    ret = "OK";
	}
	return ret;
    }
    
    //yeah there is no way to delete a key from a project
    
    @DeleteMapping("/api/delete/{project}/{environment}/")
    public String apiDeleteProjectEnvironment(@PathVariable String project, @PathVariable String environment) throws SQLException {
	//untested 
	return null;
    }
}
