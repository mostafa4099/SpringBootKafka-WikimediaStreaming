package com.mostafa;

import com.mostafa.KafkaService.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikimediaProducerApplication implements CommandLineRunner {
    @Autowired
    KafkaProducerService kafkaProducerService;

    public static void main(String[] args) {
        SpringApplication.run(WikimediaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaProducerService.sendMessage();
    }
}
