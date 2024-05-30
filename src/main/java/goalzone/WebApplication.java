package goalzone;

import goalzone.service.initdb.InitDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@RequiredArgsConstructor
@SpringBootApplication
public class WebApplication implements CommandLineRunner {

    private final InitDbService dbService;

    public static void main(String[] args){
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dbService.initDb();
    }
}
