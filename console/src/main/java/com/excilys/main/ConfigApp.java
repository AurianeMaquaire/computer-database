package com.excilys.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {com.excilys.dao.ConfigDAO.class, com.excilys.controller.ConfigController.class})
public class ConfigApp {

}
