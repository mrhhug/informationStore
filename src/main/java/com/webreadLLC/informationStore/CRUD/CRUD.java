package com.webreadLLC.informationStore.crud;

import gsonDefinitions.Project;
import gsonDefinitions.ProjectEnvironment;
import gsonDefinitions.ProjectEnvironmentKey;
import gsonDefinitions.ProjectEnvironmentKeyValue;
import gsonDefinitions.ProjectEnvironments;
import gsonDefinitions.ProjectEnvironmentKeyValues;
import gsonDefinitions.ProjectKeys;
import gsonDefinitions.Projects;

/**
 *
 * @author michael
 */

public interface CRUD {
    
    //GET
    public Projects GetProjects();
    
    public ProjectEnvironments GetProjectEnvironments(Project p);
    
    public ProjectKeys GetProjectKeys(Project p);
    
    public ProjectEnvironmentKeyValues GetProjectEnvironmentKeyValues(ProjectEnvironment pe);
    
    public ProjectEnvironmentKeyValue GetProjectEnvironmentKeyValue(ProjectEnvironmentKey pek);
    
    //PUT
    public boolean PutProjectEnvironment(ProjectEnvironment pe);
    
    public boolean PutProjectEnvironmentKeyValue(ProjectEnvironmentKeyValue pekv);
    
    //POST
    public boolean PostProject(ProjectKeys pks);
    
    public boolean PostProjectEnvironmentKeyValues(ProjectEnvironmentKeyValues pekvs);
    
    //DELETE
    //public boolean DeleteAll();
    
    public boolean DeleteProject(Project p);
    
    public boolean DeleteProjectEnvironment(ProjectEnvironment pe);
    
}
