package cn.edu.ahpu.quartz.demo.chap3;

import java.io.File;
import java.io.FileFilter;

public class FileExtensionFileFilter implements FileFilter{
	
	private  String extension;
	
	public FileExtensionFileFilter(String extension) {
		super();
		this.extension = extension.toLowerCase();
	}

	@Override
	public boolean accept(File file) {
		 String lCaseFilename = file.getName().toLowerCase();       
		 if(file.isFile() && lCaseFilename.endsWith(extension)){
			 return true;
		 }
		return false;
	}
	
}