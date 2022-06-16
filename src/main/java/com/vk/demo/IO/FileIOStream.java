package com.vk.demo.IO;

import java.io.File;
import java.io.FileNotFoundException;

public class FileIOStream {

	public FileIOStream() {
		
	}
	
	//creting the file
	@SuppressWarnings("unchecked")
	public <T extends Object>  T createFile(String Path) {
		return new File(Path).exists()==true ? (T)new FileNotFoundException("File already there").getMessage() :  (T)new File(Path).getName();
	}

}
