package com.nesterrovv.isdbBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@RestController
public class IsdbBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsdbBackendApplication.class, args);
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() throws IOException {
		InputStream inputStreamForProperties = this.getClass().getClassLoader()
				.getResourceAsStream("authorization.properties");
		Properties authorizationProperties = System.getProperties();
		authorizationProperties.load(inputStreamForProperties);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(authorizationProperties.getProperty("database_url"));
		dataSource.setUsername(authorizationProperties.getProperty("database_login"));
		dataSource.setPassword(authorizationProperties.getProperty("database_password"));
		//schema init
		Resource initSchema = new ClassPathResource("sql_scripts/create_scripts.sql");
		Resource initTriggers = new ClassPathResource("sql_scripts/triggers.sql");
		Resource initData = new ClassPathResource("sql_scripts/insert_scripts.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initTriggers, initData);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		return dataSource;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "user") String name) {
		return String.format("Hello %s!", name);
	}

}