package com.webreadLLC.informationStore.crud;

import com.webreadLLC.informationStore.gsonDefinitions.Project;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironment;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKey;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKeyValue;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironments;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectEnvironmentKeyValues;
import com.webreadLLC.informationStore.gsonDefinitions.ProjectKeys;
import com.webreadLLC.informationStore.gsonDefinitions.Projects;
import java.sql.SQLException;

/**
 *
 * @author michael
 */

public interface CRUD {
    
    //GET
    public Projects GetProjects() throws SQLException;
    
    public ProjectEnvironments GetProjectEnvironments(Project p) throws SQLException;
    
    public ProjectKeys GetProjectKeys(Project p) throws SQLException;
    
    public ProjectEnvironmentKeyValues GetProjectEnvironmentKeyValues(ProjectEnvironment pe) throws SQLException;
    
    public ProjectEnvironmentKeyValue GetProjectEnvironmentKeyValue(ProjectEnvironmentKey pek) throws SQLException;
    
    //PUT
    public boolean PutProjectEnvironment(ProjectEnvironment pe) throws SQLException;
    
    public boolean PutProjectEnvironmentKeyValue(ProjectEnvironmentKeyValue pekv) throws SQLException;
    
    //POST
    public int PostProject(ProjectKeys pks) throws SQLException;
    
    public int PostProjectEnvironmentKeyValues(ProjectEnvironmentKeyValues pekvs) throws SQLException;
    
    //DELETE
    //public boolean DeleteAll();
    
    public int DeleteProject(Project p) throws SQLException;
    
    public int DeleteProjectEnvironment(ProjectEnvironment pe) throws SQLException;
    
}
