package com.webreadLLC.informationStore.gsonDefinitions;

/**
 * @author michael
 */
public class ProjectEnvironment extends Project {
    public String environment;
    
    public ProjectEnvironment(String p, String e) {
	super(p);
	this.environment = e;
    }
}
