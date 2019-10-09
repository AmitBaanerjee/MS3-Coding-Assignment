
package com.howtodoinjava.demo.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.howtodoinjava.demo.service.DBFileService;

@RestController

@RequestMapping("/assignment")

public class DBFileController {

	@Autowired
	DBFileService service;

	@PostMapping("/import")
	@ResponseBody
	public ResponseEntity mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile)
			throws IOException {

		String tableName = reapExcelDataFile.getName();
		boolean isSuccess = service.uploadExcel(reapExcelDataFile, tableName);
		if (isSuccess == true)
			return new ResponseEntity(isSuccess, new HttpHeaders(), HttpStatus.CREATED);
		else
			return new ResponseEntity(isSuccess, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
