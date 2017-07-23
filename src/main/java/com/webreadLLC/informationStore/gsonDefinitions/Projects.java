package com.webreadLLC.informationStore.gsonDefinitions;

import java.util.Set;

/**
 * @author michael
 */
public class Projects {

    public Projects() {
    }

    public Projects(Set<String> projects) {
	this.projects = projects;
    }
    
    Set<String> projects;
}
