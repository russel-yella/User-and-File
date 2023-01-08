package com.russ.done.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.russ.done.service.FileService;

@RestController
public class FileDataController {

	@Autowired
	private FileService service;



	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadFileFromFileSystem(@PathVariable String fileName)throws IOException {
		byte[] uploadFile=service.downloadFileToFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("file/png"))
				.body(uploadFile);
}
	 @DeleteMapping("/fileSystem/{fileName}")
	 public void delete(@PathVariable String fileName) throws IOException{
		 service.deleteFile(fileName);
		 
	 }

}
