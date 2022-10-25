package com.rm.sprinboot.kafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rm.sprinboot.kafka.entity.WikimediaData;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
