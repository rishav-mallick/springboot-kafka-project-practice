package com.rm.sprinboot.kafka.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wikimedia_recentchange")
@Getter
@Setter
public class WikimediaData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Lob
	private String wikiEventData;

}