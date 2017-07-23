package com.webreadLLC.informationStore.gsonDefinitions;

import java.util.List;

/**
 * @author michael
 */
public class ProjectKeys extends Project {
    public List<String> keys;

    public ProjectKeys(String p, List<String> k) {
	super(p);
	this.keys = k;
    }
}
