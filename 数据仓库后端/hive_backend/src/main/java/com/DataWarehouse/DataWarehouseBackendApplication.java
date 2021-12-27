package com.DataWarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;

@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
@SpringBootApplication
public class DataWarehouseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWarehouseBackendApplication.class, args);
    }

}
