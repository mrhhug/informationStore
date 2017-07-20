package com.webreadLLC.informationStore.gsonDefinitions;

import java.util.Set;

/**
 * @author michael
 */
public class ProjectKeys extends Project {
    Set<String> keys;

    public ProjectKeys(String p, Set<String> k) {
	super(p);
	this.keys = k;
    }
}
