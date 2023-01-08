package com.russ.done.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RequestObject {
 private long id;
 private String email;
 private String reason;
 private MultipartFile[] files;

 
}
