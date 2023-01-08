package com.russ.done.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.russ.done.entity.File;
import com.russ.done.entity.User;
import com.russ.done.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	private final String FOLDER_PATH = "C:\\Users\\Yella Russel\\Documents\\New folder\\";

	public List<File> uploadFileToFileSystem1(MultipartFile[] files,User u) throws IOException {
		List<File> fileList = new ArrayList<File>();
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			String filePath = FOLDER_PATH + file.getOriginalFilename();
			File fileData = fileRepository.save(File.builder().name(file.getOriginalFilename()).type(file.getContentType())
					.filePath(filePath).user(u).build());
			
			 fileList.add(fileData);
             file.transferTo(new java.io.File(filePath) );

		}

		return fileList;
	}



	public byte[] downloadFileToFileSystem(String fileName) throws IOException {
		Optional<File> fileData = fileRepository.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] files = Files.readAllBytes(new java.io.File(filePath).toPath());
		return files;

	}
	
	
	public String deleteFile(final String keyName) {
	Optional<File> fileData = fileRepository.findByName(keyName);  
	String filePath = fileData.get().getFilePath();
	try {
	java.io.File files = new java.io.File(filePath); 
	files.delete();
	System.out.println("Successfully Deleted " + keyName );
}catch(Exception e) {
	System.out.println("error occure deleting files :" + e);
}
  return "Succesfully Deleted!! " + keyName;
	}

}
