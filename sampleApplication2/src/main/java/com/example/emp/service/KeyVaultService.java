/*package com.example.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.example.emp.controller.Load;
import com.microsoft.azure.keyvault.requests.SetSecretRequest;
import com.microsoft.azure.keyvault.KeyVaultClient;
 
@Service
public class KeyVaultService {
		
	  @Autowired 
	  private SecretClient secretClient;
	  
	  public String getDatabaseUsername() { 
		  KeyVaultSecret secret =  secretClient.getSecret("database-username");
	  System.out.println("database-username" + secret.getValue());
	  return
	  secret.getValue(); }
	  
	  public String getDatabasePassword() { 
		  KeyVaultSecret secret = secretClient.getSecret("database-password"); 
		  return secret.getValue(); 
		  }
	
	//@Autowired 
	 //private Load load;
	
	
	
	
	
	  secretRequest = new Builder(keyVaultUrl,key,value).build();
	  keyVaultClient.setSecret(secretRequest);
	  keyVaultClient.getSecret("database-password").getValue();
	 
	
}*/