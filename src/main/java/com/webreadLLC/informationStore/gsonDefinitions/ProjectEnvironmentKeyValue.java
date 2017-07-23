package com.webreadLLC.informationStore.gsonDefinitions;

/**
 * @author michael
 */
public class ProjectEnvironmentKeyValue extends ProjectEnvironmentKey {
    public String value;

    public ProjectEnvironmentKeyValue(String p, String e, String k, String v) {
	super(p, e, k);
	this.value = v;
    }
}
