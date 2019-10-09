package com.howtodoinjava.demo.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.howtodoinjava.demo.mapper.DBFileStorageRepository;
import com.howtodoinjava.demo.model.DBFile;
import com.opencsv.CSVReader;

@Service
public class DBFileService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DBFileStorageRepository jparepository;

	public boolean uploadExcel(MultipartFile reapExcelDataFile, String fileName) throws IOException {

		String[] nextLine;
		try {
			// read file
			// CSVReader(fileReader, ';', '\'', 1) means
			// using separator ; and using single quote ' . Skip first line when read
			List<DBFile> dbFileList = new ArrayList<DBFile>();
			List<DBFile> dbFileListBad = new ArrayList<DBFile>();
			int finalCounter=0;
			try (Reader reader = new InputStreamReader(reapExcelDataFile.getInputStream());
					CSVReader csvReader = new CSVReader(reader, ',', '\'', 1);) {
				int i = 0;
				while ((nextLine = csvReader.readNext()) != null) {
					DBFile file=new DBFile();
					int n = nextLine.length;
					if (n==11){
						if (nextLine[0]!=null && nextLine[1]!=null && nextLine[2]!=null & nextLine[3]!=null & nextLine[4]!=null & nextLine[5]!=null && nextLine[6]!=null && nextLine[7]!=null && nextLine[8]!=null)  {
							file.setA(nextLine[0]);
							file.setB(nextLine[1]);
							file.setC(nextLine[2]);
							file.setD(nextLine[3]);
							file.setD(nextLine[4]);
							file.setE(nextLine[5]);
							file.setF(nextLine[6]);
							file.setG(nextLine[7]);
							file.setH(nextLine[8]);
							file.setI(nextLine[9]);
							file.setJ(nextLine[10]);
							dbFileList.add(file);
							
						}
					}	
					else {
						  if (n - 1 >= 0)
							  file.setA(nextLine[0] != null ? nextLine[0] : null);
						  if (n - 1 >= 1)
							  file.setB(nextLine[1] != null ? nextLine[1] : null);
						  if (n - 1 >= 2)
							  file.setC(nextLine[2] != null ? nextLine[2] : null); 
						  if (n - 1 >= 3)
							  file.setD(nextLine[3] != null ? nextLine[3] : null); 
						  if (n > 4)
							  file.setE(nextLine[4] != null ? nextLine[4] : null); 
						  if (n > 5)
							  file.setF(nextLine[5] != null ? nextLine[5] : null); 
						  if (n > 6)
							  file.setG(nextLine[6] != null ? nextLine[6] : null);
						  if (n > 7)
							  file.setH(nextLine[7] != null ? nextLine[7] : null); 
						  if (n > 8)
							  file.setI(nextLine[8] != null ? nextLine[8] : null); 
						  
						dbFileListBad.add(file);
					}
					

					 
					finalCounter+=1;
				}
			}
			//for good entries
			jparepository.createTable("MS3Interview");
			boolean isSuccess = false;
			int count = 0;
			for (DBFile e : dbFileList) {
				isSuccess = jparepository.insertUser(e,"MS3Interview");
				count++;
			}
			//for bad entries
			jparepository.createTable("MS3Interview_Bad");
			boolean isFailure = false;
			int countBad = 0;
			for (DBFile e : dbFileListBad) {
				isFailure = jparepository.insertUser(e,"MS3Interview_Bad");
				countBad++;
			}
			logger.info("Recieved records:" + finalCounter);
			logger.info("Successful insertion:" + count);
			logger.info("Unsuccessful Insertion:" + (countBad));
			return isSuccess;
		} catch (IOException e) {
			System.out.println("error while reading csv and put to db : " + e.getMessage());
		}
		return false;

	}

}