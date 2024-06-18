package com.spring.Spring_Jdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.dao.StudentDao;
import com.spring.dao.StudentDao_Impl;

@Configuration
public class JdbcConfig {

	@Bean("datasource")
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/spring_jdbc");
		datasource.setUsername("root");
		datasource.setPassword("root");
		
		return datasource;
	}
	
	@Bean("template")
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(getDataSource());
		return template;
	}
	
	@Bean("studentDao")
	public StudentDao getStudentDao() {
		StudentDao_Impl studentDao = new StudentDao_Impl();
		studentDao.setTemplate(getJdbcTemplate());
		
		return studentDao;
	}
}
