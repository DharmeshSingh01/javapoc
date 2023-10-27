/*package com.example.emp.controller;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//import com.example.emp.service.KeyVaultService;
 
@Configuration
//@EnableJpaRepositories(basePackages = "your.repository.package")
public class DatabaseConfig {
 
   // @Autowired
   // private KeyVaultService keyVaultService;
 
    @Value("${spring.datasource.url}")
    private String databaseUrl;
 
   // @Bean
    public void dataSource() {
       // DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println("databaseUrl" + databaseUrl);
       // dataSource.setUrl(databaseUrl);
       // dataSource.setUsername(keyVaultService.getDatabaseUsername());
        //dataSource.setPassword(keyVaultService.getDatabasePassword());
        //dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //return (DataSource) dataSource;
    }
}*/