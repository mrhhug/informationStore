package com.webreadLLC.informationStore;

import com.google.gson.Gson;
import gsonDefinitions.AbstractGSONDefinition;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */
@RestController("/")
public class Controller {

    private final MySqlCRUD sqlCRUD;
    
    Controller() throws SQLException {
	this.sqlCRUD = new MySqlCRUD();
	sqlCRUD.init();
    }
    
    @GetMapping("api")
    public String api() {
	StringBuilder ret = new StringBuilder();
	try {
	    ret.append("{\"projects\":[");
	    for( String i : sqlCRUD.queryAllProjects()) {
		ret.append("\"").append(i).append("\",");
	    }
	    deleteLastComma(ret);
	    ret.append("]}\n");
	}
	catch (SQLException e) {
	    ret.append("ERROR : ").append(e.getLocalizedMessage());
	}
	return ret.toString();
    }
    
    @GetMapping("api/{project}")
    public String apiProject(@PathVariable String project) {
	StringBuilder ret = new StringBuilder();
	try {
	    Set<String> q = sqlCRUD.queryProjectEnvironments(project);
	    ret.append("{\"").append(project).append("\":[");
	    for( String i : q) {
		ret.append("\"").append(i).append("\",");
	    }
	    deleteLastComma(ret);
	    ret.append("]}\n");
	}
	catch (SQLException e) {
	    ret.append("ERROR : ").append(e.getLocalizedMessage());
	}
	return ret.toString();
    }
    
    @GetMapping("api/{project}/{environment}")
    public String apiProjectEnvironment(@PathVariable String project, @PathVariable String environment) throws SQLException {
	StringBuilder ret = new StringBuilder();
	try {
	    Map<String, String> KV = sqlCRUD.queryEnvironmentKeyValues(project,environment);
	    ret.append("{\"").append(project).append("\":{");
	    for( String i : KV.keySet()) {
		ret.append("\"").append(i).append("\":\"").append(KV.get(i)).append("\",");
	    }
	    deleteLastComma(ret);
	    ret.append("}}\n");
	}
	catch (SQLException e) {
	    ret.append("ERROR : ").append(e.getLocalizedMessage());
	}
	return ret.toString();
    }
    
    @PostMapping("api/newProject")
    //should be
    //@PostMapping("api/newProject/{project}")
    public String apinewProject(@RequestBody String jsonString) throws SQLException, UnsupportedEncodingException {
//	ProjectKeysGSONDefinition fromJson = (ProjectKeysGSONDefinition) decodeAndMassageJSON(jsonString, new ProjectKeysGSONDefinition());
//	decodeAndMassageJSON(jsonString, fromJson);
//	StringBuilder ret = new StringBuilder();
//	ret.append("{\"").append(fromJson.project).append("}:[");
//	for( String i : sqlCRUD.insertNewProject(fromJson)) {
//	    ret.append("\"").append(i).append("\",");
//	}
//	deleteLastComma(ret);
//	ret.append("]}\n");
//	return ret.toString();
return "";
    }
    
    @PutMapping("api/deleteProject/{project}")
    public String apideleteProject(@PathVariable String project) throws SQLException {
	sqlCRUD.deleteTable(project);
	return "DELETED project : " + project + "\n";
    }
    
    private AbstractGSONDefinition decodeAndMassageJSON(String jsonString, AbstractGSONDefinition aGSON) throws UnsupportedEncodingException {
	//shamelessly massaging api to allow for application/javascript content type instead of only text/plain
	//no idea who thought the spring framework needed to append an '='
	if('=' == jsonString.charAt(jsonString.length()-1) ) {
	    jsonString = jsonString.substring(0,jsonString.length()-1);
	}
	String decoded = URLDecoder.decode(jsonString, "ASCII");
	return new Gson().fromJson(decoded, aGSON.getClass());
    }
    
    private void deleteLastComma(StringBuilder par) {
	int index = par.lastIndexOf(",");
	if(0 < index) {
	    par.deleteCharAt(par.lastIndexOf(","));
	}
    }
}
