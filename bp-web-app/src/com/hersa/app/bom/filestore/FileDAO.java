package com.hersa.app.bom.filestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class FileDAO {

	public void save(InputStream inputStream, File file) throws IOException {
		
		OutputStream output = new FileOutputStream(file);
		
		try {
			
			IOUtils.copy(inputStream, output);
			
		} finally {
			if (output != null) { output.close(); }
		}
	}
}
