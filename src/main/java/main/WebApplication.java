package main;

import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@RequiredArgsConstructor
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WebApplication implements CommandLineRunner {

    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //dbService.initDb();
        System.out.println("RUN");
    }
}
