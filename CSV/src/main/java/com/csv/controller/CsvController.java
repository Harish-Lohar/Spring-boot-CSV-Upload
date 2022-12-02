package com.csv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csv.service.CsvService;

@RestController
public class CsvController {

	@Autowired
	private CsvService csvService;

	@PostMapping("/file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
		return csvService.upload(multipartFile);
	}

}
