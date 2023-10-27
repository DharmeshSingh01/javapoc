package com.example.emp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.context.annotation.PropertySource;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
//import com.example.emp.keyvaultconfig.SecretClientApplication;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.SecretClient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
*/

import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.example.emp.controller.KeyVaultClient;
import com.example.emp.controller.KeyVaultManuallyConfiguredClient;
//import com.example.emp.controller.KeyVaultAutoconfiguredClient;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
public class SampleApplication2Application implements CommandLineRunner {
//	public class SampleApplication2Application  {

	
	// Spring Cloud Azure will automatically inject SecretClient in your ApplicationContext.
	// public   SecretClient secretClient;
		/*
		 * public SampleApplication2Application(SecretClient secretClient) {
		 * this.secretClient = secretClient; }
		 */


//	@Value("$(spring.datasource.url)")
	 //  private String propertyName;
	   
	 //  @Value("${spring.datasource.url}")
	  //  public String databaseUrl;
	 
	
	
	
	   @Value("${database.secret.value}")
	    private String mySecret;

	    private KeyVaultClient keyVaultClient = null;

	    public SampleApplication2Application(@Qualifier(value = "KeyVaultManuallyConfiguredClient") KeyVaultManuallyConfiguredClient keyVaultAutoconfiguredClient) {
	        this.keyVaultClient = keyVaultAutoconfiguredClient;
	    }
		
	 public static void main(String[] args) {
			SpringApplication.run(SampleApplication2Application.class, args);
			
		}
	 @Override
	    public void run(String... args) throws Exception {
	        KeyVaultSecret keyVaultSecret = keyVaultClient.getSecret("database-password");
	        System.out.println("Hey, our secret is here ->" + keyVaultSecret.getValue());
	        System.out.println("Hey, our secret is here from application properties file ->" + mySecret);
	    }
  
   /* @Override
    public void run(String... args) {
    
    	//String keyVaultUrl = String.format(KEY_VAULT_URL, getProperty(AZURE_KEY_VAULT_NAME, ""));
        secretClient = new SecretClientBuilder()
                .vaultUrl("https://javapockeyvault1.vault.azure.net/")
                .credential(new ClientSecretCredentialBuilder()
                        .clientId("50acd962-d938-462f-a53f-66b14f2a15d5")
                        .clientSecret("2mT8Q~FcrKXIyvFQCC4OkdfxtRkV5gdyJKKbFa-S")
                        .tenantId("93795427-ffbe-4db3-a023-dc4cc9aba561")
                        .build())
                .buildClient();
        //Map<String, String> map = new HashMap<String, String>();
       String propertyName = secretClient.getSecret("database-password").getValue();
       System.out.println("database url: ======= " + databaseUrl);
        System.out.println("spring-datasource-password fsdgfdgfdgdfgdfgs: ======= " + propertyName);
		//map.put("my-database-secret", mySecret);	 
        System.out.println("spring-datasource-password: ======= " + secretClient.getSecret("database-password").getValue());
    }*/
   

   
   }
