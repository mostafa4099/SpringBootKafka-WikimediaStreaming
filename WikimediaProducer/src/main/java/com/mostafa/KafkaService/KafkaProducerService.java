package com.mostafa.KafkaService;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.mostafa.EventHandler.WikimediaChangeEventHandler;
import com.mostafa.config.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducerService {

    private Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TopicConfig topicConfig;

    /**
     * Publish Wikimedia change event string message in kafka cluster within specified topic
     */
    public void sendMessage() throws InterruptedException {
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventHandler handler = new WikimediaChangeEventHandler(kafkaTemplate, topicConfig.topic().name());

        EventSource eventSource = new EventSource.Builder(handler, URI.create(url)).build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
