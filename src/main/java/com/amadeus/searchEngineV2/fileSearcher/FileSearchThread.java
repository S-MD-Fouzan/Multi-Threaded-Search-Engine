package com.amadeus.searchEngineV2.fileSearcher;



import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amadeus.searchEngineV2.model.Path;
import com.amadeus.searchEngineV2.model.PathFactory;
import com.amadeus.searchEngineV2.model.PathRepository;

public class FileSearchThread implements Supplier {
	
	
	
	private List<Path> filesFoundPath;
	private File drive;
	private String fileName;
	
		
	public FileSearchThread(File drive, String fileName) {
		super();
		this.drive = drive;
		this.fileName = fileName;
		this.filesFoundPath = new Vector<Path>();
	}
	
	public FileSearchThread() {
		super();
		this.filesFoundPath = new Vector<Path>();
		
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getDrive() {
		return drive;
	}

	public void setDrive(File drive) {
		this.drive = drive;
	}

	public List<Path> getFilesFoundPath() {
		return this.filesFoundPath;
	}

	
	public void searchFile(File folder, String fileName) {
		File files[] = folder.listFiles();
		
		if(files != null)
		for(File currentFile : files ) {
			
			if(currentFile.isDirectory() && !currentFile.isHidden()) {
				this.searchFile(currentFile, fileName);
			}
			else if(currentFile.isFile()) {
				if(currentFile.getName().equalsIgnoreCase(fileName)) {
					this.filesFoundPath.add(PathFactory.getInstance(currentFile.getAbsolutePath()));
				}
			}
		}
	}
	
	@Override
	public Object get() {
		searchFile(drive,fileName);
		return this.filesFoundPath;
	}

}

