package com.flibustier;


import com.flibustier.Repository.TimestampRepository;
import com.flibustier.Service.TimestampService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApplication {
    private final TimestampRepository timestampRepository;

    public MainApplication(TimestampRepository timestampRepository) {
        this.timestampRepository = timestampRepository;
    }

    public static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
    }
    @Bean
    public TimestampService timestampService() {
        return new TimestampService(timestampRepository);
    }
}
