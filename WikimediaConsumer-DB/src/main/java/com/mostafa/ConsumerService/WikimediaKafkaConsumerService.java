package com.mostafa.ConsumerService;

import com.mostafa.entity.WikimediaData;
import com.mostafa.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikimediaKafkaConsumerService {
    private Logger logger = LoggerFactory.getLogger(WikimediaKafkaConsumerService.class);

    @Autowired
    WikimediaDataRepository dataRepository;

    /**
     * Consume specified group's and topic's string data and save the data in db
     */
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeString(String message){
        logger.info("Consumed message: "+message);

        WikimediaData data = new WikimediaData();
        data.setData(message);

        dataRepository.save(data);
    }
}
