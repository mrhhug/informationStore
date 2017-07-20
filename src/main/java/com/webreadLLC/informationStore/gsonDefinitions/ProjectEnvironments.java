package com.webreadLLC.informationStore.gsonDefinitions;

import java.util.Set;

/**
 * @author michael
 */
public class ProjectEnvironments extends Project {
    Set<String> environments;

    public ProjectEnvironments(String p) {
	super(p);
    }
}
