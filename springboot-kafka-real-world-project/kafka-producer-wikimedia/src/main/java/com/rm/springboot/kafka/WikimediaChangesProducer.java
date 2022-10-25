package com.rm.springboot.kafka;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.rm.springboot.kafka.handler.WikimediaChangeHandler;

@Service
public class WikimediaChangesProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
	KafkaTemplate<String, String> kafkaTemplate;

	public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publish() throws InterruptedException {
		String topic = "wikimedia_recentchange";
		// read message stream from wikimedia
		EventHandler handler = new WikimediaChangeHandler(kafkaTemplate, topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";

		EventSource.Builder builder = new EventSource.Builder(handler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(10);

		LOGGER.info(String.format("Sent successfully with message"));
	}
}
