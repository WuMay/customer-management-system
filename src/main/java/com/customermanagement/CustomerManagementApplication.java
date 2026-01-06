package com.customermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManagementApplication.class, args);
        System.out.println("\n=================================================");
        System.out.println("客户数据管理系统启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("H2 控制台: http://localhost:8080/h2-console");
        System.out.println("JDBC URL: jdbc:h2:mem:customerdb");
        System.out.println("用户名: sa");
        System.out.println("密码: (留空)");
        System.out.println("=================================================\n");
    }
}
