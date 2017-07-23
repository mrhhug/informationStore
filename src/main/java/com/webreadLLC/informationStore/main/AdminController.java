package com.webreadLLC.informationStore.main;

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

    @GetMapping("api/admin/dump/")
    public String apiAdminDump() {
	//TODO
	return "";
    }
    
    @GetMapping("api/admin/dump/{project}/")
    public String apiAdminDumpProject(@PathVariable String project) {
	//TODO
	return "";
    }
    
    @GetMapping("api/admin/dump/{project}/{environment}/")
    public String apiAdminDumpProjectEnvironment(@PathVariable String project) {
	//TODO
	return "";
    }
    
    @PostMapping("api/admin/fill/")
    public String apiAdminFill(@RequestBody String par) {
	//TODO
	return "";
    }
    
    @PostMapping("api/admin/fill/{project}/")
    public String apiAdminFillProject(@RequestBody String par) {
	//TODO
	return "";
    }
    
    @PostMapping("api/admin/fill/{project}/{environment}/")
    public String apiAdminFillProjectEnvironment(@RequestBody String par) {
	//TODO
	return "";
    }
}
