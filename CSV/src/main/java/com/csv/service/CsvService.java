package com.csv.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CsvService {

	ResponseEntity<String> upload(MultipartFile multipartFile) throws IOException;

}
