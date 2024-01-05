package com.goit;


import com.goit.operator.OperatorProperties;
import com.goit.report.SchedulerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OperatorProperties.class, SchedulerProperties.class})
@PropertySource("classpath:application.properties")
public class Launcher {
    public static void main(String args[]){
        SpringApplication.run(Launcher.class, args);
    }
}