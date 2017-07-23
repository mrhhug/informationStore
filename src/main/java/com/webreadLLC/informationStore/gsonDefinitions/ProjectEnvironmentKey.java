package com.webreadLLC.informationStore.gsonDefinitions;

/**
 * @author michael
 */
public class ProjectEnvironmentKey extends ProjectEnvironment {
    public String key;
    
    public ProjectEnvironmentKey(String p, String e, String k) {
	super(p, e);
	this.key = k;
    }
}
