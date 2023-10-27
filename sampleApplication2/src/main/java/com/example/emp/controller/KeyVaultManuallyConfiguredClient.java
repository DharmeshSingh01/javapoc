package com.example.emp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.cloud.service.implementation.keyvault.KeyVaultProperties;

//import com.microsoft.azure.keyvault.KeyVaultClient;
//import com.microsoft.azure.keyvault.KeyVaultClient;
//import com.microsoft.azure.credentials.*;
//import com.azure.security.keyvault.secrets.models.KeyVaultSecret;





//@EnableConfigurationProperties(KeyVaultProperties.class)
@Component("KeyVaultManuallyConfiguredClient")
public class KeyVaultManuallyConfiguredClient implements KeyVaultClient {

    //private KeyVaultProperties keyVaultProperties;

    private SecretClient secretClient;
    @Value("${azure.keyvault.uri}")
    private String KeyVaultEndPoint;
    @Override
    
    public SecretClient getSecretClient() {
        if (secretClient == null) {
        	secretClient = new SecretClientBuilder()
                    .vaultUrl(KeyVaultEndPoint)
                    .credential(new ClientSecretCredentialBuilder()
                            .clientId("50acd962-d938-462f-a53f-66b14f2a15d5")
                            .clientSecret("2mT8Q~FcrKXIyvFQCC4OkdfxtRkV5gdyJKKbFa-S")
                            .tenantId("93795427-ffbe-4db3-a023-dc4cc9aba561")
                            .build())
                    .buildClient();
        }
        System.out.println("Url from property file: " + KeyVaultEndPoint);
        return secretClient;
    }
    
    /* private final SecretClient secretClient;

	    public KeyVaultManuallyConfiguredClient(SecretClient secretClient) {
	        this.secretClient = secretClient;
	    }

	    @Override
	    public SecretClient getSecretClient() {
	        return secretClient;
	    }
	    */
}
/*



































import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Load {
	

	 // @Value("${azure.keyvault.client-id}")
	  //private String clientId;

	 // @Value("${azure.keyvault.client-secret}")
	 // private String clientSecret;
	  public SecretClient secretClient;
	  @Bean
	  public DataSource keyVaultClient() {
		//  public void keyVaultClient() {
	   //ServiceClientCredentials credentials = new AzureKeyVaultCredential(clientId, clientSecret);
		  secretClient = new SecretClientBuilder()
                .vaultUrl("https://javapockeyvault1.vault.azure.net/")
                .credential(new ClientSecretCredentialBuilder()
                        .clientId("50acd962-d938-462f-a53f-66b14f2a15d5")
                        .clientSecret("2mT8Q~FcrKXIyvFQCC4OkdfxtRkV5gdyJKKbFa-S")
                        .tenantId("93795427-ffbe-4db3-a023-dc4cc9aba561")
                        .build())
                .buildClient();
		  KeyVaultSecret secret = secretClient.getSecret("database-password");
		   System.out.println("database url21312312312312313123123: ======= " + secret.getValue());
		   DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        System.out.println("databaseUrl" + secret.getValue());
	        dataSource.setUrl("jdbc:mysql://mysql123.mysql.database.azure.com:3306/studentdb?useUnicode=true&useJDBCCompliantTimezone");
	        dataSource.setUsername("");
	        dataSource.setPassword(secret.getValue());
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        return (DataSource) dataSource;
	    //return secretClient;
	  }
	  
	}




//KeyVaultSecret secret = secretClient.getSecret("database-password");
//return secret.getValue();
*/