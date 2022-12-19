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

@SpringBootApplication
@RestController
public class IsdbBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsdbBackendApplication.class, args);
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5430/studs");
		dataSource.setUsername("s312621");
		dataSource.setPassword("my_password_is_not_your_business");

		// schema init
		Resource initSchema = new ClassPathResource("sql_scripts/create_scripts.sql");
		Resource initData = new ClassPathResource("sql_scripts/insert_scripts.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		return dataSource;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "user") String name) {
		return String.format("Hello %s!", name);
	}

}