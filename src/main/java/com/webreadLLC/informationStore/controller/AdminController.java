package com.webreadLLC.informationStore.controller;

import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 */

@RestController("api/admin/")
public class AdminController {
    
    @GetMapping("api/admin/mysqldump/")
    public String apiAdminMysqldump() throws SQLException {
	//TODO
	return "";
    }
    
    @GetMapping("api/admin/mysqldump/{project}/")
    public String apiAdminMysqldumpProject(@PathVariable String project) throws SQLException {
	//TODO
	return "";
    }
    
    @GetMapping("api/admin/mysqldump/{project}/{environment}/")
    public String apiAdminMysqldumpProjectEnvironment(@PathVariable String project) throws SQLException {
	//TODO
	return "";
    }
    
    @PostMapping("api/admin/mysqlinsert/")
    public String apiAdminMysqlinsert(@RequestBody String par) {
	//TODO
	return "";
    }
    
    @PostMapping("api/admin/mysqlinsert/{project}/")
    public String apiAdminMysqlinsertProject(@RequestBody String par) {
	//TODO
	return "";
    }
    
    @PostMapping("api/admin/mysqlinsert/{project}/{environment}/")
    public String apiAdminMysqlinsertProjectEnvironment(@RequestBody String par) {
	//TODO
	return "";
    }
}
