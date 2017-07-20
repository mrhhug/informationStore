package com.webreadLLC.informationStore.gsonDefinitions;

import java.util.Map;

/**
 * @author michael
 */
public class ProjectEnvironmentKeyValues extends ProjectEnvironment {
    Map<String,String> keyValuePairs;

    public ProjectEnvironmentKeyValues(String p, String e, Map<String, String> kvp) {
	super(p, e);
	this.keyValuePairs = kvp;
    }
}
