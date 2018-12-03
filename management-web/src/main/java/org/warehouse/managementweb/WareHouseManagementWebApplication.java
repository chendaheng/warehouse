package org.warehouse.managementweb;

/**
 * @author cplayer on 2018/9/18.
 * @version 1.0
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.warehouse.managementservice",
        "org.warehouse.managementfacade",
        "org.warehouse.managementweb"})
@MapperScan({"org.warehouse.managementservice", "org.warehouse.managementfacade"})
public class WareHouseManagementWebApplication {
    public static void main (String[] args) {
        SpringApplication.run(WareHouseManagementWebApplication.class, args);
    }
}
