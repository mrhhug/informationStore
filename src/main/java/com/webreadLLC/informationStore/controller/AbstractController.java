package com.webreadLLC.informationStore.controller;

import com.google.gson.Gson;
import com.webreadLLC.informationStore.MySqlCRUD;
import gsonDefinitions.AbstractGSONDefinition;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */

@RestController("/")
public abstract class AbstractController {

    final static MySqlCRUD sqlCRUD = new MySqlCRUD();
    
    AbstractController() throws SQLException {
	sqlCRUD.init();
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
    
    
    
    AbstractGSONDefinition decodeAndMassageJSON(String jsonString, AbstractGSONDefinition aGSON) throws UnsupportedEncodingException {
	//shamelessly massaging api to allow for application/javascript content type instead of only text/plain
	//no idea who thought the spring framework needed to append an '='
	if('=' == jsonString.charAt(jsonString.length()-1) ) {
	    jsonString = jsonString.substring(0,jsonString.length()-1);
	}
	String decoded = URLDecoder.decode(jsonString, "ASCII");
	return new Gson().fromJson(decoded, aGSON.getClass());
    }
    
    void deleteLastComma(StringBuilder par) {
	int index = par.lastIndexOf(",");
	if(0 < index) {
	    par.deleteCharAt(par.lastIndexOf(","));
	}
    }
}
