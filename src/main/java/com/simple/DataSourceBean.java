package com.ubs.tracker.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataSourceBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceBean.class);

    @Bean
    public static DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        
        String commandToH2DB = "jdbc:h2:%s%s;AUTO_SERVER=true";
        String commandLine = String.format(commandToH2DB, dbPath, dbName);
        System.setProperty("com.tracker.h2.url", commandLine);

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(commandLine);
        LOGGER.info("Data base command line - " + commandLine);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }
}
