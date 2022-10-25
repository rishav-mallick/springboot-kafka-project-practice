package com.rm.sprinboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.rm.sprinboot.kafka.entity.WikimediaData;
import com.rm.sprinboot.kafka.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	private WikimediaDataRepository wikimediaDataRepository;

	public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
		this.wikimediaDataRepository = wikimediaDataRepository;
	}

	@KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroupTopic")
	public void consume(String eventMessage) {
		LOGGER.info(String.format("Event Message received -> %s", eventMessage));
		WikimediaData wikimediaData = new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);

		wikimediaDataRepository.save(wikimediaData);

	}
}
