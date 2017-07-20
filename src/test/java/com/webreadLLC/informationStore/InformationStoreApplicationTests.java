package com.webreadLLC.informationStore;

import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationStoreApplicationTests {
    private MockMvc mockMvc;

    String project = UUID.randomUUID().toString();
    String key = UUID.randomUUID().toString();
    String environment = UUID.randomUUID().toString();
    String additionalKey = UUID.randomUUID().toString();
    String value = UUID.randomUUID().toString();
    String bkey0 = UUID.randomUUID().toString();
    String bvalue0 = UUID.randomUUID().toString();
    String bkey1 = UUID.randomUUID().toString();
    String bvalue1 = UUID.randomUUID().toString();
    
    @Test
    public void contextLoads() {
    }
    
    @Test
    public void testAll() {
	addProject();
//	addProjectEnvironment();
//	addAdditionalProjectKey();
//	updateProjectEnvironmentValue();
//	bulkUpdateProjectEnvironmentValue();
//	deleteProjectEnvironment();
//	deleteProject();
    }
    
    private void addProject() {
	mockMvc.perform(post("/some/super/secret/url")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                    new BasicNameValuePair("someparam1", "true"),
                    new BasicNameValuePair("someparam2", "test")
            )))));
    }
    private void addProjectEnvironment() {
	
    }
    private void addAdditionalProjectKey() {
	
    }
    private void updateProjectEnvironmentValue() {
	
    }
    private void bulkUpdateProjectEnvironmentValue() {
	
    }
    private void deleteProjectEnvironment() {
	
    }
    private void deleteProject() {
	
    }
//    private void deleteAllProjects() {
//	
//    }
    
}
