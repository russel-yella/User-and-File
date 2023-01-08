package com.russ.done.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.russ.done.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
	Optional<File> findByName(String fileName);
	
}
