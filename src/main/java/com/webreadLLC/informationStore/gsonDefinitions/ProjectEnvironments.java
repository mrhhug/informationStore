package com.webreadLLC.informationStore.gsonDefinitions;

import java.util.Set;

/**
 * @author michael
 */
public class ProjectEnvironments extends Project {
    public Set<String> environments;

    public ProjectEnvironments(String p, Set<String> e) {
	super(p);
	this.environments = e;
    }
}
